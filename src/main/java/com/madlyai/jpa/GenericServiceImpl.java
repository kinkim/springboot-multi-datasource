package com.madlyai.jpa;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    @Override
    public List<T> queryEntityListBySql(String sql, Map<String, Object> params) {
        return getRepository().queryEntityListBySql(sql, params);
    }

    @Override
    public Optional<T> findOne(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public List<T> queryEntityListBySql(String sql, int start, int length, Map<String, Object> params) {
        return getRepository().queryEntityListBySql(sql, start, length, params);
    }

    @Override
    public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> params) {
        return getRepository().queryListBySql(sql, params);
    }

    @Override
    public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Map<String, Object> params) {
        return getRepository().queryListBySql(sql, params);
    }

    @Override
    public Long queryEntityListCountBySql(String sql, Map<String, Object> params) {
        return getRepository().queryEntityListCountBySql(sql, params);
    }

    @Override
    public Map<String, Object> queryEntityListAndCountBySql(String sql, int start, int length, Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("list", this.queryEntityListBySql(sql, start, length, params));
        result.put("count", this.queryEntityListCountBySql("select count(1) from (" + sql + ") t_count", params));

        return result;
    }

    @Override
    public Map<String, Object> queryMapListAndCountBySql(String sql, int start, int length, Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("list", this.queryListBySql(sql, start, length, params));
        result.put("count", this.queryEntityListCountBySql("select count(1) from (" + sql + ") t_count", params));

        return result;
    }

    @Override
    public void delete(ID paramID) {
        getRepository().deleteById(paramID);
    }

    @Override
    public void delete(T paramT) {
        getRepository().delete(paramT);
    }

    @Override
    public void delete(List<T> entities) {
        getRepository().deleteAll(entities);
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        return getRepository().executeSql(sql, params);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

}
