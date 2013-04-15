package edu.wright.ceg4210.eyetracker;

import java.util.Iterator;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ForegroundReportingService extends IntentService {

	public ForegroundReportingService() {
		super("ForegroundReportingService");
	}

	public ForegroundReportingService(final String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(final Intent intent) {
		try {
			Log.d("ForegroundReportingService", "I'm handing the intent " + intent.getDataString());

			while (true) {
				final ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

				final Iterator<RunningAppProcessInfo> i = mActivityManager.getRunningAppProcesses().iterator();
				RunningAppProcessInfo appToCheck;
				while (i.hasNext()) {
					appToCheck = i.next();

					if (appToCheck.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
						Log.d("ForegroundReportingService", "The current foreground process is " +
								appToCheck.processName);
					}
				}

				try {
					Thread.sleep(100000);
				} catch (final InterruptedException e) {
					Log.d("ForegroundReportingService", "My sleep was interupted.");
					break;
				}
			}
		} catch (final Exception e) {
			Log.e("ForegroundReportingService", "Unhandled exception caught: " + e.getMessage());
		}
	}
}
