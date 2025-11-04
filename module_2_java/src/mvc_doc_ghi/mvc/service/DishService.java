package mvc.service;

import mvc.common.exception.IdDuplicatedException;
import mvc.entity.Dish;
import mvc.repository.DishRepository;

import java.util.List;

public class DishService implements IDishService{
    private DishRepository dishRepository = new DishRepository();
    @Override
    public List<Dish> findAll() {
        return dishRepository.getDishes();
    }

    @Override
    public void save(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Dish findById(int id) {
        return null;
    }

    @Override
    public void update(Dish dish) {

    }

    @Override
    public boolean checkDuplicateId(int id) throws IdDuplicatedException {
        List<Dish> dishes = dishRepository.getDishes();
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                throw new IdDuplicatedException("Id trùng lặp");
            }
        }
        return false;
    }
}
