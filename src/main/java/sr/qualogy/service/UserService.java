package sr.qualogy.service;

import jakarta.persistence.EntityManager;
import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.User;
import sr.qualogy.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository(JPAConfiguration.getEntityManager());
    }

}
