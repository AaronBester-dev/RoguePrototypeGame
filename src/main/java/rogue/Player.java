package rogue;
import java.util.ArrayList;
import java.awt.Point;
/**
 * The player character
 */
public class Player {

    private String playerName;
    private Point playerLocation = new Point();

    // Default constructor
    public Player() {

    }


    public Player(String name) {
        this.setName(name);
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
        return null;

    }


    public void setCurrentRoom(Room newRoom) {

    }
}
