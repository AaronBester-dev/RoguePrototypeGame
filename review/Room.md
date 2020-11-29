| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|getWidth|Gets the width of a room.|none|none|none|3
|setWidth|Sets the width of a room.|none|none|none|3
|getHeight|Gets the height of a room.|none|none|none|3
|setHeight|Sets the height of a room.|none|none|none|3
|getId|Gets the id of a room.|none|none|none|3
|setId|Sets the id of a room.|none|none|none|3
|getRoomItems|Gets a arraylist of every item in the room.|none|none|none|3
|setRoomItems|Sets a arraylist of every item in the room.|none|none|none|3
|getRoomDisplayArray|Gets a 2d arraylist containing what object is in each x and y coordinate in the room.|none|none|none|3
|setRoomDisplayArray|Sets a 2d arraylist containing what object is in each x and y coordinate in the room.|none|none|none|3
|setRogue|Sets the rogue object of a room.|none|none|none|3
|getRogue|Gets the rogue object of a room.|none|none|none|3
|getPlayer|Gets the player object of a room.|none|none|none|3
|setPlayer|Sets the player object of a room.|none|none|none|3
|getDoor|Gets the door object associated with the given direction in a room.|none|Room.getDoors(), HashMap.get(), HashMap.containsKey()|String|7
|setDoor|Sets the door object associated with the given direction in a room.|none|Room.getDoors(), HashMap.containsKey(), HashMap.replace(), HashMap.put()|String, Door|7
|getDoors|Gets the entire door hashmap of a room.|none|none|none|3
|isPlayerInRoom|Checks if the player is in the room or not.|none|none|none|3
|addItem|Adds item to the room if its in a valid position and if the item exists in the main item list.|int itemX, int itemY, ArrayList<Item> rogueItems, int itemFound, Item singleItem|Item.getId(), Item.XyLocation(), Rogue.getItems(), ArrayList<Item>.add() Point.getX(), Point.getY(), Room.getRogue(), Room.getWidth(), Room.getHeight(), String.equals()|Item,String|20
|verifyRoom|Checks if the room is a valid room or not and returns false if it isn't. Also checks for a not enough doors exception as well.|none|Room.verifyDoorConnections(), Room.verifyRoomItems(), Room.verifyPlayerLocation(), Room.getDoors(), HashMap.get()|String|20
|verifyDoorConnections|Checks if the rooms doors all have a valid connection and returns a exception if they don't.|String key, Door doorHolder|Room.getDoors(),HashMap.keySet(),  HashMap.get(), ArrayList<Room>.size(), Door.getConnectedRooms(), Door.getOtherRoom()|String,Room|10
|verifyRoomItems|Checks if the items in the room are all in a valid location.|Item singleItem, int itemX, int itemY|Room.getHeight(), Room.getWidth(), Item.getXYLocation(), Point.getX(), Point.getY()|none|13
|verifyPlayerLocation|Checks if the player in the room is in a valid location.|int playerX, int playerY|Room.isPlayerInRoom(), Room.getHeight(), Room.getWidth(), Player.getXYLocation(), Point.getX(), Point.getY(), Room.getPlayer()|none|13
|updateDisplayRoom|Updates the roomDisplayArray when changes are made to the room|none|Room.initializeRoomDisplayArray(), Room.addDoorsToRoomDisplayArray(), Room.addContentsToRoomDisplayArray()|none|6
|displayRoom|Creates a string represenation of the current room|String roomDisplayString|Room.initializeRoomDisplayArray(), Room.addDoorsToRoomDisplayArray(), Room.addContentsToRoomDisplayArray(), Room.convertDisplayArrayToString(), Room.getWidth(), Room.getHeight()|String|9
|initializeRoomDisplayArray|Initializes the roomDisplayArray to a empty room as tall as the current height of the room and as wide as the current width of the room.|int x, int y|Room.getWidth(), Room.getHeight(), Room.getRoomDisplayArray()|none|18
|addDoorsToRoomDisplayArray|Adds doors to the roomDisplayArray.|Door doorHolder|Room.getDoor(), Room.getWidth(), Room.getHeight(), Room.getRoomDisplayArray(), Door.getWallPosition()|String|18
|addContentsToRoomDisplayArray|Adds player and items to the roomDisplayArray.|int playerX, int playerY, int i|Room.getPlayer(), Player.getXyLocation(), Item.getXyLocation(),Room.isPlayerInRoom(), Point.getX(), Point.getY(), ArrayList<Items>.get(), Integer.toString(), Item.getId(), Room.getRoomItems(), Room.getRoomDisplayArray()|Integer|10
|convertDisplayArrayToString|Converts the roomDisplayArray to a single string.|int x, int y, int itemId, Item singleItem|Room.getWidth(), Room.getHeight(), Room.getRoomItems(), Room.getRoomDisplayArray(), Item.getId(), Character.isLetter(), Integer.decode(), Item.getType(), String.toUpperCase(), String.equals(), String.charAt()|String, Integer|20



