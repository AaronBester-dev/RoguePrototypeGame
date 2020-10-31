package rogue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
*A1Solution is the main class that provides a solution to assignment 1.
*
*/
public class A1Solution {

/**
*<p>main method parses the symbol,room,and configuration file.
*main also creates newrogueGame and runs everything it needs to make the program work.
*</p>
*@param args array of command line arguments
*/
    public static void main(String[] args) {
     
      // Hardcoded configuration file location/name
      //please don't change this for this version of the assignment
      String configurationFileLocation = "fileLocations.json";
       /*
      String roomFileLocation = "";
      String symbolFileLocation = "";
 // reading the input file locations using the configuration file
      JSONParser parser = new JSONParser();
      try {
        Object obj = parser.parse(new FileReader(configurationFileLocation));
        JSONObject configurationJSON = (JSONObject) obj;

        // Extract the Rooms value from the file to get the file location for rooms
        roomFileLocation = (String) configurationJSON.get("Rooms");

        // Extract the Symbols value from the file to get the file location for symbols-map
        symbolFileLocation = (String) configurationJSON.get("Symbols");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
  */
// instantiate a new Rogue object and call methods to do the required things
      Rogue rogueGame = new Rogue(configurationFileLocation);
      System.out.println(rogueGame.displayAll());
    }
}
