package rogue;
import java.io.Serializable;
import java.util.ArrayList;

/**
*Class that stores the items the player is holding.
*/

public class Inventory implements Serializable {
    private char mode;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int currentItemIndex;

/**
*Default constructor that makes a empty inventory.
*/

    public Inventory() {
      mode = 'z';
      currentItemIndex = 0;
    }

/**
*Sets whether the item is going to be tossed,worn,or eaten.
*@return current inventory mode.
 */
    public char getMode() {
      return mode;
    }

/**
*Sets whether the item is going to be tossed,worn,or eaten.
*@param newMode the new mode the inventory is set to.
 */
    public void setMode(char newMode) {
      mode = newMode;
    }

    /**
*Gets the currently selected item.
*@return the currentyly selected item.
 */
    public Item getCurrentItem() {
      return inventory.get(currentItemIndex);
    }

/**
*Adds item to the list of items.
*@param newItem item to be added to the inventory.
 */
    public void addItem(Item newItem) {
      inventory.add(newItem);
    }
/**
*Uses the currently selected item.
*@return the description of the item being used
 */
    public String useItem() {
      if (inventory.isEmpty()) {
        return ("Inventory is empty");
      } else {
        if (getMode() == 'e') {
          return eatItem();
        } else if (getMode() == 't') {
          return tossItem();
        } else if (getMode() == 'w') {
          return wearItem();
        }
      }
      return ("Inventory is empty");
    }
/**
*Checks if the item can be eaten and eats it.
*@return the description of the item being eaten.
 */
    private String eatItem() {
      if (getCurrentItem().getType().equals("Food")) {
        Food foodToEat = (Food) getCurrentItem();
        inventory.remove(getCurrentItem());
        return (foodToEat.eat());
      } else if (getCurrentItem().getType().equals("SmallFood")) {
        SmallFood smallFoodToEat = (SmallFood) getCurrentItem();
        inventory.remove(getCurrentItem());
        return (smallFoodToEat.eat());
      } else if (getCurrentItem().getType().equals("Potion")) {
        Potion potionToEat = (Potion) getCurrentItem();
        inventory.remove(getCurrentItem());
        return (potionToEat.eat());
      } else {
        return (getCurrentItem().getName() + " is not edible.");
      }
    }
/**
*Checks if the item can be worn and equips it or unequips it if its already worn.
*@return the description of the item being worn.
 */
    private String wearItem() {
      String currentItemType = getCurrentItem().getType();
      if (getCurrentItem().getType().equals("Clothing")) {
        Clothing clothingToWear = (Clothing) getCurrentItem();
        inventory.get(currentItemIndex).setType(currentItemType + " (Equipped)");
        inventory.get(currentItemIndex).setName(getCurrentItem().getName() + " (Equipped)");
        return (clothingToWear.wear());
      } else if (getCurrentItem().getType().equals("Ring")) {
        Ring ringToWear = (Ring) getCurrentItem();
        inventory.get(currentItemIndex).setType(currentItemType + " (Equipped)");
        inventory.get(currentItemIndex).setName(getCurrentItem().getName() + " (Equipped)");
        return (ringToWear.wear());
      }
      return unequipItem();
    }

    private String unequipItem() {
      if (getCurrentItem().getType().equals("Ring (Equipped)")) {
        Ring ringToTakeOff = (Ring) getCurrentItem();
        inventory.get(currentItemIndex).setType("Ring");
        inventory.get(currentItemIndex).setName(getCurrentItem().getName().replace(" (Equipped)", ""));
        return (ringToTakeOff.wear());
      } else if (getCurrentItem().getType().equals("Clothing (Equipped)")) {
        Clothing clothingToTakeOff = (Clothing) getCurrentItem();
        inventory.get(currentItemIndex).setType("Clothing");
        inventory.get(currentItemIndex).setName(getCurrentItem().getName().replace(" (Equipped)", ""));
        return (clothingToTakeOff.wear());
      } else {
        return (getCurrentItem().getName() + " can not be equipped");
      }
    }
/**
*Checks if the item can be tossed and tosses it.
*@return the description of the item being tossed.
 */
    private String tossItem() {
      if (getCurrentItem().getType().equals("SmallFood")) {
        SmallFood foodToToss = (SmallFood) getCurrentItem();
        inventory.remove(getCurrentItem());
        return (foodToToss.toss());
      } else if (getCurrentItem().getType().equals("Potion")) {
        Potion potionToToss = (Potion) getCurrentItem();
        inventory.remove(getCurrentItem());
        return (potionToToss.toss());
      } else {
        return (getCurrentItem().getName() + " is not tossable.");
      }
    }
/**
*Creates a string representation of the inventory.
*@return the string representation of the inventory.
 */
    public String printInventory() {
      int i = 0;
      String inventoryString = "";
      if (inventory.isEmpty()) {
        return "                    ";
      } else {
        for (i = 0; i < inventory.size(); i++) {
          if (i == currentItemIndex) {
            inventoryString += inventory.get(i).getName() + "<--\n";
          } else {
            inventoryString += inventory.get(i).getName() + "                   \n";
          }
        }
        return (inventoryString);
      }
    }

    /**
*Creates a string representation of the inventory.
*@return the string representation of the inventory.
 */
    public String printInventoryWithoutSelection() {
      int i = 0;
      String inventoryString = "";
      if (inventory.isEmpty()) {
        return "                    ";
      } else {
        for (i = 0; i < inventory.size(); i++) {
          inventoryString += inventory.get(i).getName() + "                              \n";
        }
        return (inventoryString);
      }
    }
/**
*Changes the currently selected item to the one above the currently selected item.
 */
    public void moveUpThroughInventory() {
      if (currentItemIndex - 1 >= 0) {
        --currentItemIndex;
      }
    }
/**
*Changes the currently selected item to the one below the currently selected item.
 */
    public void moveDownThroughInventory() {
      if (currentItemIndex + 1 < inventory.size()) {
        ++currentItemIndex;
      }
    }
}
