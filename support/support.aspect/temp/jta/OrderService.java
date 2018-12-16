package in.java.support.aspect.jta;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface OrderService {

    void persistOrders(List<OrderItem> orderItems);

    List<OrderItem> getAllOrders();
}