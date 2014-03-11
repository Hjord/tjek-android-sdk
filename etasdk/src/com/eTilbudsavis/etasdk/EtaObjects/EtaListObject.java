package com.eTilbudsavis.etasdk.EtaObjects;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.eTilbudsavis.etasdk.Utils.EtaLog;

public abstract class EtaListObject<T> extends EtaErnObject<T> implements Comparable<T>, Serializable {
	
	public static final String TAG = "EtaListObject";

	private static final long serialVersionUID = 8166712456946780878L;
	
	/**
	 * The state an EtaListObject can be in, this is an indication of
	 * whether the item needs synchronization with the API or not.
	 */
	public interface State {
		int TO_SYNC	= 0;
		int SYNCING	= 1;
		int SYNCED	= 2;
		int DELETE	= 4;
		int ERROR	= 5;
	}
	
	/** A string indication the first item in a list of items */
	public final static String FIRST_ITEM = "00000000-0000-0000-0000-000000000000";
	
	private int mState = State.TO_SYNC;
	
	/**
	 * Get the current state of this object. The state is <i>not</i> a feature of the API, and only for usage client-side.
	 * This is used throughout the SDK, to handle synchronization of e.g. lists, and their items.
	 * @return A {@link State}
	 */
	public int getState() {
		return mState;
	}
	
	/**
	 * Set a new {@link State} for this object.
	 * @param state A {@link State} (where state >= {@link State#TO_SYNC} && state <= {@link State#ERROR})
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T setState(int state) {
		if (State.TO_SYNC <= state && state <= State.ERROR) {
			mState = state;
		}
		return (T)this;
	}
	
	public String getStateString() {
		
		switch (mState) {
		case State.TO_SYNC:
			return "TO_SYNC";

		case State.SYNCING:
			return "SYNCING";

		case State.SYNCED:
			return "SYNCED";

		case State.DELETE:
			return "DELETE";
			
		case State.ERROR:
			return "ERROR";
			
		default:
			break;
		}
		return null;
	}
	
	public JSONObject toJSON() {
		JSONObject o = super.toJSON();
		try {
			o.put("state", getStateString());
		} catch (JSONException e) {
			EtaLog.d(TAG, e);
		}
		return o;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + mState;
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
		EtaListObject other = (EtaListObject) obj;
//		if (mState != other.mState)
//			return false;
		return true;
	}
	
	
	
}
