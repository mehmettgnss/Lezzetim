package programci.lezzetim;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class mylist extends android.app.Fragment {

    database db;
    ListView liste;
    List<String> yemek;
    String[] bilgiler;
    SessionManagement sm;
    String ad;
    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.mylistpage,container,false);
        liste = (ListView) view.findViewById(R.id.yemek_list);

        sm = new SessionManagement(mylist.this.getActivity());
        liste.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(mylist.this.getActivity(),listeayrinti.class);
                String selectedItem = (String) parent.getItemAtPosition(position);
                i.putExtra("id",selectedItem);
                startActivity(i);
            }
        });

        try {
             db = new database(mylist.this.getActivity());
            HashMap<String, String> user = sm.getUserDetails();
            ad = user.get(SessionManagement.KEY_USERNAME);
            List<String> vVeriler = db.benimyemeklerim(ad);
            ArrayAdapter<String> adap2 = new ArrayAdapter<String>(mylist.this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
            liste.setAdapter(adap2);


        }
        catch (Exception e){
            Toast.makeText(mylist.this.getActivity(), "Bilinmeyen Hata!\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }


}
