package rogue;
import java.awt.Point;
import org.json.simple.JSONObject;
/**
 * A basic Item class; basic functionality for both consumables and equipment.
 */
public class Item  {

    private int itemId;
    private String itemName;
    private String itemType;
    private Character itemCharacter;
    private String itemDescription;
    private Point itemLocation;
    private Room currentRoom;

/**
*Item is a basic constrictor that sets item to default values.
*/
    public Item() {
      setId(0);
      setName("Empty");
      setType("NOTYPE");
      setXyLocation(new Point(0, 0));
    }
/**
*Item is a basic constrictor that sets item to values based on jsonItem.
*@param jsonItem item json object parsed from the item json file
*/
    public Item(JSONObject jsonItem) {
      Integer id = Integer.decode(jsonItem.get("id").toString());
      Integer x = Integer.decode(jsonItem.get("x").toString());
      Integer y = Integer.decode(jsonItem.get("y").toString());

      setId(id);
      setName("NONAME");
      setType("NOTYPE");
      setXyLocation(new Point(x, y));
    }
/**
*Item is a basic constrictor that sets item to values based on various varibles.
*@param id id of the object
*@param name name of the object
*@param type type of the object
*@param xyLocation xyLocation of the object
*/
    public Item(int id, String name, String type, Point xyLocation) {
      setId(id);
      setName(name);
      setType(type);
      setXyLocation(xyLocation);
    }

    // Getters and setters
/**
*gets id of a item.
*@return the current id of the item
*/
    public int getId() {
      return (itemId);
    }
/**
*sets id of a item.
*@param id the new id of a item
*/
    public void setId(int id) {
      itemId = id;
    }
/**
*gets name of a item.
*@return the current name of the item
*/
    public String getName() {
      return (itemName);
    }
/**
*sets name of a item.
*@param name the new name of a item
*/
    public void setName(String name) {
      itemName = name;
    }
/**
*gets type of a item.
*@return the current type of the item
*/
    public String getType() {
      return (itemType);
    }
/**
*sets type of a item.
*@param type the new type of a item
*/
    public void setType(String type) {
      itemType = type;
    }
/**
*gets display character of a item.
*@return the current display character of the item
*/
    public Character getDisplayCharacter() {
      return (itemCharacter);
    }
/**
*sets display character of a item.
*@param newDisplayCharacter the new display character of a item
*/
    public void setDisplayCharacter(Character newDisplayCharacter) {
      itemCharacter = newDisplayCharacter;
    }
/**
*gets description of a item.
*@return the current description of the item
*/
    public String getDescription() {
      return (itemDescription);
    }
/**
*sets description of a item.
*@param newDescription the new description of the item
*/
    public void setDescription(String newDescription) {
      itemDescription = newDescription;
    }
/**
*gets xy location of a item.
*@return the current xyLocation of the item
*/
    public Point getXyLocation() {
      return (itemLocation);
    }
/**
*sets xy location of a item.
*@param newXyLocation the new xyLocation of the item
*/
    public void setXyLocation(Point newXyLocation) {
      itemLocation = newXyLocation;
    }
/**
*gets room the item is currently in.
*@return the current room of the item
*/
    public Room getCurrentRoom() {
      return (currentRoom);
    }
/**
*sets room the item is currently in.
*@param newCurrentRoom the new room of the item
*/
    public void setCurrentRoom(Room newCurrentRoom) {
      currentRoom = newCurrentRoom;
    }
}
