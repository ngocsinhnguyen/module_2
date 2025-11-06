package mvc.service;

import mvc.common.exception.IdDuplicatedException;
import mvc.entity.Dish;

public interface IDishService extends IService<Dish> {


    boolean checkDuplicateId(int id) throws IdDuplicatedException;
}
