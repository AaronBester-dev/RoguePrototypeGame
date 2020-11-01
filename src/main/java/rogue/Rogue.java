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

      newRoom.setDoor("N",Integer.decode(roomMap.get("N")));
      newRoom.setDoor("W",Integer.decode(roomMap.get("W")));
      newRoom.setDoor("E",Integer.decode(roomMap.get("E")));
      newRoom.setDoor("S",Integer.decode(roomMap.get("S")));

      newRoom.updateDisplayRoom();

      roomArray.add(newRoom);
      
    }

    public void addItem(Map <String,String> toAdd){
      /* 
        look up the attributes of the item in the map
        set the fields in the object you just created using those values
        add the item object to the list of items in the dungeon
        add the item to the room it is currently located in
        */
      Item newItem = new Item();
      newItem.setId(Integer.decode(toAdd.get("id")));
      newItem.setName(toAdd.get("name"));
      newItem.setType(toAdd.get("type"));
      new Point newItemLocation = new Point(Integer.decode(toAdd.get("x")),Integer.decode(toAdd.get("y")));
      newItem.setXyLocation(newItemLocation);
      rogueItems.add(newItem);
      roomArray.get(Integer.decode(toAdd.get("room"))).addItem(newItem);

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
