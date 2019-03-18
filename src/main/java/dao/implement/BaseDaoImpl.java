package dao.implement;

import bean.PageBean;
import dao.BaseDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T getById(Class<T> entityClass, String id) {
        List<T> list=sessionFactory.getCurrentSession().createQuery("select en from "+entityClass.getSimpleName()
                +" en where en.id=?1").setParameter(1,id).getResultList();
        return list.size()==0?null:list.get(0);
    }

    @Override
    public T getById(Class<T> entityClass, int id) {
        List<T> list=sessionFactory.getCurrentSession().createQuery("select en from "+entityClass.getSimpleName()
                +" en where en.id=?1").setParameter(1,id).getResultList();
        return list.size()==0?null:list.get(0);
    }

    @Override
    public T getById(Class<T> entityClass, long id) {
        List<T> list=sessionFactory.getCurrentSession().createQuery("select en from "+entityClass.getSimpleName()
                +" en where en.id=?1").setParameter(1,id).getResultList();
        return list.size()==0?null:list.get(0);
    }

    @Override
    public Serializable save(T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void delete(Class<T> entityClass, String id) {
        sessionFactory.getCurrentSession().createQuery("delete from "+entityClass.getSimpleName()+" en where en.id =?1")
        .setParameter(1,id).executeUpdate();
    }

    @Override
    public void delete(Class<T> entityClass, int id) {
        sessionFactory.getCurrentSession().createQuery("delete from "+entityClass.getSimpleName()+" en where en.id =?1")
                .setParameter(1,id).executeUpdate();
    }

    @Override
    public List<T> getAll(Class<T> entityClass) {

        return find("select en from "+entityClass.getSimpleName()+" en");
    }

    @Override
    public long count(Class<T> entityClass) {
        return Integer.valueOf(String.valueOf(sessionFactory.getCurrentSession().createQuery("select count(*) from "
                +entityClass.getSimpleName()).getSingleResult()));
    }

    @Override
    public void BatchToSave(List<T> entities) {
        for(int i=0;i<entities.size();++i){
            sessionFactory.getCurrentSession().save(entities.get(i));
            if (i%20==0){
                sessionFactory.getCurrentSession().flush();
                sessionFactory.getCurrentSession().clear();
            }
        }
    }

    @Override
    public void BatchToUpdate(List<T> entities) {
        for (T entity : entities) {
            sessionFactory.getCurrentSession().update(entity);
        }
    }

    @Override
    public void BatchToDelete(Class<T> entityClass, List<String> id) {
        Query query=sessionFactory.getCurrentSession().createQuery("delete from " + entityClass.getSimpleName() + " en where en.id=?1");
        for(String i:id) {
            query.setParameter(1,i);
        }
        query.executeUpdate();
    }

    /**
    * 根据hql语句查询*/
    protected List<T>find(String hql){
        return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
    }
    /**
     * 根据带占位符的hql查询实体*/
    protected List<T>find(String hql,Object... params){
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        for(int i=0;i<params.length-1;++i){
            query.setParameter(i,params[i]);
        }
        return query.getResultList();
    }
    /**
     * @param pageNumber 页号
     * @param pageSize 页面大小
     * 根据hql语句实现分页查询*/
    protected PageBean<T> findByPage(String hql,int pageNumber,int pageSize){
        PageBean<T>pageBean=new PageBean<T>();
        pageBean.setCurrentPage(pageNumber);
        pageBean.setPageSize(pageSize);
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        pageBean.setTotalRecords(query.getMaxResults());
        pageBean.setTotalPageNo(Math.round(Math.ceil((double) query.getMaxResults()/pageSize)));
        pageBean.setData(query.setFirstResult(pageNumber-1).setMaxResults(pageSize).getResultList());
        return pageBean;
    }
    /**
     * @param pageNumber 页号
     * @param pageSize 页面大小
     * @param parms  根据具体参数查询
     */
    protected PageBean<T> findByPage(String hql, int pageNumber, int pageSize, Object...parms){
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        for(int i=1;i<=parms.length;++i)
            query.setParameter(i,parms[i-1]);
        PageBean<T>pageBean=new PageBean<T>();
        pageBean.setCurrentPage(pageNumber);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecords(query.getMaxResults());
        pageBean.setTotalPageNo(Math.round(Math.ceil((double) query.getMaxResults()/pageSize)));
        pageBean.setData(query.setFirstResult(pageNumber-1).setMaxResults(pageSize).getResultList());
        return pageBean;
    }
}
