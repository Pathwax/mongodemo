package com.mongodemo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private int id;//订单id
    private String status;//状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date orderTime;//下单时间
    private String shipper;//发货人
    private String shippingAddress;//发货地址
    private long shipperPhone;//发货人手机
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date shipTime;//发货时间
    private String recevier;//接收人
    private String recevierAddress;//接收地址
    private long receviePhone;//接收人号码
    private List<Logistics> logistics;//物流信息
}
