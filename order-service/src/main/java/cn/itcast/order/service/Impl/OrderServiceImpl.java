package cn.itcast.order.service.Impl;

import cn.itcast.order.constants.RestTemplateConstants;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import cn.itcast.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order queryOrderById(Long orderId) {
        //1.先查询订单信息
        Order order = this.getById(orderId);
        if (order == null){
            return null;
        }
        //2.根据订单信息中的userId查询用户信息
        String url= RestTemplateConstants.USER_URL_PREFIX+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //3.封装用户信息
        order.setUser(user);
        //4..返回订单
        return order;
    }
}
