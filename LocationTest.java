import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.mockito.*;

public class LocationTest{
  //Test that a when a null string is passed as a name
  //that the method will throw a null pointer exception so
  //getName will not have to return a null string
  @Test
  public void testGetNameNull() {
    String s = null;
    try {
      Location loc = new Location(s);
      loc.getName();
      fail();
    } catch (NullPointerException npe) {
      //expected
    }
  }

  //Test that when a Name is pased as a parameter to a driver that
  //get location will return the same location back
  @Test
  public void testGetName() {
    String s = new String("fakename");
    try {
      Location loc = new Location(s);
      String ret = loc.getName();
      assertEquals(ret,"fakename");
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

  //if the array of outside cities has the current location in it
  //the outsideCities() funciton should return true
  @Test
  public void testOutsideCitiesTrue() {
    Location loc = new Location("Test");
    @SuppressWarnings( "unchecked" )
    ArrayList oc = Mockito.mock(ArrayList.class);
    Mockito.when(oc.size()).thenReturn(1);
    Mockito.when(oc.get(0)).thenReturn(loc);
    boolean ret = loc.isOutsideCity(oc);
    assertTrue(ret);
  }

  //if the array of outside cities does not have the current location in it
  //the outsideCities() funciton should return false
  @Test
  public void testOutsideCitiesFalse() {
    Location loc = new Location("Test");
    Location notLoc = new Location("notSameAsTest");
    @SuppressWarnings( "unchecked" )
    ArrayList oc = Mockito.mock(ArrayList.class);
    Mockito.when(oc.size()).thenReturn(1);
    Mockito.when(oc.get(0)).thenReturn(notLoc);
    boolean ret = loc.isOutsideCity(oc);
    assertFalse(ret);
  }

  //If there is a null Path returned it getRandomPath
  //it should not return the path and instead throw an IllegalStateException
  //this would mean that the location is connected to nowhere so the driver is stuck
  @Test
  public void testGetRandomPathNotNull(){
    try{
      Location loc = new Location("Library");
      Path p = loc.getRandomPath(8);
      System.out.println("path " + p);
    }catch(IllegalStateException ise){
      //expected
    }
  }
}
