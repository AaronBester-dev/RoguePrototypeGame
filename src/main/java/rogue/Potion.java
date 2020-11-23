package rogue;
import java.lang.String;
public class Potion extends Magic implements Edible, Tossable{

    public Potion(){
      super();
    }

    @Override
    public String toss(){
      String description = super.getDescription();
      String[] descriptionArray = description.split(":");
      return(descriptionArray[0]);
    }

    @Override
    public String eat(){
      String description = super.getDescription();
      String[] descriptionArray = description.split(":");
      return(descriptionArray[1]);
    }

}