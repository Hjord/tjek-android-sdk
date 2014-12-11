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
package com.eTilbudsavis.etasdk.EtaObjects.helper;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

import com.eTilbudsavis.etasdk.Eta;
import com.eTilbudsavis.etasdk.EtaObjects.Interface.EtaObject;
import com.eTilbudsavis.etasdk.Log.EtaLog;
import com.eTilbudsavis.etasdk.Utils.Api.JsonKey;
import com.eTilbudsavis.etasdk.Utils.Json;
import android.os.Parcelable;
import android.os.Parcel;

public class Pageflip implements EtaObject<JSONObject>, Serializable, Parcelable {
	
	private static final long serialVersionUID = 1L;

	public static final String TAG = Eta.TAG_PREFIX + Pageflip.class.getSimpleName();
	
	private String mLogo;
	private int mColor = 0;

	public static Parcelable.Creator<Pageflip> CREATOR = new Parcelable.Creator<Pageflip>(){
		public Pageflip createFromParcel(Parcel source) {
			return new Pageflip(source);
		}
		public Pageflip[] newArray(int size) {
			return new Pageflip[size];
		}
	};
	
	public Pageflip() {
		
	}
	
	public Pageflip(int color) {
		mColor = color;
	}
	
	public static Pageflip fromJSON(JSONObject pageflip) {
		Pageflip p = new Pageflip();
		if (pageflip == null) {
			return p;
		}
		
		p.setLogo(Json.valueOf(pageflip, JsonKey.LOGO));
		String color = Json.valueOf(pageflip, JsonKey.COLOR, "7b9119");
		p.setColor(Color.parseColor(String.format("#%s", color)));
		
		return p;
	}
	
	public JSONObject toJSON() {
		JSONObject o = new JSONObject();
		try {
			o.put(JsonKey.LOGO, Json.nullCheck(getLogo()));
			o.put(JsonKey.COLOR, Json.nullCheck(getColorString()));
		} catch (JSONException e) {
			EtaLog.e(TAG, "", e);
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + mColor;
		result = prime * result + ((mLogo == null) ? 0 : mLogo.hashCode());
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
		Pageflip other = (Pageflip) obj;
		if (mColor != other.mColor)
			return false;
		if (mLogo == null) {
			if (other.mLogo != null)
				return false;
		} else if (!mLogo.equals(other.mLogo))
			return false;
		return true;
	}

	private Pageflip(Parcel in) {
		this.mLogo = in.readString();
		this.mColor = in.readInt();
	}

	public int describeContents() { 
		return 0; 
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.mLogo);
		dest.writeInt(this.mColor);
	}
	
	
}
