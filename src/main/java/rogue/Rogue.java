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
        symbols = filename;
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
        
    }
    public String displayAll(){
        //creates a string that displays all the rooms in the dungeon
        return null;
    }
}