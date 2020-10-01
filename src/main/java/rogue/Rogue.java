package rogue;

import java.util.ArrayList;

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
    private ArrayList<Item> rogueItems = new ArrayList<Item>();
    private ArrayList<Room> roomArray = new ArrayList<Room>();

    public void setPlayer(Player thePlayer){
        player = thePlayer;
    }


    public void setSymbols(String filename){
        //TODO Read stuff from symbols file
    }

    public ArrayList<Room> getRooms(){
        return roomArray;

    }

    public ArrayList<Item> getItems(){
        return rogueItems;

    }
    public Player getPlayer(){
        return player;
    }

    public void createRooms(String filename){
        JSONParser parser = new JSONParser();
        JSONObject roomObject = (JSONObject) parser.parse(new FileReader(filename));
        JSONArray jsonRooms = (JSONArray) roomObject.get("room");
        for(Object object : jsonRooms ){
            roomArray.add(Room((JSONObject)object));
        }

        JSONArray jsonItems = (JSONArray) roomObject.get("items");
        for(Object object : jsonItems){
            rogueItems.add(Item((JSONObject)object));
        }
    }
    public String displayAll(){
        //creates a string that displays all the rooms in the dungeon
        return null;
    }
}