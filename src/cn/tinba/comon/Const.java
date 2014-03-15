package cn.tinba.comon;

public class Const {
	
	// string constants
	/**
	 * 用于在HttpSession中存放当前用户
	 * */
	public static final String LOGINED_USER = "LOGINED_USER";	
	
	/**
	 * 用于在HttpSession中存放当前用户 上传的 歌曲列表
	 * */
	public static final String MY_SONG_LIST = "MY_SONG_LIST";
	
	/**
	 * 用于在HttpSession中存放当前用户 选择播放的 歌曲列表
	 * */
	public static final String CURRENT_SONG_LIST = "CURRENT_SONG_LIST";
	
	/**
	 * 用于在HttpSession中 所有 用户上传的歌曲列表
	 * */
	public static final String ALL_SONG_LIST = "ALL_SONG_LIST";		
	
	/**
	 *  用于在HttpSession中存放 播放次数最多 的   歌曲 TOP10
	 * */
	public static final String TOP10_POPULAR_SONGS = "TOP10_POPULAR_SONGS";
	
	/**
	 *  用于在HttpSession中存放 最近上传 的   歌曲 TOP10
	 * */
	public static final String TOP10_RECENTLY_SONGS = "TOP10_RECENTLY_SONGS";
	
	/**
	 * 用于在HttpSession中存放 各种风格的播放次数最多的  歌曲 TOP10
	 * */
	public static final String TOP10_EACH_STYLE_SONG_LISTS = "TOP10_EACH_STYLE_SONG_LISTS";
	
	/**
	 *  用于在HttpSession中存放 播放次数最多 的   歌曲 TOP10
	 * */
	public static final String SEARCH_RESULT = "SEARCH_RESULT";	
	
	
	// int constants 
	/**
	 * 
	 * */
	public static final int DEAULT_USER_LEVEL = 1;
	
	/**
	 * 用户默认空间大小
	 *   以 KB 为单位
	 * */
	public static final int DEAULT_USER_SPACE = 200 * 1024; // 150 K (KB) 
	
}
