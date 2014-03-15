package cn.tingba.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.tingba.common.Const;
import cn.tingba.entity.Song;
import cn.tingba.entity.User;
import cn.tingba.entity.util.SortSongList;
import cn.tingba.service.SongService;
import cn.tingba.service.UserLogService;
import cn.tingba.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	private UserService userService = new UserService();
	private SongService songService = new SongService();
	private UserLogService userLogService = new UserLogService();
	
	public String login() {
		// 检查是否 已经登陆了
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get(Const.LOGINED_USER) != null) { 
			return SUCCESS; // 如果已经登录，直接跳过
		}
		
		// 验证用户名密码
		User user = userService.findByNamePass(username, password);
		if ( user == null ) { // execute SQL select
			addFieldError("username", "用户名或密码错误！");
			return INPUT;	
		}
		
		User curUser = user; // userService.getUser(username);  // execute SQL select
		System.err.println("当前用户:" + curUser);
		
		List<Song> mySongList = new ArrayList<Song>(curUser.getSongs()); 
		SortSongList.byTime(mySongList); // SORT Songs
		
		session.put(Const.MY_SONG_LIST, mySongList);
		session.put(Const.LOGINED_USER, curUser); //写入信息，标记已登录的用户

		userLogService.logUserAction(curUser, "LOGIN");
		return SUCCESS;		
	}
	
	public String logout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get(Const.LOGINED_USER) != null) {
			userLogService.logUserAction((User)session.get(Const.LOGINED_USER), "LOGOUT");
			session.remove(Const.LOGINED_USER); //删除之前写入用户的信息	
		}
		
		if( session.get(Const.MY_SONG_LIST) != null ) { 
			session.remove(Const.MY_SONG_LIST); // 删除我上传的歌曲列表 
		}
		
		if( session.get(Const.CURRENT_SONG_LIST) != null ) { 
			session.remove(Const.CURRENT_SONG_LIST); // 删除 播放列表 
		}
		return SUCCESS;
	}
 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
