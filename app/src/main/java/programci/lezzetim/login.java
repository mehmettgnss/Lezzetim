package programci.lezzetim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;
import java.net.ResponseCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import static android.Manifest.permission.READ_CONTACTS;


public class login extends AppCompatActivity {




    // UI references.
    private EditText mUserName;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView mReg;

    String username,pass;
    SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        mUserName = (EditText) findViewById(R.id.txtusername);
        mPasswordView = (EditText) findViewById(R.id.txtpassword);
        mReg = (TextView) findViewById(R.id.txtreg);

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_sign);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mUserName.getText().toString();
                pass = mPasswordView.getText().toString();
                if(username.isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(login.this, "Fields are Empty!!!", Toast.LENGTH_SHORT).show();
                }
                database db=new database(login.this);
                String contpass=db.passcont(username);
                String id = db.idgetir(username);
                String ad = db.adgetir(username);
                String email = db.emailgetir(username);

                 if(pass.equals(contpass))
                {
                    session = new SessionManagement(getApplicationContext());
                    session.createLoginSession(id,ad,username,email,pass);
                    Toast.makeText(login.this, "Sign successful...", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),main.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(login.this, "Incorret Username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });



        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void txt_click (View view){
        Intent i2 = new Intent(getApplicationContext(),register.class);
        startActivity(i2);
    }

}


