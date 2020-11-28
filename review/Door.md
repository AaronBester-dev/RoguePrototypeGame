| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|getWallPosition|Gets what tile of the wall contains the door.||||3
|setWallPosition|Sets what tile of the wall contains the door.||||3
|getOtherRoomid|Gets the id of the room that is attached to the door.||||3
|setOtherRoomid|Sets the id of the room that is attached to the door.||||3
|connectRoom|Connects the door to another room.||add from Arraylist class.|Room r|3
|getConnectedRoom|Gets the arraylist of rooms that are attached to the door.||||3
|getOtherRoom|Gets the room attached to the door that is not the current room.|int currentRoomIndex|getConnectedRooms|Room currentRoom|8