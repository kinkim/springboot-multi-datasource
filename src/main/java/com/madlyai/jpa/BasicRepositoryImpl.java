package com.madlyai.jpa;

import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.ResultTransformer;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Hone on 2016/8/26.
 */
public class BasicRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BasicRepository<T, ID> {
    private final EntityManager entityManager;
    private final Class<T> entityClass;
    private final Transformer transformer = new Transformer();

    public BasicRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityClass = entityInformation.getJavaType();
    }
    public BasicRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }
    @Override
    public List<T> queryEntityListBySql(String sql, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql, this.entityClass);
        setParameter(query, params);
        return query.getResultList();
    }
    @Override
    public List<T> queryEntityListBySql(String sql, int start, int length, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql, this.entityClass);
        setParameter(query, params);
        setPage(query, start, length);
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql);
        if (params != null) {
            setParameter(query, params);
        }
        ((NativeQuery) query.unwrap(NativeQuery.class)).setResultTransformer(this.transformer);
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql);
        setParameter(query, params);
        setPage(query, start, length);
        ((NativeQuery) query.unwrap(NativeQuery.class)).setResultTransformer(this.transformer);
        return query.getResultList();
    }
/*    @Override
    public Long queryEntityListCountBySql(String sql, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql);
        setParameter(query, params);
        Object result = query.getSingleResult();
        if (result != null) {
            return Long.valueOf(result.toString());
        }
        return Long.valueOf(0L);
    }*/
    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql);
        if (params != null) {
            setParameter(query, params);
        }
        return query.executeUpdate();
    }



    private void setParameter(Query query, Map<String, Object> params) {
        if (params != null) {
            Iterator it = params.entrySet().iterator();
            Map.Entry en = null;
            while (it.hasNext()) {
                en = (Map.Entry) it.next();
                query.setParameter((String) en.getKey(), en.getValue());
            }
        }
    }

    private void setPage(Query query, int start, int length) {
        if (length > 0) {

            query.setFirstResult(start);

            query.setMaxResults(length);
        }
    }

    private class Transformer implements ResultTransformer {
        private static final long serialVersionUID = -6769467458218159371L;

        private Transformer() {
        }

        public Object transformTuple(Object[] tuple, String[] aliases) {
            Map map = new HashMap();
            int i = 0;
            for (String name : aliases) {
                map.put(name, tuple[(i++)]);
            }
            return map;
        }

        public List transformList(List collection) {
            return collection;
        }
    }
}
