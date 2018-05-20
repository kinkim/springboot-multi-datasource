package com.madlyai.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 邓华
 */
public abstract interface GenericService<T, ID extends Serializable> {
    /**
     * 获取基础的Repository
     *
     * @return
     */
    public abstract BasicRepository<T, ID> getRepository();

    public abstract List<T> getAll();

    public abstract Optional<T> findOne(ID id);

    public abstract List<T> queryEntityListBySql(String sql, Map<String, Object> paramMap);

    public abstract List<T> queryEntityListBySql(String sql, int paramInt1, int paramInt2, Map<String, Object> paramMap);

    public abstract List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> paramMap);

    List<Map<String, Object>> queryListBySql(String sql, int start, int length, Map<String, Object> params);

    public abstract Long queryEntityListCountBySql(String sql, Map<String, Object> paramMap);

    public abstract Map<String, Object> queryEntityListAndCountBySql(String sql, int start, int length, Map<String, Object> paramMap);

    Map<String, Object> queryMapListAndCountBySql(String sql, int start, int length, Map<String, Object> params);

    public abstract void delete(ID paramID);

    public abstract void delete(T paramT);

    public abstract void delete(List<T> entities);

    public abstract int executeSql(String sql, Map<String, Object> paramMap);

}