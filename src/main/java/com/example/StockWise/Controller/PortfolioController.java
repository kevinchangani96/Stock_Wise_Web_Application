package com.example.StockWise.Controller;

import com.example.StockWise.Model.Portfolio;
import com.example.StockWise.Model.dto.PortfolioDto;
import com.example.StockWise.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/get")
    private PortfolioDto getProtfolio(@RequestParam String email){
        return portfolioService.getProtfolio(email);
    }

}
