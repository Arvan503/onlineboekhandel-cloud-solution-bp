package sr.qualogy.service;

import jakarta.persistence.EntityManager;
import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.Order;
import sr.qualogy.repository.OrderRepository;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository(JPAConfiguration.getEntityManager());
    }

}
