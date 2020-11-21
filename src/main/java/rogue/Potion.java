
public class Potion extends Magic implements Edible, Tossable{

    public Potion(){
        super();
    }

    @Override
    public String toss(){
        String description = super.getDescription();
        
        return(super.getDescription());
    }

    @Override
    public String eat(){

    }

}