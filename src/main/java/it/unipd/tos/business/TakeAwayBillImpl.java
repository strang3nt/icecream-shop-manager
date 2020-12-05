////////////////////////////////////////////////////////////////////
// [ALESSANDRO] [FLORI] [1186916]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.itemType;

public class TakeAwayBillImpl implements TakeAwayBill {

    int freeChecks;
    private final Set<User> users = new HashSet<>();

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user,
    LocalTime time) 
    throws TakeAwayBillException{
        double check = itemsOrdered
            .stream()
            .mapToDouble((i) -> i.getPrice())
            .sum();
        
        if(countItem(itemsOrdered, itemType.Gelati) > 5) {
            check -= cheapestIceCream(itemsOrdered).getPrice()/2;
        }

        if(totalGelatiAndBudini(itemsOrdered) > 50) {
            check *= 0.9;
        }

        if(itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Ordinati più di 30 elementi");
        }

        if(check < 10) {
            check += 0.5;
        }

        Random rnd = new Random();
        boolean isFree = false;
        if(this.isEligible(user, time) && freeChecks < 10) {
            isFree = rnd.nextBoolean();
            if(isFree) {
                freeChecks++;
            }
        }

        return check * (isFree ? 0 : 1);
    }

    boolean isEligible(User user, LocalTime time) {
        boolean result = false;
        if((time.getHour() == 18 || time.compareTo(LocalTime.of(19, 0)) == 0)
            && !user.isAdult()
            && !users.contains(user)) {
            users.add(user);
            result = true;
        }
        return result;
    }

    int countItem(List<MenuItem> itemsOrdered, itemType item) {
        return (int) itemsOrdered
            .stream()
            .filter(i -> i.getCategory() == item)
            .count();
    }

    MenuItem cheapestIceCream(List<MenuItem> itemsOrdered) {
        return itemsOrdered
            .stream()
            .filter(i -> i.getCategory() == itemType.Gelati)
            .min(Comparator.comparing(MenuItem::getPrice))
            .get();
    }

    double totalGelatiAndBudini(List<MenuItem> itemsOrdered) {
        return itemsOrdered
            .stream()
            .filter(
                i -> 
                    i.getCategory() == itemType.Gelati || 
                    i.getCategory() == itemType.Budini
                )
            .mapToDouble(i -> i.getPrice())
            .sum();
    }
}
