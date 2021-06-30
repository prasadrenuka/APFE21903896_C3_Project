import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time()
    {
        LocalTime openingTime =LocalTime.now().minus(Duration.ofMinutes(30));
        LocalTime closingTime =LocalTime.now().plus(Duration.ofMinutes(30));
        restaurant=new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        boolean returned_value=restaurant.isRestaurantOpen();
        assertTrue(returned_value);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time()
    {
        LocalTime openingTime =LocalTime.now().plus(Duration.ofMinutes(30));
        LocalTime closingTime =LocalTime.now().plus(Duration.ofMinutes(60));
        restaurant=new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        boolean returned_value=restaurant.isRestaurantOpen();
        assertTrue(returned_value);

    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void price_of_selected_iteams_should_be_displayed()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("veg_puff", 35);
        restaurant.addToMenu("egg_puff",45);
        restaurant.addToMenu("veg_roll",80);
        List<String> iteams_selected = new ArrayList<String>();
        iteams_selected.add("veg_puff");
        iteams_selected.add("egg_puff");
        iteams_selected.add("veg_roll");
        int returned_price=restaurant.price_of_selected_iteams(iteams_selected);
        assertEquals(160,returned_price);
    }

}