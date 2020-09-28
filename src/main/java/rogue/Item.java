package rogue;
import java.awt.Point;

/**
 * A basic Item class; basic functionality for both consumables and equipment
 */
public class Item  {

    private int itemId;
    private String itemName;
    private String itemType;
    private Character itemCharacter;
    private String itemDescription;
    private Point itemLocation = new Point();


    //Constructors
    public Item() {
        
    }

    public Item(int id, String name, String type, Point xyLocation) {

    }
    
    // Getters and setters


    public int getId() {
        return(itemId);
       
    }


    public void setId(int id) {
        itemId = id;
    }


    public String getName() {
        return(itemName);
    }


    public void setName(String name) {
        itemName = name;
    }


    public String getType() {
        return(itemType);

    }


    public void setType(String type) {
        itemType = type;
    }
    

    public Character getDisplayCharacter() {
        return(itemCharacter);
        
    }


    public void setDisplayCharacter(Character newDisplayCharacter) {
        itemCharacter = newDisplayCharacter;
    }


    public String getDescription() {
        return(itemDescription);
     
    }


    public void setDescription(String newDescription) {
        itemDescription = newDescription;
    }


    public Point getXyLocation() {
        return(itemLocation);
     
    }

    
    public void setXyLocation(Point newXyLocation) {
        itemLocation = newXyLocation;
    }


    public Room getCurrentRoom() {
        return null;
        
    }


    public void setCurrentRoom(Room newCurrentRoom) {
        
    }
}
