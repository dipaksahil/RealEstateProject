package com.realestateproject.service;

import com.realestateproject.payload.SaleDto;

import java.util.List;

public interface SaleService {
    public SaleDto createSale(SaleDto saleDto);
    public List<SaleDto> getAllSales();
    public SaleDto getSaleById(Long id);
    public SaleDto updateSale(Long id, SaleDto saleDto);
    public void deleteSale(Long id);
}
