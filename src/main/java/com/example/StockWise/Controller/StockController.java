package com.example.StockWise.Controller;


import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Services.StockMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    StockMarketService stockMarketService;

    @GetMapping("/getinfostock")
    public StockData getStockData(@RequestParam String symbol) throws IOException {
        return stockMarketService.getStockData(symbol);
    }
}