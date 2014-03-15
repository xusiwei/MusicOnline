package cn.tingba.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import cn.tingba.dao.AbstractDao;
import cn.tingba.dao.UserLogDao;
import cn.tingba.entity.UserLog;

public class UserLogDaoImpl extends AbstractDao implements UserLogDao {
	
	public void add(UserLog item) {
		super.insert(item);
	}

	public void delete(UserLog item) {
		super.remove(item);
	}

	public List<UserLog> findAll() {
		return search(new UserLog());
	}

	public UserLog findById(Serializable id) {
		return (UserLog) super.get(UserLog.class, id);
	}

	public List<UserLog> search(UserLog cond) {
		return super.find(UserLog.class, cond);
	}

	public void update(UserLog item) {
		super.rewrite(item);
	}

	public Session getSession() {
		return super.getSession();
	}

}
