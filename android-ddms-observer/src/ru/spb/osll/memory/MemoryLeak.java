package ru.spb.osll.memory;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemoryLeak extends Activity {
    private List<LeakClass> list = new ArrayList<LeakClass>(); 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button leakBtn = (Button) findViewById(R.id.start_button);
        leakBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list.add(new LeakClass());
            }
        });
    
    }

    public static class LeakClass {
        private byte[] array = new byte[1024*1024]; 
    }
}