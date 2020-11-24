package rogue;

/**
*Subclass of Item for clothing items.
*/

public class Clothing extends Item implements Wearable {
/**
*Default constructor for a clothing Item.
*/

    public Clothing() {
      super();
    }
/**
*Gets description of wearable item.
*@return wearable item description
*/
    @Override
    public String wear() {
      return (super.getDescription());
    }

}
