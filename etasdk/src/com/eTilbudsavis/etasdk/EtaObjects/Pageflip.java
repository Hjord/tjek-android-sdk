package com.eTilbudsavis.etasdk.EtaObjects;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

import com.eTilbudsavis.etasdk.Utils.Utils;

public class Pageflip extends EtaObject implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String TAG = "Pageflip";
	
	private String mLogo;
	private int mColor = 0;

	public Pageflip() {
	}

	public Pageflip(int color) {
		mColor = color;
	}
	
	public static Pageflip fromJSON(String pageflip) {
		Pageflip p = new Pageflip();
		try {
			p = fromJSON(p, new JSONObject(pageflip));
		} catch (JSONException e) {
			Utils.logd(TAG, e);
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	public static Pageflip fromJSON(JSONObject pageflip) {
		return fromJSON(new Pageflip(), pageflip);
	}
	
	public static Pageflip fromJSON(Pageflip p, JSONObject pageflip) {
		if (p == null) p = new Pageflip();
		if (pageflip == null) return p;
		
		p.setLogo(getJsonString(pageflip, S_LOGO));
		p.setColor(Color.parseColor("#"+getJsonString(pageflip, S_COLOR)));
		
		return p;
	}

	public JSONObject toJSON() {
		return toJSON(this);
	}
	
	public static JSONObject toJSON(Pageflip p) {
		JSONObject o = new JSONObject();
		try {
			o.put(S_LOGO, p.getLogo());
			o.put(S_COLOR, p.getColorString());
		} catch (JSONException e) {
			Utils.logd(TAG, e);
		}
		return o;
	}
	
	public String getLogo() {
		return mLogo;
	}
	
	public Pageflip setLogo(String url) {
		mLogo = url;
		return this;
	}
	
	public int getColor() {
		return mColor;
	}

	public String getColorString() {
		return String.format("%06X", 0xFFFFFF & mColor);
	}

	public Pageflip setColor(int color) {
		mColor = color;
		return this;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		
		if (!(o instanceof Pageflip))
			return false;

		Pageflip p = (Pageflip)o;
		return stringCompare(mLogo, p.getLogo()) &&
				mColor == p.getColor();
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
		.append(getClass().getSimpleName()).append("[")
		.append("logo=").append(mLogo)
		.append(", color=").append(mColor)
		.append("]").toString();
	}
	
}