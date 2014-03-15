package cn.tingba.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cn.tingba.dao.SongDao;
import cn.tingba.dao.impl.SongDaoImpl;
import cn.tingba.entity.Song;

public class SongTopNService {
	
	SongDao dao = new SongDaoImpl();
	
	public List<Song> byProperty(String propertyName, boolean asc, int n) {
		Session session = dao.getSession(); // (Session) HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(Song.class);
		
		// 按属性排序
		if(asc)
			criteria.addOrder(Order.asc(propertyName));
		else 
			criteria.addOrder(Order.desc(propertyName)); // MAGIC
		
		// 设置抓取数目
		criteria.setMaxResults(n); // criteria.setFetchSize(n);
		
		List list = criteria.list();
		return list;
	}
		
	public List<Song> mostPopular(int n) {
		// TODO 找到最播放最多的n首歌
		return byProperty("playcount", false, n);
	}
	
	public List<Song> mostRecently(int n) {
		// TODO 找到最近上传的n首歌
		return byProperty("uploadTime", false, n);
	}
	
	public Map<String, List<Song>> eachStyleTopN(int nTop) {
		Session session = dao.getSession(); // (Session) HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(Song.class);
		
		// 1.先获取所有style
		criteria.setProjection(Projections.groupProperty("style"));
		List list = criteria.list();	
		ArrayList<String> styleList = (ArrayList<String>) list;
//		for(Object obj : list) System.err.println((String)str);
		
		if(styleList == null || styleList.size()==0) return null;
		
		Map<String, List<Song>> result = new TreeMap<String, List<Song>>();
		
		// 2. 获取每种 style的 TOP n
		for (String style : styleList){
			Criteria cri = dao.getSession().createCriteria(Song.class);
			
			// 设置抓取数目
//			cri.setFetchSize(nTop);
			cri.setMaxResults(nTop);
			
			// 查找当前 风格
			cri.add(Restrictions.eq("style", style));
			
			// 按播放次数排序
			cri.addOrder(Order.desc("playcount"));	
			
			result.put(style, cri.list()); // 执行查询，并记录结果
		} 

		return result;
	}
}
