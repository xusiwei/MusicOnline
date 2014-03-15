package cn.tingba.action;

import java.util.List;
import java.util.Map;

import cn.tinba.comon.Const;
import cn.tingba.entity.Song;
import cn.tingba.service.SongTopNService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Top10Action extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SongTopNService songTopNService = new SongTopNService();

	@Override
	public String execute() throws Exception {
		System.err.println("TOP10正在整理...");
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		// 播放次数 TOP10
		List<Song> top10pop = songTopNService.mostPopular(10);
		if( session.get(Const.TOP10_POPULAR_SONGS) != null  ) {
			session.remove(Const.TOP10_POPULAR_SONGS);
		}
		session.put(Const.TOP10_POPULAR_SONGS, top10pop);
		
		// 最近上传 TOP10
		List<Song> top10rec = songTopNService.mostRecently(10);
		if( session.get(Const.TOP10_RECENTLY_SONGS) != null ) {
			session.remove(Const.TOP10_RECENTLY_SONGS);
		}
		session.put(Const.TOP10_RECENTLY_SONGS, top10rec);	
		
		// 各种风格 TOP10
		Map<String, List<Song>> eachStyleTop10pop =  songTopNService.eachStyleTopN(10);		
		if( session.get(Const.TOP10_EACH_STYLE_SONG_LISTS) != null ) {
			session.remove(Const.TOP10_EACH_STYLE_SONG_LISTS);
		}
		session.put(Const.TOP10_EACH_STYLE_SONG_LISTS, eachStyleTop10pop);
		
		return SUCCESS;
	}

}
