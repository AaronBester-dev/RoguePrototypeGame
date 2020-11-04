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
    private String[][] roomDisplayArray;

/**
 * Default constructor for room that intializes room to default values.
 */
    public Room() {
      setWidth(2);
      setHeight(2);
      setId(0);
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
 * adds Item to a room.
 *@param toAdd item to be added.
 *@throws ImpossiblePositionException when item is in a impossible location
 *@throws NoSuchItemException when item id doesn't exist in item array
 */

    public void addItem(Item toAdd) throws ImpossiblePositionException, NoSuchItemException {
      int itemX = (int) toAdd.getXyLocation().getX();
      int itemY = (int) toAdd.getXyLocation().getY();
      if (itemX > roomWidth || itemX < 0 || itemY > roomHeight
      || itemY < 0 || !(roomDisplayArray[itemX][itemY].equals("FLOOR"))) {
        throw new ImpossiblePositionException();
      } else if (itemX != -1) {
        throw new NoSuchItemException();
      } else {
        roomItems.add(toAdd);
      }

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
      if (doors.containsKey(direction)) {
        return (doors.get(direction));
      } else {
        return (null);
      }

    }

/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/
/**
 * setter that sets the door in the direction you want in the room.
 *@param direction direction of the door you want to place.
 *@param newDoor door item to add to map
 */
    public void setDoor(String direction, Door newDoor) {
      if (doors.containsKey(direction)) {
        doors.replace(direction, newDoor);
      } else {
        doors.put(direction, newDoor);
      }
    }
/**
 * checks whether or not the player is in the room.
 *@return true if player is in room and false if otherwise.
 */
    public boolean isPlayerInRoom() {
      return !(roomPlayer == null);
    }

/**
 * checks to see if room meets all requirements to be a room.
 *@return true if room is a valid room and false if otherwise.
 */

    public boolean verifyRoom() {
      //TO DO VERIFY IF ITEMS DOORS AND EVERYTHING IS VALID;
      return true;
    }

 /**
    * Updates the roomDisplayArray when changes are made.
    */
    public void updateDisplayRoom() {
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

      initalizeRoomDisplayArray();
      addDoorsToRoomDisplayArray();
      addContentsToRoomDisplayArray();
      convertDisplayArrayToString(roomDisplayString);

      return (roomDisplayString);
    }

   /**
    * Initializes roomDisplayArray to a empty room.
    */

    public void initalizeRoomDisplayArray() {
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

     /**
    * Adds doors to the room.
    *
    */

    public void addDoorsToRoomDisplayArray() {
      Door doorHolder = getDoor("N");
      if (doorHolder != null) {
        roomDisplayArray[0][doorHolder.getWallPosition()] = "DOOR";
      }
      doorHolder = getDoor("E");
      if (doorHolder  != null) {
        roomDisplayArray[doorHolder.getWallPosition()][0] = "DOOR";
      }
      doorHolder = getDoor("S");
      if (doorHolder != null) {
        roomDisplayArray[roomHeight - 1][doorHolder.getWallPosition()] = "DOOR";
      }
      doorHolder = getDoor("W");
      if (doorHolder != null) {
        roomDisplayArray[doorHolder.getWallPosition()][roomWidth - 1] = "DOOR";
      }
    }

      /**
    * Adds player and items to the room.
    */

    public void addContentsToRoomDisplayArray() {
      if (isPlayerInRoom()) {
        roomDisplayArray[(int) roomPlayer.getXyLocation().getY()][(int) roomPlayer.getXyLocation().getX()] = "PLAYER";
      }

      for (int i = 0; i < roomItems.size(); i++) {
        roomDisplayArray[(int) roomItems.get(i).getXyLocation().getY()]
        [(int) roomItems.get(i).getXyLocation().getX()] = roomItems.get(i).getType();
      }
    }

    /**
    * Converts displayArray to a string that displays the contents of the room.
    * @param roomDisplayString a string that displays the contents of the room.
    * @return a string that displays the contents of the room
    */

    public String convertDisplayArrayToString(String roomDisplayString) {
      for (int y = 0; y < roomHeight; y++) {
        for (int x = 0; x < roomWidth; x++) {
          roomDisplayString += roomDisplayArray[y][x];
        }
        roomDisplayString += '\n';
      }

      roomDisplayString += '\n';
      roomDisplayString += '\n';

      return (roomDisplayString);
    }
}
