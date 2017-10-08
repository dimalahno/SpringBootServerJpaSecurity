package myapplication.dao.daoimpl;


import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class AbstractDao<PK extends Serializable, T> {

    @PersistenceContext
    EntityManager entityManager;

    private final Class<T> persistentClass;


    @SuppressWarnings("unchecked")
    protected AbstractDao() {
        this.persistentClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return entityManager.find(persistentClass, key);
    }

    public void persist(T entity){
        entityManager.persist(entity);
    }

    public List<T> getAll(){
        String genericClassName = persistentClass.toGenericString();
        genericClassName = genericClassName.substring(genericClassName.lastIndexOf('.') + 1);
        String hql = "FROM " + genericClassName;
        return entityManager.createQuery(hql).getResultList();
    }

    public void update(T entity){
        entityManager.merge(entity);
    }

    public void deleteByKey(PK id){
        T entity = entityManager.find(persistentClass, id);
        entityManager.remove(entity);
    }

    protected Session getSession() {
        return (Session) entityManager.getDelegate();
    }
}
