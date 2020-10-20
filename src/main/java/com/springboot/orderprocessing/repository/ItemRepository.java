package com.springboot.orderprocessing.repository;

import com.springboot.orderprocessing.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {

}
