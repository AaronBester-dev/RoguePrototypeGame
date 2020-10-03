package rogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Point;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Rogue{

    private Player player = new Player();
    private String symbols;
    private ArrayList<Room> roomArray = new ArrayList<Room>();
    private ArrayList<Item> rogueItems = new ArrayList<Item>();
    private Map<String,String> symbolMap;
 
    public void setPlayer(Player thePlayer){
        player = thePlayer;
    }


    public void setSymbols(String filename){
        //TODO Read stuff from symbols file
        JSONParser parser = new JSONParser();
        JSONArray jsonSymbols = null;
        symbolMap = new HashMap();
        try{

            JSONObject symbolObject = (JSONObject) parser.parse(new FileReader(filename));
            jsonSymbols = (JSONArray) symbolObject.get("symbols");

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(Object object : jsonSymbols){
            JSONObject jsonSymbol = (JSONObject) object;
            symbolMap.put(jsonSymbol.get("name").toString(), jsonSymbol.get("symbol").toString());
        }
    }

    public ArrayList<Room> getRooms(){
        return roomArray;

    }

    public ArrayList<Item> getItems(){
        return null;

    }
    public Player getPlayer(){
        return player;
    }

    public void createRooms(String filename){
        JSONParser parser = new JSONParser();
        JSONArray jsonRooms = null;
        try{
               JSONObject roomObject = (JSONObject) parser.parse(new FileReader(filename));
                jsonRooms = (JSONArray) roomObject.get("room");
        }
         catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
     
       
        for(Object object : jsonRooms ){
            roomArray.add(new Room((JSONObject)object));
        }

       
    }
    public String displayAll(){
        //creates a string that displays all the rooms in the dungeon
        String roomsDisplay = "";

        for(int i = 0; i < roomArray.size(); i++){
            roomsDisplay += roomArray.get(i).displayRoom();
        }
        for(Map.Entry<String,String> symbolString : symbolMap.entrySet()){
            roomsDisplay = roomsDisplay.replaceAll(symbolString.getKey(), symbolString.getValue());
        }
        return roomsDisplay;
    }
}