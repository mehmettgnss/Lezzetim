package programci.lezzetim;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class masterayrinti extends AppCompatActivity {

    TextView mlz,sure,kisi,tarif,acik,like;
    database db;
    Toolbar tlb;
    Button btn_like,btn_dlike;
    String ad,username,yadi,id,yid;
    SessionManagement s;
    ImageView ivresim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masterayrintipage);
        s = new SessionManagement(masterayrinti.this);
        tlb= (Toolbar) findViewById(R.id.toolbar);
        ivresim = (ImageView) findViewById(R.id.iv);
        db = new database(masterayrinti.this);
        btn_like = (Button) findViewById(R.id.btn_like);
        btn_dlike = (Button) findViewById(R.id.btn_dlike);
        mlz= (TextView) findViewById(R.id.txtmlz);
        kisi= (TextView) findViewById(R.id.txtkisi);
        sure= (TextView) findViewById(R.id.txtsure);
        tarif= (TextView) findViewById(R.id.txttarif);
        acik= (TextView) findViewById(R.id.txtacik);
        like= (TextView) findViewById(R.id.txtlike);
        tlb.setTitle(getIntent().getExtras().getString("id"));
        btn_dlike.setVisibility(View.INVISIBLE);
        ad = tlb.getTitle().toString();
        id = db.yemekidgetir(ad);

        HashMap<String, String> user = s.getUserDetails();
        username = user.get(SessionManagement.KEY_USERNAME);


        if(db.kiyaslalike(username,id)==true){
            btn_like.setVisibility(View.INVISIBLE);
            btn_dlike.setVisibility(View.VISIBLE);
        }



        try{

            mlz.setText(db.yemekmlzgetir(ad).toString());
            kisi.setText(db.yemekkisigetir(ad).toString());
            sure.setText(db.yemeksuregetir(ad).toString());
            tarif.setText(db.yemektarifgetir(ad).toString());
            acik.setText(db.yemekackgetir(ad).toString());
            like.setText(String.valueOf(db.say(id)));

         /*   byte[] foodimage = db.yemekresimgetir(ad);
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage, 0, foodimage.length);*/
            ivresim.setImageBitmap(db.yemekresimgetir(ad));

        }catch (Exception e)
        {
            Toast.makeText(masterayrinti.this, "offff!!!", Toast.LENGTH_SHORT).show();


        }

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_like.setVisibility(View.INVISIBLE);
                btn_dlike.setVisibility(View.VISIBLE);

                yid = db.yemekidgetir(ad);
                db.begenen(ad,username,yid);
                like.setText(String.valueOf(db.say(id)));
                Toast.makeText(masterayrinti.this, "Liked", Toast.LENGTH_SHORT).show();


            }
        });

        btn_dlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btn_like.setVisibility(View.VISIBLE);
                btn_dlike.setVisibility(View.INVISIBLE);
                try {

                    db.deletelike(db.bidgetir(username));
                    like.setText(String.valueOf(db.say(id)));
                    Toast.makeText(masterayrinti.this, "DisLiked", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(masterayrinti.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
