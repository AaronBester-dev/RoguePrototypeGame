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
        roomArray.add(new Room(tempRoomMap));
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
      for (Map.Entry<String, String> symbolString : symbolMap.entrySet()) {
        roomsDisplay = roomsDisplay.replaceAll(symbolString.getKey(), symbolString.getValue());
      }

      return roomsDisplay;
    }
}
