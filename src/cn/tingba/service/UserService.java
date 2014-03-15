package cn.tingba.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.output.NullWriter;

import cn.tinba.comon.Const;
import cn.tingba.dao.UserDao;
import cn.tingba.dao.impl.UserDaoImpl;
import cn.tingba.entity.User;

/**
 * 实现：
 * 	注册时，新用户名验证；
 * 	登陆时，用户名、密码校验
 *  按用户名取用户
 * */
public class UserService {
	UserDao dao = new UserDaoImpl();
	
	public boolean exist(String username) {
		return findByNamePass(username, null) != null;
	}
	
	public User findByNamePass(String username, String password) {
		List<User> res = find(username, password);
		if( res == null || res.size() == 0 ) return null;
		if( res.size() > 1 ) System.err.println("用户名超过一个！");
		return res.get(0);
	}
	
	public User getUser(String username) {
		User user = new User();
		user.setUsername(username);
		return dao.search(user).get(0);
	}
	
	public boolean useSpace(User user, int size) {
		if( user.getUsed() + size > user.getSpace() ) {
			System.err.println("用户空间不足");
			return false;
		}
		user.setUsed( user.getUsed() + size );
		dao.update(user);
		return true;
	}
	
	public User register(String username, String password, String sex, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setEmail(email);
		// 以下属性在dynamic-insert dynamic-update模式下可以不填
		user.setLevel(Const.DEAULT_USER_LEVEL);
		user.setSpace(Const.DEAULT_USER_SPACE);
		user.setUsed(0);
		dao.add(user);
		return user;
	}
	
	List<User> find(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		List<User> resList = dao.search(user);
		return resList;
	} 
}
