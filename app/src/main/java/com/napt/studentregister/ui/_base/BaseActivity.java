package com.napt.studentregister.ui._base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.napt.studentregister.BuildConfig;
import com.napt.studentregister.R;
import com.napt.studentregister.cf.adapter.OneField_Adapter;
import com.napt.studentregister.cf.contant.Constant;
import com.napt.studentregister.cf.contant.PERMISSION;
import com.napt.studentregister.cf.data.DataManager;
import com.napt.studentregister.cf.helper.PermissionHelper;
import com.napt.studentregister.di.component.ActivityComponent;
import com.napt.studentregister.di.component.DaggerActivityComponent;
import com.napt.studentregister.di.module.ActivityModule;
import com.napt.studentregister.mApp;
import com.napt.studentregister.ui.register.RegisterView;
import com.napt.studentregister.ui.register.Register_i_Presenter;

import java.io.File;
import java.io.InputStream;

import javax.inject.Inject;


/**
 * Created by sreelal on 13/12/17.
 */

public class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent activityComponent;

    @Inject
    Register_i_Presenter<RegisterView> registerview;


    protected
    PermissionHelper permissionHelper;

    @Inject
    protected
    DataManager dataManager;
    private File tempFile;
    private Uri imageUri;


    private cameraResult camera_result;
    private int REQUEST_CODE;
    protected
    @Inject
    OneField_Adapter oneFieldAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((mApp) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);

        permissionHelper = new PermissionHelper(this, PERMISSION.ALL, 100);

    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void showLoading() {

    }
    @Override
    public void setCameraSet(cameraResult cam_result) {
        this.camera_result = cam_result;
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(getResources().getColor(R.color.snackbar));
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }


    public void setwindowFull(AppCompatActivity activity) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void snakbarFixed(int message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(getResources().getColor(R.color.snackbar));
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }


    @Override
    public void showRetry(int type) {
        String message = getString(R.string.resourcenotfound);
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(getResources().getColor(R.color.snackbar));
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                registerview.callback(1);
            }
        });
    }

    @Override
    public void showMessage(int resId) {

    }

    void cameraOpen() {
        try {
            tempFile = Constant.tempFilepath();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imageUri = Uri.fromFile(tempFile);
            } else {
                imageUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", tempFile);
            }
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();

            showSnackBar("Camera not working");
            //Toast.makeText(HomeActivityStudent.this, "", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void takePic(int RequestCode) {
        this.REQUEST_CODE = RequestCode;
        if (Build.VERSION.SDK_INT > 22) {
            permissionHelper = new PermissionHelper(this, PERMISSION.ALL, 122);
            permissionHelper.request(new PermissionHelper.PermissionCallback() {
                @Override
                public void onPermissionGranted() {
                    cameraOpen();
                }

                @Override
                public void onPermissionDenied() {

                }

                @Override
                public void onPermissionDeniedBySystem() {

                }
            });
        } else {
            cameraOpen();
        }
    }


    public interface cameraResult {
        void camera_Callback(Uri uri, File file,int requestcode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                if (imageUri != null) {
                    Log.e("requestcode",REQUEST_CODE+"");
                    camera_result.camera_Callback(imageUri, tempFile,REQUEST_CODE);
                } else {

                    showSnackBar("Try again");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean isNetworkConnected() {

        return false;
    }

    @Override
    public void hideKeyboard() {

    }



    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
     public  Bitmap setImageSizeReduce(Uri uri)
     {

         try {
             Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
             bitmap = getResizedBitmap(bitmap, 1000);

             InputStream input = this.getContentResolver().openInputStream(uri);
             ExifInterface ei;
             if (Build.VERSION.SDK_INT > 23)
                 ei = new ExifInterface(input);
             else
                 ei = new ExifInterface(uri.getPath());

             int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
             Log.e("oriantation", orientation + "");
             switch (orientation) {
                 case ExifInterface.ORIENTATION_ROTATE_90:
                     return rotateImage(bitmap, 90);
                 case ExifInterface.ORIENTATION_ROTATE_180:
                     return rotateImage(bitmap, 180);
                 case ExifInterface.ORIENTATION_ROTATE_270:
                     return rotateImage(bitmap, 270);
                 default:
                     return bitmap;
             }
         }catch (Exception e){

             e.printStackTrace();
             return null;}
     }
    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


}
