package cn.tingba.action;

import java.util.List;
import java.util.Map;

import cn.tinba.comon.Const;
import cn.tingba.dao.UserDao;
import cn.tingba.dao.impl.UserDaoImpl;
import cn.tingba.entity.Song;
import cn.tingba.entity.User;
import cn.tingba.service.SongService;
import cn.tingba.service.SongTopNService;
import cn.tingba.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// common URL parameter AND search form data
	private String mode;
	private String value;
	private String way;
	
//	private UserService userService = new UserService();
	private SongService songService = new SongService();
	private SongTopNService top10Service = new SongTopNService();
	
	@Override
	public String execute() throws Exception {
		// TODO 完成不同功能的歌曲搜索功能
		List<Song> resList = null;
		if(mode == null) mode = "";
		if(way == null) way = "";

		value = value.trim();
		
		if(way == "head") {
			value += "%";
		}else if(way == "midd") {
			value = "%" + value + "%";
		}else { // 默认方式
			value = "%" + value + "%";
		}
		
		System.err.println("way:" + way);
		System.err.println("mode:" + mode);
		System.err.println("value:" + value);		
		
		if(mode.equals("name")) {
			resList = songService.searchByName(value);
		}
		else if(mode.equals("singer")) {
			resList = songService.searchBySinger(value);
		}
		else if(mode.equals("description")) {
			resList = songService.searchByDescription(value);
		}
		else if(mode.equals("all")) {
			resList = songService.searchByAllTextInfo(value);
		}
		else if(mode.equals("top10")){
			if( value == "popular" ) {
				top10Service.mostPopular(10);
			}
		}
		else { // 默认方式
			resList = songService.searchByAllTextInfo(value);
		}
		
		if(resList != null) { // 
			System.err.println("找到歌曲" + resList.size() + "首");
			// 放入session
			Map<String, Object> session = ActionContext.getContext().getSession();
			List<Song> oldResList = (List<Song>) session.get(Const.SEARCH_RESULT);
			if( oldResList != null ) {
				session.remove(Const.SEARCH_RESULT);
			}
			session.put(Const.SEARCH_RESULT, resList);
		}
		else System.err.println("没有符号条件的歌曲");
		return SUCCESS;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getWay() {
		return way;
	}	
}