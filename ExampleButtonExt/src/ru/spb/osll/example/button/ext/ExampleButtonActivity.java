package ru.spb.osll.example.button.ext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
				addNewButton();
			}
		});
    }
    
    private void addNewButton(){
    	final LinearLayout container = (LinearLayout)findViewById(R.id.main_container);
    	container.addView(new Button(this));
    }
}
