package rogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    private ArrayList<Room> roomArray;
    private ArrayList<Item> rogueItems;
    private Map<String, Character> symbolMap;
    private RogueParser rogueParser;
    private Map<String, String> tempRoomMap;
    private Map<String, String> tempItemMap;

/**
*Default constructor for rogue that sets everything to default values.
*/
    public Rogue() {
      player = null;
      roomArray = new ArrayList<Room>();
      rogueItems = new ArrayList<Item>();
      symbolMap = null;
      tempRoomMap = null;
      rogueParser = new RogueParser();
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
      rogueParser = new RogueParser(filename);
    }

    public Rogue(RogueParser parser) {
      player = null;
      roomArray = new ArrayList<Room>();
      rogueItems = new ArrayList<Item>();
      symbolMap = null;
      rogueParser = parser;
    }

    public Rogue(RogueParser theDungeonInfo){

        parser = theDungeonInfo;

        Map roomInfo = parser.nextRoom();
        while(roomInfo !=null){
            addRoom(roomInfo);
            roomInfo = parser.nextRoom();
        }

        Map itemInfo = parser.nextItem();
        while(itemInfo !=null){
            addItem(itemInfo);
            itemInfo = parser.nextItem();
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
*@param filename name of the symbol file
*/

    public void setSymbols(String filename) {
        //TODO Read stuff from symbols file
      symbolMap = rogueParser.getSymbols();
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

      while((tempRoomMap = rogueParser.nextRoom()) != null){
        addRoom(tempRoomMap);
      }
      //TO DO ADD ITEMS TO ROOM
      

    }

    public void addRoom(Map <String,String> toAdd){
      Room newRoom = new Room();
      String[] doorStringArray;
      int doorConnectedRoomId = 0;
      int wallPosition = 0;
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

      doorStringArray = roomMap.get("N").split(" ");
      doorConnectedRoomId = Integer.decode(doorStringArray[0]);
      wallPosition = Integer.decode(doorStringArray[1]);
      Door nDoor = new Door(roomArray.get(integerId),roomArray.get(doorConnectedRoomId),wallPosition);

      doorStringArray = roomMap.get("W").split(" ");
      doorConnectedRoomId = Integer.decode(doorStringArray[0]);
      wallPosition = Integer.decode(doorStringArray[1]);
      Door wDoor = new Door(roomArray.get(integerId),roomArray.get(doorConnectedRoomId),wallPosition);

      doorStringArray = roomMap.get("E").split(" ");
      doorConnectedRoomId = Integer.decode(doorStringArray[0]);
      wallPosition = Integer.decode(doorStringArray[1]);
      Door eDoor = new Door(roomArray.get(integerId),roomArray.get(doorConnectedRoomId),wallPosition);

      doorStringArray = roomMap.get("S").split(" ");
      doorConnectedRoomId = Integer.decode(doorStringArray[0]);
      wallPosition = Integer.decode(doorStringArray[1]);
      Door sDoor = new Door(roomArray.get(integerId),roomArray.get(doorConnectedRoomId),wallPosition);
      

      newRoom.setDoor("N",nDoor);
      newRoom.setDoor("W",wDoor);
      newRoom.setDoor("E",eDoor);
      newRoom.setDoor("S",sDoor);

      newRoom.updateDisplayRoom();
      roomArray.add(newRoom);
      
    }

    public void addItem(Map <String,String> toAdd){
      
      Item newItem = new Item();
      int itemId = Integer.decode(toAdd.get("id"));
      int roomId = Integer.decode(toAdd.get("room"));
      int itemX = Integer.decode(toAdd.get("x"));
      int itemY = Integer.decode(toADd.get("y"));
      newItem.setId(itemId);
      newItem.setName(toAdd.get("name"));
      newItem.setType(toAdd.get("type"));
      newItem.setDescription(toAdd.get("description"));
      new Point newItemLocation = new Point(itemX,itemY);
      newItem.setXyLocation(newItemLocation);
      rogueItems.add(newItem);
      try{
        roomArray.get(roomId).addItem(newItem);
      } catch (ImpossiblePositionException e){
        //TODO MAKE IT WORK
      } catch (NoSuchItemException f){
        roomArray.get(roomId).getRoomItems().remove(itemId);
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
      for (Map.Entry<String, Character> symbolString : symbolMap.entrySet()) {
        roomsDisplay = roomsDisplay.replaceAll(symbolString.getKey(), symbolString.getValue().toString());
      }

      return roomsDisplay;
    }
}
