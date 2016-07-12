package com.hellosdl;

import com.hellosdl.sdl.SdlReceiver;
import com.hellosdl.sdl.SdlService;
import com.smartdevicelink.transport.SdlRouterStatusProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SdlReceiver.isTransportConnected(getBaseContext(),new SdlRouterStatusProvider.ConnectedStatusCallback(){
			@Override
			public void onConnectionStatusUpdate(boolean connected,Context context) {
				if(connected){
					Intent startIntent = new Intent(getBaseContext(), SdlService.class);
					startService(startIntent);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
