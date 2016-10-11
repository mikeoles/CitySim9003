import org.junit.Test;
import static org.junit.Assert.*;

public class PathTest{

  //Test that a when a location is added it will return the same location
  @Test
  public void testGetLocation() {
    Path p = new Path();
    p.setLocation("Buffalo");
    String ret = p.getLocation();
    assertEquals(ret,"Buffalo");
  }

  //Test that a when a street is added it will return the same street
  @Test
  public void testGetStreet() {
    Path p = new Path();
    p.setStreet("Coltart");
    String ret = p.getStreet();
    assertEquals(ret,"Coltart");
  }
}
