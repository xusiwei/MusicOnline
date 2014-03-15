package cn.tingba.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

public interface DaoFace<T> {

	// Ôö
	public void add(T item);

	// É¾
	public void delete(T item);

	// ¸Ä
	public void update(T item);

	// ²é
	public T findById(Serializable id); // by id

	public List<T> search(T cond); // by column

	public List<T> findAll(); // find all
	
	public Session getSession();
}