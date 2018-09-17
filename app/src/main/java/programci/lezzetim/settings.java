package programci.lezzetim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Visibility;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;


public class settings extends android.app.Fragment{

    View view;
    EditText s_ad,s_kuad,s_email;
    SessionManagement s;
    Button btn_pass,btn_upd;
    String name,username,email,id,pass;
    database db;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings,container,false);

        s = new SessionManagement(settings.this.getActivity());
        btn_upd = (Button) view.findViewById(R.id.btn_save);




        s_ad = (EditText) view.findViewById(R.id.txtsname);
        s_kuad = (EditText) view.findViewById(R.id.txtsusername);
        s_email = (EditText) view.findViewById(R.id.txtsemail);


        HashMap<String, String> user = s.getUserDetails();
        name = user.get(SessionManagement.KEY_NAME);
        username = user.get(SessionManagement.KEY_USERNAME);
        email = user.get(SessionManagement.KEY_EMAİL);
        id = user.get(SessionManagement.KEY_ID);
        pass = user.get(SessionManagement.KEY_PASSWORD);



        s_ad.setText(name);
        s_kuad.setText(username);
        s_email.setText(email);

        btn_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                        if(s_ad.getText().toString().isEmpty() || s_kuad.getText().toString().isEmpty() || s_ad.getText().toString().isEmpty()){
                            Toast.makeText(settings.this.getActivity(), "Fields are Empty!!!", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            db=new database(settings.this.getActivity());
                            db.updateData(id,s_ad.getText().toString(),s_kuad.getText().toString(),s_email.getText().toString());
                            s.setname(s_ad.getText().toString());
                            s.setusername(s_kuad.getText().toString());
                            s.setemail(s_email.getText().toString());
                            startActivity(getActivity().getIntent());
                            Toast.makeText(settings.this.getActivity(), "Succesfuly..", Toast.LENGTH_SHORT).show();
                        }



                }catch (Exception e){
                    Toast.makeText(settings.this.getActivity(), "Error!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
