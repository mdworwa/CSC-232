import java.util.ArrayList;

public class ContainerItem extends Item {
    private ArrayList<Item> container;

    public ContainerItem(String pName, String pType, String pDescription) {
        super(pName, pType, pDescription);
        container = new ArrayList<Item>();
    }

    public String getDescription(){
        return super.getDescription() + ": " + getListByNames();
    }

    public ArrayList<Item> getListOfItems(){
        return container;
    }

    public String getListByNames(){
        String itemNames = "";
        for(Item i : container){
            itemNames += i.getName()+"  ";
        }
        return itemNames;
    }

    public void addToContainer(Item i){	
        container.add(i);
    }

    public Item removeItemByName(String aName){
        for(Item l : container){
            if(l.getName().equals(aName)){
                Item temp = l;
                container.remove(l);
                return temp;
            }
        }
        return null;
    }

    public Item removeItemByIndex(int index){
        if(index <= container.size()){
            Item temp = container.get(index);
            container.remove(index);
            return temp;
        }
        return null;
    }

    public int getCount(){
        return container.size();
    }

    public boolean whether(String aName){
        for(Item l : container){
            if(l.getName().equals(aName)){
                return true;
            }
        }
        return false;
    }

    public Item getItem(String aName){
        for(Item l : container){
            if(l.getName().equals(aName)){
                return l;
            }
        }
        return null;
    }
}
