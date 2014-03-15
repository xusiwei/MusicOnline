package cn.tingba.service.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import cn.tingba.entity.Song;
import cn.tingba.service.SongTopNService;

public class TopNServiceTest {

	SongTopNService topService = new SongTopNService();
	
	public static void main(String[] args) {
		TopNServiceTest t = new TopNServiceTest();
//		t.testTop10();
		t.testEachStyleTopN();
		System.out.println("test finish...");
	}
	
	void testTop10() {
		System.err.println("most popular:");
		List<Song> top10 = topService.mostPopular(10);
		if(top10 == null) {
			System.err.println("no result!");
			return ;
		}
		
		int no = 0;
		for(Song s: top10) {
			System.err.print( ++no + "  " + s.getPlaycount() + "  ");
			System.err.println(s);
		}
		
		System.err.println("most recently:");
		top10 = topService.mostRecently(10);
		if(top10 == null) {
			System.err.println("no result!");
			return ;
		}
		
		no = 0;
		for(Song s: top10) {
			System.err.print( ++no + "  " + s.getUploadTime() + "  ");
			System.err.println(s);
		}			
	}

	void testEachStyleTopN() {
		int nTop = 10;
		Map<String, List<Song>> res = topService.eachStyleTopN(nTop);
		if(res == null) {
			System.err.println("NO RESULT FOUND...");
			return ;
		}
//		Set<Entry<String,List<Song>>> iter = res.entrySet();
		for ( Entry<String, List<Song>> e : res.entrySet() ) {
			System.err.println( e.getKey() + " TOP " + nTop + ":");
			List<Song> top = e.getValue();
			if (top == null) System.err.println("NO VALUE FOR KEY...");
			else {
				int i = 0;
				for( Song s : top ) {
					System.err.print(++i + " " + s.getPlaycount() + " ");
					System.err.println(s);
				}
			}
		}
	}
}
