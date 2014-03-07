package com.eTilbudsavis.etasdk.EtaObjects;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eTilbudsavis.etasdk.Utils.EtaLog;
import com.eTilbudsavis.etasdk.Utils.Json;


/**
 * <p>This class is a representation of a store as the API v2 exposes it</p>
 * 
 * <p>More documentation available on via our
 * <a href="http://engineering.etilbudsavis.dk/eta-api/pages/references/stores.html">Store Reference</a>
 * documentation, on the engineering blog.
 * </p>
 * 
 * @author Danny Hvam - danny@etilbudsavis.dk
 *
 */
public class Store extends EtaErnObject<Store> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String TAG = "Store";
	
	private String mStreet;
	private String mCity;
	private String mZipcode;
	private Country mCountry;
	private double mLatitude = 0.0;
	private double mLongitude = 0.0;
	private String mDealerUrl;
	private String mDealerId;
	private Branding mBranding;
	private String mContact;
	
	private Dealer mDealer;

	public Store() { }
	
	public static ArrayList<Store> fromJSON(JSONArray stores) {
		ArrayList<Store> list = new ArrayList<Store>();
		try {
			for (int i = 0 ; i < stores.length() ; i++ )
				list.add(Store.fromJSON((JSONObject)stores.get(i)));
			
		} catch (JSONException e) {
			EtaLog.d(TAG, e);
		}
		return list;
	}
	
	public static Store fromJSON(JSONObject store) {
		return fromJSON(new Store(), store);
	}
	
	public static Store fromJSON(Store s, JSONObject store) {
		if (s == null) s = new Store();
		if (store == null) return s;
		
		try {
			s.setId(Json.valueOf(store, ServerKey.ID));
			s.setErn(Json.valueOf(store, ServerKey.ERN));
			s.setStreet(Json.valueOf(store, ServerKey.STREET));
			s.setCity(Json.valueOf(store, ServerKey.CITY));
			s.setZipcode(Json.valueOf(store, ServerKey.ZIP_CODE));
			s.setCountry(Country.fromJSON(store.getJSONObject(ServerKey.COUNTRY)));
			s.setLatitude(Json.valueOf(store, ServerKey.LATITUDE, 0.0d));
			s.setLongitude(Json.valueOf(store, ServerKey.LONGITUDE, 0.0d));
			s.setDealerUrl(Json.valueOf(store, ServerKey.DEALER_URL));
			s.setDealerId(Json.valueOf(store, ServerKey.DEALER_ID));
			s.setBranding(Branding.fromJSON(store.getJSONObject(ServerKey.BRANDING)));
			s.setContact(Json.valueOf(store, ServerKey.CONTACT));
		} catch (JSONException e) {
			EtaLog.d(TAG, e);
		}
		return s;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject o = super.toJSON();
		try {
			o.put(ServerKey.STREET, Json.nullCheck(getStreet()));
			o.put(ServerKey.CITY, Json.nullCheck(getCity()));
			o.put(ServerKey.ZIP_CODE, Json.nullCheck(getZipcode()));
			o.put(ServerKey.COUNTRY, Json.nullCheck(getCountry().toJSON()));
			o.put(ServerKey.LATITUDE, getLatitude());
			o.put(ServerKey.LONGITUDE, getLongitude());
			o.put(ServerKey.DEALER_URL, Json.nullCheck(getDealerUrl()));
			o.put(ServerKey.DEALER_ID, Json.nullCheck(getDealerId()));
			o.put(ServerKey.BRANDING, Json.nullCheck(getBranding().toJSON()));
			o.put(ServerKey.CONTACT, Json.nullCheck(getContact()));
		} catch (JSONException e) {
			EtaLog.d(TAG, e);
		}
		return o;
	}
	
	@Override
	public String getErnPrefix() {
		return ERN_STORE;
	}
	
	public Store setStreet(String street) {
		mStreet = street;
		return this;
	}

	public String getStreet() {
		return mStreet;
	}

	public Store setCity(String city) {
		mCity = city;
		return this;
	}

	public String getCity() {
		return mCity;
	}

	public Store setZipcode(String zipcode) {
		mZipcode = zipcode;
		return this;
	}

	public String getZipcode() {
		return mZipcode;
	}

	public Store setCountry(Country country) {
		mCountry = country;
		return this;
	}

	public Country getCountry() {
		return mCountry;
	}

	public Store setLatitude(Double latitude) {
		mLatitude = latitude;
		return this;
	}

	public Double getLatitude() {
		return mLatitude;
	}

	public Store setLongitude(Double longitude) {
		mLongitude = longitude;
		return this;
	}

	public Double getLongitude() {
		return mLongitude;
	}

	public String getDealerUrl() {
		return mDealerUrl;
	}

	public Store setDealerUrl(String url) {
		mDealerUrl = url;
		return this;
	}

	public Store setDealerId(String dealer) {
		mDealerId = dealer;
		return this;
	}

	public String getDealerId() {
		return mDealerId;
	}

	public Branding getBranding() {
		return mBranding;
	}

	public Store setBranding(Branding branding) {
		mBranding = branding;
		return this;
	}

	public Store setContact(String contact) {
		mContact = contact;
		return this;
	}
	
	public String getContact() {
		return mContact;
	}

	public Store setDealer(Dealer d) {
		mDealer = d;
		return this;
	}
	
	public Dealer getDealer() {
		return mDealer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((mBranding == null) ? 0 : mBranding.hashCode());
		result = prime * result + ((mCity == null) ? 0 : mCity.hashCode());
		result = prime * result
				+ ((mContact == null) ? 0 : mContact.hashCode());
		result = prime * result
				+ ((mCountry == null) ? 0 : mCountry.hashCode());
		result = prime * result
				+ ((mDealerId == null) ? 0 : mDealerId.hashCode());
		result = prime * result
				+ ((mDealerUrl == null) ? 0 : mDealerUrl.hashCode());
		long temp;
		temp = Double.doubleToLongBits(mLatitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mLongitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mStreet == null) ? 0 : mStreet.hashCode());
		result = prime * result
				+ ((mZipcode == null) ? 0 : mZipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (mBranding == null) {
			if (other.mBranding != null)
				return false;
		} else if (!mBranding.equals(other.mBranding))
			return false;
		if (mCity == null) {
			if (other.mCity != null)
				return false;
		} else if (!mCity.equals(other.mCity))
			return false;
		if (mContact == null) {
			if (other.mContact != null)
				return false;
		} else if (!mContact.equals(other.mContact))
			return false;
		if (mCountry == null) {
			if (other.mCountry != null)
				return false;
		} else if (!mCountry.equals(other.mCountry))
			return false;
		if (mDealerId == null) {
			if (other.mDealerId != null)
				return false;
		} else if (!mDealerId.equals(other.mDealerId))
			return false;
		if (mDealerUrl == null) {
			if (other.mDealerUrl != null)
				return false;
		} else if (!mDealerUrl.equals(other.mDealerUrl))
			return false;
		if (Double.doubleToLongBits(mLatitude) != Double
				.doubleToLongBits(other.mLatitude))
			return false;
		if (Double.doubleToLongBits(mLongitude) != Double
				.doubleToLongBits(other.mLongitude))
			return false;
		if (mStreet == null) {
			if (other.mStreet != null)
				return false;
		} else if (!mStreet.equals(other.mStreet))
			return false;
		if (mZipcode == null) {
			if (other.mZipcode != null)
				return false;
		} else if (!mZipcode.equals(other.mZipcode))
			return false;
		return true;
	}
	
}
