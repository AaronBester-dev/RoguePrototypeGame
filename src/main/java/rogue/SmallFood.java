
public class SmallFood extends Food implements Tossable{

    public SmallFood(){
      super();
    }

    @Override
    public String toss(){
      return(super.getDescription());
    }

}