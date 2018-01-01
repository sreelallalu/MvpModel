package com.napt.studentregister.cf.helper;

import android.content.Context;
import android.os.Environment;

import com.napt.studentregister.cf.helper.connection.FileFoleder;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by sreelal on 20/11/17.
 */

public class FileSave implements FileFoleder{

     Context context;

     @Inject
    public FileSave(Context context) {

        this.context = context;
    }



    @Override
    public boolean rootfolder() {
        File storageDir=null;
          try {
           storageDir = Environment.getExternalStoragePublicDirectory("NAPT");
              if (!storageDir.exists()) {
                  storageDir.mkdir();
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageDir.mkdir();
    }

    @Override
    public void profilefolder() {


    }

    @Override
    public void attachfolder() {

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
