package rogue;
import java.awt.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  

/**
 * A basic Item class; basic functionality for both consumables and equipment
 */
public class Item  {

    private int itemId;
    private String itemName;
    private String itemType;
    private Character itemCharacter;
    private String itemDescription;
    private Point itemLocation;
    private Room currentRoom;

    //Constructors
    public Item() {
      setId(0);
      setName("Empty");
      setType("NOTYPE");
      setXyLocation(new Point(0,0));
    }

    public Item(JSONObject jsonItem){
      Integer id = Integer.decode(jsonItem.get("id").toString());
      Integer x =   Integer.decode(jsonItem.get("x").toString());
      Integer y =   Integer.decode(jsonItem.get("y").toString());
      
      setId(id);
      setName("NONAME");
      setType("NOTYPE");
      setXyLocation(new Point(x,y));
    }

    public Item(int id, String name, String type, Point xyLocation) {
      setId(id);
      setName(name);
      setType(type);
      setXyLocation(xyLocation);
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
      return currentRoom;    
    }

    public void setCurrentRoom(Room newCurrentRoom) {
      currentRoom = newCurrentRoom;
    }
}
