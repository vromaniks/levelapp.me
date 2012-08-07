package ru.spb.osll.ex.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExServiceActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(ExServiceActivity.this, ExService.class));
			}
		});

        final Button btnStop = (Button) findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(ExServiceActivity.this, ExService.class));
			}
		});
    }

	@Override
	protected void onDestroy() {
		stopService(new Intent(ExServiceActivity.this, ExService.class));
		super.onDestroy();
	}
}