package com.mkslm;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class NewsManager {

	private static List<NewsInf> pool = new LinkedList<>();
	private static NewsRetriever retriever = new NewsRetriever();

	static {
		updateNews();
	}
	private NewsManager(){}
	public NewsInf getNextNews() {
		Collections.sort(pool, new Comparator<NewsInf>() {

			@Override
			public int compare(NewsInf o1, NewsInf o2) {
				long x = o1.valueTime + o1.windowMinutes * 60000 - o2.valueTime
						- o2.windowMinutes * 60000;
				if (x > 0)
					return 1;
				if (x < 0)
					return -1;
				return (int) (Math.abs(o2.impact) - Math.abs(o1.impact));
			}
		});
		if (pool.size() > 0) {
			return pool.remove(0);
		}
		return null;
	}

	private  static void updateNews() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					for (NewsInf news : pool) {
						if (news.valueTime + news.windowMinutes * 60000 < System
								.currentTimeMillis())
							pool.remove(news);
					}
					List<NewsInf> retrieved = retriever.getNews();
					for (NewsInf news : retrieved) {
						boolean hasNews = false;
						for (NewsInf tmp : pool) {
							if (tmp.equals(news)) {
								hasNews = true;
								break;
							}
						}
						if (!hasNews) {
							System.out.println("adding news");
							pool.add(news);
						}

					}
					for (NewsInf news : pool)
						System.out.println(news);
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();
	}

}
