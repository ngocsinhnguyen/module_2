package mvc.common.compare;

import mvc.entity.Dish;

import java.util.Comparator;

public class DishCompareByName implements Comparator<Dish> {
    @Override
    public int compare(Dish o1, Dish o2) {
        return o1.compareTo(o2);

    }
}
