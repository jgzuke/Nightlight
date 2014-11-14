package com.nightlight;

import com.gc.materialdesign.views.Slider;
import com.gc.materialdesign.views.Slider.OnValueChangedListener;
import com.gc.materialdesign.widgets.ColorSelector;
import com.gc.materialdesign.widgets.ColorSelector.OnColorSelectedListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

	Context context;
	private int backColor = Color.WHITE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
	    setContentView(R.layout.activity_main);
	    findViewById(R.id.color).setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v)
			{
	    		ColorSelector colorSelector = new ColorSelector(context, backColor,
						new OnColorSelectedListener()
					    {
							@Override
							public void onColorSelected(int color)
							{
								backColor = color;
								getWindow().getDecorView().setBackgroundColor(color);
							}
					    });
						
					    colorSelector.show();
			}
		});
	    ((Slider)findViewById(R.id.brightness)).setOnValueChangedListener(new OnValueChangedListener()
				{
					@Override
					public void onValueChanged(int value)
					{
						WindowManager.LayoutParams lp = getWindow().getAttributes();
					    lp.screenBrightness = value / 100.0f;
					    getWindow().setAttributes(lp);
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
