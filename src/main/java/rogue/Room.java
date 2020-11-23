package rogue;
import java.util.ArrayList;


import java.util.HashMap;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room  {

    private int roomWidth;
    private int roomHeight;
    private int roomId;
    private ArrayList<Item> roomItems = new ArrayList<Item>();
    private Player roomPlayer = null;
    private HashMap<String, Door> doors = new HashMap<>();
    private Rogue gameRoomBelongsTo;
    private String[][] roomDisplayArray;

/**
 * Default constructor for room that intializes room to default values.
 */
    public Room() {
      roomDisplayArray = new String[roomHeight][roomWidth];
    }

   // Required getter and setters below
/**
 * getter that gets width of a room.
 *@return width of room
 */
    public int getWidth() {
      return (roomWidth);
    }

/**
 * setter that sets width of a room.
 *@param newWidth new width of room
 */
    public void setWidth(int newWidth) {
      roomWidth = newWidth;
    }
/**
 * getter that gets height of a room.
 *@return height of room
 */
    public int getHeight() {
      return (roomHeight);
    }
/**
 * setter that sets height of a room.
 *@param newHeight new height of room
 */
    public void setHeight(int newHeight) {
      roomHeight = newHeight;
    }
/**
 * getter that gets id of a room.
 *@return id of room
 */
    public int getId() {
      return (roomId);
    }
/**
 * setter that sets id of a room.
 *@param newId new id of room
 */
    public void setId(int newId) {
      roomId = newId;
    }
/**
 * getter that gets items array of a room.
 *@return list of items in room
 */
    public ArrayList<Item> getRoomItems() {
      return (roomItems);
    }
/**
 * setter that sets room items array to a new list of item.
 *@param newRoomItems new list of items
 */
    public void setRoomItems(ArrayList<Item> newRoomItems) {
      roomItems = newRoomItems;
    }
/**
 * getter that gets room display array.
 *@return room display array.
 */
    public String[][] getRoomDisplayArray() {
      return roomDisplayArray;
    }
/**
 * setter that sets roomDisplayArray to a new roomDisplayArray.
 *@param newRoomDisplayArray new RoomDisplayArray
 */
    public void setRoomDisplayArray(String[][] newRoomDisplayArray) {
      roomDisplayArray = newRoomDisplayArray;
    }

  /**
 * setter that sets the rogue game the room belongs to.
 *@param newRogue new rogue of room
 */
    public void setRogue(Rogue newRogue) {
      gameRoomBelongsTo = newRogue;
    }

      /**
 *getter that gets the rogue game the room belongs to.
 *@return rogue game room is attached to.
 */
    public Rogue getRogue() {
      return (gameRoomBelongsTo);
    }

/**
 * getter that gets the player in the room.
 *@return player in room
 */
    public Player getPlayer() {
      return (roomPlayer);
    }
/**
 * setter that sets player to a room.
 *@param newPlayer new player in the room.
 */
    public void setPlayer(Player newPlayer) {
      roomPlayer = newPlayer;
    }
/**
 * getter that gets the door of direction you want in the room.
 *@param direction direction that the door is in.
 *@return door that is in the room in the direction you want.
 */
    public Door getDoor(String direction) {
      if (getDoors().containsKey(direction)) {
        return (getDoors().get(direction));
      } else {
        return (null);
      }

    }

    /**
 * setter that sets the door in the direction you want in the room.
 *@param direction direction of the door you want to place.
 *@param newDoor door item to add to map
 */
    public void setDoor(String direction, Door newDoor) {
      if (getDoors().containsKey(direction)) {
        getDoors().replace(direction, newDoor);
      } else {
        getDoors().put(direction, newDoor);
      }
    }

/**
 * getter that gets hashmap of every door in the room.
 *@return Hashmap of doors that are in the room.
 */
    public HashMap<String, Door> getDoors() {
      return (doors);
    }

/**
 * checks whether or not the player is in the room.
 *@return true if player is in room and false if otherwise.
 */
    public boolean isPlayerInRoom() {
      return !(roomPlayer == null);
    }

 /**
 * adds Item to a room.
 *@param toAdd item to be added.
 *@throws ImpossiblePositionException when item is in a impossible location
 *@throws NoSuchItemException when item id doesn't exist in item array
 */

    public void addItem(Item toAdd) throws ImpossiblePositionException, NoSuchItemException {
      int itemX = (int) toAdd.getXyLocation().getX();
      int itemY = (int) toAdd.getXyLocation().getY();
      ArrayList<Item> rogueItems = getRogue().getItems();
      int itemFound = 0;
      if ((itemX >= getWidth() - 1) || (itemX <= 0) || (itemY >= getHeight() - 1)
      || (itemY <= 0) || !(roomDisplayArray[itemY][itemX].equals("FLOOR"))) {
        throw new ImpossiblePositionException();
      } else {
        roomItems.add(toAdd);
      }
      for (Item singleItem : rogueItems) {
        if (toAdd.getId() == singleItem.getId()) {
          itemFound = 1;
        }
      }
      if (itemFound != 1) {
        throw new NoSuchItemException();
      }
    }

/**
 * checks to see if room meets all requirements to be a room.
 *@return true if room is a valid room and false if otherwise.
 *@throws NotEnoughDoorsException throws if there is no doors in room
 */

    public boolean verifyRoom() throws NotEnoughDoorsException {
      if (getDoors().get("N") == null && getDoors().get("W") == null
      && getDoors().get("S") == null && getDoors().get("E") == null) {
        throw new NotEnoughDoorsException();
      }

      if(!(verifyDoorConnections())){
        return (false);
      }

      if(!(verifyRoomItems())){
        return false;
      }

      if(!(verifyPlayerLocation())){
        return false;
      }

      return true;
    }

    private boolean verifyDoorConnections(){
      for (String key: getDoors().keySet()) {
        Door doorHolder = getDoors().get(key);
        if (doorHolder != null) {
          if (doorHolder.getConnectedRooms().size() < 2 || doorHolder.getOtherRoom(this) == null) {
            return false;
          }
        }
      }
      return(true);
    }

    private boolean verifyRoomItems(){
      for (Item singleItem: roomItems) {
        int itemX = (int) singleItem.getXyLocation().getX();
        int itemY = (int) singleItem.getXyLocation().getY();
        if (itemX >= getWidth() - 1 || itemX <= 0) {
          return false;
        }
        if (itemY >= getHeight() - 1 || itemY <= 0) {
          return false;
        }
      }
      return(true);
    }

    private boolean verifyPlayerLocation(){
      if (isPlayerInRoom()) {
        int playerX = (int) getPlayer().getXyLocation().getX();
        int playerY = (int) getPlayer().getXyLocation().getY();
        if (playerX >= getWidth() - 1 || playerX <= 0) {
          return false;
        }
        if (playerY >= getHeight() - 1 || playerY <= 0) {
          return false;
        }
      }
      return true;
    }

 /**
    * Updates the roomDisplayArray when changes are made.
    */
    public void updateDisplayRoom() {
      roomDisplayArray = new String[roomHeight][roomWidth];
      initalizeRoomDisplayArray();
      addDoorsToRoomDisplayArray();
      addContentsToRoomDisplayArray();
    }

   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents.
    * @return (String) String representation of how the room looks.
    */

    public String displayRoom() {
      String roomDisplayString = "";
      roomDisplayArray = new String[roomHeight][roomWidth];
      initalizeRoomDisplayArray();
      addDoorsToRoomDisplayArray();
      addContentsToRoomDisplayArray();
      roomDisplayString = convertDisplayArrayToString(roomDisplayString);
      return (roomDisplayString);
    }

    private void initalizeRoomDisplayArray() {
      for (int x = 0; x < roomWidth; x++) {
        roomDisplayArray[0][x] = "NS_WALL";
      }

      for (int y = 1; y < roomHeight - 1; y++) {
        int x = 0;
        roomDisplayArray[y][x] = "EW_WALL";
        for (x = 1; x < roomWidth - 1; x++) {
          roomDisplayArray[y][x] = "FLOOR";
        }
        roomDisplayArray[y][x] = "EW_WALL";
      }

      for (int x = 0; x < roomWidth; x++) {
        roomDisplayArray[roomHeight - 1][x] = "NS_WALL";
      }
    }

    private void addDoorsToRoomDisplayArray() {
      Door doorHolder = getDoor("N");
      if (doorHolder != null) {
        roomDisplayArray[0][doorHolder.getWallPosition()] = "NDOOR";
      }
      doorHolder = getDoor("W");
      if (doorHolder  != null) {
        roomDisplayArray[doorHolder.getWallPosition()][0] = "WDOOR";
      }
      doorHolder = getDoor("S");
      if (doorHolder != null) {
        roomDisplayArray[roomHeight - 1][doorHolder.getWallPosition()] = "SDOOR";
      }
      doorHolder = getDoor("E");
      if (doorHolder != null) {
        roomDisplayArray[doorHolder.getWallPosition()][roomWidth - 1] = "EDOOR";
      }
    }

    private void addContentsToRoomDisplayArray() {
      if (isPlayerInRoom()) {
        roomDisplayArray[(int) roomPlayer.getXyLocation().getY()][(int) roomPlayer.getXyLocation().getX()] = "PLAYER";
      }

      for (int i = 0; i < roomItems.size(); i++) {
        roomDisplayArray[(int) roomItems.get(i).getXyLocation().getY()]
        [(int) roomItems.get(i).getXyLocation().getX()] = Integer.toString(roomItems.get(i).getId());
      }
    }

    private String convertDisplayArrayToString(String roomDisplayString) {
      for (int y = 0; y < roomHeight; y++) {
        for (int x = 0; x < roomWidth; x++) {
          if (!(Character.isLetter(roomDisplayArray[y][x].charAt(0)))) {
            int itemId = Integer.decode(roomDisplayArray[y][x]);
            for (Item singleItem : roomItems) {
              if (singleItem.getId() == itemId) {
                roomDisplayString += singleItem.getType().toUpperCase();
              }
            }
          } else if (roomDisplayArray[y][x].equals("NDOOR") || roomDisplayArray[y][x].equals("SDOOR")
          || roomDisplayArray[y][x].equals("EDOOR") || roomDisplayArray[y][x].equals("WDOOR")) {
            roomDisplayString += "DOOR";
          } else {
            roomDisplayString += roomDisplayArray[y][x];
          }
        }
        roomDisplayString += '\n';
      }
      roomDisplayString += "\n\n";
      return (roomDisplayString);
    }
}
