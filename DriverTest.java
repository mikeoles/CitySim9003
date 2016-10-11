import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.*;

public class DriverTest{

  //Create a new driver not at a coffee shop
  //Ensure the new driver doesn't have any coffee
  //The driver should return 0 cups because they have never visted a coffee
  @Test
  public void testGetCoffeeNeverVisted() {
    Location l = Mockito.mock(Location.class);
    Mockito.when(l.getName()).thenReturn("NotCoffee");
    Driver d = new Driver(1,l);
    int cups = d.getCupsOfCoffee();
    assertEquals(cups,0);
  }

  //Create a new driver at a coffee shop
  //Since the driver is starting out at the coffe shop he should start with a cup of coffee
  @Test
  public void testStartAtCoffee() {
    Location l = Mockito.mock(Location.class);
    Mockito.when(l.getName()).thenReturn("Coffee");
    Driver d = new Driver(1,l);
    int cups = d.getCupsOfCoffee();
    assertEquals(cups,1);
  }

  //The Driver should start with 0 cups of coffee
  //After that the driver will go to the coffee shop 50 times so he should have 50 cups
  //Makes sure cups are not added when the driver goes to the library
  @Test
  public void testGoToCoffeeManyTimes() {
    Location cLoc = Mockito.mock(Location.class);
    Mockito.when(cLoc.getName()).thenReturn("Coffee");
    Location ncLoc = Mockito.mock(Location.class);
    Mockito.when(ncLoc.getName()).thenReturn("Library");
    Driver d = new Driver(1,ncLoc);
    for(int i=0; i<50; i++){
      d.setLocation(cLoc);
      d.setLocation(ncLoc);
    }
    int cups = d.getCupsOfCoffee();
    assertEquals(cups,50);
  }

  //Test that a when an id is added it will return the same id
  @Test
  public void testGetID() {
    Location loc = new Location("place");
    Driver driver = new Driver(2,loc);
    int ret = driver.getId();
    assertEquals(ret,2);
  }

  //Test that a when an id of zero is added it will return the same id
  @Test
  public void testGetIDZero() {
    Location loc = new Location("place");
    Driver driver = new Driver(0,loc);
    int ret = driver.getId();
    assertEquals(ret,0);
  }

  //Test that a when a negative id is added it will return the same id
  @Test
  public void testGetIDNegative() {
    Location loc = new Location("place");
    Driver driver = new Driver(-1000000,loc);
    int ret = driver.getId();
    assertEquals(ret,-1000000);
  }

  //Test that a when a null location is passed into the driver that
  //the driver will throw a NullPointerException
  @Test
  public void testGetLocationNull() {
    Location loc = Mockito.mock(Location.class);
    try {
      Driver driver = new Driver(0,loc);
      driver.getLocation();
      fail();
    } catch (NullPointerException npe) {
      //expected
    }
  }

  //Test that when a location is pased as a parameter to a driver that
  //get location will return the same location back
  @Test
  public void testGetLocationWithConstructor() {
    Location loc = new Location("fakename");
    Location secondLoc = loc;
    try {
      Driver driver = new Driver(0,loc);
      Location retLoc = driver.getLocation();
      assertEquals(retLoc,secondLoc);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  //Make sure that when a location set using set location that
  //get location will return the new updated location
  @Test
  public void testGetLocationWithSetLocation() {
    Location loc = new Location("firstlocation");
    Location secondLoc = new Location("secondlocation");
    try {
      Driver driver = new Driver(0,loc);
      driver.setLocation(secondLoc);
      Location retLoc = driver.getLocation();
      assertEquals(retLoc,secondLoc);
    } catch (NullPointerException npe) {
      fail();
    }
  }

}
