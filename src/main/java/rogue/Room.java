package rogue;
import java.util.ArrayList; 
import java.util.Map;
import java.awt.Point;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room  {

   private int roomWidth;
   private int roomHeight;
   private int roomId;
   private int roomDoor;
   private ArrayList<Item> roomItems = new ArrayList<Item>();
   private Player player = new Player();

    // Default constructor
 public Room() {
    this.setWidth(0);
    this.setHeight(0);
    this.setId(0);
 }

 
public Room(JSONObject jsonRoom){
  
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
    return roomDoor;
 }

/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/

public void setDoor(String direction, int location){
  roomDoor = location;
}


public boolean isPlayerInRoom() {

return true;
}




   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
    * @return (String) String representation of how the room looks
    */
   public String displayRoom() {
    return null;
     
     
   }
}