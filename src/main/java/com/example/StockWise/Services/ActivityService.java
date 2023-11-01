package com.example.StockWise.Services;

import com.example.StockWise.Model.StockOrder;
import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.StockActivity;
import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Model.dto.StockOrderRequest;
import com.example.StockWise.Repository.ActivityRepo;
import com.example.StockWise.Repository.UserRepo;
import com.example.StockWise.Services.Utility.ApiCallingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ActivityService {
    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    UserRepo userRepo;

    public StockActivity BuyStock(StockOrderRequest order, String email) throws IOException {
        User  user = userRepo.findByUserEmail(email);
        StockData stockData = ApiCallingMethod.apiCallingMethodByStock(order.getStockName());
        StockOrder stockOrder = new StockOrder();
        StockActivity stockActivity = new StockActivity();
        double totalamount = order.getQuantity()*Double.parseDouble(stockData.getCurrentPrice());

        //this code will save all the details in stockorder table
        if (user.getStatus().equals("logged in")) {
            if (user.getFund() > totalamount) {
                stockOrder.setEmail(email);
                stockOrder.setStockName(order.getStockName());
                stockOrder.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));
                stockOrder.setQuantity(order.getQuantity());
                stockOrder.setUserName(user.getUserName());
                stockOrder.setTotalAmount(totalamount);
                stockOrder.setStatus("BUY");
                activityRepo.save(stockOrder);
                user.setFund(user.getFund() - totalamount);
                userRepo.save(user);
// this code for response
                stockActivity.setTotalAmount(totalamount);
                stockActivity.setStockName(order.getStockName());
                stockActivity.setQuantity(order.getQuantity());
                stockActivity.setStatus("BUY");
                stockActivity.setMessege("Success");
                stockActivity.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));
                return stockActivity;
            } else {
                stockActivity.setTotalAmount(0.0);
                stockActivity.setStockName(null);
                stockActivity.setQuantity(0);
                stockActivity.setStatus("Error");
                stockActivity.setMessege("Insufficient Balance");
                stockActivity.setStockPrice(0);
                return stockActivity;

            }
        }
        return  stockActivity;
    }
}
