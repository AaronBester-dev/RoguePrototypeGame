
public class Clothing extends Item implements Wearable{

    public Clothing(){
        super();
    }

    @Override
    public String wear(){
        return(super.getDescription());
    }

}