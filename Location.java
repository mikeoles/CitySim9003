import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Location{
  private String name;
  private ArrayList<String> connectedLocations;
  private HashMap<String,String> streets;

  public Location(String name){
    setName(name);
    connectedLocations = new ArrayList<String>();
    streets = new HashMap<String,String>();
  }

  public void setName(String name){
    if(name==null){
      throw new NullPointerException();
    }else{
      this.name = name;
    }
  }

	public String getName(){
		return name;
	}

  //add the street name to a hashmap so it can be found if the driver travels
  //from this location to another location
  public void addConnection(String streetName,String connectedTo){
    connectedLocations.add(connectedTo);
    streets.put(connectedTo,streetName);
  }

  //returns the street that connects it to the name of the location passed in
  private String getStreetName(String connectsTo){
    return streets.get(connectsTo);
  }

  //check if the location of the driver was a location outside of the city
  public boolean isOutsideCity(ArrayList<Location> outsideCities){
    boolean isOutsideCity = false;
    for(int i=0; i<outsideCities.size(); i++){
      if(this==outsideCities.get(i)){
        isOutsideCity = true;
      }
    }
    return isOutsideCity;
  }

  //returns number of connectedLocations
  private int getCLSize(){
    return connectedLocations.size();
  }

  //returns a random path to one of the connectedLocations
  public Path getRandomPath(int seed){
    Random rand = new Random(seed);
    int listSize = getCLSize();
    if(listSize==0){
      throw new IllegalStateException();
    }
    String location = connectedLocations.get(rand.nextInt(listSize));
    String street = getStreetName(location);
    Path nextPath = new Path();
    nextPath.setLocation(location);
    nextPath.setStreet(street);
    return nextPath;
  }


}
