package Compulsory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";
    private static volatile EntityManagerFactorySingleton instance;
    private final EntityManagerFactory entityManagerFactory;

    private EntityManagerFactorySingleton() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManagerFactorySingleton getInstance() {
        if (instance == null) {
            synchronized (EntityManagerFactorySingleton.class) {
                if (instance == null) {
                    instance = new EntityManagerFactorySingleton();
                }
            }
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}

