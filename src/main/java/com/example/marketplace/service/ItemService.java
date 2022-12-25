package com.example.marketplace.service;

import com.example.marketplace.models.Item;
import com.example.marketplace.models.ItemResponse;
import com.example.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository productRepository;

    public Item add(ItemResponse response) {
        Item item = new Item(response.getName());
        productRepository.save(item);
        return item;
    }

    public void remove(Integer id) {
        Optional<Item> item = productRepository.findById(id);
        if(item.isPresent()){
            productRepository.deleteById(id);
        }
    }

    public Optional<Item> getById(Integer id) {
        return productRepository.findById(id);
    }

    public Iterable<Item> getAllItems() {
        return productRepository.findAll();
    }

    public Item save(Item item) {
        return productRepository.save(item);

    }
}
