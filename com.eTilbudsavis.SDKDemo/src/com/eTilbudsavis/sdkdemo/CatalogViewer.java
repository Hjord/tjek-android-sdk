package com.eTilbudsavis.sdkdemo;

import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.eTilbudsavis.etasdk.Api.ListListener;
import com.eTilbudsavis.etasdk.Eta;
import com.eTilbudsavis.etasdk.Pageflip;
import com.eTilbudsavis.etasdk.Pageflip.PageflipListener;
import com.eTilbudsavis.etasdk.EtaObjects.Catalog;
import com.eTilbudsavis.etasdk.EtaObjects.EtaError;
import com.eTilbudsavis.etasdk.Utils.Utils;
import com.eTilbudsavis.sdkdemo.helpers.Keys;
import com.etilbudsavis.sdkdemo.R;

public class CatalogViewer extends Activity {

	public static final String TAG = "CatalogViewer";
	Eta mEta;
	Pageflip pf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_viewer);
        
        /* Create a new instance of Eta 
         * TODO: Un comment line below and add own API KEY/SECRET
         */
        mEta = new Eta(Keys.API_KEY, Keys.API_SECRET, this);
        
        /* Enable debug mode, so debug info will show in LogCat
         * You might not want to have this set to true in a release version.
         */
        mEta.debug(true);
        
        // Set the location (This could also be set via LocationManager)
        mEta.getLocation().setLatitude(55.63105);
        mEta.getLocation().setLongitude(12.5766);
        mEta.getLocation().setRadius(700000);
        mEta.getLocation().setSensor(false);
        
		mEta.getCatalogList(catalogListener).execute();
    }
    
	// A catalogs listener, 
	ListListener<Catalog> catalogListener = new ListListener<Catalog>() {
		
		@Override
		public void onComplete(boolean isCache, int statusCode, List<Catalog> list, EtaError error) {

			if (Utils.isSuccess(statusCode) && !list.isEmpty()) {
				
				/* If the callback one or more catalogs,
				 * show the first catalog in a pageflip.
				 */
				
				pf = (Pageflip)findViewById(R.id.pageflip);
		        pf.execute(mEta, pfl, list.get(0).getId());
		        
			} else {
				
				Utils.logd(TAG, error.toString());
				
			}
		}
	};
	
	// Pageflip listener, triggered on callbacks from the pageflip.
    PageflipListener pfl = new PageflipListener() {
		
		@Override
		public void onEvent(String event, String uuid, JSONObject object) {
			Toast.makeText(getApplicationContext(), event, Toast.LENGTH_SHORT).show();
			Utils.logd(TAG, event + " - " + object.toString());
		}
		
		@Override
		public void onReady(String uuid) {
			Toast.makeText(getApplicationContext(), "Ready", Toast.LENGTH_SHORT).show();
			Utils.logd(TAG, "Ready: " + uuid);
		}

	};
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    menu.add(Menu.NONE, 0, 0, "Sideoversigt");

    return super.onCreateOptionsMenu(menu); 
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	switch (item.getItemId()) {

    	case 0:
    		pf.toggleThumbnails();
    		break;

    	default:
    		break;

    	}
    	return super.onOptionsItemSelected(item);
    }
    
}
