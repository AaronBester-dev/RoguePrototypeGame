| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|getMode|Gets the current way that a item will be used.|none|none|none|3
|setMode|Sets the current way that a item will be used.|none|none|none|3
|getCurrentItem|Gets the current item selected by the user.|none|ArrayList<Item>.get()|Integer|3
|addItem|Adds a item to the inventory.|none|ArrayList<Item>.add()|Item|3
|useItem|Uses the current item that is selected in the way designated by the current inventory mode.|none|ArrayList<Item>.isEmpty(), Inventory.eatItem(), Inventory.tossItem(), Inventory.wearItem(), Inventory.getMode()|none|15
|eatItem|Eats the current item that is selected.|Food foodToEat, SmallFood smallFoodToEat, Potion potionToEat|String.equals(),ArrayList<Item>.remove(), Inventory.getCurrentItem(), Item.getType(), Food/Potion/SmallFood.eat() |String, Item|17
|wearItem|Equips the current item that is selected.|String currentItemType, Clothing clothingToWear, Ring ringToWear|String.equals(), ArrayList<Item>.get(), Inventory.getCurrentItem(), Item.getType(), Item.setType(), Item.setName(), Clothing.wear()|String, Integer|15
|unequipItem|Unequips the current item that is selected.|Clothing clothingToTakeOff, Ring ringToTakeOff|String.equals(),Inventory.getCurrentItem(), Item.getType(), Item.setType(), Item.setName(), Clothing.wear()|Integer, String|15
|tossItem|Tosses the current item that is selected.|Potion potionToToss, SmallFood smallFoodToToss|String.equals(), ArrayList<Item>.remove(), Inventory.getCurrentItem(), Item.getType(), SmallFood/Potion.toss()|String, Item|13
|printInventory|Creates a string that displays the current inventory with a arrow showing the currently selected item.|Int i, String inventoryString|ArrayList<Item>.isEmpty(), ArrayList<Item>.size(), ArrayList<Item>.get(), Item.getName()|Integer|16
|printInventoryWithoutSelection|Creates a string that displays the current inventory.|Int i, String inventoryString|ArrayList<Item>.isEmpty(), ArrayList<Item>.size(), ArrayList<Item>.get(), Item.getName()|Integer|11
|moveUpThroughInventory|Changes the current item to the item above the previous item.|none|none|none|5
|moveDownThroughInventory|Changes the current item to the item below the previous item.|none|ArrayList<Item>.size()|none|5