package com.planning.collections.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Google Guava 使用
 *
 * @author yxc
 * @since 2020-11-02 19:15
 **/
public class GuavaLearn {

    private final static BiMap<Integer, String> daysOfWeek = HashBiMap.create();
    private final static Multimap<String, String> groceryCart = ArrayListMultimap.create();
    private final static Table<String, String, String> cityCoordinates = HashBasedTable.create();
    private final static Table<String, String, String> movies = HashBasedTable.create();
    private long start;

    static {
        daysOfWeek.put(1, "Monday");
        daysOfWeek.put(2, "Tuesday");
        daysOfWeek.put(3, "Wednesday");
        daysOfWeek.put(4, "Thursday");
        daysOfWeek.put(5, "Friday");
        daysOfWeek.put(6, "Saturday");
        daysOfWeek.put(7, "Sunday");

        groceryCart.put("Fruits", "Apple");
        groceryCart.put("Fruits", "Grapes");
        groceryCart.put("Fruits", "Strawberries");
        groceryCart.put("Vegetables", "Spinach");
        groceryCart.put("Vegetables", "Cabbage");

        cityCoordinates.put("40.7128° N", "74.0060° W", "New York");
        cityCoordinates.put("48.8566° N", "2.3522° E", "Paris");
        cityCoordinates.put("19.0760° N", "72.8777° E", "Mumbai");

        movies.put("Tom Hanks", "Meg Ryan", "You've Got Mail");
        movies.put("Tom Hanks", "Catherine Zeta-Jones", "The Terminal");
        movies.put("Bradley Cooper", "Lady Gaga", "A Star is Born");
        movies.put("K\"Bradley Cooper\", \"Lady Gaga\"eenu Reaves", "Sandra Bullock", "Speed");
        movies.put("Tom Hanks", "Sandra Bullock", "Extremely Loud & Incredibly Close");
    }

    @Test
    public void givenBiMap_whenValue_thenKeyReturned() {
        assertEquals(Integer.valueOf(7), daysOfWeek.inverse().get("Sunday"));
        assertEquals("Tuesday", daysOfWeek.get(2));
    }

    @Test
    public void givenMultiValuedMap_whenFruitsFetched_thenFruitsReturned() {
        List<String> fruitsList = Arrays.asList("Apple", "Grapes", "Strawberries");
        assertEquals(fruitsList, groceryCart.get("Fruits"));
    }

    @Test
    public void givenMultiValuedMap_whenVeggiesFetched_thenVeggiesReturned() {
        List<String> veggies = Arrays.asList("Spinach", "Cabbage");
        assertEquals(veggies, groceryCart.get("Vegetables"));
    }

    @Test
    public void givenMultiValuedMap_whenFruitsRemoved_thenVeggiesPreserved() {
        assertEquals(5, groceryCart.size());

        groceryCart.remove("Fruits", "Apple");
        assertEquals(4, groceryCart.size());

        groceryCart.removeAll("Fruits");
        assertEquals(2, groceryCart.size());
    }

    @Test
    public void givenCoordinatesTable_whenFetched_thenOK() {
        List<String> expectLongitudes = Arrays.asList("74.0060° W", "2.3522° E", "72.8777° E");
        assertArrayEquals(expectLongitudes.toArray(), cityCoordinates.columnKeySet().toArray());

        List<String> expectedCities = Arrays.asList("New York", "Paris", "Mumbai");
        assertArrayEquals(expectedCities.toArray(), cityCoordinates.values().toArray());

        assertTrue(cityCoordinates.rowKeySet().contains("40.7128° N"));
    }

    @Test
    public void givenMoviesTable_whenFetched_thenOK() {
        assertEquals(3, movies.row("Tom Hanks").size());

        assertEquals(2, movies.column("Sandra Bullock").size());

        assertEquals("A Star is Born", movies.get("Bradley Cooper", "Lady Gaga"));

        assertTrue(movies.containsValue("Speed"));
    }

    /**
     * Result:
     * <p>
     * Insert time: 25
     * Value:501
     * Fetch time key:900
     * Key:500
     * Fetch time value:871
     */
    @Test
    public void givenHashBiMap_whenHundredThousandKeys_thenPerformanceNoted() {
        HashBiMap<Integer, Integer> map = HashBiMap.create();
        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            map.put(i, i + 1);
        }
        System.out.println("Insert time: " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));

        start = System.nanoTime();
        Integer value = map.get(500);
        System.out.println("Value:" + value);
        System.out.println("Fetch time key:" + TimeUnit.MICROSECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));

        start = System.nanoTime();
        Integer key = map.inverse()
                .get(501);
        System.out.println("Key:" + key);
        System.out.println("Fetch time value:" + TimeUnit.MICROSECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));
    }


}