package rogue;
/**
*Subclass of Magic for Ring items.
*/
public class Ring extends Magic implements Wearable {
/**
*Default constructor for a Ring Item.
*/
    public Ring() {
      super();
    }
/**
*Gets description of ring when it is worn.
*@return ring item wear description
*/
    @Override
    public String wear() {
      return (super.getDescription());
    }
}
