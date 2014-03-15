package cn.tingba.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Servlet;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.tinba.comon.Const;
import cn.tingba.entity.Song;
import cn.tingba.entity.User;
import cn.tingba.service.SongService;
import cn.tingba.service.UserLogService;
import cn.tingba.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File song;
	private String songFileName;
	private String songContentType; // 文件格式， 可以由此校验
	private String name;
	private String singer;
	private String style;
	private String description;
	private String validateNum;
	private String serverFilePath;

	public static String SONG_REPOSITORY = "upload/song"; // repository
	
	private UserService userService = new UserService();
	private SongService songService = new SongService();
	private UserLogService userLogService = new UserLogService();
	
	public String execute() throws Exception {
		// 校验验证码
		String randstr = (String) ActionContext.getContext().
						getSession().get(ImageAction.IMAGE_STR_KEY);
		if (!randstr.equals(validateNum)) {
			addFieldError("validateNum", "验证码输入错误！");
			return INPUT;
		}
		
		// 检查文件格式
		if (!songContentType.equals("audio/mp3")) {
			addFieldError("song", "文件格式不支持！");
			return INPUT;
		}
		
		// 实现歌曲上传
		String repoPath = ServletActionContext.getServletContext().getRealPath(SONG_REPOSITORY);
		
		String uuidName = UUID.randomUUID().toString();
		if(songFileName.indexOf('.') != -1) {
			int dot = songFileName.lastIndexOf('.');
			uuidName += songFileName.substring(dot); // 扩展名
		}	
		
		if(song != null) {
			serverFilePath = SONG_REPOSITORY + "/" + uuidName;
			File saveFile = new File(new File(repoPath), uuidName);
			if(!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			
			int KBs = (int)Math.round(song.length()/1024.0);
			
//			System.out.println("saveFile:" + saveFile.getPath());
			System.err.println("你上传的文件格式为：" + songContentType);
			System.err.println("服务器端文件名为：" + serverFilePath);
			System.err.println("文件大小：" + song.length());

			FileUtils.copyFile(song, saveFile); // 保存
			
			// 登记到数据库
			Map<String, Object> session = ActionContext.getContext().getSession();
			User user = (User)session.get(Const.LOGINED_USER);
			Song song = songService.register(name, singer, style, description, serverFilePath, user);
			userService.useSpace(user, KBs);
			
			// 更新全部歌曲列表
//			List<Song> songList = songService.listAll();
//			
//			if( session.get(Const.ALL_SONG_LIST) != null ) { // 如果已经查询过了，删除上次查询结果
//				session.remove(Const.ALL_SONG_LIST);
//			}
//			session.put(Const.ALL_SONG_LIST, songList);
			
			// 更新我上传的歌曲列表
			List<Song> mySongList = (List<Song>)session.get(Const.MY_SONG_LIST);
			if( mySongList == null) {
				mySongList = new ArrayList<Song>();		
				session.put(Const.MY_SONG_LIST, mySongList);
			}
			mySongList.add(song);
			
			System.err.println("current user:" + user);
			
			userLogService.logUserAction(user, "UPLOAD");
			return SUCCESS;
		}
		else {
			System.err.println("歌曲文件为空！");
			return INPUT;
		}
	}
	
	// getter and setters

	public void setSongContentType(String songContentType) {
		this.songContentType = songContentType;
	}

	public String getSongContentType() {
		return songContentType;
	}

	public File getSong() {
		return song;
	}

	public void setSong(File song) {
		this.song = song;
	}

	public String getSongFileName() {
		return songFileName;
	}

	public void setSongFileName(String songFileName) {
		this.songFileName = songFileName;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServerFilePath() {
		return serverFilePath;
	}

	public void setServerFilePath(String serverFilePath) {
		this.serverFilePath = serverFilePath;
	}

	public String getValidateNum() {
		return validateNum;
	}

	public void setValidateNum(String validateNum) {
		this.validateNum = validateNum;
	}
}
