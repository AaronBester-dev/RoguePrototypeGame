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
      String description = super.getDescription();
      String[] descriptionArray = description.split(":");
      if (descriptionArray.length > 1) {
        return (descriptionArray[1]);
      }
      return ("You toss the potion.");
    }
/**
*Gets description of smallfood when it is eaten.
*@return smallfood item eat description
*/
    @Override
    public String eat() {
      String description = super.getDescription();
      String[] descriptionArray = description.split(":");
      return (descriptionArray[0]);
    }

}
