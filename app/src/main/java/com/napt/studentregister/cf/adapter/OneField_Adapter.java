package com.napt.studentregister.cf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.napt.studentregister.R;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.cf.model.db.LocalBody;

import java.util.List;

/**
 * Created by sreelal on 21/12/17.
 */

public class OneField_Adapter extends BaseAdapter {

    List<?> arraylist;
    Context context;
    boolean type=false;
    public OneField_Adapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
    }

    public OneField_Adapter(Context context,
                         List<?> arrayofUsers,boolean type) {
        // TODO Auto-generated constructor stub

        arraylist=arrayofUsers;
        this.context=context;
        this.type=type;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int positon, View view, ViewGroup parent) {
        // TODO Auto-generated method stub

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.spinnertxt, parent,false);

        String value="";
        if(type)
        {   Name_Id name=(Name_Id) arraylist.get(positon);
            value=name.getName();
        }else{
            LocalBody name=(LocalBody)arraylist.get(positon);
            value=name.getLb_name_eng();
        }
        TextView spinner = (TextView) view.findViewById(R.id.spinnertextview);
        spinner.setText(value!=null?value:"");

        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.spinnertxt, parent,false);
         String value="";
            if(type)
            {   Name_Id name=(Name_Id) arraylist.get(position);
                 value=name.getName();
            }else{
                LocalBody name=(LocalBody)arraylist.get(position);
                 value=name.getLb_name_eng();
            }
        TextView spinner = (TextView) view.findViewById(R.id.spinnertextview);
        if(position==0)
        {
            spinner.setVisibility(View.GONE);

        }
        else{

            spinner.setVisibility(View.VISIBLE);
            spinner.setText(value!=null?value:"");
        }


        return view;

    }

    public void Collection_NameId(List<Name_Id> list) {
        if(this.arraylist.size()!=0){arraylist.clear();}
       this. arraylist=list;
    }


    public void Collection_Localbodyname(List<LocalBody> list, boolean type) {
                if(this.arraylist.size()!=0){arraylist.clear();}
              this. arraylist=list;
              this.type=type;
    }
}
