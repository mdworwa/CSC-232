/**
 * @authors Monika Worwa, Giselle Villegas
 */
import java.util.Scanner;

public class Driver {
	
	private static Location myLocation;
	private static ContainerItem myInventory;
	private static boolean run;

	public static void main(String[] args) {
		
		playGame();	
		System.out.println("Welcome to Final Destination");
		
		System.out.println("It's not what you think. The character is trying to prepare for a birthday party. \nThe "
				+ "following is a list of the items you need to collect in the various locations: \ncake, keys, dress, "
				+ "shoes, coat, purse, lipstick, fork, plates, and napkins. \nThe lipstick should be placed into the "
				+ "purse. Everything else should be put into your inventory.\nThe final destination of the game is "
				+ "the car. \nHowever, you can only get to the car once you collect all of the items. \nEnter help when "
				+ "prompted to enter a command to see the commands of the game.");
		
		Scanner s = new Scanner(System.in);
		String userInput = "";
		String[] commands;
		String nameInputted = "";
		String containerInput = "";
	
		while(run){
			System.out.print("Please enter a command: ");
			userInput = s.nextLine();
			userInput = userInput.toLowerCase();
			commands = userInput.split(" ");
			
			switch(commands[0]){
				case "look":{
					if(commands.length == 1){
						if(myLocation.getFullList().size() > 1){
							System.out.println(myLocation.getDescription()+": "+myLocation.locationNames());
						}
						else{
							System.out.println(myLocation.getDescription()+"\n"+"There are no items here.");
						}
					}
					else{
						System.out.println("Pleae only enter 'look'");
					}
					break;
				}
				case "examine":{
					if(commands.length==2 && myLocation.isPresent(commands[1])){
						nameInputted = commands[1];
						for(Item i : myLocation.getFullList()){
							if(i.getName().equals(nameInputted)){
								System.out.println(i.getName()+": "+i.getDescription());
							}
						}
					}	
					else if(commands.length==1){
						System.out.println("The name of item to examine is missing.");
					}
					else if(commands.length>2){
						System.out.println("Please only enter 'examine' and the name of the item to examine.");
					}
					else{
						System.out.println("Item not found.");
					}
					break;
				}
				case "take":{
					if(commands.length==2){
						nameInputted = commands[1];
						if(myLocation.isPresent(nameInputted)){
							Item check = myLocation.removeItem(nameInputted);
							myInventory.addToContainer(check);
							System.out.println(nameInputted+" was added to your inventory");
						}
						else{
							System.out.println("Item does not exist at this location.");
						}
					}
					else if(commands.length==4 && commands[2].equals("from")){
						nameInputted = commands[1];
						containerInput = commands[3];
						if(myLocation.isPresent(containerInput)){
							ContainerItem check = (ContainerItem) myLocation.retrieve(containerInput);
							if(check.whether(nameInputted)){
								Item temp = check.removeItemByName(nameInputted);
								myInventory.addToContainer(temp);
								myLocation.remove(temp);
								System.out.println(nameInputted+" was added to your inventory");
							}
							else{
								System.out.println("The item you are looking for is not in the container");
							}
						}
						else{
							System.out.println("Container does not exist at this location.");
						}
					}
					else if(commands.length>4){
						System.out.println("Please enter 'take' and the item you want to take or 'take' name of item 'from' container name.");
					}
					else{
						System.out.println("Take what?");
					}
					break;
				}
				case "drop":{
					if(commands.length==2){
						nameInputted = commands[1];
						if(myInventory.whether(nameInputted)){
							Item temp = myInventory.removeItemByName(nameInputted);
							myLocation.addItem(temp);
							System.out.println(nameInputted+" is now available in the "+myLocation.getName());
						}
						else{
							System.out.println("Item not in inventory.");
						}
					}
					else if(commands.length>2){
						System.out.println("Please enter only 'drop'");
					}
					else{
						System.out.println("Name of item to drop is missing.");
					}
					break;
				}
				case "put":{
					if(commands.length == 4 && commands[2].equals("in")){
						nameInputted = commands[1];
						containerInput = commands[3];
						if(myInventory.whether(nameInputted)){
							if(myLocation.isPresent(containerInput)){
								Item one = myInventory.removeItemByName(nameInputted);
								ContainerItem two = (ContainerItem) myLocation.retrieve(containerInput);
								two.addToContainer(one);
								myInventory.removeItemByName(nameInputted);
								System.out.println(nameInputted+" is now in "+containerInput+".");
							}
							else{
								System.out.println("The container you are looking for is not in your location.");
							}
						}
						else{
							System.out.println("The item you are looking for is not in your inventory.");
						}
					}
					else{
						System.out.println("Please enter 'put' item name 'in' container name");
					}
					break;
				}
				case "inventory":{
					if(myInventory.getListOfItems().size()>0){
						System.out.println(myInventory.getDescription());
					}
					else{
						System.out.println("There is nothing in your inventory.");
					}
					break;
				}
				case "go":{
					if(commands.length == 2){
						if(myLocation.moveTo(commands[1]) != null){
							myLocation = myLocation.moveTo(commands[1]);
							System.out.println("You are now in the "+myLocation.getName()+".");
						}
						else{
							System.out.println("I'm sorry, you can't go there.");
						}
					}
					else if(commands.length > 2){
						System.out.println("Please enter 'go' north, east, west, or south.");
					}
					else{
						System.out.println("The direction in which you want to go is missing.");
					}
					break;
				}
				case "help":{
					help();
					break;
				}
				case "quit":{
					System.out.println("Game Over");
					s.close();
					run = false;
					System.exit(0);
					break;
				}
				default:{
					System.out.println("I'm sorry, I don't know how to do that.");
					break;
				}
			};
			/*After we know how to do 'hidden items' then we will come up with a code to track our inventory and to 
			 * make sure that the items needed are in it and now just any items to meet the number. */
		}
	}
	public static void help(){
		System.out.println("The commands to this game are listed below: "
			+ "\n look: lists the items in your current location"
			+"\n examine: examines item(s)"
			+"\n take: takes item from current location and adds it to your inventory"
			+
			"\n drop: drops item into current location"
			+"\n put: takes item from inventory and puts it into a container in current location"
			+"\n inventory: lists the items in your inventory"
			+"\n go: moves to a different location based on the direction given"
			+"\n help: lists the commands of the game"
			+"\n quit: quits the game");
	}
	
	public static void playGame(){
		Location garage = new Location("garage", "List of items in garage");
		Location kitchen = new Location("kitchen", "List of items in kitchen");
		Location bathroom = new Location("bathroom", "List of items in bathroom");
		Location bedroom = new Location("bedroom", "List of items in bedroom");
		Location livingroom = new Location("livingroom", "List of items in livingroom");
		//Car will be the place that will need to be unlocked after all items needed are in the player's inventory
		Location car = new Location("car", "final destination");
		
		kitchen.addItem(new Item("forks", "utensil", "eating utensil"));
		kitchen.addItem(new Item("plates", "utensil", "eating utensil"));
		kitchen.addItem(new Item("apples", "edible", "fruit" ));
		//Keys will be used to unlock the car after entering the garage
		kitchen.addItem(new Item("keys", "brass", "to unlock house and car"));
		kitchen.addItem(new Item("napkins", "paper", "for birthday party"));
		bedroom.addItem(new Item("necklace", "gold", "casual necklace"));
		bedroom.addItem(new Item("bed", "furniture", "where owner sleeps"));
		bathroom.addItem(new Item("sink", "white sink", "old-fashioned"));
		bathroom.addItem(new Item("perfume", "polo", "perfect for going out"));
		garage.addItem(new Item("bike", "bmx", "perfect for teenage boys"));
		livingroom.addItem(new Item("couch", "sectional", "comfortable"));
		livingroom.addItem(new Item("tv", "samsung", "55 inch flatscreen"));
		
		ContainerItem fridge = new ContainerItem("fridge", "machine", "stores food that is meant to be cold");
		ContainerItem dresser = new ContainerItem("dresser", "furniture", "stores clothes");
		ContainerItem wardrobe = new ContainerItem("wardrobe", "wooden", "stores items in bedroom");
		ContainerItem mirror = new ContainerItem("mirror", "glass", "can store items behind it when you open it");
		ContainerItem purse = new ContainerItem("purse", "big", "great for storing items");
		ContainerItem closet = new ContainerItem("closet", "walk-in", "stores item in livingroom");
		
		kitchen.addItem(fridge);
		bedroom.addItem(dresser);
		bedroom.addItem(wardrobe);
		bathroom.addItem(mirror);
		livingroom.addItem(closet);
		livingroom.addItem(purse);
			
		fridge.addToContainer(new Item("cake", "edible", "for birthday party"));
		dresser.addToContainer(new Item("pants", "clothing", "casual"));
		dresser.addToContainer(new Item("top", "clothing", "casual"));
		wardrobe.addToContainer(new Item("shoes", "heels", "nice for special occasions"));
		wardrobe.addToContainer(new Item("dress", "clothing", "nice for special occasions"));
		mirror.addToContainer(new Item("lipstick","matte","red"));
		closet.addToContainer(new Item("coat", "clothing", "for cold weather"));
		
		garage.connect("east", car);
		car.connect("west", garage);
		garage.connect("south",kitchen);
		kitchen.connect("north", garage);
		livingroom.connect("north", kitchen);
		kitchen.connect("south", livingroom);
		kitchen.connect("east", bedroom);
		bedroom.connect("west", kitchen);
		bedroom.connect("south", bathroom);
		bathroom.connect("north", bedroom);
		bathroom.connect("west", livingroom);
		livingroom.connect("east", bathroom);
		
		myLocation = bedroom;
		myInventory = new ContainerItem("inventory", "container", "bag of needed items" );
			
		run = true;
	}
}
