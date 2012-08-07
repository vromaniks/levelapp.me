package ru.spb.osll.ex.receiver;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ExReceiverActivity extends Activity {
    
	private BroadcastReceiver m_receiver; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // register receiver
        m_receiver = new ExReceiver();
        registerReceiver(m_receiver, new IntentFilter(ExReceiver.ACTION));
    
        // send message
        Button btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ExReceiver.ACTION);
				i.putExtra(ExReceiver.TYPE_MESS, "OSLL Rulez!!!");
				sendBroadcast(i);
			}
		});
    }

    @Override
	protected void onDestroy() {
    	unregisterReceiver(m_receiver);
    	super.onDestroy();
	}

	public class ExReceiver extends BroadcastReceiver {
    	public static final String ACTION 		= "osll.ex.action";
    	public static final String TYPE_MESS	= "osll.ex.mess";
    	
    	@Override
    	public void onReceive(Context context, Intent intent) {
    		String mess = intent.getStringExtra(TYPE_MESS);
    		Toast.makeText(ExReceiverActivity.this, mess, Toast.LENGTH_SHORT).show();
    	}
    }
}