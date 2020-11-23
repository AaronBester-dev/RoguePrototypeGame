
public class Potion extends Magic implements Edible, Tossable{

    public Potion(){
      super();
    }

    @Override
    public String toss(){
      String description = super.getDescription();
      String[] descriptionArray = str.split(":");
      return(descriptionArray[0]);
    }

    @Override
    public String eat(){
      String description = super.getDescription();
      String[] descriptionArray = str.split(":");
      return(descriptionArray[1]);
    }

}