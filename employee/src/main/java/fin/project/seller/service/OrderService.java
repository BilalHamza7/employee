package fin.project.seller.service;

import fin.project.seller.data.Order;
import fin.project.seller.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrderDetails()
    {   return orderRepository.findAll();
    }
}
