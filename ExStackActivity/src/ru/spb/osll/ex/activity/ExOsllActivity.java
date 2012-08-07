package ru.spb.osll.ex.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExOsllActivity extends Activity {	
	private static final String COUNT_KEY = "count.key";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		final Integer stackCount = getIntent().getExtras() != null ? 
			getIntent().getExtras().getInt(COUNT_KEY, 0) : 0;
		
        Button btn = (Button) findViewById(R.id.btn_next);
        btn.setText("Stack count: " + stackCount);
        btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ExOsllActivity.this, ExOsllActivity.class);
				i.putExtra(COUNT_KEY, stackCount + 1);
				startActivity(i);
			}
		});
	}
}
