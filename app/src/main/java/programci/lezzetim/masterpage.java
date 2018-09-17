package programci.lezzetim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class masterpage extends android.app.Fragment{
    ListView liste;
    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.masterpage,container,false);
        liste = (ListView) view.findViewById(R.id.list_food);
        try {
            database db = new database(masterpage.this.getActivity());
            List<String> vVeriler = db.YemekListele();
            ArrayAdapter<String> adap = new ArrayAdapter<String>(masterpage.this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
            liste.setAdapter(adap);
        }
        catch (Exception e){
            Toast.makeText(masterpage.this.getActivity(), "Bilinmeyen Hata!\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(masterpage.this.getActivity(),masterayrinti.class);
                String selectedItem = (String) parent.getItemAtPosition(position);
                i.putExtra("id",selectedItem);
                startActivity(i);
            }
        });



        return view;
    }
}
