package sr.qualogy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.qualogy.entity.ShoppingCart;

public class ShoppingCartRepository {

    private EntityManager entityManager;

    public ShoppingCartRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}