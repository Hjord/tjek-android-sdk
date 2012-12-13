/*!
 * eTilbudsavis ApS
 * (c) 2012, eTilbudsavis ApS
 * http://etilbudsavis.dk
 */
/**
 * @fileoverview	Main class.
 * @author			Morten Bo <morten@etilbudsavis.dk>
 * 					Danny Hvam <danny@etilbudsavid.dk>
 * @version			0.3.0
 */
package com.etilbudsavis.etasdk;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

// Main object for interacting with the SDK.
public class ETA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Endpoints.
	private static final String MAIN_URL = "https://etilbudsavis.dk";
	private static final String PROVIDER_URL = MAIN_URL + "/connect/";

	// Preferences.
	protected static final String PREFS_NAME = "eta";
	protected static final String PREFS_UUID_NAME = "uuid";
	
	// Authorization.
	private final String mApiKey;
	private final String mApiSecret;
	private String mUUID;
	
	// Location.
	public Location location = new Location(this);
	
	// API.
	public API api = new API(this);
	
	// HTML at `PROVIDER_URL` endpoint.
	private String mHtmlCached = "";
	private int mHtmlAcquired = 0;
	private int mHtmlExpire = 15 * 60;
	
	public ArrayList<Pageflip> pageflipList = new ArrayList<Pageflip>();
	
	/**
	 * Constructor for the SDK.
	 *
	 * @param apiKey
	 *			The API key found at http://etilbudsavis.dk/api/
	 * @param apiSecret
	 *			The API secret found at http://etilbudsavis.dk/api/
	 * @Param Context
	 * 			The context of the activity instantiating this class.
	 */
	public ETA(String apiKey, String apiSecret, Context context) {
		mApiKey = apiKey;
		mApiSecret = apiSecret;

		// Persist UUID throughout app life span.
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		mUUID = prefs.getString(PREFS_UUID_NAME, null);

		// Create UUID if it doesn't already exist.
		if (mUUID == null) {
			mUUID = Utilities.createUUID();
			prefs.edit().putString(PREFS_UUID_NAME, mUUID).commit();
		}
	}
	
	/**
	 * Returns the main url of eTilbudsavis.
	 *
	 * @return Main url as String
	 */
	public String getMainUrl() {
		return MAIN_URL;
	}

	/**
	 * Returns the provider URL needed for Pageflip.
	 *
	 * @return Provider URL as String
	 */
	public String getProviderUrl() {
		return PROVIDER_URL;
	}
	
	/**
	 * Returns the SDK name for shared preferences
	 *
	 * @return ETA SDK SharedPreferences name as String
	 */
	public String getPrefsName() {
		return PREFS_NAME;
	}
	
	/**
	 * Returns the API key found at http://etilbudsavis.dk/api/.
	 *
	 * @return API key as String
	 */
	public String getApiKey() {
		return mApiKey;
	}
	
	/**
	 * Returns the API secret found at http://etilbudsavis.dk/api/
	 *
	 * @return API secret as String
	 */
	public String getApiSecret() {
		return mApiSecret;
	}
	
	/**
	 * Returns the unique UUID generated by the SDK.
	 *
	 * @return UUID as String
	 */
	public String getUUID() {
		return mUUID;
	}
	
	/**
	 * Returns HTML cache.
	 *
	 * @return Cached HTML as String
	 */
	public String getHtmlCached() {
		return mHtmlCached;
	}

	/**
	 * Sets the cached HTML content.
	 *
	 * @param HTML
	 */
	public void setHtmlCached(String html) {
		// Validate input.
		if (html.matches(".*\\<[^>]+>.*")) {
			mHtmlCached = html;
			mHtmlAcquired = Utilities.getTime();
		}
	}

	/**
	 * Returns the time at which the HTML was cached.
	 *
	 * @return HTML caching timestamp
	 */
	public int getHtmlAcquired() {
		return mHtmlAcquired;
	}

	/**
	 * Returns the TTL for the HTML cache.
	 *
	 * @return TTL for the HTML cache
	 */
	public int getHtmlExpire() {
		return mHtmlExpire;
	}

	/**
	 * Sets the TTL for the HTML cache.
	 *
	 * @param TTL in seconds
	 */
	public void setHtmlExpire(int seconds) {
		mHtmlExpire = seconds;
	}
}