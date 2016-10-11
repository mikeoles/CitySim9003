import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.*;

public class CitySim9003Test{

  //A test to check that arguements are handled correctly
  //A positive integer passed in should return that value as the seed
  @Test
  public void testCheckArgsPositive() {
    String[] a = {"48"};
    try {
        assertEquals(48,CitySim9003.checkArgs(a));
    } catch (IllegalArgumentException iae) {
        // expected behavior
    }
  }


  //A test to check that arguements are handled correctly
  //An exception should be thrown if the user enters a number that is too large to be an int
  @Test
  public void testCheckArgsLarge() {
    String[] a = {"234093435345432435"};
    try {
        CitySim9003.checkArgs(a);
        fail();
    } catch (IllegalArgumentException iae) {
        // expected behavior
    }
  }

  //A test to check that arguements are handled correctly
  //A negative integer passed in should return that value as the seed
  @Test
  public void testCheckArgsNegative() {
    String[] a = {"-8"};
    try {
        assertEquals(-8,CitySim9003.checkArgs(a));
    } catch (IllegalArgumentException iae) {
        fail();
    }
  }

  //A test to check that arguements are handled correctly
  //An exception should be thrown if the user does not enter any arguements
  @Test
  public void testCheckArgsNoArgs() {
    String[] a = {};
    try {
        CitySim9003.checkArgs(a);
        fail();
    } catch (IllegalArgumentException iae) {
        // expected behavior
    }
  }

  //A test to check that arguements are handled correctly
  //An exception should be thrown if the user enters a string
  @Test
  public void testCheckArgsString() {
    String[] a = {"hello"};
    try {
        CitySim9003.checkArgs(a);
        fail();
    } catch (IllegalArgumentException iae) {
        // expected behavior
    }
  }

  //A test to check that arguements are handled correctly
  //An exception should be thrown if the user enters multiple args
  //This should happen even if all arguementss are valid
  @Test
  public void testCheckNoMultipleArgs() {
    String[] a = {"1","-34"};
    try {
        CitySim9003.checkArgs(a);
        fail();
    } catch (IllegalArgumentException iae) {
        // expected behavior
    }
  }

}
