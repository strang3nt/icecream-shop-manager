package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.itemType;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class TakeAwayBillImplTest {

    private final TakeAwayBillImpl bill = new TakeAwayBillImpl();
    private final User customer = new User("Pinco Pallo", LocalDate.of(1999, 6, 10));
    private final List<MenuItem> check = Arrays.asList(
            new MenuItem(MenuItem.itemType.Bevande, "CocaCola", 2.5),
            new MenuItem(MenuItem.itemType.Budini, "Biancaneve", 9.5),
            new MenuItem(MenuItem.itemType.Gelati, "Pistacchio", 7.5),
            new MenuItem(MenuItem.itemType.Gelati, "Pallina Panna", 3.5),
            new MenuItem(MenuItem.itemType.Gelati, "Banana Split", 5.5),
            new MenuItem(MenuItem.itemType.Gelati, "Coppa Ananas", 6),
            new MenuItem(MenuItem.itemType.Gelati, "Puffo", 11.5),
            new MenuItem(MenuItem.itemType.Gelati, "50 euro di gelato", 50)
        );

    @Test
    public void getOrderPrice_lessThan30Elements_calculate() throws TakeAwayBillException{
        assertEquals((96 - 1.75) * 0.9, bill.getOrderPrice(check, customer), 0);
    }

    @Test(expected = TakeAwayBillException.class)
    public void getOrderPrice_moreThan30Elements_calculate() throws TakeAwayBillException {
        List<MenuItem> bigBill = check.stream().collect(Collectors.toList());

        for(int i = bigBill.size();  i < 31; i++) {
            bigBill.add(new MenuItem(MenuItem.itemType.Gelati, "Pallina", 2.5));
        }

        @SuppressWarnings("unused")
        double value = bill.getOrderPrice(bigBill, customer);
    }
    @Test
    public void cheapestIceCream_getCheapest_find() {
        MenuItem panna = check.get(3);
        assertEquals(panna, bill.cheapestIceCream(check));
    }

    @Test
    public void countItem_itemsfed_count() {
        assertEquals(1, bill.countItem(check, itemType.Budini));
    }

}
