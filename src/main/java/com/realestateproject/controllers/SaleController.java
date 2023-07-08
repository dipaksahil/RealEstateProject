package com.realestateproject.controllers;

import com.realestateproject.payload.SaleDto;
import com.realestateproject.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;
    // http://localhost:8080/api/sales
    @PostMapping
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto) {
        SaleDto createdSale = saleService.createSale(saleDto);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }
    // http://localhost:8080/api/sales
    @GetMapping
    public ResponseEntity<List<SaleDto>> getAllSales() {
        List<SaleDto> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
    // http://localhost:8080/api/sales/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> getSaleById(@PathVariable Long id) {
        SaleDto sale = saleService.getSaleById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable Long id, @RequestBody SaleDto saleDto) {
        SaleDto updatedSale = saleService.updateSale(id, saleDto);
        return new ResponseEntity<>(updatedSale, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}