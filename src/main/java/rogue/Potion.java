package rogue;

/**
*Subclass of Magic for Potion items.
*/
public class Potion extends Magic implements Edible, Tossable {
/**
*Default constructor for a magic Item.
*/
    public Potion() {
      super();
    }
/**
*Gets description of potion when it is tossed.
*@return potion item toss description
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
*Gets description of potion when it is eaten.
*@return potion item eat description
*/
    @Override
    public String eat() {
      String description = super.getDescription();
      String[] descriptionArray = description.split(":");
      return (descriptionArray[0]);
    }

}
