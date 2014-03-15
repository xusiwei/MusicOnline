package cn.tingba.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import cn.tingba.dao.AbstractDao;
import cn.tingba.dao.DaoFace;
import cn.tingba.entity.Comment;

public class CommentDaoImpl extends AbstractDao implements DaoFace<Comment> {
	
	public void add(Comment item) {
		super.insert(item);
	}

	public void delete(Comment item) {
		super.remove(item);
	}

	public List<Comment> findAll() {
		return search(new Comment());
	}

	public Comment findById(Serializable id) {
		return (Comment) super.get(Comment.class, id);
	}

	public List<Comment> search(Comment cond) {
		return super.find(Comment.class, cond);
	}

	public void update(Comment item) {
		super.rewrite(item);
	}

	public Session getSession() {
		return super.getSession();
	}
}
