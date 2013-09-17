package com.eTilbudsavis.etasdk;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;

import com.eTilbudsavis.etasdk.EtaObjects.ResponseWrapper;
import com.eTilbudsavis.etasdk.Utils.Params;
import com.eTilbudsavis.etasdk.Utils.Utils;

public class EtaCache implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TAG = "EtaCache";
	
	private static final long ITEM_CACHE_TIME = 15 * Utils.MINUTE_IN_MILLIS;
	private static final long HTML_CACHE_TIME = 15 * Utils.MINUTE_IN_MILLIS;
	private static final String HTML_REGEX = ".*\\<[^>]+>.*";

	// Define catchable types
	Map<String, String>	types = new HashMap<String, String>(4);
	
	private Map<String, CacheItem> mItems = Collections.synchronizedMap(new HashMap<String, EtaCache.CacheItem>());

	public EtaCache() {
		types.put("catalogs", Params.FILTER_CATALOG_IDS);
		types.put("offers", Params.FILTER_OFFER_IDS);
		types.put("dealers", Params.FILTER_DEALER_IDS);
		types.put("stores", Params.FILTER_STORE_IDS);
	}

	public void putHtml(String uuid, String html, int statusCode) {
		// Validate input.
		if (html.matches(HTML_REGEX)) {
			mItems.put(uuid, new CacheItem(html, statusCode));
		}
	}

	public String getHtml(String uuid) {
		CacheItem c = mItems.get(uuid);
		String html = null;
		if (c != null && c.time > (System.currentTimeMillis() - HTML_CACHE_TIME) ) {
			html =  c.object.toString();
			html = html.matches(HTML_REGEX) ? html : null;
		}
		return html;
	}

	public void put(ResponseWrapper response) {
		if (Utils.isSuccess(response.getStatusCode())) {
			if (response.isJSONArray()) {
				put(response.getStatusCode(), response.getJSONArray());
			} else if (response.isJSONObject()) {
				put(response.getStatusCode(), response.getJSONObject());
			}
		}
	}
	
	private void put(int statusCode, JSONArray objects) {

		try {
		if (objects != null && objects.length()>0) {
				JSONObject o = objects.getJSONObject(0);
				if (o.has("ern")) {
					for (int i = 0; i < objects.length() ; i++) {
						put(statusCode, objects.getJSONObject(i));
					}
				}
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	private void put(int statucCode, JSONObject object) {
		if (object != null && object.has("ern")) {
			try {
				mItems.put(object.getString("ern"), new CacheItem(object, statucCode));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	private CacheItem get(String key) {
		CacheItem c = mItems.get(key);
		if (c != null) {
			if ( ! ((c.time + ITEM_CACHE_TIME) > System.currentTimeMillis()) ) {
				mItems.remove(c);
				c = null;
			}
		}
		return c;
	}
	
	public void clear() {
		mItems = new HashMap<String, EtaCache.CacheItem>();
	}
	
	public class CacheItem {
		
		public long time;
		public int statuscode;
		public Object object;
		
		public CacheItem(Object o, int statusCode) {
			time = System.currentTimeMillis();
			this.statuscode = statusCode;
			object = o;
		}
	}
	
	private Set<String> getFilter(String filterName, Bundle apiParams) {
		String tmp = apiParams.getString(filterName);
		Set<String> list = new HashSet<String>();
		Collections.addAll(list, TextUtils.split(tmp, ","));
		return list;
	}
	
	private String getErnPrefix(String type) {
		return "ern:" + type.substring(0, type.length()-1) + ":";
	}
	
	public ResponseWrapper get(String url, Bundle apiParams) {

		ResponseWrapper resp = null;
		
		String[] path = url.split("/");
		
		if (types.containsKey(path[path.length-1])) {
			// if last element is a type, then we'll expect a list
			String type = path[path.length-1];

			Set<String> ids = new HashSet<String>(0);
			String filter = types.get(type);
			if (apiParams.containsKey(filter)) {
				ids = getFilter(filter, apiParams);
			}
			
			// No ids? no catchable items...
			if (ids.size() == 0)
				return resp;

			// Get all possible items requested from cache
			JSONArray jArray = new JSONArray();
			for (String id : ids) {
				String ern = getErnPrefix(type) + id;
				CacheItem c = get(ern);
				if (c != null) {
					jArray.put((JSONObject)c.object);
				}
			}
			
			// If cache had ALL items, then return the list.
			int size = jArray.length();
			if (size > 0 && (size == ids.size()) ) {
				resp = new ResponseWrapper(200, jArray.toString());
			}
			
		} else if (types.containsKey(path[path.length-2])) {
			// if second to last element is a valid type, then we'll expect an item id
			// (this isn't always true, but if it isn't then the item-id, shouldn't be in cache )

			String id = path[path.length-1];
			String type = path[path.length-2];
			
			String ern = getErnPrefix(type) + id;
			CacheItem c = get(ern);
			if (c != null) {
				resp = new ResponseWrapper(c.statuscode, c.object.toString());
			}
		}
		
		return resp;
		
	}
	
}