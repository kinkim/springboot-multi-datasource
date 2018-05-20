package com.madlyai.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    public abstract List<T> queryEntityListBySql(String sql, Map<String, Object> paramMap);

    public abstract List<T> queryEntityListBySql(String sql, int start, int length, Map<String, Object> paramMap);

    public abstract List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> paramMap);

    public abstract List<Map<String, Object>> queryListBySql(String sql, int start, int length, Map<String, Object> paramMap);

    public abstract Long queryEntityListCountBySql(String sql, Map<String, Object> paramMap);

    public abstract int executeSql(String sql, Map<String, Object> paramMap);


}
