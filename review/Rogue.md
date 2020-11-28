| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|setPlayer|Sets the current player of the rogue game.||||3
|getPlayer|Gets the current player of the rogue game.||||3
|setSymbols|Sets the current symbolmap of the rogue game.||||3
|getRooms|Gets a arraylist of every room in the rogue game.||||3
|getItems|Gets a arraylist of every item in the rogue game.||||3
|getNextDisplay|Gets the string of the next screen to display.||||3
|getInventoryString|Gets a string that displays the current inventory.||||3
|convertStringToSymbols|Takes a display string and replaces every string with the correct symbol for the string.|String key|keySet, replaceAll, trim, get, toString|String key|4
|addRoom|Creates and adds a room to the room arraylist.|Room newRoom, Integer integerId, Boolean isPlayer, Integer integerHeight, Integer integerWidth|decode, get, parseBoolean, toString, booleanValue, setPlayer, setCurrentRoom, setId, setWidth, setHeight, setDoor, addDoor, setRogue, updateDisplayRoom, getRooms, add|String, Room, Integer, Player|20
|addItem|Creates and adds a item to the item arraylist.|Item newItem, Integer itemId, Room roomToAddTo, Integer roomId|createItemSubclass, get, decode, setId, setName, setType, setDescription, getRooms, getId, addItemToRoom, updateDisplayRoom, putItemInRoomInCorrectPosition|String, Room, Integer|20
|createItemSubclass|Creates a item subclass based off of the given item's type.||get, equals|String|15
|addItemToItemList|Adds the item to the item list in rogue.|int itemX, int itemY, Point newItemLocation|decode, get, setXYLocation, add|Point, String ,Item|11
|putItemInRoomInCorrectPosition|Adds the item to the given room and ensures the item is placed in the correct position.|int x, int y, boolean isItemInCorrectLocation|addItem, getWidth, setXYLocation, getRoomItems, remove|Item|20
|addDoor|Creates and adds a door to the given room.|String[] doorStringArray, int doorConnectedRoomId, int wallPosition|equals, split, decode|String|15
|connectDoors|Connects every door to a room that isn't already connected.|Door doorHolder, Room tempRoom, String key, Room singleRoom|getRooms, getDoors, keySet, getDoor, getOtherRoomid, getId, getConnectedRooms, size, connectRoom|Room, String|16
|openInventoryPanel|Displays the current inventory to the user.||setMode, printInventory|Character|4
|moveThroughInventoryPanel|Changes the currently selected item to the one above or below the current item depending on the user input.||moveUpThroughInventory, moveDownThroughInventory, printInventory||8
|useCurrentItem|Uses the current item and displays the inventory after using the item.|String message, Item itemToToss|getMode, getCurrentItem, useItem, tossItemInRoom, getCurrentRoom, displayRoom, convertStringToSymbols, printInventoryWithoutSelection|Item|14
|tossItemInRoom|If a item is tossed it places the tossed item in the current room in front of the player.|int x, int y, Point itemXYLocation|getXyLocation, getX, setCurrentRoom, getCurrentRoom, putItemInRoomInCorrectPosition|Room,Item,Point|8
|makeMove|Moves the player and picks up item based on where the user wants to go.|int playerX, int playerY, Point wherePlayerWantsToGo, String moveMessage, String returnValue |getXyLocation, getX, getY, checkInput, hasPlayerGoneOutOfBounds, whatHappenedWhenIMoved, equals, getCurrentRoom, displayRoom, convertStringToSymbols|Character, Point|20
|hasPlayerGoneOutOfBounds|Checks if the user has accidentally moved out of bounds.|int x, int y|getX, getY, getCurrentRoom, getWidth, getHeight||12
|checkInput|Changes the x and y location the player is moving to based off of the user's input.|int playerX, int playerY|getXyLocation, getX, getY, setLocation|Integer|19
|whatHappenedWhenIMoved|Checks what the player is bumping into and either does nothing, picks up a item if it lands on a item, or moves to another room if it lands on a door.|Room currentRoom String[][] currentRoomDisplayArray, String collisionObject, Item itemToBePickedUp, Door doorToWalkthrough|movePlayer, getCurrentRoom, getRoomDisplayArray,whatDidICollideWith,getItemFromRoom,addItem,printInventoryWithoutSelection,movePlayer, getType, getDoor, valueOf, charAt, movePlayerToOtherRoom|String[][], Point, Player, Item, Character, Integer|20
|whatDidICollideWith|Checks what type of object the player is going to land on.|int newPlayerX, int newPlayerY, String collisionObject|getX, getY, equals, isDigit, charAt|String, Character, Integer|13
|getItemFromRoom|Takes item from room and removes it.|int itemX, int itemY, String[][] currentRoomDisplayArray, int itemId, Item returnedItem, Item singleItem|getX, getY, getRoomDisplayArray, decode, getRoomItems, getId, remove|String, Item|14
|movePlayer|Moves the player to the new xyLocation.||setXyLocation|Point|3
|movePlayerToOtherRoom|Moves the player to another room.|Room currentRoom, Room otherRoom, Door otherDoor, Point newXyLocation|getCurrentRoom, getOtherRoom, setPlayer, setCurrentRoom, equals, getDoor, setLocation, getWallPosition, getHeight, getDoor, movePlayer|Room, Player, String, Point|20
|checkRooms|Checks every single room in the room array to see if its valid and removes it if its not.|ArrayList<Room> badRoomList, Room singleRoom|verifyRoom, add, notEnoughDoorsExceptionFix, setCurrentRoom, connectDoors, removeRooms|Room, ArrayList<Room>|17
|removeRooms|Removes all rooms that are invalid.|int i|size, contains, get, remove, isPlayerInRoom, setCurrentRoom |Room, Integer|10
|notEnoughDoorsExceptionFix|Adds doors to a room if it contains no doors.|String keyOfDoorToConnectTo, Room roomToConnectDoorTo, Room singleRoom, String key|size, getDoors, keySet, getDoor, makeAndAddNewDoor|String, Room|19
|makeAndAddNewDoor|Makes a new door in the room that has no doors and a room that has a empty wall.||equals, setDoor, getId|String|20

