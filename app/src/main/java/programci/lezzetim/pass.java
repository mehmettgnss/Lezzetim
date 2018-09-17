package programci.lezzetim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class pass extends android.app.Fragment {



    EditText s_opass,s_ypass,s_tpass;
    Button geri,kayit;
    SessionManagement s;
    database db;
    String o_sifre,y_sifre,t_sifre,id,pass;


    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.passpage,container,false);

        s_opass = (EditText) view.findViewById(R.id.txteskisifre);
        s_ypass = (EditText) view.findViewById(R.id.txtyenisifre);
        s_tpass = (EditText) view.findViewById(R.id.txttekrarsifre);
        geri = (Button) view.findViewById(R.id.btnlike);
        kayit = (Button) view.findViewById(R.id.save);





        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(pass.this.getActivity(),main.class);
                startActivity(i);
            }
        });

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o_sifre = s_opass.getText().toString();
                y_sifre = s_ypass.getText().toString();
                t_sifre = s_tpass.getText().toString();
                s = new SessionManagement(pass.this.getActivity());
                HashMap<String, String> user = s.getUserDetails();
                id = user.get(SessionManagement.KEY_ID);
                pass = user.get(SessionManagement.KEY_PASSWORD);
                try {
                    if(o_sifre.isEmpty() || y_sifre.isEmpty() || t_sifre.isEmpty()){
                        Toast.makeText(pass.this.getActivity(), "Fields are Empty!!!", Toast.LENGTH_SHORT).show();
                    }

                    if (o_sifre.equals(pass) || y_sifre == t_sifre) {
                        db=new database(pass.this.getActivity());
                        db.updatePas(id,y_sifre);
                        s.setpass(y_sifre);
                        startActivity(getActivity().getIntent());
                        Toast.makeText(pass.this.getActivity(), "Succesfuly..", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(pass.this.getActivity(), " False Password !!", Toast.LENGTH_SHORT).show();
                      }

                }
                catch (Exception e){
                    Toast.makeText(pass.this.getActivity(), "Error!!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;
    }

}
