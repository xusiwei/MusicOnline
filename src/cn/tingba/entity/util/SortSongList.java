package cn.tingba.entity.util;

import java.util.Collections;
import java.util.List;

import cn.tingba.entity.Song;

public class SortSongList {
	static SongTimeComparator comp = null;

	@SuppressWarnings("unchecked")
	public static void byTime(List<Song> list) {
		if (comp == null) comp = new SongTimeComparator();
		Collections.sort(list, comp);
	}
}
