package org.example.storage_online.controller;
import org.example.storage_online.service.ItemService;
import org.example.storage_online.model.Item;
import org.example.storage_online.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
public class ItemController {
    @Autowired
    private ExcelService excelService;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return excelService.getAllItems();
    }

    @PostMapping("/items")
    public void addItem(@RequestBody Item item) throws IOException {
        String name = item.getName();
        String designer = item.getDesigner();
        double price = item.getPrice();
        excelService.addItem(name, designer, price);
    }
}
