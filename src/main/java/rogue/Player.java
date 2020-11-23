package rogue;

import java.util.ArrayList;

import java.awt.Point;
/**
 * The player character.
 */
public class Player {
    private String playerName;
    private Point playerLocation;
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<Item>();

/**
 * Default player constructor that sets player to default values.
 */
    public Player() {
      setName("Aaron");
      setXyLocation(new Point(1, 1));
    }
/**
 * player constructor that sets player to values of variables.
 *@param name name of the player
 *@param newLocation newLocation of the player
 *@param newRoom newRoom of the player
 */
    public Player(String name, Point newLocation, Room newRoom) {
      setName(name);
      setXyLocation(newLocation);
      setCurrentRoom(newRoom);
    }
/**
 * player constructor that only sets name of the player.
 *@param name name of the player
 */
    public Player(String name) {
      setName(name);
    }
/**
 * getter that gets the current name of the player.
 *@return current name of the player
 */
    public String getName() {
      return (playerName);
    }
/**
 * setter that sets the current name of the player.
 *@param newName new name of the player
 */
    public void setName(String newName) {
      playerName = newName;
    }
/**
 * getter that gets the current xyLocation of the player.
 *@return current xyLocation of the player
 */
    public Point getXyLocation() {
      return (playerLocation);
    }
/**
 * setter that sets the current xyLocation of the player.
 *@param newXyLocation new xyLocation of the player
 */
    public void setXyLocation(Point newXyLocation) {
      playerLocation = newXyLocation;
    }
/**
 * getter that sets the current room of the player.
 *@return the current room of the player
 */
    public Room getCurrentRoom() {
      return (currentRoom);
    }
/**
 * setter that sets the current room of the player.
 *@param newRoom new room of the player
 */
    public void setCurrentRoom(Room newRoom) {
      currentRoom = newRoom;
    }
/**
 *picks up the item for the player and adds it to the inventory.
 *@param itemToBePickedUp item that is going to be added.
 */
    public void pickUpItem(Item itemToBePickedUp) {
      inventory.add(itemToBePickedUp);
    }
}
