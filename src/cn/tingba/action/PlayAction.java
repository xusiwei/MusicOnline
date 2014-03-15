package cn.tingba.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.tinba.comon.Const;
import cn.tingba.entity.Song;
import cn.tingba.service.SongService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PlayAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String audioSrc; // audioSrc用来存歌曲
	private ArrayList<Integer> songIdList; // for song_addToList
	private Integer index; // for song_removeItem
	private boolean uniqueAdd; // for checkbox
	private Integer playIndex;
	
	private SongService songService = new SongService();

	public String goodSong() {
		if(playIndex == null) {
			System.err.println("没有playIndex");
			return SUCCESS;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<Song> curSongList = (List<Song>) session.get(Const.CURRENT_SONG_LIST);
		
		Song song = curSongList.get(playIndex);
		songService.logGood(song);
		return SUCCESS;
	}
	
	public String badSong() {
		if(playIndex == null) {
			System.err.println("没有playIndex");
			return SUCCESS;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<Song> curSongList = (List<Song>) session.get(Const.CURRENT_SONG_LIST);
		
		Song song = curSongList.get(playIndex);
		songService.logBad(song);
		return SUCCESS;
	}
	
	public String addToList() {
		System.err.println("uniqueAdd: " + uniqueAdd);
		
		List<Song> newSongList = songService.getSongList(songIdList);
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<Song> curSongList = (List<Song>) session.get(Const.CURRENT_SONG_LIST);
		
		// 当前的  和  提交的都为空 ==> 没有歌曲可以播放
		if(newSongList == null && curSongList == null) {
			addFieldError("hint", "播放列表为空，请选择歌曲！");
			return INPUT; // 不跳转到播放页面
		}
		
		// 提交的 为空（当前不空）直接过去 
		if(newSongList == null) return SUCCESS;
		
		if( curSongList == null ) { // 当前为空
			session.put(Const.CURRENT_SONG_LIST, newSongList);
			curSongList = newSongList;
			for( Song song : newSongList ) {
				songService.logPlay(song);   // 登记播放
			}
		}
		else { // 当前不空
			if( uniqueAdd ) { // 不重复 添加
				for(Song s : newSongList) {
					// 如果不在播放列表中
					if( ! curSongList.contains(s) ) {
						curSongList.add(s);
						songService.logPlay(s); // 登记播放
					}
				}
			}
			else { // 重复添加
				curSongList.addAll(newSongList);
			}
		}
		
		audioSrc = curSongList.get(0).getSavepath();
		
		return SUCCESS;
	}
	
	public String removeItem() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<Song> curSongList = (List<Song>) session.get(Const.CURRENT_SONG_LIST);
		if( curSongList == null || index == null ) {
			System.err.println("歌曲列表为空，或index为空");
			return SUCCESS;
		}
		
//		index -= 1; // 页面上获取到的索引 从1开始
		if( index > curSongList.size() ) {
			return SUCCESS;
		}
		
		curSongList.remove(index-1); 
		
		if( curSongList.size() >= 1 ) 
			this.audioSrc = curSongList.get(0).getSavepath();
		else // 没有歌曲了 
			return INPUT; // TODO 以后再看要不要改，暂时让它跳回去
		
		return SUCCESS;
	}
	
	public String playSong() {
		//  实现播放一首歌
		System.err.println("准备播放音频源：" + audioSrc);
		return SUCCESS;
	}	
	
	// getter and setters

	public String getAudioSrc() {
		return audioSrc;
	}

	public void setAudioSrc(String audioSrc) {
		this.audioSrc = audioSrc;
	}

	public void setSongIdList(ArrayList<Integer> songIdList) {
		this.songIdList = songIdList;
	}

	public ArrayList<Integer> getSongIdList() {
		return songIdList;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setUniqueAdd(boolean uniqueAdd) {
		this.uniqueAdd = uniqueAdd;
	}

	public boolean isUniqueAdd() {
		return uniqueAdd;
	}

	public void setPlayIndex(Integer playIndex) {
		this.playIndex = playIndex;
	}

	public Integer getPlayIndex() {
		return playIndex;
	}

}
