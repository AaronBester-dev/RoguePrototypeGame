package rogue;

/**
*exception for when a item doesn't exist.
*/
public class NoSuchItemException extends Exception {

/**
*exception for when a item doesn't exist.
*/
    public NoSuchItemException() {
      super("Sorry Item is not located in the rogue Item list");
    }
}
