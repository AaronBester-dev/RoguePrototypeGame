package rogue;

public class Food extends Item implements Edible{

    public Food(){
      super();
    }

    @Override
    public String eat(){
      return(super.getDescription());
    }

}