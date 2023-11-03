package com.example.StockWise.Controller;

import com.example.StockWise.Model.dto.StockActivity;
import com.example.StockWise.Model.dto.StockOrderRequest;
import com.example.StockWise.Services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;
    @PostMapping("/buyStock")
    private StockActivity BuyStock(@RequestBody StockOrderRequest order, @RequestParam String email) throws  IOException {
        return activityService.BuyStock(order,email);
    }

    @PostMapping("/sellStock")
    private StockActivity SellStock(@RequestBody StockOrderRequest order,@RequestParam String email) throws IOException{
        return activityService.SellStock(order,email);
    }



}
