package com.artemislab.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code CarRegistry} class represents a registry of cars' vehicle ids
 * identified by <em>manufacturer</em>, <em>model</em> and <em>color</em>.
 */
public class CarRegistry {
  /**
   * The internal storage of the registry.
   */
  private Map<String, List<String>> storage;

  /**
   * The cache used to store generated combinations of keys used by the internal
   * storage of the registry, for faster access when the same combination of
   * <em>manufacturer</em>, <em>model</em> and <em>color</em> used multiple times
   * to add a new record about vehicle id.
   */
  private Map<String, List<String>> keysByArgs;

  /**
   * Creates a new instance of {@code CarRegistry}.
   */
  public CarRegistry() {
    storage = new HashMap<>();
    keysByArgs = new HashMap<>();
  }

  /**
   * Adds a new record about vehicle id into the registry.
   * <p>
   * Throws an {@code IllegalArgumentException} if any of the provided arguments
   * have either null, empty or blank (containing only white space characters)
   * value.
   * 
   * <p>
   * Before adding a new record about vehicle id all arguments are trimmed with
   * all leading and trailing white space characters removed.
   * 
   * @param manufacturer The manufacturer of a car. Must not be null, empty or
   *                     blank.
   * @param model        The model of a car. Must not be null, empty or blank.
   * @param color        The color of a car. Must not be null, empty or blank.
   * @param vehicleId    The vehicle id of a car. Must not be null, empty or
   *                     blank.
   */
  public void put(String manufacturer, String model, String color, String vehicleId) {
    String trimmedManufacturer = checkForNullOrEmptyOrBlank(manufacturer,
        "Manufacturer must not be null, empty or blank.");
    String trimmedModel = checkForNullOrEmptyOrBlank(model, "Model must not be null, empty or blank.");
    String trimmedColor = checkForNullOrEmptyOrBlank(color, "Color must not be null, empty or blank.");
    String trimmedVehicleId = checkForNullOrEmptyOrBlank(vehicleId, "Vehicle Id must not be null, empty or blank.");

    String[] args = argsToArray(trimmedManufacturer, trimmedModel, trimmedColor);
    String argsAsString = Arrays.toString(args);
    List<String> keys = keysByArgs.getOrDefault(argsAsString, new ArrayList<>());

    if (keys.isEmpty()) {
      keys = getKeysSubsets(args);
      keysByArgs.put(argsAsString, keys);
    }

    for (String key : keys) {
      List<String> vehicleIds = storage.getOrDefault(key, new ArrayList<>());
      vehicleIds.add(trimmedVehicleId);
      storage.put(key, vehicleIds);
    }
  }

  /**
   * Retrieves a list of vehicle ids which mapped to the given
   * <em>manufacturer</em>, <em>model</em> and <em>color</em>.
   * <p>
   * Note that any or all of the given arguments may have either null, empty or
   * blank (containing only white space characters) value.
   * 
   * @param manufacturer The manufacturer of a car. May be null, empty or blank.
   * @param model        The model of a car. May be null, empty or blank.
   * @param color        The color of a car. May be null, empty or blank.
   * @return the list of vehicle ids satisfying the given search criteria.
   */
  public List<String> get(String manufacturer, String model, String color) {
    String[] args = argsToArray(manufacturer, model, color);
    StringBuilder key = new StringBuilder();

    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg == null || arg.trim().isEmpty()) {
        key.append("null");
      } else {
        key.append(arg);
      }
      if (i < args.length - 1) {
        key.append("_");
      }
    }

    List<String> vehicleIds = storage.getOrDefault(key.toString(), new ArrayList<>());
    return List.copyOf(vehicleIds);
  }

  private static String checkForNullOrEmptyOrBlank(String value, String message) {
    if (value == null) {
      throw new IllegalArgumentException(message);
    }

    String trimmedValue = value.trim();
    if (trimmedValue.isEmpty()) {
      throw new IllegalArgumentException(message);
    }

    return trimmedValue;
  }

  private static String[] argsToArray(String manufacturer, String model, String color) {
    return new String[] { manufacturer, model, color };
  }

  private static List<String> getKeysSubsets(String[] args) {
    List<String> keysSubsets = new ArrayList<>();
    getKeysSubsetsHelper(args, 0, new ArrayList<>(), keysSubsets);
    keysSubsets.add("null_null_null");
    return keysSubsets;
  }

  private static void getKeysSubsetsHelper(String[] args, int start, List<Integer> keySubset,
      List<String> keysSubsets) {
    for (int i = start; i < args.length; i++) {
      keySubset.add(i);
      keysSubsets.add(keySubsetToString(args, keySubset));
      getKeysSubsetsHelper(args, i + 1, keySubset, keysSubsets);
      keySubset.remove(keySubset.size() - 1);
    }
  }

  private static String keySubsetToString(String[] args, List<Integer> keySubset) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      if (keySubset.contains(i)) {
        result.append(args[i]);
      } else {
        result.append("null");
      }
      if (i < args.length - 1) {
        result.append("_");
      }
    }
    return result.toString();
  }
}
