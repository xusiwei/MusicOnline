package cn.tingba.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import cn.tingba.dao.AbstractDao;
import cn.tingba.dao.SongDao;
import cn.tingba.dao.UserDao;
import cn.tingba.entity.Song;

public class SongDaoImpl extends AbstractDao implements SongDao {

	public void add(Song item) {
		super.insert(item);
	}

	public void delete(Song item) {
		super.remove(item);
	}

	public List<Song> findAll() {
		return search(new Song());
	}

	public Song findById(Serializable id) {
		return (Song) super.get(Song.class, id);
	}

	public List<Song> search(Song cond) {
		return super.find(Song.class, cond);
	}

	public void update(Song item) {
		super.rewrite(item);
	}

	public Session getSession() {
		return super.getSession();
	}
}
