package org.example.storage_online.service;

import org.example.storage_online.model.Item;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@Service

public class ExcelService {
    private static final String FILE_NAME = "shop.xlsx";

    // Метод для отримання всіх книг з Excel файлу
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        try (FileInputStream excelFile = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String name = row.getCell(0).getStringCellValue();
                String designer = row.getCell(1).getStringCellValue();
                double price = row.getCell(2).getNumericCellValue();
                Item item = new Item(name, designer, price);
                itemList.add(item);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return itemList;
    }



    public void addItem(String name, String designer, double price) throws IOException {
        try (FileInputStream excelFile = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(name);
            row.createCell(1).setCellValue(designer);
            row.createCell(2).setCellValue(price);
            try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
                workbook.write(outputStream);
            }
        }
    }


    public void updateItem(String oldName, String oldDesigner, String newName, String newDesigner, double newPrice) throws IOException {
        try (FileInputStream excelFile = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String name = row.getCell(0).getStringCellValue();
                String designer = row.getCell(1).getStringCellValue();
                if (name.equals(oldName) && designer.equals(oldDesigner)) {
                    row.getCell(0).setCellValue(newName);
                    row.getCell(1).setCellValue(newDesigner);
                    row.getCell(2).setCellValue(newPrice);
                    break;
                }
            }
            try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
                workbook.write(outputStream);
            }
        }
    }


    public void deleteItem(String name, String designer) throws IOException {
        try (FileInputStream excelFile = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String cellName = row.getCell(0).getStringCellValue();
                String cellDesigner = row.getCell(1).getStringCellValue();
                if (cellName.equals(name) && cellDesigner.equals(designer)) {
                    sheet.removeRow(row);
                    break;
                }
            }
            try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
                workbook.write(outputStream);
            }
        }
    }
}
