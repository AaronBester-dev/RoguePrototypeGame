package rogue;
public class ImpossiblePositionException extends Exception{
    public ImpossiblePositionException(){
        super("Sorry Position can't be found.");
    }
}