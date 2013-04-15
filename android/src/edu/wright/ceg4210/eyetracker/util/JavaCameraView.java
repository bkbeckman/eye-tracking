package edu.wright.ceg4210.eyetracker.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import edu.wright.ceg4210.eyetracker.activity.DemoModeActivity;

public class JavaCameraView extends org.opencv.android.JavaCameraView {

	public JavaCameraView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		Log.i("javacameraview", "instantiating.");
	}

	public JavaCameraView(final DemoModeActivity context, final int cameraFacingFront) {
		super(context, cameraFacingFront);
		Log.i("javacameraview", "instantiating.");
		initializeCamera(300, 300);
	}

}
