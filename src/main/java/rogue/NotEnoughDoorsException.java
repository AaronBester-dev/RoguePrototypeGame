package rogue;

/**
*exception for when a room doesn't have enough doors.
*/
public class NotEnoughDoorsException extends Exception {

/**
*exception for when a room doesn't have enough doors.
*/
    public NotEnoughDoorsException() {
      super("No door found in room.");
    }
}
