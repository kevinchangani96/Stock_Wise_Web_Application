package com.example.StockWise.Repository;

import com.example.StockWise.Controller.Activity;
import com.example.StockWise.Model.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepo extends JpaRepository<StockOrder,Long> {

}
