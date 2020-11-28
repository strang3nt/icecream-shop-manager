package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

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
}
