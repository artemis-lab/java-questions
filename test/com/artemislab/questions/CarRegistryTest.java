package com.artemislab.questions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

/**
 * The {@code CarRegistryTest} class implements tests for {@code CarRegisty}.
 */
public class CarRegistryTest {

  private CarRegistry carRegistry;

  @Before
  public void initialize() {
    carRegistry = new CarRegistry();
  }

  @Test
  public void testPutOneEntry_NullManufacturer_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put(null, "Civic", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Manufacturer must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyManufacturer_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("", "Civic", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Manufacturer must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankManufacturer_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("   ", "Civic", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Manufacturer must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_NullModel_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", null, "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Model must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyModel_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Model must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankModel_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "   ", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Model must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_NullColor_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "Civic", null, "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Color must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyColor_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "Civic", "", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Color must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankColor_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "Civic", "   ", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Color must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_NullVehicleId_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "Civic", "Blue", null);
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Vehicle Id must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyVehicleId_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "Civic", "Blue", "");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Vehicle Id must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankVehicleId_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        carRegistry.put("Honda", "Civic", "Blue", "   ");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Vehicle Id must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutZeroEntries_GetByManufacturerModelColor() {
    List<String> vehicleIds = carRegistry.get("Honda", "Civic", "Blue");

    assertEquals(Arrays.asList(), vehicleIds);
  }

  @Test
  public void testPutOneEntry_GetByManufacturerModelColor() {
    carRegistry.put("Honda", "Civic", "Blue", "123");
    List<String> vehicleIds = carRegistry.get("Honda", "Civic", "Blue");

    assertEquals(Arrays.asList("123"), vehicleIds);
  }

  @Test
  public void testPutTwoEntries_SameManufacturerModelColor_GetByManufacturerModelColor() {
    carRegistry.put("Honda", "Civic", "Blue", "123");
    carRegistry.put("Honda  ", "Civic  ", " Blue ", " 456   ");
    List<String> vehicleIds = carRegistry.get("Honda", "Civic", "Blue");

    assertEquals(Arrays.asList("123", "456"), vehicleIds);
  }

  @Test
  public void testPutManyEntries_DifferentManufacturerModelColor_GetByManufacturerModelColor() {
    carRegistry.put("Honda", "Civic", "Blue", "123");
    carRegistry.put("Honda", "Civic", "Blue", "456");
    carRegistry.put("Honda", "Acord", "Black", "789");
    carRegistry.put("Honda", "Acord", "Black Metallic", "098");
    carRegistry.put("Toyota", "Corolla", "Red", "468");
    carRegistry.put("Toyota", "Corolla", "White", "654");
    carRegistry.put("Toyota", "Camry", "Silver", "246");
    carRegistry.put("Nissan", "Juke", "White", "135");
    carRegistry.put("Nissan", "Juke", "Red Metallic", "579");

    assertEquals(Arrays.asList("123", "456"), carRegistry.get("Honda", "Civic", "Blue"));
    assertEquals(Arrays.asList("789"), carRegistry.get("Honda", "Acord", "Black"));
    assertEquals(Arrays.asList("098"), carRegistry.get("Honda", "Acord", "Black Metallic"));
    assertEquals(Arrays.asList("468"), carRegistry.get("Toyota", "Corolla", "Red"));
    assertEquals(Arrays.asList("654"), carRegistry.get("Toyota", "Corolla", "White"));
    assertEquals(Arrays.asList("246"), carRegistry.get("Toyota", "Camry", "Silver"));
    assertEquals(Arrays.asList("135"), carRegistry.get("Nissan", "Juke", "White"));
    assertEquals(Arrays.asList("579"), carRegistry.get("Nissan", "Juke", "Red Metallic"));
  }

  @Test
  public void testPutManyEntries_DifferentManufacturerModelColor_GetByWildcard() {
    carRegistry.put("Honda", "Civic", "Blue", "123");
    carRegistry.put("Honda", "Civic", "Blue", "456");
    carRegistry.put("Honda", "Acord", "Black", "789");
    carRegistry.put("Honda", "Acord", "Black Metallic", "098");
    carRegistry.put("Toyota", "Corolla", "Red", "468");
    carRegistry.put("Toyota", "Corolla", "White", "654");
    carRegistry.put("Toyota", "Camry", "Silver", "246");
    carRegistry.put("Nissan", "Juke", "White", "135");
    carRegistry.put("Nissan", "Juke", "Red Metallic", "579");

    assertEquals(Arrays.asList("123", "456"), carRegistry.get("Honda", "Civic", null));
    assertEquals(Arrays.asList("123", "456", "789", "098"), carRegistry.get("Honda", null, ""));
    assertEquals(Arrays.asList("123", "456"), carRegistry.get("", "Civic", null));
    assertEquals(Arrays.asList("123", "456"), carRegistry.get(null, " ", "Blue"));
    assertEquals(Arrays.asList("468", "654", "246"), carRegistry.get("Toyota", null, null));
    assertEquals(Arrays.asList("468", "654"), carRegistry.get("Toyota", "Corolla", null));
    assertEquals(Arrays.asList("468"), carRegistry.get(" ", " ", "Red"));
    assertEquals(Arrays.asList("135", "579"), carRegistry.get(null, "Juke", null));
    assertEquals(Arrays.asList("654", "135"), carRegistry.get(null, null, "White"));
    assertEquals(Arrays.asList("579"), carRegistry.get(null, null, "Red Metallic"));
    assertEquals(Arrays.asList("579"), carRegistry.get(null, null, "Red Metallic"));
    assertEquals(Arrays.asList("123", "456", "789", "098", "468", "654", "246", "135", "579"),
        carRegistry.get(null, null, null));
    assertEquals(Arrays.asList("123", "456", "789", "098", "468", "654", "246", "135", "579"),
        carRegistry.get("", "  ", null));
  }
}
