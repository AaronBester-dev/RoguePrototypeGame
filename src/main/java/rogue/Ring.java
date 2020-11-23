package rogue;

public class Ring extends Magic implements Wearable{

    public Ring(){
      super();
    }

    @Override
    public String wear(){
      return(super.getDescription());
    }
}