package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.itemType;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TakeAwayBillImplTest {

    private final TakeAwayBillImpl bill = new TakeAwayBillImpl();
    private final User customer = new User("Pinco Pallo", LocalDate.of(1999, 6, 10));

    @Test
    public void getOrderPrice_listOfOrders_calculate() {

        List<MenuItem> check = Arrays.asList(
            new MenuItem(MenuItem.itemType.Bevande, "CocaCola", 2.5),
            new MenuItem(MenuItem.itemType.Budini, "Biancaneve", 9.5),
            new MenuItem(MenuItem.itemType.Budini, "Pinguino", 7.5),
            new MenuItem(MenuItem.itemType.Bevande, "Banana Split", 5.5)
        );

        assertEquals(25, bill.getOrderPrice(check, customer), 0);
    }

    @Test
    public void getOrderPrice_fourIceCreams_calculate() {
        List<MenuItem> check = Arrays.asList(
            new MenuItem(MenuItem.itemType.Gelati, "CocaCocoa", 2.5),
            new MenuItem(MenuItem.itemType.Budini, "Biancaneve", 9.5),
            new MenuItem(MenuItem.itemType.Gelati, "Pistacchio", 7.5),
            new MenuItem(MenuItem.itemType.Gelati, "Banana Split", 5.5),
            new MenuItem(MenuItem.itemType.Gelati, "Coppa Ananas", 6),
            new MenuItem(MenuItem.itemType.Gelati, "Puffo", 11.5)
        );

        assertEquals(42.5, bill.getOrderPrice(check, customer), 0);
    }

    @Test
    public void getOrderPrice_getTotalWithDiscountCheapestIceCream_calculate() {
        List<MenuItem> check = Arrays.asList(
            new MenuItem(MenuItem.itemType.Gelati, "CocaCocoa", 2.5),
            new MenuItem(MenuItem.itemType.Budini, "Biancaneve", 9.5),
            new MenuItem(MenuItem.itemType.Gelati, "Pistacchio", 7.5),
            new MenuItem(MenuItem.itemType.Gelati, "Banana Split", 5.5),
            new MenuItem(MenuItem.itemType.Gelati, "Puffo", 11.5),
            new MenuItem(MenuItem.itemType.Gelati, "Coppa Ananas", 6),
            new MenuItem(MenuItem.itemType.Gelati, "Cioccolato", 4)
        );

        assertEquals(45.25, bill.getOrderPrice(check, customer), 0);
    }

    @Test
    public void cheapestIceCream_getCheapest_find() {

        MenuItem cocaCocoa = new MenuItem(MenuItem.itemType.Gelati, "CocaCocoa", 2.5);

        List<MenuItem> check = Arrays.asList(
            cocaCocoa,
            new MenuItem(MenuItem.itemType.Budini, "Biancaneve", 9.5),
            new MenuItem(MenuItem.itemType.Gelati, "Pistacchio", 7.5),
            new MenuItem(MenuItem.itemType.Gelati, "Banana Split", 5.5),
            new MenuItem(MenuItem.itemType.Gelati, "Puffo", 11.5),
            new MenuItem(MenuItem.itemType.Gelati, "Coppa Ananas", 6),
            new MenuItem(MenuItem.itemType.Gelati, "Cioccolato", 4)
        );
    
        assertEquals(cocaCocoa, bill.cheapestIceCream(check));
    }

    @Test
    public void countItem_itemsfed_count() {
        List<MenuItem> check = Arrays.asList(
            new MenuItem(MenuItem.itemType.Gelati, "CocaCocoa", 2.5),
            new MenuItem(MenuItem.itemType.Budini, "Biancaneve", 9.5),
            new MenuItem(MenuItem.itemType.Gelati, "Pistacchio", 7.5),
            new MenuItem(MenuItem.itemType.Gelati, "Banana Split", 5.5),
            new MenuItem(MenuItem.itemType.Budini, "Pinguino", 7.5),
            new MenuItem(MenuItem.itemType.Gelati, "Coppa Ananas", 6)
        );

        assertEquals(2, bill.countItem(check, itemType.Budini));
    }
}
