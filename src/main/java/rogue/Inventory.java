
public class Inventory{

    private char mode;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int currentItemIndex;

    public Inventory() {
        mode = "";
        currentItemIndex = 0;
    }

    public void setMode(char newMode) {
        mode = newMode;
    }

    public void addItem(Item newItem){
        inventory.add(newItem);
    }

    public String useItem() {
        Item currentItem = inventory[currentItemIndex];
        if(mode == 'e'){
          return eatItem(currentItem);
        }

        if(mode == 't'){
          return tossItem(currentItem);
        }

        if(mode == 'w'){
          return wearItem(currentItem);
        }
    }

    private String eatItem(Item currentItem) {
        if(currentItem.getType() == "Food") {
          Food foodToEat = (Food) currentItem;
          inventory.remove[currentItem];
          return (foodToEat.eat());
        } else if(currentItem.getType() == "Potion") {
          Potion potionToEat = (Potion) currentItem;
          inventory.remove[currentItem];
          return (potionToEat.eat());
        } else{
          return ("Item is not edible.");
        }
    }

    private String wearItem(Item currentItem) {
        String currentItemType = currentItem.getType();
         if(currentItem.getType() == "Clothing") {
            Clothing clothingToWear = (Clothing) currentItem;
            inventory.get[currentItem].setType(currentItemType + " (Equipped)");
            return(clothingToWear.wear());
          } else if(currentItem.getType() == "Ring") {
            Ring ringToWear = (Ring) currentItem;
            inventory.get[currentItem].setType(currentItemType + " (Equipped)");
            return(ringToWear.wear());
          } else if(currentItem.getType() == "Ring (Equipped)") {
            Ring ringToTakeOff = (Ring) currentItem;
            inventory.get[currentItem].setType("Ring");
            return(ringToTakeOff.wear());
          } else if(currentItem.getType() == "Clothing (Equipped)") {
            Clothing clothingToTakeOff = (Ring) currentItem;
            inventory.get[currentItem].setType("Clothing");
            return(clothingToTakeOff.wear());
          } else{
            return("Item is not wearable.");
          }
    }

    private String tossItem(Item currentItem) {
      if(currentItem.getType() == "SmallFood") {
            SmallFood foodToToss = (SmallFood) currentItem;
            inventory.remove[currentItem];
            return(foodToToss.toss());
          } else if(currentItem.getType() == "Potion") {
            Potion potionToToss = (Potion) currentItem;
            inventory.remove[currentItem];
            return(potionToToss.toss());
          } else{
            return("Item is not tossable.");
          }
    }

    public String printInventory(){
        int i = 0;
        String inventoryString = "";
        for(i = 0; i < inventory.size(); i++){
            if(i == currentItemIndex){
                inventoryString += inventory[i].getType() + "<--\n";
            }
            else{
                inventoryString += inventory[i].getType() + "\n";
            }
        }
        return(inventoryString);
    }

    public void moveUpThroughInventory(){
        if(currentItemIndex - 1 >= 0){
            --currentItemIndex;
        }
    }

    public void moveDownThroughInventory(){
        if(currentItemIndex + 1 < inventory.size()){
            ++currentItemIndex;
        }
    }

}