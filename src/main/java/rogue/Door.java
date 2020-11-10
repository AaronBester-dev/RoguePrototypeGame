package rogue;

import java.util.ArrayList;

/**
*Class that holds all the information for a door.
*/
public class Door {

    private ArrayList<Room> connectedRooms;
    private int wallPosition;
    private int otherRoomid;

/**
*Default constructor that makes a simple door.
*/

    public Door() {
      connectedRooms = new ArrayList<>();
      wallPosition = 0;
      otherRoomid = -1;
    }

/**
*constructor that makes a simple door based off of given door variables.
*@param currentRoom the room that the door first connects to.
*@param newOtherRoomid the id of the room on the other side of the door.
*@param newWallPosition where the door is located on the wall.
*/

    public Door(Room currentRoom, int newOtherRoomid, int newWallPosition) {
      connectedRooms = new ArrayList<>();
      connectRoom(currentRoom);
      setOtherRoomid(newOtherRoomid);
      setWallPosition(newWallPosition);
    }

/**
*getter that gets the wall position of a door.
*@return the wall position of the door.
*/

    public int getWallPosition() {
      return (wallPosition);
    }
/**
*setter that sets the wall position of a door.
*@param newWallPosition the new wall position of the door.
*/
    public void setWallPosition(int newWallPosition) {
      wallPosition = newWallPosition;
    }
/**
*getter that gets the id of the other room the door connects to.
*@return the ide of the other room the door connects to.
*/
    public int getOtherRoomid() {
      return otherRoomid;
    }
/**
*setter that sets the other room id of a door.
*@param newOtherRoomid the id of the other room the door connects to.
*/
    public void setOtherRoomid(int newOtherRoomid) {
      otherRoomid = newOtherRoomid;
    }
/**
*connects a new room to the door.
*@param r room that is to be connected.
*/
    public void connectRoom(Room r) {
      connectedRooms.add(r);
    }
/**
*gets array list of every room the door is connected to.
*@return list of every room the door is connected to
*/
    public ArrayList<Room> getConnectedRooms() {
      return (connectedRooms);
    }
/**
*gets the other room that the door is connected to that the player isn't in.
*@param currentRoom the room the player is currently in.
*@return the other room that the player isn't in.
*/
    public Room getOtherRoom(Room currentRoom) {
      int currentRoomIndex = connectedRooms.indexOf(currentRoom);
      if (currentRoomIndex == 0) {
        return (connectedRooms.get(1));
      } else {
        return (connectedRooms.get(0));
      }
    }

}
