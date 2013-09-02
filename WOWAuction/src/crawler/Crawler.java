package crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import connection.HttpConnection;
import data.Auction;

public class Crawler {

	/**
	 * 
	 * @return the return is a HashMap normally has only one element.
	 */
	public HashMap<String, Long> getAuctionURLs() {

		String host = "http://www.battlenet.com.cn";
		String realm = "%E5%AE%88%E6%8A%A4%E4%B9%8B%E5%89%91";//  ÿª§÷ÆΩ£
		String url = host + "/api/wow/auction/data/" + realm;

		try {
			String jsonText = HttpConnection.getData(url, "utf-8");
			JSONObject json = new JSONObject(jsonText);

			// {"files":[{"url":"http://www.battlenet.com.cn/auction-data/110d250cf28de6d965a85f90c4b766ae/auctions.json","lastModified":1378131279000}]}
			JSONArray jsonArray = json.getJSONArray("files");
			HashMap<String, Long> urls = new HashMap<String, Long>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject arrayElement = jsonArray.getJSONObject(i);
				String auctionURL = arrayElement.getString("url");
				long lastModified = arrayElement.getLong("lastModified");
				urls.put(auctionURL, lastModified);
			}
			return urls;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Auction> crawle(String url, long lastModified) {
		if (url == null || lastModified == -1)
			return null;
		try {
			String jsonText = HttpConnection.getData(url, "utf-8");
			JSONObject json = new JSONObject(jsonText);
			JSONObject alliance = json.getJSONObject("alliance");
			// {
			// "realm":{"name":"Guardian Blade","slug":"guardian-blade"},
			// "alliance":{"auctions":[
			// {"auc":1101385012,"item":71635,"owner":"Artemis","bid":3500100,"buyout":3500150,"quantity":1,"timeLeft":"VERY_LONG","rand":0,"seed":1081080518},
			JSONArray jsonArray = alliance.getJSONArray("auctions");
			ArrayList<Auction> aucs = new ArrayList<Auction>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject arrayElement = jsonArray.getJSONObject(i);
				long aucId = arrayElement.getLong("auc");
				long itemId = arrayElement.getLong("item");
				String owner = arrayElement.getString("owner");
				long bid = arrayElement.getLong("bid");
				long buyout = arrayElement.getLong("buyout");
				int quantity = arrayElement.getInt("quantity");
				String timeLeft = arrayElement.getString("timeLeft");
				int rand = arrayElement.getInt("rand");
				long seed = arrayElement.getLong("seed");
				aucs.add(new Auction.Builder(aucId, itemId, owner, bid, buyout,
						quantity, timeLeft, rand, seed).build());
			}
			return aucs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		HashMap<String, Long> urls = crawler.getAuctionURLs();
		String url = null;
		long lastModified = -1;
		for (Entry<String, Long> e : urls.entrySet()) {
			url = e.getKey();
			lastModified = e.getValue();
			break;
		}
		crawler.crawle(url, lastModified);
	}

}