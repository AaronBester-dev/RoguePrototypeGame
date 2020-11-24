package rogue;
/**
*Subclass of Food for SmallFood items.
*/
public class SmallFood extends Food implements Tossable {
/**
*Default constructor for a SmallFood Item.
*/
    public SmallFood() {
      super();
    }
/**
*Gets description of smallfood when it is tossed.
*@return smallfood item toss description
*/
    @Override
    public String toss() {
      return (super.getDescription());
    }

}
