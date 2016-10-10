import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Location{
  public String name;
  public ArrayList<String> connectedLocations;
  public HashMap<String,String> streets;

  public Location(String name){
    this.name = name;
    connectedLocations = new ArrayList<String>();
    streets = new HashMap<String,String>();
  }

	public String getName() {
		return name;
	}

  public void addConnection(String streetName,String connectedTo){
    connectedLocations.add(connectedTo);
    streets.put(connectedTo,streetName);
  }

  private String getStreetName(String locationName){
    return streets.get(locationName);
  }

  public boolean isOutsideCity(){
    if(name=="Philadelphia" || name=="Cleveland"){
      return true;
    }else{
      return false;
    }
  }

  public Path getRandomPath(int seed){
    Random rand = new Random(seed);
    int listSize = connectedLocations.size();
    String location = connectedLocations.get(rand.nextInt(listSize));
    String street = getStreetName(location);
    Path nextPath = new Path();
    nextPath.setLocation(location);
    nextPath.setStreet(street);
    return nextPath;
  }


}
