| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|getMode|Gets the current way that a item will be used.||||3
|setMode|Sets the current way that a item will be used.||||3
|getCurrentItem|Gets the current item selected by the user.||||3
|addItem|Adds a item to the inventory.||add in arraylist class|newItem|3
|useItem|Uses the current item that is selected in the way designated by the current inventory mode.||eatItem, tossItem, wearItem, getCurrentItem, getMode||15
|eatItem|Eats the current item that is selected.|Food foodToEat, SmallFood smallFoodToEat, Potion potionToEat|getCurrentItem, getType in Item Class, eat from Food/Potion subclasses||17
|wearItem|Equips the current item that is selected.|String currentItemType, Clothing clothingToWear, Ring ringToWear|getCurrentItem, getType in Item Class, setType in Item class, setName in Item class, wear from Clothing subclass|currentItemType, String|15
|wearItem|Unequips the current item that is selected.|Clothing clothingToTakeOff, Ring ringToTakeOff|getCurrentItem, getType in Item Class, setType in Item class, setName in Item class, wear from Clothing subclass|currentItemType, String|15
|tossItem|Tosses the current item that is selected.|Potion potionToToss, SmallFood smallFoodToToss|getCurrentItem, getType in Item Class, toss from SmallFood/Potion subclass||13
|printInventory|Creates a string that displays the current inventory with a arrow showing the currently selected item.|int i, String inventoryString|isEmpty, size, get, getName||16
|printInventoryWithoutSelection|Creates a string that displays the current inventory.|int i, String inventoryString|isEmpty, size, get, getName||11
|moveUpThroughInventory|Changes the current item to the item above the previous item.||||5
|moveDownThroughInventory|Changes the current item to the item below the previous item.||size||5