package com.napt.studentregister.cf.contant;

import com.napt.studentregister.cf.helper.FileSave;
import com.napt.studentregister.cf.model.Name_Id;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.currentTimeMillis;

/**
 * Created by sreelal on 1/1/18.
 */

public class Constant {

    public static List<Name_Id> Shirt_SizeListItems()

    {

        List<Name_Id> _shirtsize = new ArrayList<>();
        _shirtsize.clear();

        _shirtsize.add(new Name_Id("Select Size", ""));
        _shirtsize.add(new Name_Id("S", "1"));
        _shirtsize.add(new Name_Id("M", "2"));
        _shirtsize.add(new Name_Id("L", "3"));
        _shirtsize.add(new Name_Id("XL", "4"));
        _shirtsize.add(new Name_Id("XXL", "5"));
        return _shirtsize;

    }

    public static List<Name_Id> Education_ListItems() {
        List<Name_Id> educationlist = new ArrayList<>();
        educationlist.clear();
        educationlist.add(new Name_Id("Select", ""));
        educationlist.add(new Name_Id("10th", "1"));
        educationlist.add(new Name_Id("12th", "2"));
        educationlist.add(new Name_Id("Degree", "3"));
        return educationlist;

    }


    public static File tempFilepath() {

        File tempFile = null;
        String timeStamp = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String imageFileName = timeStamp + currentTimeMillis() + "_.jpg";
        File profile =FileSave.profilefolder();
        if (!profile.exists()) {
            profile.mkdir();
        }
        tempFile = new File(profile, imageFileName);
        return tempFile;

    }


}
