public class Item {
    private String name;
    private String type;
    private String description;

    public Item(String pName, String pType, String pDescription){
        name = pName;
        type = pType;
        description = pDescription;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getDescription(){
        return description;
    }

    public void setName(String aName){
        name = aName;
    }

    public void setType(String aType){
        type = aType;
    }

    public void setDescription(String aDescription){
        description = aDescription;
    }

    public String toString(){
        return "Item: "+"\n"
        +"Name: "+name+"\n"
        +"Type: "+type+"\n"
        +"Description: "+description;
    }
}
