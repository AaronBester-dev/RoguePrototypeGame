package rogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.awt.Point;

/**
*Rogue is the class that sets up the rogue game.
*/

public class Rogue implements Serializable {
    public static final char UP = 's';
    public static final char DOWN = 'x';
    public static final char LEFT = 'z';
    public static final char RIGHT = 'c';
    private String nextDisplay = "";
    private String inventoryString = "";
    private Player player;
    private Inventory inventory = new Inventory();
    private ArrayList<Room> roomArray = new ArrayList<Room>();
    private ArrayList<Item> rogueItems = new ArrayList<Item>();
    private HashMap<String, Character> symbolMap;
    private transient RogueParser parser;
    private Map<String, String> tempRoomMap;
    private Map<String, String> tempItemMap;

/**
*Default constructor for rogue that sets everything to default values.
*/
    public Rogue() {
      setPlayer(null);
      symbolMap = null;
      tempRoomMap = null;
      parser = new RogueParser();
    }

/**
*constructor for rogue sets up room to the values in the room file.
*@param theDungeonInfo parser that gets all values from the file
*/

    public Rogue(RogueParser theDungeonInfo) {
      parser = theDungeonInfo;
      Map roomInfo = parser.nextRoom();
      while (roomInfo != null) {
        addRoom(roomInfo);
        roomInfo = parser.nextRoom();
      }
      Map itemInfo = parser.nextItem();
      while (itemInfo != null) {
        addItem(itemInfo);
        itemInfo = parser.nextItem();
      }
      setSymbols();
      connectDoors();
      checkRooms();
      checkRooms();
      if (player.getCurrentRoom() != null) {
        nextDisplay = player.getCurrentRoom().displayRoom();
        convertStringToSymbols();
      }
    }

/**
* setter that sets the current player in rogue.
*@param thePlayer new player
*/

    public void setPlayer(Player thePlayer) {
      player = thePlayer;
    }

/**
* sets the current symbols map to the symbols in the symbol file in rogue.
*/

    public void setSymbols() {
      symbolMap = parser.getSymbols();
    }

/**
* gets the current room array in rogue.
*@return room array of every room in the game.
*/
    public ArrayList<Room> getRooms() {
      return roomArray;
    }

/**
* gets the current item array in rogue.
*@return room array of every room in the game.
*/

    public ArrayList<Item> getItems() {
      return rogueItems;
    }

/**
* gets the current player in rogue.
*@return current player in rogue.
*/

    public Player getPlayer() {
      return player;
    }

    /**
* returns the string that displays the room.
*@return string that displays the next display
*/
    public String getNextDisplay() {
      return nextDisplay;
    }
    /**
* returns the string that displays the inventory.
*@return string that displays the current inventory.
*/
    public String getInventoryString() {
      return inventoryString;
    }

    private void convertStringToSymbols() {
      for (String key : symbolMap.keySet()) {
        nextDisplay = nextDisplay.replaceAll(key.trim(), symbolMap.get(key).toString());
      }
    }


/**
* creates a room and adds it to the room array.
*@param toAdd hashmap that contains a room map.
*/

    public void addRoom(Map<String, String> toAdd) {
      Room newRoom = new Room();
      Integer integerId = Integer.decode(toAdd.get("id"));
      Boolean isPlayer = Boolean.parseBoolean(toAdd.get("start").toString());
      Integer integerHeight = Integer.decode(toAdd.get("height").toString());
      Integer integerWidth = Integer.decode(toAdd.get("width").toString());
      if (isPlayer.booleanValue()) {
        setPlayer(new Player());
        newRoom.setPlayer(player);
        player.setCurrentRoom(newRoom);
      }
      newRoom.setId(integerId);
      newRoom.setHeight(integerHeight);
      newRoom.setWidth(integerWidth);
      newRoom.setDoor("N", addDoor(toAdd.get("N"), newRoom));
      newRoom.setDoor("S", addDoor(toAdd.get("S"), newRoom));
      newRoom.setDoor("W", addDoor(toAdd.get("W"), newRoom));
      newRoom.setDoor("E", addDoor(toAdd.get("E"), newRoom));
      newRoom.setRogue(this);
      newRoom.updateDisplayRoom();
      getRooms().add(newRoom);
    }

/**
* creates a item and adds it to a room.
*@param toAdd item map that contains all item values.
*/

    public void addItem(Map<String, String> toAdd) {
      Item newItem = createItemSubclass(toAdd);
      int itemId = Integer.decode(toAdd.get("id"));
      Room roomToAddTo = null;
      int roomId = 0;
      newItem.setId(itemId);
      newItem.setName(toAdd.get("name"));
      newItem.setType(toAdd.get("type"));
      newItem.setDescription(toAdd.get("description"));
      if (toAdd.get("room") != null) {
        roomId = Integer.decode(toAdd.get("room"));
        addItemToRoom(toAdd, newItem, roomId);
        for (Room singleRoom : getRooms()) {
          if (roomId == singleRoom.getId()) {
            roomToAddTo = singleRoom;
          }
        }
        roomToAddTo.updateDisplayRoom();
        putItemInRoomInCorrectPosition(roomToAddTo, newItem);
      }
    }

    private Item createItemSubclass(Map<String, String> toAdd) {
      if (toAdd.get("type").equals("Food")) {
        return (Item) new Food();
      } else if (toAdd.get("type").equals("Magic")) {
        return  (Item) new Magic();
      } else if (toAdd.get("type").equals("Potion")) {
        return (Item) new Potion();
      } else if (toAdd.get("type").equals("Ring")) {
        return (Item) new Ring();
      } else if (toAdd.get("type").equals("Clothing")) {
        return (Item) new Clothing();
      } else {
        return (Item) new SmallFood();
      }
    }

    private void addItemToRoom(Map<String, String> toAdd, Item newItem, int roomId) {
      int itemX = 0;
      int itemY = 0;
      Point newItemLocation = null;
      roomId = Integer.decode(toAdd.get("room"));
      itemX = Integer.decode(toAdd.get("x"));
      itemY = Integer.decode(toAdd.get("y"));
      newItemLocation = new Point(itemX, itemY);
      newItem.setXyLocation(newItemLocation);
      rogueItems.add(newItem);
    }

    private void putItemInRoomInCorrectPosition(Room roomToAddTo, Item newItem) {
      int x = 0;
      int y = 1;
      boolean itemIsInCorrectLocation = false;
      while (!itemIsInCorrectLocation) {
          try {
            roomToAddTo.addItem(newItem);
            itemIsInCorrectLocation = true;
          } catch (ImpossiblePositionException e) {
            x++;
            if (x >= roomToAddTo.getWidth() - 1) {
              y++;
            }
            newItem.setXyLocation(new Point(x, y));
          } catch (NoSuchItemException f) {
            roomToAddTo.getRoomItems().remove(newItem);
            itemIsInCorrectLocation = true;
          }
      }
    }

/**
* creates a new door.
*@param toAdd string that contains conRoomId and wall postition
*@param newRoom room to add the door to
*@return Door returns the new door that was created
*/

    public Door addDoor(String toAdd, Room newRoom) {
      String[] doorStringArray;
      int doorConnectedRoomId = 0;
      int wallPosition = 0;

      if (toAdd.equals("-1")) {
        return (null);
      } else {
        doorStringArray = toAdd.split(" ");
        doorConnectedRoomId = Integer.decode(doorStringArray[0]);
        wallPosition = Integer.decode(doorStringArray[1]);

        return (new Door(newRoom, doorConnectedRoomId, wallPosition));
      }
    }

/**
* connects doors to the other room after room array has been created.
*/

    public void connectDoors() {
      Door doorHolder = null;
      for (Room tempRoom : getRooms()) {
        for (String key : tempRoom.getDoors().keySet()) {
          doorHolder = tempRoom.getDoor(key);
          if (doorHolder != null) {
            for (Room singleRoom : roomArray) {
              if ((doorHolder.getOtherRoomid() == singleRoom.getId()) && (singleRoom != tempRoom)
              && doorHolder.getConnectedRooms().size() == 1) {
                doorHolder.connectRoom(singleRoom);
              }
            }
          }
        }
      }
    }
/**
*Changes the next display to a string representation of the inventory.
*@param input character from the keyboard
*/

    public void openInventoryPanel(char input) {
      inventory.setMode(input);
      nextDisplay = inventory.printInventory();
    }
/**
*Allows the player to move up and down through the inventory panel.
*@param input character from the keyboard
*/
    public void moveThroughInventoryPanel(char input) {
      if (input == UP) {
        inventory.moveUpThroughInventory();
      } else if (input == DOWN) {
        inventory.moveDownThroughInventory();
      }
      nextDisplay = inventory.printInventory();
    }
/**
*Uses the currently selected item and returns the description of the item used.
*@return the description of the item being used
*/
    public String useCurrentItem() {
      String message = "";
      Item itemToToss = null;
      if (inventory.getMode() == 't') {
        itemToToss = inventory.getCurrentItem();
      }
      message = inventory.useItem(itemToToss);
      if (itemToToss != null) {
        tossItemInRoom(itemToToss);
      }
      nextDisplay = player.getCurrentRoom().displayRoom();
      convertStringToSymbols();
      inventoryString = inventory.printInventoryWithoutSelection();
      return message;
    }

    private void tossItemInRoom(Item itemToToss) {
      int x = (int) player.getXyLocation().getX();
      int y = (int) player.getXyLocation().getY() + 1;
      Point itemXYLocation = new Point(x, y);
      itemToToss.setCurrentRoom(player.getCurrentRoom());
      itemToToss.setXyLocation(itemXYLocation);
      putItemInRoomInCorrectPosition(player.getCurrentRoom(), itemToToss);
    }

/**
* returns the string of the room after a move.
*@return string that displays the room after a move
*@param input input from player
*@throws InvalidMoveException when player makes a move that isn't allowed
*/

    public String makeMove(char input) throws InvalidMoveException {
      int playerX = (int) player.getXyLocation().getX();
      int playerY = (int) player.getXyLocation().getY();
      Point wherePlayerWantsToGo = new Point(playerX, playerY);
      String moveMessage = "";
      String returnValue = "";

      try {
        moveMessage = checkInput(input, wherePlayerWantsToGo);
      } catch (InvalidMoveException e) {
        throw new InvalidMoveException();
      }
      returnValue = whatHappenedWhenIMoved(wherePlayerWantsToGo);
      if  (!(returnValue.equals(""))) {
        moveMessage = returnValue;
      }
      nextDisplay = player.getCurrentRoom().displayRoom();
      convertStringToSymbols();
      return (moveMessage);
    }

    private String checkInput(char input, Point wherePlayerWantsToGo) throws InvalidMoveException {
      int playerX = (int) player.getXyLocation().getX();
      int playerY = (int) player.getXyLocation().getY();
      if (input == UP) {
        wherePlayerWantsToGo.setLocation(playerX, --playerY);
        return ("You walk up");
      } else if (input == LEFT) {
        wherePlayerWantsToGo.setLocation(--playerX, playerY);
        return "You walk left";
      } else if (input == RIGHT) {
        wherePlayerWantsToGo.setLocation(++playerX, playerY);
        return "You walk right";
      } else if (input == DOWN) {
        wherePlayerWantsToGo.setLocation(playerX, ++playerY);
        return "You walk down";
      } else {
        throw new InvalidMoveException();
      }
    }

    private String whatHappenedWhenIMoved(Point wherePlayerWantsToGo) {
      Room currentRoom = player.getCurrentRoom();
      String collisionObject = "";
      Item itemToBePickedUp = null;
      Door doorToWalkThrough = null;
      String[][] currentRoomDisplayArray = player.getCurrentRoom().getRoomDisplayArray();
      collisionObject = whatDidICollideWith(currentRoomDisplayArray, wherePlayerWantsToGo);
      if (collisionObject == "NS_WALL" || collisionObject == "EW_WALL") {
        return "You hit a wall";
      } else if (collisionObject == "FLOOR") {
        movePlayer(player, wherePlayerWantsToGo);
      } else if (collisionObject == "ITEM") {
        itemToBePickedUp = getItemFromRoom(currentRoom, wherePlayerWantsToGo);
        inventory.addItem(itemToBePickedUp);
        inventoryString = inventory.printInventoryWithoutSelection();
        movePlayer(player, wherePlayerWantsToGo);
        return "Picked up a " + itemToBePickedUp.getType();
      } else {
        doorToWalkThrough = currentRoom.getDoor(String.valueOf(collisionObject.charAt(0)));
        movePlayerToOtherRoom(player, doorToWalkThrough, String.valueOf(collisionObject.charAt(0)));
        return "You walk through a door.";
      }
      return ("");
    }

/**
* checks to see what the player has collided with.
*@return type of object player has collided with.
*@param roomDisplayArray current list of how the room looks
*@param wherePlayerWantsToGo location of where player wants to move
*/
    public String whatDidICollideWith(String[][]roomDisplayArray, Point wherePlayerWantsToGo) {
      int newPlayerX = (int) wherePlayerWantsToGo.getX();
      int newPlayerY = (int) wherePlayerWantsToGo.getY();
      String collisionObject = roomDisplayArray[newPlayerY][newPlayerX];

      if (collisionObject.equals("FLOOR")) {
        return ("FLOOR");
      } else if (Character.isDigit(collisionObject.charAt(0))) {
        return ("ITEM");
      } else {
        return (collisionObject);
      }
    }
/**
* gets item from room and returns it.
*@return item the player is going to pick up
*@param currentRoom current room the player is in
*@param wherePlayerWantsToGo location of where player wants to move
*/
    public Item getItemFromRoom(Room currentRoom, Point wherePlayerWantsToGo) {
      int itemX = (int) wherePlayerWantsToGo.getX();
      int itemY = (int) wherePlayerWantsToGo.getY();
      String[][] currentRoomDisplayArray = currentRoom.getRoomDisplayArray();
      int itemId = Integer.decode(currentRoomDisplayArray[itemY][itemX]);
      Item returnedItem = null;
      for (Item singleItem : currentRoom.getRoomItems()) {
        if (singleItem.getId() == itemId) {
          returnedItem = singleItem;
        }
      }
      currentRoom.getRoomItems().remove(returnedItem);
      return (returnedItem);
    }

/**
* moves player to location they want to go to within a room.
*@param currentPlayer current player object.
*@param wherePlayerWantsToGo location of where player wants to move
*/
    public void movePlayer(Player currentPlayer, Point wherePlayerWantsToGo) {
      currentPlayer.setXyLocation(wherePlayerWantsToGo);
    }

/**
* moves player from one room to another.
*@param currentPlayer current player object.
*@param doorToWalkThrough current door to move through.
*@param direction direction of original door.
*/
    public void movePlayerToOtherRoom(Player currentPlayer, Door doorToWalkThrough, String direction) {
      Room currentRoom = currentPlayer.getCurrentRoom();
      Room otherRoom = doorToWalkThrough.getOtherRoom(currentRoom);
      Door otherDoor = null;
      Point newXyLocation = new Point(0, 0);
      otherRoom.setPlayer(currentPlayer);
      currentRoom.setPlayer(null);
      currentPlayer.setCurrentRoom(otherRoom);
      if (direction.equals("N")) {
        otherDoor = otherRoom.getDoor("S");
        newXyLocation.setLocation(otherDoor.getWallPosition(), otherRoom.getHeight() - 2);
      } else if (direction.equals("S")) {
        otherDoor = otherRoom.getDoor("N");
        newXyLocation.setLocation(otherDoor.getWallPosition(), 1);
      } else if (direction.equals("W")) {
        otherDoor = otherRoom.getDoor("E");
        newXyLocation.setLocation(otherRoom.getWidth() - 2, otherDoor.getWallPosition());
      } else {
        otherDoor = otherRoom.getDoor("W");
        newXyLocation.setLocation(1, otherDoor.getWallPosition());
      }
      movePlayer(currentPlayer, newXyLocation);
    }

    private void checkRooms() {
      ArrayList<Room> badRoomList = new ArrayList<Room>();
      for (Room singleRoom : roomArray) {
        try {
          if (!(singleRoom.verifyRoom())) {
            badRoomList.add(singleRoom);
          }
        } catch (NotEnoughDoorsException n) {
          if (!(notEnoughDoorsExceptionFix(singleRoom))) {
            player.setCurrentRoom(null);
          } else {
            connectDoors();
          }
        }
      }
      removeRooms(badRoomList);
    }

    private void removeRooms(ArrayList<Room> badRoomList) {
      for (int i = 0; i < badRoomList.size(); i++) {
        if (roomArray.contains(badRoomList.get(i))) {
          roomArray.remove(badRoomList.get(i));
        }
        if (badRoomList.get(i).isPlayerInRoom()) {
          player.setCurrentRoom(null);
        }
      }
    }

    private boolean notEnoughDoorsExceptionFix(Room roomToFix) {
      String keyOfDoorToConnectTo = "";
      Room roomToConnectDoorTo = null;
      if (roomArray.size() >= 2) {
        for (Room singleRoom : roomArray) {
          if (singleRoom != roomToFix) {
            for (String key : singleRoom.getDoors().keySet()) {
              if (singleRoom.getDoor(key) == null) {
                keyOfDoorToConnectTo = key;
                roomToConnectDoorTo = singleRoom;
              }
            }
          }
        }
        return makeAndAddNewDoor(keyOfDoorToConnectTo, roomToConnectDoorTo, roomToFix);
      } else {
        return (false);
      }
    }

    private boolean makeAndAddNewDoor(String keyOfDoorToConnectTo, Room roomToConnectDoorTo, Room roomToFix) {
       if (roomToConnectDoorTo == null) {
          return (false);
        } else {
          if (keyOfDoorToConnectTo.equals("N")) {
            roomToConnectDoorTo.setDoor("N", new Door(roomToConnectDoorTo, roomToFix.getId(), 1));
            roomToFix.setDoor("S", new Door(roomToFix, roomToConnectDoorTo.getId(), 1));
          } else if (keyOfDoorToConnectTo.equals("S")) {
            roomToConnectDoorTo.setDoor("S", new Door(roomToConnectDoorTo, roomToFix.getId(), 1));
            roomToFix.setDoor("N", new Door(roomToFix, roomToConnectDoorTo.getId(), 1));
          } else if (keyOfDoorToConnectTo.equals("W")) {
            roomToConnectDoorTo.setDoor("W", new Door(roomToConnectDoorTo, roomToFix.getId(), 1));
            roomToFix.setDoor("E", new Door(roomToFix, roomToConnectDoorTo.getId(), 1));
          } else if (keyOfDoorToConnectTo.equals("E")) {
            roomToConnectDoorTo.setDoor("E", new Door(roomToConnectDoorTo, roomToFix.getId(), 1));
            roomToFix.setDoor("W", new Door(roomToFix, roomToConnectDoorTo.getId(), 1));
          }
          return true;
        }
    }
}
