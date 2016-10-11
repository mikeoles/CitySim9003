import java.util.*;

public class CitySim9003{
  public static ArrayList<Location> mapLocations;
  public static ArrayList<Location> outsideCities;

  public static void main(String[] args) {

    //number of drivers to simulate
    final int numDrivers = 5;

    int seed = 0;
    try{
      seed = checkArgs(args);
    }catch(IllegalArgumentException iae){
      System.out.println("Please enter a single integer arguement");
      System.exit(0);
    }

    //list of all locations on the map
    outsideCities = new ArrayList<Location>();
    mapLocations = new ArrayList<Location>();
    mapLocations = setupMap();

    Random rand = new Random(seed);

    //simulate drivers
    for(int i=0; i<numDrivers; i++){
      //Create A Driver
      int nextSeed = rand.nextInt();
      Location start = getRandomCityLocation(nextSeed);
      Driver currentDriver = new Driver(i+1,start);
      System.out.println("Driver starts the simulation at the " + start.getName());

      //Simulate driver's path through the city
      while(!currentDriver.getLocation().isOutsideCity(outsideCities)){
        Location currentLocation = currentDriver.getLocation();

        //Find where the driver will go next
        nextSeed = rand.nextInt();
        Path nextPath = new Path();
        try{
          nextPath = currentLocation.getRandomPath(nextSeed);
        }catch(IllegalStateException ise){
          System.out.println("Driver is stuck at: " + currentLocation);
          System.exit(0);
        }
        String nextLocationName = nextPath.getLocation();
        Location nextLocation = getLocationByName(nextLocationName);

        printDriverStatus(currentDriver, nextPath, nextLocation);

        currentDriver.setLocation(nextLocation);
      }

      System.out.println("Driver has " + currentDriver.getCupsOfCoffee() + " cups of coffee");
      System.out.println();
    }
  }

  //returns a location based a the name of that location
  private static Location getLocationByName(String name){
    for(int i=0;i<mapLocations.size(); i++){
      Location loc = mapLocations.get(i);
      String locName = loc.getName();
      if(locName.equals(name)){
        return loc;
      }
    }
    System.out.println("Error location: " + name +  " not found");
    return null;
  }

  //returns a random location from the map that is inside the city
  private static Location getRandomCityLocation(int seed){
    Random rand = new Random(seed);
    int listSize = mapLocations.size();
    while(true){
      Location loc = mapLocations.get(rand.nextInt(listSize));
      if(!loc.isOutsideCity(outsideCities)){
        return loc;
      }
    }
  }

  //return users chosen seed from arguement
  public static int checkArgs(String[] args){
    int seed = 0;

    if(args.length!=1){
      throw new IllegalArgumentException("Multiple Arguements Entered");
    }

    try{
      seed = Integer.parseInt(args[0]);
    }
    catch(NumberFormatException e){
      throw new IllegalArgumentException("No Integer Entered");
    }

    return seed;
  }

  //create the map by adding locations and the nearby locations they are conneced to
  private static ArrayList<Location> setupMap(){
    Location hotel = new Location("Hotel");
    hotel.addConnection("Bill St.","Library");
    hotel.addConnection("Fourth Ave.","Diner");
    mapLocations.add(hotel);

    Location library = new Location("Library");
    library.addConnection("Bill St.","Hotel");
    library.addConnection("Fifth Ave.","Cleveland");
    mapLocations.add(library);

    Location diner = new Location("Diner");
    diner.addConnection("Phil St.","Coffee");
    diner.addConnection("Fourth Ave.","Philadelphia");
    mapLocations.add(diner);

    Location coffee = new Location("Coffee");
    coffee.addConnection("Phil St.","Diner");
    coffee.addConnection("Fifth Ave.","Library");
    mapLocations.add(coffee);

    Location cleveland = new Location("Cleveland");
    mapLocations.add(cleveland);
    outsideCities.add(cleveland);

    Location philadelphia = new Location("Philadelphia");
    mapLocations.add(philadelphia);
    outsideCities.add(philadelphia);

    return mapLocations;
  }

  private static void printDriverStatus(Driver currentDriver, Path nextPath, Location nextLocation){
    System.out.print("Driver " + currentDriver.getId());
    System.out.print(" heading from " + currentDriver.getLocation().getName());
    if(nextLocation.isOutsideCity(outsideCities)){
      System.out.print(" to Outside City ");
      System.out.println(" via " + nextPath.getStreet());
      System.out.println("Driver had gone to " + nextPath.getLocation() + " !");
    }else{
      System.out.print(" to " + nextPath.getLocation());
      System.out.println(" via " + nextPath.getStreet());
    }
  }

}
