public class Driver{
  private int id;
  private Location curLocation;
  private int cupsOfCoffee;

  public Driver(int id,Location location){
    this.id = id;
    setLocation(location);
    this.cupsOfCoffee = 0;
    checkCoffee(location);
  }

	public int getId() {
		return id;
	}

	public Location getLocation() {
		return curLocation;
	}

	public void setLocation(Location location) {
    if(location==null){
      throw new NullPointerException();
    }else{
      this.curLocation = location;
      checkCoffee(location);
    }
  }

  public int getCupsOfCoffee(){
    return this.cupsOfCoffee;
  }

  //check if the driver is currently at a location where they will buy coffee
  //increment the number of cups of coffee they have if they are
  private void checkCoffee(Location location){
    String locationName = location.getName();
    String locationNameLowerCase = locationName.toLowerCase();
    if(locationNameLowerCase.equals("coffee")){
      this.cupsOfCoffee++;
    }
  }
}
