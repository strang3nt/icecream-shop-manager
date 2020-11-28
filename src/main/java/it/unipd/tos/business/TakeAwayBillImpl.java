////////////////////////////////////////////////////////////////////
// [ALESSANDRO] [FLORI] [1186916]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImpl implements TakeAwayBill{

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) {
        double check = itemsOrdered
            .stream()
            .mapToDouble((i) -> i.getPrice())
            .sum();

        return check;
    }
}
