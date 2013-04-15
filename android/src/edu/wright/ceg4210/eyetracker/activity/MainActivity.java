package edu.wright.ceg4210.eyetracker.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import edu.wright.ceg4210.eyetracker.ForegroundReportingService;
import edu.wright.ceg4210.eyetracker.R;

public class MainActivity extends Activity {
	private static final String BUTTON_TEXT_ON = "On";
	private static final String BUTTON_TEXT_OFF = "Off";

	private final boolean frsRunning = false;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			// Set foreground button toggle behavior
			final Button frsToggle = (Button) findViewById(R.id.toggleForegroundProcessReporter);
			frsToggle.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					toggleReportingService(frsToggle);
				}
			});
			toggleReportingService(frsToggle);

			// Set calibration behavior.
			final Button buttonCalibrate = (Button) findViewById(R.id.buttonCalibrate);
			buttonCalibrate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(final View v) {
					startActivity(new Intent(MainActivity.this, CalibrationActivity.class));
				}
			});

			// Set the demo mode activity
			((Button) findViewById(R.id.buttonDemoMode)).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(final View v) {
					startActivity(new Intent(MainActivity.this, DemoModeActivity.class));
				}
			});

		} catch (final Exception e) {
			Log.e("MainActivity", "There is an unhandled exception that occurred. " + e.getMessage());
		}
	}

	private void toggleReportingService(final Button frsToggle) {
		final Intent frsIntent = new Intent(this, ForegroundReportingService.class);

		if (frsRunning) {
			Log.d("MainActivity", "Stopping FRS.");
			stopService(frsIntent);
			frsToggle.setText(BUTTON_TEXT_OFF);
		} else {
			Log.d("MainActivity", "Starting FRS.");
			startService(frsIntent);
			frsToggle.setText(BUTTON_TEXT_ON);
		}
	}

	private boolean statusOfReportingService() {
		final ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		return true;
		// mActivityManager.getRunningServices(200).contains(arg0)
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
