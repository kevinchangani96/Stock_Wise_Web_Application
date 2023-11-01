package com.example.StockWise.Services;

import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Services.Utility.ApiCallingMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockMarketService {
    public StockData getStockData(String symbol) throws IOException {
        return ApiCallingMethod.apiCallingMethodByStock(symbol);
    }
}