package cn.itcast.order.service.Impl;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    /*@Autowired
    private RestTemplate restTemplate;*/
    @Autowired
    private UserClient userClient;

    @Override
    public Order queryOrderById(Long orderId) {
        //1.先查询订单信息
        Order order = this.getById(orderId);
        if(order==null){
            return null;
        }
        //2.利用Feign客户端调用用户服务查询用户信息
        User user = userClient.findById(order.getUserId());
        //3.封装用户信息
        order.setUser(user);
        //4.返回订单
        return order;
    }


    /*@Override
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
    }*/
}
