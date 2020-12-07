package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.itemType;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;

public class TakeAwayBillImplTest {

    private TakeAwayBillImpl bill = new TakeAwayBillImpl();
    private final User customer = new User("Mario Rossi", LocalDate.of(1999, 6, 10));
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
        assertEquals((96 - 1.75) * 0.9, bill.getOrderPrice(check, customer, LocalTime.now()), 0);
    }

    @Test(expected = TakeAwayBillException.class)
    public void getOrderPrice_moreThan30Elements_calculate() throws TakeAwayBillException {
        List<MenuItem> bigBill = check.stream().collect(Collectors.toList());

        for(int i = bigBill.size();  i < 31; i++) {
            bigBill.add(new MenuItem(MenuItem.itemType.Gelati, "Pallina", 2.5));
        }

        @SuppressWarnings("unused")
        double value = bill.getOrderPrice(bigBill, customer, LocalTime.now());
    }

    @Test
    public void getOrderPrice_lessThan10Elements_calcolate() throws TakeAwayBillException{
        List<MenuItem> smallBill = Arrays.asList(
            new MenuItem(MenuItem.itemType.Gelati, "Pallina", 2.5));
        
        assertEquals(3, bill.getOrderPrice(smallBill, customer, LocalTime.now()), 0);   
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

    @Test
    public void totalGelatiAndBudini_expectedBehaviour() {
        assertEquals(93.5, bill.totalGelatiAndBudini(check), 0);
    }

    @Test
    public void isEligible_minorUserDifferentHour_expectedBehaviouor() {
        User minor1 = new User("Giovanni Verdi", LocalDate.of(2005, 11, 3));
        User minor2 = new User("Giovanni Rossi", LocalDate.of(2005, 11, 4));
        User minor3 = new User("Giovanni Bianchi", LocalDate.of(2005, 11, 5));
        User minor4 = new User("Giovanni Blu", LocalDate.of(2005, 11, 6));
        
        assertEquals(true, bill.isEligible(minor1, LocalTime.of(18, 0)));
        assertEquals(true, bill.isEligible(minor2, LocalTime.of(19, 0)));
        assertEquals(true, bill.isEligible(minor3, LocalTime.of(18, 59)));
        assertEquals(false, bill.isEligible(minor4, LocalTime.of(19, 1)));
    }

    @Test
    public void isEligible_AdultUserDifferentHour_expectedBehaviouor() {
        User adult1 = new User("Luigi Verdi", LocalDate.of(1956, 11, 3));
        User adult2 = new User("Luigi Rossi", LocalDate.of(1999, 11, 4));
        User adult3 = new User("Luigi Bianchi", LocalDate.of(2002, 11, 5));
        User adult4 = new User("Luigi Blu", LocalDate.of(1990, 11, 6));
        
        assertEquals(false, bill.isEligible(adult1, LocalTime.of(18, 0)));
        assertEquals(false, bill.isEligible(adult2, LocalTime.of(19, 0)));
        assertEquals(false, bill.isEligible(adult3, LocalTime.of(18, 59)));
        assertEquals(false, bill.isEligible(adult4, LocalTime.of(19, 1)));
    }

    @Test
    public void getOrderPrice_goesOnUntil10FreeChecks_() throws TakeAwayBillException{
        for(int i = 0; bill.freeChecks < 10; ++i) {
            User mock = new User(String.valueOf(i), LocalDate.of(2007, 5, 5));
            int beforeCheck = bill.freeChecks;
            double total = bill
                .getOrderPrice(check, mock, LocalTime.of(18, 35));
            int afterCheck = bill.freeChecks;
            if(afterCheck-1 == beforeCheck)
                assertEquals(0, total, 0);
            else
                assertEquals((96 - 1.75) * 0.9, total, 0);
        }
    }

    @Test
    public void getOrderPrice_goesOnUntil1FreeCheck_() throws TakeAwayBillException{
        double total = 1;
        for(int i = 0; bill.freeChecks == 0; ++i) {
            User mock = new User(String.valueOf(i), LocalDate.of(2007, 5, 5));
            total = bill
                .getOrderPrice(check, mock, LocalTime.of(18, 35));
            if(bill.freeChecks == 0)
            assertEquals((96 - 1.75) * 0.9, total, 0);
        }
        assertEquals(0, total, 0);
    }

    @After
    public void resetTakeAwayBillImplObj() {
        bill = new TakeAwayBillImpl();
    }

}
