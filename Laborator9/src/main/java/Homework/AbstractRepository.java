package Homework;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractRepository<T> {

    private final Class<T> entityClass;

    @PersistenceContext
    public EntityManager entityManager;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }

    public void deleteAll() {
        entityManager.createQuery("DELETE FROM " + entityClass.getSimpleName()).executeUpdate();
    }

    public List<T> findByName(String namePattern) {
        return entityManager.createNamedQuery(entityClass.getSimpleName() + ".findByName", entityClass)
                .setParameter("name", "%" + namePattern + "%")
                .getResultList();
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }
}


