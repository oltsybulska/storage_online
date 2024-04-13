package org.example.storage_online.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.example.storage_online.model.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private List<Item> itemList = new ArrayList<>();

    public List<Item> getAllItems() {
        return itemList;
    }

    public static Item addItem(@RequestBody Item item) {
        return ItemService.addItem(item);
    }
}
