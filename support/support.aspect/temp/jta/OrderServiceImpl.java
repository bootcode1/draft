package in.java.support.aspect.jta;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private Dao<OrderItem> dao;

    @Override
    public void persistOrders(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            long id = dao.save(orderItem);
            System.out.println("id generated: " + id);
        }
    }

    @Transactional
    @Override
    public List<OrderItem> getAllOrders() {
        return dao.loadAll();
    }
}