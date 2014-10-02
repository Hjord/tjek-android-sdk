package com.eTilbudsavis.etasdk.Network.Impl;

import com.eTilbudsavis.etasdk.Eta;
import com.eTilbudsavis.etasdk.Log.EtaLog;
import com.eTilbudsavis.etasdk.Network.Request;
import com.eTilbudsavis.etasdk.Network.RequestDebugger;

public class PerformanceDebugger implements RequestDebugger {
	
	public static final String TAG = Eta.TAG_PREFIX + PerformanceDebugger.class.getSimpleName();
	
	public void onFinish(Request<?> req) {
		EtaLog.d(TAG, req.getLog().getString(getClass().getSimpleName()));
	}

	public void onDelivery(Request<?> r) {
		EtaLog.d(TAG, "TotalDuration: " + r.getLog().getTotalDuration());
	}
	
}
