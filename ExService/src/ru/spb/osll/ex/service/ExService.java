package ru.spb.osll.ex.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ExService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		showMessage("Hello, I'm Service. Service is created!");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		showMessage("Hello, I'm Service. Service is started!");
	}

	@Override
	public void onDestroy() {
		showMessage("Service is removed!");
		super.onDestroy();
	}
	
	private void showMessage(String mess){
		Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
	}
}