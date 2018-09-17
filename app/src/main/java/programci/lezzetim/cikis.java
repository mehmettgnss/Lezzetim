package programci.lezzetim;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class cikis extends android.app.Fragment {
    SessionManagement sm;
    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.app_bar_main,container,false);
        Button btnout = (Button) view.findViewById(R.id.btnyes);
        Button btncan = (Button) view.findViewById(R.id.btnno);
        sm = new SessionManagement(cikis.this.getActivity());

        btnout.setVisibility(View.VISIBLE);
        btncan.setVisibility(View.VISIBLE);

        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logoutUser();
            }
        });
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cikis.this.getActivity(),main.class);
                startActivity(i);
            }
        });

        return view;
    }
}
