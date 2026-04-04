package cn.itcast.order.service;

import cn.itcast.order.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {
    Order queryOrderById(Long orderId);
}
