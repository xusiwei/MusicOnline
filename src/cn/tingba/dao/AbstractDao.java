package cn.tingba.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.tingba.util.HibernateSessionFactory;

public abstract class AbstractDao {
	static Session session = null;
	
	protected AbstractDao() {
		if( session == null ) { // 懒汉式 单例模式
			session = HibernateSessionFactory.getSession();
			System.err.println("获取Hibernate.Session...");
		}
	}
	
//	protected void finalize() throws Throwable {
////		session.close();
//		System.err.println("DAO被回收！关闭Hibernate.session...");
//	}
	
	protected Session getSession() {
		return session;
	}
	
	/**
	 * 添加数据
	 * not-null字段必须有值
	 * @param object
	 */
	protected void insert(Object object){
		Transaction tran=null;
		//获取session
//		Session session=HibernateSessionFactory.getSession();
		try{
			//开始事务
			tran=session.beginTransaction();
			//持久化操作
		    session.save(object); // **
		    //提交事务
		    tran.commit();
		}catch (Exception e) {
			if(tran!=null){
				//事务回滚
				tran.rollback();
			}
			e.printStackTrace();
		}finally{
			//关闭session
//			session.close();
		}		
	}	
	
	/**
	 * 加载数据
	 * 仅按id字段查询
	 * @param cla
	 * @param id
	 * @return
	 */
	protected Object get(Class cla,Serializable id){
		Object object=null;
//		Session session=HibernateSessionFactory.getSession();
		try{
			object=session.get(cla, id); // **
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
//			session.close();
		}
		return object;
	}	
	
	/**
	 * 删除数据
	 * @param object
	 */
	protected void remove(Object object){
		Transaction tran=null;
//		Session session=HibernateSessionFactory.getSession();
		try{
			tran=session.beginTransaction();
		    session.delete(object); // **
		    tran.commit();
		}catch (Exception e) {
			if(tran!=null){
				tran.rollback();
			}
			e.printStackTrace();
		}finally{
//			session.close();
		}
	}	
	
	/**
	 * 修改数据
	 * @param object
	 */
	protected void rewrite(Object object){
		Transaction tran=null;
//		Session session=HibernateSessionFactory.getSession();
		try{
			tran=session.beginTransaction();
		    session.update(object); // **
		    tran.commit();
		}catch (Exception e) {
			if(tran!=null){
				tran.rollback();
			}
			e.printStackTrace();
		}finally{
//			session.close();
		}
	}	
	
	/**
	 * 查询数据
	 * 按非null字段查询
	 * @param cla
	 * @param condition
	 * @return
	 */
	protected List find(Class cla,Object condition){
//		Session session=null;
		List list=null;
		try {
//			session = HibernateSessionFactory.getSession();
			list = session.createCriteria(cla).add(Example.create(condition)).list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
//			session.close();
		}
		return list;		
	}

}
