package com.mongodemo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Logistics {
    private int orderId;//订单id
    private String operation;//操作
    private String operator;//操作员
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date operationTime;//操作时间
    private String address;//地址
    private String details;//备注细节
}
