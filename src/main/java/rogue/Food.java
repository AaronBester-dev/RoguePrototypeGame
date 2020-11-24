package rogue;

/**
*Subclass of Item for food items.
*/

public class Food extends Item implements Edible {

/**
*Default constructor for a food Item.
*/

    public Food() {
      super();
    }
/**
*Gets description of edible item.
*@return edible item description
*/
    @Override
    public String eat() {
      return (super.getDescription());
    }

}
