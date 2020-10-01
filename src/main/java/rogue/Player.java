package rogue;
import java.util.ArrayList;
import java.awt.Point;
/**
 * The player character
 */
public class Player {

    private String playerName;
    private Point playerLocation;
    private Room currentRoom;

    // Default constructor
    public Player() {
        setName("Bingus");
        setXyLocation(playerLocation);
        setCurrentRoom(currentRoom) ;
    }

    public Player(String name, Point newLocation, Room newRoom ){
        setName(name);
        setXyLocation(newLocation);
        setCurrentRoom(newRoom);
    }


    public Player(String name) {
        setName(name);
    }


    public String getName() {
        return(playerName);
    }


    public void setName(String newName) {
        playerName = newName;
    }

    public Point getXyLocation() {
        return(playerLocation);

    }


    public void setXyLocation(Point newXyLocation) {
        playerLocation = newXyLocation;
    }


    public Room getCurrentRoom() {
        return currentRoom;

    }


    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }
}
