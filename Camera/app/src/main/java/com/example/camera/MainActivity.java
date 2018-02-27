package com.example.camera;

import com.example.camera.camera.CameraCallbackPreviewListener;
import com.example.camera.camera.CameraGLSurfaceView;
import com.example.camera.camera.FunctionCamera;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // Used to load the 'native-lib' library on application startup.

    private FunctionCamera mFunctionCamera;
    private TextView mVersion;
    private CameraGLSurfaceView mGlSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        mVersion = (TextView) findViewById(R.id.tensorflowVersion);

        mVersion.setText();

        mGlSurfaceView = (CameraGLSurfaceView) findViewById(R.id.previewSurface);

        mFunctionCamera = new FunctionCamera();

        mFunctionCamera.setCameraCallbackPreviewListener(new CameraCallbackPreviewListener() {
            @Override
            public void onPreviewFrame(Bitmap bitmap) {
                Log.d(TAG, "onPreviewFrame:调用了");
                mGlSurfaceView.setBitmap(bitmap);
            }
        });

        mFunctionCamera.openCamera();
    }
}
