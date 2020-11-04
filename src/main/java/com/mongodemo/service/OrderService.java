package com.mongodemo.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodemo.pojo.Logistics;
import com.mongodemo.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {
    @Autowired
    MongoTemplate mongoTemplate;

    // 添加订单
    public void addorder(Order order){
        mongoTemplate.insert(order,"order");
    }

    // 更新物流
    public void addLogisticAndUpdateStatus(Logistics logistics){
        String status = logistics.getOperation();
        Query query = new Query(Criteria.where("_id").is(logistics.getOrderId()));
        // Update对象用来设置更新的字段和数据，其set()方法用来直接更新对应字段的值，而push()方法用来向对应数组中追加数值。
        Update update = new Update();
        update.set("status",status);// 更新状态
        update.push("logistics",logistics);
        // 用来实现更新操作的提交，如果MongoDB中不存在该数据那么就插入到MongoDB中。
        mongoTemplate.upsert(query,update,Order.class);
    }

    // 通过id查询物流
    public Order getOrderById(int id){
        Query query = new Query(Criteria.where("_id").is(id));
        // 第一个参数为查询的条件，第二个参数为查询结果转换成Java对象的类型
        Order order = mongoTemplate.findOne(query,Order.class);
        return order;
    }

    // 根据id删除记录
    public boolean deleteOrderById(int id){
        Query query = new Query(Criteria.where("_id").is(id));
        // 第一个参数为待删除标记的定位条件，第二个参数为待删除数据的Java类型，第三个参数为待删除数据所在集合的名称。
        DeleteResult result = mongoTemplate.remove(query, Order.class, "order");
        return result.wasAcknowledged();
    }

    // 查询所有订单
    public List<Order> getAllOrder(){
        // 第一个参数为查询结果转为Java的类型，第二个参数为待查询的集合
        List<Order> list = mongoTemplate.findAll(Order.class, "order");
        return list;
    }
}
