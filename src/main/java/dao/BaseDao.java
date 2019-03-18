package dao;

import com.sun.istack.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public interface BaseDao<T> {
    /**
     * 根据ID加载实体
     */
    T getById(Class<T> entityClass, @NotNull String id);

    T getById(Class<T> entityClass, int id);

    T getById(Class<T> entityClass, long id);

    /**
     * 保存实体
     */
    Serializable save(T entity);

    /**
     * 更新实体
     */
    void update(T entity);

    void saveOrUpdate(T entity);

    /**
     * 删除实体
     */
    void delete(T entity);

    /**
     * 根据ID删除
     */
    void delete(Class<T> entityClass, @NotNull String id);
    void delete(Class<T> entityClass,int id);

    /**
     * 获得所有实体
     */
    List<T> getAll(Class<T> entityClass);

    /**
     * 获得实体总数
     */
    long count(Class<T> entityClass);

    /**
     * 批量保存
     */
    void BatchToSave(List<T> entities);

    /**
     * 批量更新
     */
    void BatchToUpdate(List<T> entities);

    /**
     * 批量删除
     */
    void BatchToDelete(Class<T> entityClass, @NotNull List<String> id);
}
