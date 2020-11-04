package rogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.awt.Point;




/**
*Rogue is the class that sets up the rogue game.
*/

public class Rogue {
    public static final char UP = 'h';
    public static final char DOWN = 'l';
    public static final char LEFT = 'j';
    public static final char RIGHT = 'k';
    private String nextDisplay = "-----\n|.@..|\n|....|\n-----";

    private Player player;
    private ArrayList<Room> roomArray = new ArrayList<Room>();
    private ArrayList<Item> rogueItems = new ArrayList<Item>();
    private HashMap<String, Character> symbolMap;
    private RogueParser parser;
    private Map<String, String> tempRoomMap;
    private Map<String, String> tempItemMap;

/**
*Default constructor for rogue that sets everything to default values.
*/
    public Rogue() {
      player = null;
      symbolMap = null;
      tempRoomMap = null;
      parser = new RogueParser();
    }

/**
*constructor for rogue that sets everything to default values and sets up the parser.
*@param filename name of the file that contains the config settings
*/

    public Rogue(String filename) {
      player = null;
      roomArray = new ArrayList<Room>();
      rogueItems = new ArrayList<Item>();
      symbolMap = null;
      parser = new RogueParser(filename);
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
*@param filename name of the symbol file
*/

    public void setSymbols() {
        //TODO Read stuff from symbols file
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
* creates the room array based off of the rooms in room file.
*@param filename name of the room file
*/
    public void createRooms(String filename) {

      while ((tempRoomMap = parser.nextRoom()) != null) {
        addRoom(tempRoomMap);
      }
      //TO DO ADD ITEMS TO ROOM
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
        Player roomPlayer = new Player();
        newRoom.setPlayer(roomPlayer);
      }

      newRoom.setId(integerId);
      newRoom.setHeight(integerHeight);
      newRoom.setWidth(integerWidth);

      newRoom.setDoor("N", addDoor(toAdd.get("N"), newRoom));
      newRoom.setDoor("W", addDoor(toAdd.get("W"), newRoom));
      newRoom.setDoor("E", addDoor(toAdd.get("E"), newRoom));
      newRoom.setDoor("S", addDoor(toAdd.get("S"), newRoom));

      newRoom.updateDisplayRoom();
      roomArray.add(newRoom);

    }

/**
* creates a item and adds it to a room.
*@param toAdd item map that contains all item values.
*/

    public void addItem(Map<String, String> toAdd) {

      Item newItem = new Item();
   
      int itemId = Integer.decode(toAdd.get("id"));
      
      newItem.setId(itemId);
      newItem.setName(toAdd.get("name"));
      newItem.setType(toAdd.get("type"));
      newItem.setDescription(toAdd.get("description"));

      if(toAdd.get("room") != null){
        int roomId = Integer.decode(toAdd.get("room"));
        int itemX = Integer.decode(toAdd.get("x"));
        int itemY = Integer.decode(toAdd.get("y"));
         Point newItemLocation = new Point(itemX, itemY);
        newItem.setXyLocation(newItemLocation);
        rogueItems.add(newItem);
        try {
          roomArray.get(roomId-1).addItem(newItem);
        } catch (ImpossiblePositionException e) {
          //TODO MAKE IT WORK
        } catch (NoSuchItemException f) {
          roomArray.get(roomId-1).getRoomItems().remove(itemId);
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
      for (Room tempRoom : roomArray) {
        doorHolder = tempRoom.getDoor("N");
        if (doorHolder != null) {
          doorHolder.connectRoom(roomArray.get(doorHolder.getOtherRoomid()-1));
        }
        doorHolder = tempRoom.getDoor("W");
        if (doorHolder != null) {
          doorHolder.connectRoom(roomArray.get(doorHolder.getOtherRoomid()-1));
        }
        doorHolder = tempRoom.getDoor("S");
        if (doorHolder != null) {
          doorHolder.connectRoom(roomArray.get(doorHolder.getOtherRoomid()-1));
        }
        doorHolder = tempRoom.getDoor("E");
        if (doorHolder != null) {
          doorHolder.connectRoom(roomArray.get(doorHolder.getOtherRoomid()-1));
        }
      }
    }

/**
* returns the string that displays the current rooms in the dungeon.
*@return string that displays the current rooms in the dungeon
*/

    public String displayAll() {
        //creates a string that displays all the rooms in the dungeon
      String roomsDisplay = "";

      for (int i = 0; i < roomArray.size(); i++) {
        roomsDisplay += roomArray.get(i).displayRoom();
      }
      for (HashMap.Entry<String, Character> symbolString : symbolMap.entrySet()) {
        roomsDisplay = roomsDisplay.replaceAll(symbolString.getKey(), symbolString.getValue().toString());
      }

      return roomsDisplay;
    }

/**
* returns the string of the room after a move.
*@return string that displays the room after a move
*@param input input from player
*/

    public String makeMove(char input) {
      //TODO MAKE IT MOVE
      return "NOT WORKING";
    }
/**
* returns the string that displays the room.
*@return string that displays the next display
*/
    public String getNextDisplay() {
      //GET STRING OF NEXT MOVE
      return "NOT DONE";
    }


}
