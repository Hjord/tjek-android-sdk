/*******************************************************************************
* Copyright 2014 eTilbudsavis
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/
package com.eTilbudsavis.etasdk.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.eTilbudsavis.etasdk.Eta;
import com.eTilbudsavis.etasdk.log.EtaLog;
import com.eTilbudsavis.etasdk.model.interfaces.IJson;
import com.eTilbudsavis.etasdk.utils.Api.JsonKey;
import com.eTilbudsavis.etasdk.utils.Json;
import com.eTilbudsavis.etasdk.utils.Utils;

public class Branding implements IJson<JSONObject>, Serializable, Parcelable {
	
	private static final long serialVersionUID = 1L;

	public static final String TAG = Eta.TAG_PREFIX + Branding.class.getSimpleName();
	
	private String mName;
	private String mUrlName;
	private String mWebsite;
	private String mLogo;
	private Integer mLogoBackground;
	private Integer mColor;
	private Pageflip mPageflip;

	public static Parcelable.Creator<Branding> CREATOR = new Parcelable.Creator<Branding>(){
		public Branding createFromParcel(Parcel source) {
			return new Branding(source);
		}
		public Branding[] newArray(int size) {
			return new Branding[size];
		}
	};

	public Branding() {
		
	}
	
	public static Branding fromJSON(JSONObject branding) {
		Branding b = new Branding();
		if (branding == null) {
			return b;
		}
		
		try {
			b.setName(Json.valueOf(branding, JsonKey.NAME));
			b.setUrlName(Json.valueOf(branding, JsonKey.URL_NAME));
			b.setWebsite(Json.valueOf(branding, JsonKey.WEBSITE));
			b.setLogo(Json.valueOf(branding, JsonKey.LOGO));
			Integer c = Json.colorValueOf(branding, JsonKey.COLOR);
			b.setColor(c);
			if (c!=null) {
				b.setLogoBackground(Json.colorValueOf(branding, JsonKey.LOGO_BACKGROUND, b.getColor()));
			} else {
				b.setLogoBackground(Json.colorValueOf(branding, JsonKey.LOGO_BACKGROUND, null));
			}
			b.setPageflip(Pageflip.fromJSON(branding.getJSONObject(JsonKey.PAGEFLIP)));
		} catch (JSONException e) {
			EtaLog.e(TAG, "", e);
		}
		return b;
	}
	
	public JSONObject toJSON() {
		JSONObject o = new JSONObject();
		try {
			o.put(JsonKey.NAME, Json.nullCheck(getName()));
			o.put(JsonKey.URL_NAME, Json.nullCheck(getUrlName()));
			o.put(JsonKey.WEBSITE, Json.nullCheck(getWebsite()));
			o.put(JsonKey.LOGO, Json.nullCheck(getLogo()));
			o.put(JsonKey.COLOR, Json.nullCheck(Utils.colorToString(getColor())));
			o.put(JsonKey.LOGO_BACKGROUND, Json.nullCheck(Utils.colorToString(getLogoBackground())));
			o.put(JsonKey.PAGEFLIP, Json.nullCheck(getPageflip().toJSON()));
		} catch (JSONException e) {
			EtaLog.e(TAG, "", e);
		}
		return o;
	}
	
	public Branding setName(String name) {
		mName = name;
		return this;
	}

	public String getName() {
		return mName;
	}

	public Branding setUrlName(String urlName) {
		mUrlName = urlName;
		return this;
	}

	public String getUrlName() {
		return mUrlName;
	}

	public Branding setWebsite(String website) {
		mWebsite = website;
		return this;
	}

	public String getWebsite() {
		return mWebsite;
	}

	public Branding setLogo(String logo) {
		mLogo = logo;
		return this;
	}

	public String getLogo() {
		return mLogo;
	}

	public Branding setLogoBackground(Integer color) {
		mLogoBackground = Utils.colorSanitize(color);
		return this;
	}
	
	public Integer getLogoBackground() {
		return mLogoBackground;
	}
	
	public Branding setColor(Integer color) {
		mColor = Utils.colorSanitize(color);
		return this;
	}

	public Integer getColor() {
		return mColor;
	}
	
	public Branding setPageflip(Pageflip pageflip) {
		mPageflip = pageflip;
		return this;
	}

	public Pageflip getPageflip() {
		return mPageflip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mColor == null) ? 0 : mColor.hashCode());
		result = prime * result + ((mLogo == null) ? 0 : mLogo.hashCode());
		result = prime * result
				+ ((mLogoBackground == null) ? 0 : mLogoBackground.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result
				+ ((mPageflip == null) ? 0 : mPageflip.hashCode());
		result = prime * result
				+ ((mUrlName == null) ? 0 : mUrlName.hashCode());
		result = prime * result
				+ ((mWebsite == null) ? 0 : mWebsite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branding other = (Branding) obj;
		if (mColor == null) {
			if (other.mColor != null)
				return false;
		} else if (!mColor.equals(other.mColor))
			return false;
		if (mLogo == null) {
			if (other.mLogo != null)
				return false;
		} else if (!mLogo.equals(other.mLogo))
			return false;
		if (mLogoBackground == null) {
			if (other.mLogoBackground != null)
				return false;
		} else if (!mLogoBackground.equals(other.mLogoBackground))
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPageflip == null) {
			if (other.mPageflip != null)
				return false;
		} else if (!mPageflip.equals(other.mPageflip))
			return false;
		if (mUrlName == null) {
			if (other.mUrlName != null)
				return false;
		} else if (!mUrlName.equals(other.mUrlName))
			return false;
		if (mWebsite == null) {
			if (other.mWebsite != null)
				return false;
		} else if (!mWebsite.equals(other.mWebsite))
			return false;
		return true;
	}

	private Branding(Parcel in) {
		this.mName = in.readString();
		this.mUrlName = in.readString();
		this.mWebsite = in.readString();
		this.mLogo = in.readString();
		this.mLogoBackground = (Integer)in.readValue(Integer.class.getClassLoader());
		this.mColor = (Integer)in.readValue(Integer.class.getClassLoader());
		this.mPageflip = in.readParcelable(Pageflip.class.getClassLoader());
	}

	public int describeContents() { 
		return 0; 
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.mName);
		dest.writeString(this.mUrlName);
		dest.writeString(this.mWebsite);
		dest.writeString(this.mLogo);
		dest.writeValue(this.mLogoBackground);
		dest.writeValue(this.mColor);
		dest.writeParcelable(this.mPageflip, flags);
	}
	
}