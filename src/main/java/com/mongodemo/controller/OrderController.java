package com.mongodemo.controller;

import com.mongodemo.pojo.Logistics;
import com.mongodemo.pojo.Order;
import com.mongodemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("addorder")
    public String addOrder(Order order){
        order.setStatus("发货中");
        order.setOrderTime(new Date());
        order.setShipTime(new Date());
        orderService.addorder(order);
        return "添加成功";
    }
    @PostMapping("updateorder")
    public String updateOrder(Logistics logistics){
        logistics.setOperationTime(new Date());
        orderService.addLogisticAndUpdateStatus(logistics);
        return "添加成功";
    }
    @GetMapping("getorderbyid")
    public Order getOrderById(int id){
        Order order = orderService.getOrderById(id);
        return order;
    }
    @GetMapping("deletebyid")
    public String deleteById(int id){
        return orderService.deleteOrderById(id)?"删除成功":"删除失败";
    }
    @GetMapping("getallorders")
    public Map<String,Object> getAllOrder(){
        Map<String,Object> map = new HashMap<>();
        List<Order> list = orderService.getAllOrder();
        map.put("code","0");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }
}

