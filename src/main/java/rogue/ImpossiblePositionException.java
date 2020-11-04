package rogue;

/**
*exception for when a item is in a impossible location.
*/


public class ImpossiblePositionException extends Exception {

/**
*exception for when for when a item is in a impossible location.
*/


    public ImpossiblePositionException() {
        super("Sorry Position can't be found.");
    }
}
