package com.napt.studentregister.cf.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.napt.studentregister.cf.helper.connection.DataBasePresent;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.cf.model.db.LocalBody;
import com.napt.studentregister.di.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sreelal on 16/12/17.
 */
@Singleton
public class DataBase extends SQLiteOpenHelper implements DataBasePresent {
    private static final String DATABASE_NAME = "napt_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DISTRICT1 = "loacalbody_tb1";
    private static final String KEY_IDX = "id1";
    private static final String DIST_ID = "districtid";
    private static final String DIST_NAME = "district_name";
    private static final String TABLE_LOCAL_TYPE = "loacalbody_tb2";
    private static final String LB_ID = "localb_id";
    private static final String LB_NAME = "localb_name";
    private static final String TABLE_LOCAL_BODYNAME = "loacalbodyname_tb2";
    private static final String LOCALBN_ID = "local_t_id";
    private static final String LOCALBN_TYPE = "local_t_type";
    private static final String LOCALBN_NAME = "local_t_name";
    private static final String LOCALBN_DISTID = "local_t_distid";

    @Inject
    public DataBase(@ApplicationContext  Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DISTRICT_TABLE = "CREATE TABLE " + TABLE_DISTRICT1 + "("
                + KEY_IDX + " INTEGER PRIMARY KEY,"
                + DIST_ID + " TEXT ,"
                + DIST_NAME + " TEXT"
                + ")";
        String CREATE_LOCALTYPE = "CREATE TABLE " + TABLE_LOCAL_TYPE + "("
                + LB_ID + " TEXT PRIMARY KEY,"
                + LB_NAME + " TEXT"
                + ")";
        String CREATE_LOCAL_BODY = "CREATE TABLE " + TABLE_LOCAL_BODYNAME + "("
                + LOCALBN_ID + " TEXT PRIMARY KEY,"
                + LOCALBN_TYPE + " TEXT,"
                + LOCALBN_NAME + " TEXT,"
                + LOCALBN_DISTID + " TEXT"
                + ")";

        db.execSQL(CREATE_DISTRICT_TABLE);
        db.execSQL(CREATE_LOCALTYPE);
        db.execSQL(CREATE_LOCAL_BODY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTRICT1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCAL_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCAL_BODYNAME);
        onCreate(db);
    }

    @Override
    public void setDistrict(List<Name_Id> list) {
   if(!check(TABLE_DISTRICT1)){
       deleteAll(TABLE_DISTRICT1) ;
      }
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Name_Id city : list) {
                values.put(DIST_ID, city.getId());
                values.put(DIST_NAME, city.getName());
                db.insert(TABLE_DISTRICT1, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            db.endTransaction();
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void setLocalBodyType(List<Name_Id> list) {
 if(!check(TABLE_LOCAL_TYPE)){
       deleteAll(TABLE_LOCAL_TYPE) ;
      }
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Name_Id city : list) {
                values.put(LB_ID, city.getId());
                values.put(LB_NAME, city.getName());
                db.insert(TABLE_LOCAL_TYPE, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            db.endTransaction();
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }


    }

    @Override
    public void setLocalBodyName(List<LocalBody> list) {
      if(!check(TABLE_LOCAL_BODYNAME)){
       deleteAll(TABLE_LOCAL_BODYNAME) ;
      }
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values1 = new ContentValues();
            for (LocalBody city : list) {
                values1.put(LOCALBN_ID, city.getLb_id());
                values1.put(LOCALBN_NAME, city.getLb_name_eng());
                values1.put(LOCALBN_DISTID, city.getDistrictcd());
                values1.put(LOCALBN_TYPE, city.getLb_type());
                db.insert(TABLE_LOCAL_BODYNAME, null, values1);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            db.endTransaction();
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public List<Name_Id> getDistrict() {

        List<Name_Id> distlist = new ArrayList<>();
        String selectQuery = "SELECT  * FROM "+TABLE_DISTRICT1;

        distlist.add(0,new Name_Id("Select District","0") );

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list localbodyname localbodytype
        if (cursor.moveToFirst()) {
            do {
                try {
                    distlist.add(new Name_Id(cursor.getString(2),cursor.getString(1)));
                } catch (Exception e) {

                }


            } while (cursor.moveToNext());
        }

        return distlist;



    }

    public boolean check(String tablename){
        boolean b=false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM "+tablename,null);
        cursor.moveToFirst();
        if (cursor.getInt(0) > 0)
        {
            b=false;
        }if(cursor.getInt(0)==0)
        {
            b=true;
        }
        cursor.close();
        return b;
    }
    public void deleteAll(String tablename )
    {
       SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
        db.execSQL("delete from "+ tablename);
      //  db.execSQL("TRUNCATE table" + tablename);
        db.close();
    }


    @Override
    public List<Name_Id> getLocalBodyType() {
        List<Name_Id> localbodylist = new ArrayList<>();
        String selectQuery = "SELECT  * FROM "+TABLE_LOCAL_TYPE;
        localbodylist.add(0,new Name_Id("Select Local Type","0"));
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list localbodyname localbodytype
        if (cursor.moveToFirst()) {
            do {
                try {
                    localbodylist.add(new Name_Id(cursor.getString(1),cursor.getString(0)));
                } catch (Exception e) {

                   e.printStackTrace();
                }


            } while (cursor.moveToNext());
        }

        return localbodylist;



    }

    @Override
    public List<LocalBody> getLocalBodyName(String distid,String localtype) {
        List<LocalBody> localbodyname = new ArrayList<>();
        String selectQuery = "SELECT  * FROM "+TABLE_LOCAL_BODYNAME+" where "+LOCALBN_TYPE+" = '"+localtype+"' and "+LOCALBN_DISTID+" ='"+distid+"'";

        localbodyname.add(0,new LocalBody("0","0","Select Local Name",""+0));
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list localbodyname localbodytype
        if (cursor.moveToFirst()) {
            do {
                try {

                    localbodyname.add(new LocalBody(cursor.getString(0),cursor.getString(2)));

                } catch (Exception e) {

                    //Log.d("due details", e.toString());
                }


            } while (cursor.moveToNext());
        }

        return localbodyname;
    }


}
