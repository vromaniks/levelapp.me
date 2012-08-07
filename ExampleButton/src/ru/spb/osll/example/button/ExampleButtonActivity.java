package ru.spb.osll.example.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ExampleButtonActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button button = (Button)findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ExampleButtonActivity.this, "Osll rulez!!!",
					Toast.LENGTH_SHORT).show();
			}
		});
    }
}
