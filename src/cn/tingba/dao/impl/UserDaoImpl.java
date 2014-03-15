package cn.tingba.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import cn.tingba.dao.AbstractDao;
import cn.tingba.dao.UserDao;
import cn.tingba.entity.User;

public class UserDaoImpl extends AbstractDao implements UserDao {

	public void add(User item) {
		super.insert(item);
	}

	public void delete(User item) {
		super.remove(item);
	}

	public List<User> findAll() {
		return search(new User());
	}

	public User findById(Serializable id) {
		return (User)super.get(User.class, id);
	}

	public List<User> search(User cond) {
		return super.find(User.class, cond);
	}

	public void update(User item) {
		super.rewrite(item);
	}

	public Session getSession() {
		return super.getSession();
	}
}
