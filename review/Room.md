| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|getWidth|Gets the width of a room.||||3
|setWidth|Sets the width of a room.||||3
|getHeight|Gets the height of a room.||||3
|setHeight|Sets the height of a room.||||3
|getId|Gets the id of a room.||||3
|setId|Sets the id of a room.||||3
|getRoomItems|Gets a arraylist of every item in the room.||||3
|setRoomItems|Sets a arraylist of every item in the room.||||3
|getRoomDisplayArray|Gets a 2d arraylist containing what object is in each x and y coordinate in the room.||||3
|setRoomDisplayArray|Sets a 2d arraylist containing what object is in each x and y coordinate in the room.||||3
|setRogue|Sets the rogue object of a room.||||3
|getRogue|Gets the rogue object of a room.||||3
|getPlayer|Gets the player object of a room.||||3
|setPlayer|Sets the player object of a room.||||3
|getDoor|Gets the door object associated with the given direction in a room.||getDoors|String direction|7
|setDoor|Sets the door object associated with the given direction in a room.||getDoors|String direction, Door newDoor|7
|getDoors|Gets the entire door hashmap of a room.||||3
|isPlayerInRoom|Checks if the player is in the room or not.||||3
|addItem|Adds item to the room if its in a valid position and if the item exists in the main item list.|int itemX int itemY ArrayList<Item> rogueItems int itemFound|getId for Item class, get XYLocation for Item class, getItems for Rogue class, add for ArrayList|Item toAdd|20
|verifyRoom|Checks if the room is a valid room or not.||verifyDoorConnections, verifyRoomItems, verifyPlayerLocation, getDoors||20
|verifyDoorConnections|Checks if the rooms doors all have a valid connection.|String key, Door doorHolder|verifyDoorConnections, verifyRoomItems, verifyPlayerLocation, getDoors, getConnectedRooms for door class, getOtherRoom for door class|key Room this|10
|verifyRoomItems|Checks if the items in the room are all in a valid location.|Item singleItem, int itemX, int itemY|getHeight, getWidth, getXYLocation for Item class||13
|verifyPlayerLocation|Checks if the player in the room is in a valid location.|int playerX, int playerY|isPlayerInRoom, getHeight, getWidth getXYLocation for player class ||13
|updateDisplayRoom|Updates the roomDisplayArray when changes are made to the room|ArrayList roomDisplayArray |initializeRoomDisplayArray, addDoorsToRoomDisplayArray, addContentsToRoomDisplayArray||6
|displayRoom|Creates a string represenation of the current room|ArrayList roomDisplayArray, String roomDisplayString |initializeRoomDisplayArray, addDoorsToRoomDisplayArray, addContentsToRoomDisplayArray,convertDisplayArrayToString, getWidth, getHeight|roomDisplayString|9
|initializeRoomDisplayArray|Initializes the roomDisplayArray to a empty room as tall as the current height of the room and as wide as the current width of the room.|ArrayList roomDisplayArray, String roomDisplayString |initializeRoomDisplayArray, addDoorsToRoomDisplayArray, addContentsToRoomDisplayArray,convertDisplayArrayToString, getWidth, getHeight, getRoomDisplayArray|roomDisplayString|18
|addDoorsToRoomDisplayArray|Adds doors to the roomDisplayArray.|Door doorHolder|getDoor, getWidth, getHeight, getRoomDisplayArray, getWallPosition for Door class||18
|addContentsToRoomDisplayArray|Adds player and items to the roomDisplayArray.|int playerX, int playerY|getPlayer, getXYLocation for Player and Item class,isPlayerInRoom, getRoomItems, getRoomDisplayArray||10
|convertDisplayArrayToString|Converts the roomDisplayArray to a single string.|int x, int y, int itemId, Item singleItem|getWidth, getHeight, getRoomItems, getRoomDisplayArray, getId for item class||20



