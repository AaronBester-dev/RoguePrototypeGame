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
*@param itemToToss the item that is to be tossed if a item gets tossed.
 */
    public String useItem(Item itemToToss) {
        if (inventory.isEmpty()) {
            return ("Inventory is empty");
        } else {
          Item currentItem = inventory.get(currentItemIndex);
          if (mode == 'e') {
            return eatItem(currentItem);
          } else if (mode == 't') {
            itemToToss = currentItem;
            return tossItem(itemToToss);
          } else if (mode == 'w') {
            return wearItem(currentItem);
          }
        }
        return ("Inventory is empty");
    }
/**
*Checks if the item can be eaten and eats it.
*@return the description of the item being eaten.
*@param currentItem the item that is currently selected by the user
 */
    private String eatItem(Item currentItem) {
        if (currentItem.getType().equals("Food")) {
          Food foodToEat = (Food) currentItem;
          inventory.remove(currentItem);
          return (foodToEat.eat());
        } else if (currentItem.getType().equals("SmallFood")) {
          SmallFood smallFoodToEat = (SmallFood) currentItem;
          inventory.remove(currentItem);
          return (smallFoodToEat.eat());
        } else if (currentItem.getType().equals("Potion")) {
          Potion potionToEat = (Potion) currentItem;
          inventory.remove(currentItem);
          return (potionToEat.eat());
        } else {
          return ("Item is not edible.");
        }
    }
/**
*Checks if the item can be worn and equips it or unequips it if its already worn.
*@return the description of the item being worn.
*@param currentItem the item that is currently selected by the user
 */
    private String wearItem(Item currentItem) {
        String currentItemType = currentItem.getType();
         if (currentItem.getType().equals("Clothing")) {
            Clothing clothingToWear = (Clothing) currentItem;
            inventory.get(currentItemIndex).setType(currentItemType + " (Equipped)");
            inventory.get(currentItemIndex).setName(currentItem.getName() + " (Equipped)");
            return (clothingToWear.wear());
          } else if (currentItem.getType().equals("Ring")) {
            Ring ringToWear = (Ring) currentItem;
            inventory.get(currentItemIndex).setType(currentItemType + " (Equipped)");
            inventory.get(currentItemIndex).setName(currentItem.getName() + " (Equipped)");
            return (ringToWear.wear());
          }
          return unequipItem(currentItem);
    }

    private String unequipItem(Item currentItem) {
      if (currentItem.getType().equals("Ring (Equipped)")) {
        Ring ringToTakeOff = (Ring) currentItem;
        inventory.get(currentItemIndex).setType("Ring");
        inventory.get(currentItemIndex).setName(currentItem.getName().replace(" (Equipped)", ""));
        return (ringToTakeOff.wear());
      } else if (currentItem.getType().equals("Clothing (Equipped)")) {
        Clothing clothingToTakeOff = (Clothing) currentItem;
        inventory.get(currentItemIndex).setType("Clothing");
        inventory.get(currentItemIndex).setName(currentItem.getName().replace(" (Equipped)", ""));
        return (clothingToTakeOff.wear());
      } else {
        return ("Item can not be equipped");
      }
    }
/**
*Checks if the item can be tossed and tosses it.
*@return the description of the item being tossed.
*@param currentItem the item that is currently selected by the user
 */
    private String tossItem(Item currentItem) {
      if (currentItem.getType().equals("SmallFood")) {
            SmallFood foodToToss = (SmallFood) currentItem;
            inventory.remove(currentItem);
            return (foodToToss.toss());
          } else if (currentItem.getType().equals("Potion")) {
            Potion potionToToss = (Potion) currentItem;
            inventory.remove(currentItem);
            return (potionToToss.toss());
          } else {
            return ("Item is not tossable.");
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
                inventoryString += inventory.get(i).getName() + "                              \n";
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
