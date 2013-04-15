package edu.wright.ceg4210.eyetracker.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import edu.wright.ceg4210.eyetracker.R;
import edu.wright.ceg4210.eyetracker.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e. status bar and navigation/system bar) with
 * user interaction.
 * 
 * @see SystemUiHider
 */
public class CalibrationActivity extends Activity {
	/**
	 * Whether or not the system UI should be auto-hidden after {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after user interaction before hiding the system
	 * UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 0;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise, will show the system UI visibility upon
	 * interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_calibration);
		setupActionBar();

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.tap_to_begin);

		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
		mSystemUiHider.setup();

		mSystemUiHider.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {

			@Override
			public void onVisibilityChange(final boolean visible) {
				controlsView.setVisibility(View.GONE);
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
		});

		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				final TextView textTap = (TextView) findViewById(R.id.tap_to_begin);
				textTap.setVisibility(View.GONE);
				controlsView.setVisibility(View.GONE);
				mSystemUiHider.hide();
			}
		});
	}

	@Override
	protected void onPostCreate(final Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// TODO: If Settings has multiple levels, Up should navigate up
			// that hierarchy.
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the system UI. This is to prevent the jarring
	 * behavior of controls going away while interacting with activity UI.
	 */
	/*
	 * View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
	 * 
	 * @Override public boolean onTouch(final View view, final MotionEvent motionEvent) { if (AUTO_HIDE) {
	 * delayedHide(AUTO_HIDE_DELAY_MILLIS); } return false; } };
	 */

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {

		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any previously scheduled calls.
	 */
	private void delayedHide(final int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}

	private void drawTarget() {

	}

	Runnable mCalibrater = new Runnable() {
		@Override
		public void run() {
		}

	};
}
