package com.example.admin.asm_zlud.purepackagesupport.OutAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.OutModel;

import java.util.List;

public class CustomAdapter2 extends ArrayAdapter<OutModel> {

    ///////////////////////////////////
    //CUSTOM VARIABLES CHANGE IN USE//
    /////////////////////////////////
    List<OutModel> arrayOutModel;
    int resource;
    Activity myContext;
    ListView listView;
    View note;
    //////////////////////////////////////
    //THIS CUSTOM ADAPTER IS USING FOR //
    //LISTVIEW----------------GRIDVIEW//
    ///////////////////////////////////

    //Constructor
    //////////////////////////////////

//    public CustomAdapter(@NonNull Activity context, int resource, @NonNull List<InModel> objects, ArrayList<InModel> arrayInModel, int resource1, Activity myContext) {
//        super(context, resource, objects);
//        this.arrayInModel = arrayInModel;
//        this.resource = resource1;
//        this.myContext = myContext;
//    }

    public CustomAdapter2(@NonNull Activity context, int resource, @NonNull List<OutModel> objects) {
        super(context, resource, objects);
        myContext=context;
        this.resource=resource;
        arrayOutModel=objects;
    }


    ///////////////////////////////////////////////////////////////////////
    //Overide this getView method to get the convertView
    //Use the convertView to interact with the elements in CustomView Item
    ///////////////////////////////////////////////////////////////////////


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        convertView = inflater.inflate(resource, null);
        /////////////////////////////////////////////////
        //Create Temp from DataModel to put on Custom Item
        /////////////////////////////////////////////////
        OutModel temp= arrayOutModel.get(position);
        ////////////////////////////////////////////////
        TextView tvType=convertView.findViewById(R.id.tvReaSonOut);
        TextView tvMoney=convertView.findViewById(R.id.tvMoNeyOut);

        ///////////////////////////////////////////////

        ///////////////////////////////////////////////
        //Set Data to Item
        //////////////////////////////////////////////
        tvType.setText(temp.getType().toString());
        tvMoney.setText(temp.getAmount().toString());
        return convertView;
    }
    //////////////////////////////////////////////////////////////////////////////

}
