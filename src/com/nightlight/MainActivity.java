package com.nightlight;

import com.gc.materialdesign.views.Slider;
import com.gc.materialdesign.views.Slider.OnValueChangedListener;
import com.gc.materialdesign.widgets.ColorSelector;
import com.gc.materialdesign.widgets.ColorSelector.OnColorSelectedListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
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
				ColorSelector colorSelector = new ColorSelector(context, Color.WHITE,
						new OnColorSelectedListener()
					    {
							@Override
							public void onColorSelected(int color)
							{
								getWindow().getDecorView().setBackgroundColor(color);
							}
					    });
						
					    colorSelector.show();
			}
		});
	    findViewById(R.id.brightness).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View flatButton)
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				Slider bar = (Slider) layoutInflater.inflate(R.layout.slidebar, null, false);
				WindowManager.LayoutParams lp = getWindow().getAttributes();
			    lp.screenBrightness = 0;
			    getWindow().setAttributes(lp);
				bar.setOnValueChangedListener(new OnValueChangedListener()
				{
					@Override
					public void onValueChanged(int value)
					{
						WindowManager.LayoutParams lp = getWindow().getAttributes();
					    lp.screenBrightness = value / 100.0f;
					    getWindow().setAttributes(lp);
					}
				});
				builder.setView(bar).setTitle("Brightness").create().show();
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
