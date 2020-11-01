package rogue;
public class NoSuchItemException extends Exception{
    public NoSuchItemException(){
        super("Sorry Item can't be found.");
    }
}