package programci.lezzetim;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Handler;

public class listeayrinti extends AppCompatActivity {

    EditText mlz,sure,kisi,tarif,acik,like;
    database db;
    Toolbar tlb;
    String ad;
    RelativeLayout rl1,rl2;
    Button btn_del,btn_up,btn_no,btn_yes;
    TextView id,x;
    ImageView ivresim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listeayrintipage);

        ivresim = (ImageView) findViewById(R.id.iv);
        tlb= (Toolbar) findViewById(R.id.toolbar);
        btn_del =(Button) findViewById(R.id.btn_del);
        btn_up = (Button) findViewById(R.id.btn_update);
        btn_no =(Button) findViewById(R.id.btn_no);
        btn_yes =(Button) findViewById(R.id.btn_yes);
        mlz= (EditText) findViewById(R.id.txtmlz);
        kisi= (EditText) findViewById(R.id.txtsayi);
        sure= (EditText) findViewById(R.id.txtkisi);
        tarif= (EditText) findViewById(R.id.txttarif);
        acik= (EditText) findViewById(R.id.txtacik);
        like= (EditText) findViewById(R.id.txtlike);
        db = new database(listeayrinti.this);
        tlb.setTitle(getIntent().getExtras().getString("id"));
        rl2 = (RelativeLayout) findViewById(R.id.rl_b);

        rl2.setVisibility(View.INVISIBLE);


        try{
            ad = tlb.getTitle().toString();
            db = new database(getApplicationContext());
            mlz.setText(db.yemekmlzgetir(ad).toString());
            kisi.setText(db.yemekkisigetir(ad).toString());
            sure.setText(db.yemeksuregetir(ad).toString());
            tarif.setText(db.yemektarifgetir(ad).toString());
            acik.setText(db.yemekackgetir(ad).toString());


            ivresim.setImageBitmap(db.yemekresimgetir(ad));

        }catch (Exception e)
        {

            Toast.makeText(listeayrinti.this, "offff!!!", Toast.LENGTH_SHORT).show();


        }

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_no.setVisibility(View.VISIBLE);
                btn_yes.setVisibility(View.VISIBLE);
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_no.setVisibility(View.INVISIBLE);
                        btn_yes.setVisibility(View.INVISIBLE);
                    }
                });
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.delete(ad);
                        Intent i = new Intent(listeayrinti.this,main.class);
                        startActivity(i);
                        Toast.makeText(listeayrinti.this, "Succesfuly deleting", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        btn_up.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mlz.setFocusable(true);
                mlz.setFocusableInTouchMode(true);
                kisi.setFocusable(true);
                kisi.setFocusableInTouchMode(true);
                sure.setFocusable(true);
                sure.setFocusableInTouchMode(true);
                tarif.setFocusable(true);
                tarif.setFocusableInTouchMode(true);
                acik.setFocusable(true);
                acik.setFocusableInTouchMode(true);

                Toast.makeText(listeayrinti.this, " Lets Updating ", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlz.requestFocus()== false){
                    Toast.makeText(listeayrinti.this, " Please Long Click ", Toast.LENGTH_SHORT).show();
                }
                else{
                    db = new database(getApplicationContext());
                    db.updateyemek(db.yemekidgetir(ad), mlz.getText().toString(), kisi.getText().toString(),
                            sure.getText().toString(), tarif.getText().toString(), acik.getText().toString());
                    mlz.setFocusable(false);
                    mlz.setFocusableInTouchMode(false);
                    kisi.setFocusable(false);
                    kisi.setFocusableInTouchMode(false);
                    sure.setFocusable(false);
                    sure.setFocusableInTouchMode(false);
                    tarif.setFocusable(false);
                    tarif.setFocusableInTouchMode(false);
                    acik.setFocusable(false);
                    acik.setFocusableInTouchMode(false);
                    Toast.makeText(listeayrinti.this, " Succesfuly Update ", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }




}
