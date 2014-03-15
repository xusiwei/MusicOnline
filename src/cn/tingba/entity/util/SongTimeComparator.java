package cn.tingba.entity.util;

import java.sql.Timestamp;
import java.util.Comparator;

import cn.tingba.entity.Song;

public class SongTimeComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		Timestamp t1 = ((Song) o1).getUploadTime();
		Timestamp t2 = ((Song) o2).getUploadTime();
		return t1.compareTo(t2);
	}
}
