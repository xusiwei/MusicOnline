package cn.tingba.dao.test;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;

import cn.tingba.dao.SongDao;
import cn.tingba.dao.UserDao;
import cn.tingba.dao.impl.SongDaoImpl;
import cn.tingba.dao.impl.UserDaoImpl;
import cn.tingba.entity.Song;
import cn.tingba.entity.User;

public class DaoTest {
	private static Logger logger = Logger.getLogger(DaoTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaoTest t = new DaoTest();
//		t.testUserDao();
//		t.testSongDao();
		t.testUserSongs();
	}
	
	Scanner stdin = new Scanner(System.in);
	PrintStream out = System.err;
	
	public void testUserSongs() {
		UserDao userDao = new UserDaoImpl();
		SongDao songDao = new SongDaoImpl();
		
		User user1 = userDao.findById(1);
		out.println("user1:" + user1);
		
		Set<Song> songSet = user1.getSongs();
		if (songSet != null) {
			for (Song s : songSet) {
				out.println(s);
			}
		}
		
		songSet.add(new Song(user1, "da", "haha", "test"));
		
		userDao.update(user1);
		
//		Song song = new Song();
//		song.setId(1);
////		song.setUser(user1);
//		List<Song> songList = songDao.search(song);
//		if (songList != null) {
//			for (Song s : songList) {
//				out.println(s);
//			}
//		}
	} 
	
	public void testUserDao() {	
		out.print("input username:");
		String name = stdin.next();
		User user = new User(name, "123");
		UserDao userDao = new UserDaoImpl();
		
		out.println("UserTest...");
		out.println("test add(item)...");
		out.print("item:");
		out.println(user);
//		System.out.println(String.format("%2d %10s %6s\n", user.getId(), user.getUsername(), user.getPassword()));
//		userDao.add(user);
//		userDao.add(user); // 将会写入所有字段
		
		out.println("test search(cond)...");
		out.print("cond:");
		out.println(user);
		// 按非null字段查询
		List<User> resList = userDao.search(user); 
		for(User u: resList) {
			out.println(u);
//			System.out.println(u.getId() + "\t" + u.getUsername() + "\t" + u.getPassword() + "\t"
//					+ u.getSex() + "\t" + u.getEmail());
		}
		
		out.println("test findById..");
		// 仅按id字段查询
		User resUser = userDao.findById(1);
		out.println("id==1:");
		out.println(resUser);
		
		out.println("test update...");
		resUser.setEmail("admin@ahpu.edu.cn");
		User newUser = new User();
		newUser.setId(1);
		newUser.setUsername("admin");
		newUser.setPassword("12345");
		newUser.setEmail("admin@189.cn");
		// update将会更新所有字段
		userDao.update(newUser);
		out.println(resUser);
		
		out.println("test delete(item)...");
		User user2 = new User();
		user2.setUsername(new String());
		user2.setPassword(new String());
		user2.setId(resList.get(0).getId());
		// delete仅需id即可，但not-null字段不能为null（可以随便new）
		userDao.delete(user2);

		out.println("test findAll...");
		// select * from user where 1=1
		for(User u: userDao.findAll()) {
			out.println(u);
//			System.out.println(u.getId() + "\t" + u.getUsername() + "\t" + u.getPassword() + "\t"
//					+ u.getSex() + "\t" + u.getEmail());
		}

		out.println("finish test...");
	}

	public void testSongDao() {
		Song song = new Song();
		
//		out.print("input song name:");
//		String name = stdin.next();
//		out.print("input song singer:");
//		String singer = stdin.next();
//		out.print("input song style:");
//		String style = stdin.next();
//		out.println("input song description:");
//		String desc = stdin.next();
//		
//		song.setName(name);
//		song.setSinger(singer);
//		song.setStyle(style);
//		song.setDescription(desc);
		
		User user = new User();
		user.setId(1);
		song.setUser(user);
//		UUID uuid = UUID.randomUUID();
//		song.setSavepath("/uplaod/" + uuid.toString());
//		Date now = new Date();
//		song.setUploadTime(new Timestamp(now.getTime()));
//		out.println(now.toLocaleString());
//		out.println(now.toString());
		
		SongDao dao = new SongDaoImpl();
		
//		out.println("test add(item)...");
//		out.print("item:");
//		out.println(song);
//		dao.add(song); // ADD
//		
//		dao.add(song); // ADD
		
		out.println("test findById(id)...");
		Song res = dao.findById(1); // findById
		out.println("res:");
		out.println(res);
		
		out.println("test search(cond)...");
		out.print("cond:");
		song.setUser(new UserDaoImpl().findById(1));
		out.println(song);
		for(Song s: dao.search(song)) {
			out.println(s);
		}
		
		out.println("这次要按歌名查找：");
		out.println("test search(cond)...");
		out.print("cond:");
		song.setName("da");
		out.println(song);
		for(Song s: dao.search(song)) {
			out.println(s);
		}		
		
		out.println("test findAll()...");
		for(Song s: dao.findAll()) { // findAll
			out.println(s);
		}
		
//		out.println("test update(item)...");
//		song.setName("haha");
//		out.println("item:");
//		out.println(song);
//		dao.update(song); // update
//		out.println(song);
//		
//		out.println("show all...");
//		for(Song s: dao.findAll()) { // findAll
//			out.println(s);
//		}
//		
//		out.println("test delete...");
//		dao.delete(song);
//		
//		out.println("show all...");
//		for(Song s: dao.findAll()) { // findAll
//			out.println(s);
//		}
//		
//		out.println("finish...");
	}
}
