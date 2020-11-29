| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|nextRoom|Gets the next room from a JSonArray.|none|Iterator.hasNext(), Iterator.next()|none|7
|nextItem|Gets the next item from a JSonArray.|none|Iterator.hasNext(), Iterator.next()|none|7
|getSymbols|Gets the symbolmap from a parser.|none|none|none|3
|getNumOfItems|Gets the number of items from a parser.||||3
|getNumOfRooms|Gets the number of rooms from a parser.||||3
|parse|Gets all of the required files and all room and symbols data from said files.|JSONParser parser, JSONObject roomsJSON, JSONObject symbolsJSON, Object obj, JSONObject configurationJSON, String roomsFileLocation, Object roomsObj, Object symbolsObj|RogueParser.parse(),RogueParser.extractSymbolsInfo(),ArrayList.iterator(),JSONObject.get(),RogueParser.extractRoomInfo(), RogueParser.extractItemInfo()|String, JSONObject|20
|parseWithoutGettingRoomFile|Gets all of the required files and all room and symbols data from said files but is given a room file rather than extracting it.|JSONParser parser, JSONObject roomsJSON, JSONObject symbolsJSON, Object obj, JSONObject configurationJSON, String roomsFileLocation, Object roomsObj, Object symbolsObj|RogueParser.parse(),RogueParser.extractSymbolsInfo(),ArrayList.iterator(),JSONObject.get(),RogueParser.extractRoomInfo(), RogueParser.extractItemInfo()|JSONObject, String|20
|extractSymbolInfo|Gets all information from a symbol file.|JSONArray symbolsJSONArray, int i, JSONObject symbolObj|ArrayList.size(), JSONObject.get(), HashMap.put(), Character.toString(), String.valueOf(), String.charAt()|String, Character, Integer|9
|extractRoomInfo|Gets all information from a room file.|JSONArray roomsJSONArray, int i|ArrayList.get(), ArrayList.size(), ArrayList.add()|Room, Integer, String|9
|singleRoom|Get a single room's information.|HashMap<String, String> room, JSONArray doorArray, int j, JSONObject doorObj, String dir, String conRoomAndWallPos, JSONArray lootArray|HashMap.put(), JSONObject.get(), Integer.toString(), ArrayList.size(), String.valueOf(), HashMap.replace()|String, Integer|19
|itemPosition|Create a map for information about an item in a room.|HashMap<String, String> loot|HashMap.put(), JSONObject.get(), Integer.toString()|String|10
|extractItemInfo|Get the Item information from the Item key.|JSONArray itemsJSONArray, int i|JSONObject.get(), ArrayList.add(), ArrayList.size(), RogueParser.singleItem()|String, JSONObject, Map<String, String>|9
|singleItem|Get a single item from its JSON object.|HashMap<String, String> item|HashMap.put(), JSONObject.get(), Integer.toString(), String.equals()|String|16
