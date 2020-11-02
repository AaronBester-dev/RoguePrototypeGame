package rogue;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Map;

/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room  {

    private int roomWidth;
    private int roomHeight;
    private int roomId;
    private int nDoor = -1;
    private int sDoor = -1;
    private int eDoor = -1;
    private int wDoor = -1;
    private ArrayList<Item> roomItems = new ArrayList<Item>();
    private Player roomPlayer = null;
    private HashMap<String,Integer> doors = new HashMap<String,Integer>();
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
/**
 * Default constructor for room that intializes room to default values.
 *@param jsonRoom jsonObject that contains all of the values needed for a room
 */
    public Room(Map<String,String> roomMap) {
      Integer integerId = Integer.decode(roomMap.get("id"));
      Boolean isPlayer = Boolean.parseBoolean(roomMap.get("start").toString());
      Integer integerHeight = Integer.decode(roomMap.get("height").toString());
      Integer integerWidth = Integer.decode(roomMap.get("width").toString());

      if (isPlayer.booleanValue()) {
        roomPlayer = new Player();
      }

      setId(integerId);
      setHeight(integerHeight);
      setWidth(integerWidth);

      setDoor("N",Integer.decode(roomMap.get("N")));
      setDoor("W",Integer.decode(roomMap.get("W")));
      setDoor("E",Integer.decode(roomMap.get("E")));
      setDoor("S",Integer.decode(roomMap.get("S")));

      roomDisplayArray = new String[roomHeight][roomWidth];
      updateDisplayRoom();

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

    public void addItem(Item toAdd){
      int itemX = (int) toAdd.getXyLocation().getX();
      int itemY = (int) toAdd.getXyLocation().getY();
      if(itemX > roomWidth || itemX < 0 || itemY > roomHeight || itemY < 0 || !(roomDisplayArray[itemX][itemY].equals("FLOOR"))){
        throw(ImpossiblePositionException);
      }
      else if(itemX != -1){
        throw (NoSuchItemException);
      }
      else{
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
    public int getDoor(String direction) {
      return(doors.get(direction));
    }

/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/
/**
 * setter that sets the door in the direction you want in the room.
 *@param direction direction of the door you want to place.
 *@param location location of where you want to place the door
 */
    public void setDoor(String direction, int location) {
      if(doors.contains(direction)){
        doors.replace(direction,location);
      }
      else{
        doors.put(direction,location);
      }
    }
/**
 * checks whether or not the player is in the room.
 *@return true if player is in room and false if otherwise.
 */
    public boolean isPlayerInRoom() {
      return !(roomPlayer == null);
    }

    public boolean verifyRoom(){
      //TO DO VERIFY IF ITEMS DOORS AND EVERYTHING IS VALID;
      return true;
      return false;
    } 

   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents.
    * @return (String) String representation of how the room looks.
    */

    public void updateDisplayRoom(){
      initalizeRoomDisplayArray();
      addDoorsToRoomDisplayArray();
      addContentsToRoomDisplayArray();
    }

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
    * @param roomDisplayArray a array that holds the x and y locations of every object in the room.
    * @return a array that holds the x and y locations of every object in the room
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
    * @param roomDisplayArray a array that holds the x and y locations of every object in the room.
    * @return a array that holds the x and y locations of every object in the room.
    */

    public void addDoorsToRoomDisplayArray() {
      if (nDoor != -1) {
        roomDisplayArray[0][nDoor] = "DOOR";
      }
      if (eDoor != -1) {
        roomDisplayArray[eDoor][0] = "DOOR";
      }
      if (sDoor != -1) {
        roomDisplayArray[roomHeight - 1][sDoor] = "DOOR";
      }
      if (wDoor != -1) {
        roomDisplayArray[wDoor][roomWidth - 1] = "DOOR";
      }
    }

      /**
    * Adds player and items to the room.
    * @param roomDisplayArray a array that holds the x and y locations of every object in the room.
    * @return a array that holds the x and y locations of every object in the room
    */

    public void addContentsToRoomDisplayArray() {
      if (isPlayerInRoom()) {
        roomDisplayArray[(int) roomPlayer.getXyLocation().getY()][(int) roomPlayer.getXyLocation().getX()] = "PLAYER";
      }

      for (int i = 0; i < roomItems.size(); i++) {
        roomDisplayArray[(int) roomItems.get(i).getXyLocation().getY()]
        [(int) roomItems.get(i).getXyLocation().getX()] = "ITEM";
      }
    }

    /**
    * Converts displayArray to a string that displays the contents of the room.
    * @param roomDisplayArray a array that holds the x and y locations of every object in the room.
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
