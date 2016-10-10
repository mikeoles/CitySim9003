public class Driver{
  private int id;
  private Location curLocation;
  private int cupsOfCoffee;

  public Driver(int id,Location location){
    this.id = id;
    this.curLocation = location;
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
    this.curLocation = location;
    checkCoffee(location);
  }

  public int getCupsOfCoffee(){
    return this.cupsOfCoffee;
  }

  private void checkCoffee(Location location){
    if(location.getName().equals("Coffee")){
      this.cupsOfCoffee++;
    }
  }
}
