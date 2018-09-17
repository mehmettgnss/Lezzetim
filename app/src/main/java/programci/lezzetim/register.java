package programci.lezzetim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText name,username,email,pass;
    String sname;
    String susername;
    String semail;
    String spass;
    database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);

        Button btn = (Button) findViewById(R.id.btn_reg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.txtregname);
                username = (EditText) findViewById(R.id.txtregusername);
                email = (EditText) findViewById(R.id.txtregemail);
                pass = (EditText) findViewById(R.id.txtregpassword);

                try {
                    db = new database(register.this);
                    sname = name.getText().toString();
                    susername = username.getText().toString();
                    semail = email.getText().toString();
                    spass = pass.getText().toString();

                    if (sname.isEmpty() || susername.isEmpty() || semail.isEmpty() || spass.isEmpty())
                        Toast.makeText(getApplicationContext(), "Fields Are Empty!!!", Toast.LENGTH_SHORT).show();

                    else {

                        if(db.kiyasla(susername)==false){db.usereg(sname, susername, semail, spass);
                            Intent i = new Intent(getApplicationContext(), login.class);
                            startActivity(i);

                            Toast.makeText(getApplicationContext(), " Registery Succesful", Toast.LENGTH_SHORT).show();}
                        else{
                            Toast.makeText(register.this, "This Username In Use !\n" , Toast.LENGTH_SHORT).show();
                        }


                    }

                } catch (Exception e) {
                    Toast.makeText(register.this, "Error!\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
