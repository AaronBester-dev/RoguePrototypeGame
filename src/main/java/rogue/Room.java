package rogue;
import java.util.ArrayList; 
import java.util.Map;
import java.awt.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room  {

   private int roomWidth;
   private int roomHeight;
   private int roomId;
   private int nDoor;
   private int sDoor;
   private int eDoor;
   private int wDoor;
   private ArrayList<Item> roomItems = new ArrayList<Item>();
   private Player player = new Player();
   private boolean isPlayer;

    // Default constructor
 public Room() {
    setWidth(0);
    setHeight(0);
    setId(0);
 }

 
public Room(JSONObject jsonRoom){
  Integer integerId = Integer.decode(jsonRoom.get("id").toString());
  isPlayer = Boolean.parseBoolean(jsonRoom.get("start").toString());
  Integer integerHeight = Integer.decode(jsonRoom.get("height").toString());
  Integer integerWidth = Integer.decode(jsonRoom.get("width").toString());
  if(isPlayer == false){
    setPlayer(null);
  }
  setId(integerId);
  setHeight(integerHeight);
  setWidth(integerWidth);

  for(Object door : (JSONArray) jsonRoom.get("doors")){
    JSONObject jsonDoor = (JSONObject) door;
    setDoor(jsonDoor.get("dir").toString(),Integer.decode(jsonDoor.get("id").toString()));
  }

  for(Object item : (JSONArray) jsonRoom.get("loot")){
    roomItems.add((new Item((JSONObject) item)));
  }


}
 


   // Required getter and setters below

 
 public int getWidth() {
   return(roomWidth);
 }

 
 public void setWidth(int newWidth) {
   roomWidth = newWidth;
 }

 
 public int getHeight() {
   return(roomHeight);
 }


 public void setHeight(int newHeight) {
   roomHeight = newHeight;
 }

 public int getId() {
   return(roomId);

 }


 public void setId(int newId) {
   roomId = newId;
 }


 public ArrayList<Item> getRoomItems() {
    return roomItems;

 }


 public void setRoomItems(ArrayList<Item> newRoomItems) {
    roomItems = newRoomItems;
 }


 public Player getPlayer() {
    return player;
 }


 public void setPlayer(Player newPlayer) {
    player = newPlayer;
 }

 public int getDoor(String direction){
    if(direction == "N"){
      return(nDoor);
    }
    else if(direction == "S"){
      return(sDoor);
    }
    else if(direction == "E"){
      return(eDoor);
    }
    else{
      return(wDoor);
    }
 }

/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/

public void setDoor(String direction, int location){
   if(direction == "N"){
      nDoor = location;
    }
    else if(direction == "S"){
      sDoor = location;
    }
    else if(direction == "E"){
      eDoor = location;
    }
    else{
      wDoor = location;
    }
}


public boolean isPlayerInRoom() {
  if(isPlayer == false){
    return false;
  }
  else{
    return true;
  }
}




   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
    * @return (String) String representation of how the room looks
    */
   public String displayRoom() {
    return null;
     
     
   }
}