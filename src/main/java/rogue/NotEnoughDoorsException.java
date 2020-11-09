package rogue;

/**
*exception for when a room doesn't have enough doors.
*/
public class NotEnoughDoorsException extends Exception {

/**
*exception for when a room doesn't have enough doors.
*/
    public NotEnoughDoorsException() {
      super("Sorry not enough doors in room.");
    }
}
