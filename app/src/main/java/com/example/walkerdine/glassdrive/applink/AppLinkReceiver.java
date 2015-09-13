package com.example.walkerdine.glassdrive.applink;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.walkerdine.glassdrive.Drive;

public class AppLinkReceiver  extends BroadcastReceiver {		
	public void onReceive(Context context, Intent intent) {
		// Start the AppLinkService on BT connection
		if (intent.getAction().compareTo(BluetoothDevice.ACTION_ACL_CONNECTED) == 0) {
			Drive app = Drive.getInstance();
			if (app != null) {
				app.startSyncProxyService();
			}
		}
		// Stop the AppLinkService on BT disconnection
		else if (intent.getAction().compareTo(BluetoothDevice.ACTION_ACL_DISCONNECTED) == 0) {
			AppLinkService als = AppLinkService.getInstance();
			Drive app = Drive.getInstance();
			if (app != null && als != null) {
				app.endSyncProxyService();
			}
		}
		else if (intent.getAction().equals(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
			// signal your service to stop audio playback
		}
	}
}