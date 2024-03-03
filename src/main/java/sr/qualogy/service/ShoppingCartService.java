package sr.qualogy.service;

import jakarta.persistence.EntityManager;
import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.ShoppingCart;
import sr.qualogy.repository.ShoppingCartRepository;

public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService() {
        this.shoppingCartRepository = new ShoppingCartRepository(JPAConfiguration.getEntityManager());
    }

}
