package com.napt.studentregister.cf.helper;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by sreelal on 20/11/17.
 */

public class FileSave  {

    Context context;

    public static File rootfolder() {
        File storageDir = null;
        try {
            storageDir = Environment.getExternalStoragePublicDirectory("NAPT");
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageDir;
    }


    public  static  File profilefolder() {
           rootfolder() ;
        File storageDir = null;
        try {
            storageDir = Environment.getExternalStoragePublicDirectory("Profile");
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageDir;
    }


    public static File attachfolder() {

          rootfolder() ;
        File storageDir=null;
        try {
            storageDir = Environment.getExternalStoragePublicDirectory("Attachment");
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageDir;
    }





  /*  public static File profile(Context c) {

        try {
            storageDir = Environment.getExternalStoragePublicDirectory("NAPT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageDir;
    }

    public static File attachment(Context c) {
        try {
            storageDir = Environment.getExternalStoragePublicDirectory("NAPT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageDir;
    }*/
}
