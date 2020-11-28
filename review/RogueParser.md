| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|nextRoom|Gets the next room from a JSonArray.||hasNext, next||7
|nextItem|Gets the next item from a JSonArray.||hasNext, next||7
|getSymbols|Gets the symbolmap from a parser.||||3
|getNumOfItems|Gets the number of items from a parser.||||3
|getNumOfRooms|Gets the number of rooms from a parser.||||3
|parse|Gets all of the required files and all room and symbols data from said files.|JSONParser parser, JSONObject roomsJSON, JSONObject symbolsJSON, Object obj, JSONObject configurationJSON, String roomsFileLocation, Object roomsObj, Object symbolsObj|parse,extractSymbolsInfo,iterator,get,extractRoomInfo, extractItemInfo|JSONObject|20
|parseWithoutGettingRoomFile|Gets all of the required files and all room and symbols data from said files but is given a room file rather than extracting it.|JSONParser parser, JSONObject roomsJSON, JSONObject symbolsJSON, Object obj, JSONObject configurationJSON, String roomsFileLocation, Object roomsObj, Object symbolsObj|parse,extractSymbolsInfo,iterator,get,extractRoomInfo, extractItemInfo|JSONObject|20
|extractSymbolInfo|Gets all information from a symbol file.|JSONArray symbolsJSONArray, int i, JSONObject symbolObj|size, get, put, toString, valueOf, charAt|String, Character, Integer|9
|extractRoomInfo|Gets all information from a room file.|JSONArray roomsJSONArray, int i|get, size, add|Room, Integer|9
|singleRoom|Get a single room's information.|HashMap<String, String> room, JSONArray doorArray, int j, JSONObject doorObj, String dir, String conRoomAndWallPos, JSONArray lootArray|put, get, toString, size, valueOf, replace|String, Integer|19
|itemPosition|Create a map for information about an item in a room.|HashMap<String, String> loot|put, get, toString|String|10
|extractItemInfo|Get the Item information from the Item key.|JSONArray itemsJSONArray, int i|get, add, size, singleItem|String, JSONObject|9
|singleItem|Get a single item from its JSON object.|HashMap<String, String> item|put, get, toString, equals|String|16
