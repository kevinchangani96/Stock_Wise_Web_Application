package com.example.StockWise.Services;

import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Model.dto.StockDecision;
import com.example.StockWise.Services.Utility.ApiCallingMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StockMarketService {
    public StockData getStockData(String symbol) throws IOException {
        return ApiCallingMethod.apiCallingMethodByStock(symbol);
    }

    public List<StockData> getAllStockInfoData() throws IOException {
        return ApiCallingMethod.getAllStockData();
    }

    public StockDecision getStockDecisionByStockName(String symbol) throws IOException {
        return ApiCallingMethod.getStockDecisionByStockName(symbol);
    }
}
