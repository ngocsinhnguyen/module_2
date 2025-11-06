package mvc.controller;

import mvc.common.exception.IdDuplicatedException;
import mvc.entity.Dish;
import mvc.service.DishService;
import mvc.service.IDishService;
import mvc.service.IService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuBufferController {
    private IDishService iService = new DishService();

    public List<Dish> getMenuBuffer() {

        return iService.findAll();
    }

    public void add(Dish dish) {
        iService.save(dish);
    }

    public boolean checkDuplicateId(int id) throws IdDuplicatedException {
        return iService.checkDuplicateId(id);
    }
}
