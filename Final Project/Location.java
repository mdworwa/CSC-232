import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    private String name;
    private String description;
    private ArrayList<Item> items;
    private HashMap<String, Location> direction;

    public Location(String pName, String pDescription){
        name = pName;
        description = pDescription;
        items = new ArrayList<Item>();
        direction = new HashMap<String, Location>();
    }

    public String getName(){
        return name;	
    }

    public String getDescription(){
        return description;
    }

    public ArrayList<Item> getFullList(){
        return items;
    }

    public String locationNames(){
        String itemNames = "";
        for(Item i : items){
            itemNames += i.getName()+"  ";
        }
        return itemNames;
    }

    public void setName(String pName){
        name = pName;
    }

    public void setDescription(String pDescription){
        description = pDescription;
    }

    public void addItem(Item l){
        items.add(l);
    }

    public boolean isPresent(String aName){
        for(Item l : items){
            if(l.getName().equals(aName)){
                return true;
            }
        }
        return false;
    }

    public Item retrieve(String aName){
        for(Item l : items){
            if(l.getName().equals(aName)){
                return l;
            }
        }
        return null;
    }

    public int numOfLocations(){
        return items.size();
    }

    public void remove(Item i){
        items.remove(i);
    }

    public Item removeItem(String aName){
        for(Item l : items){
            if(l.getName().equals(aName)){
                Item temp = l;
                items.remove(l);
                return temp;
            }
        }
        return null;
    }

    public void connect(String wayToGo, Location location){
        direction.put(wayToGo, location);
    }

    public Location moveTo(String wayToGo){
        if(direction.containsKey(wayToGo)){
            return direction.get(wayToGo);
        }
        return null;
    }
}
