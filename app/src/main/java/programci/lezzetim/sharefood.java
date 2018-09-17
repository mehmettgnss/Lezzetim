package programci.lezzetim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class sharefood extends android.app.Fragment {
    EditText y_adi, y_mlz, y_sure, y_trf, y_ack;
    Spinner y_kisi;
    Button btn_paylas, btn_vazgec, btn_cek;
    String yadi, ymlz, ykisi, ysure, ytrf, yack, username;
    Integer like;
    SessionManagement s;
    ImageView iv;
    byte[] gelenresim;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.sharefoodpage, container, false);
        s = new SessionManagement(sharefood.this.getActivity());



        btn_vazgec = (Button) v.findViewById(R.id.btn_vazcay);
        btn_vazgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sharefood.this.getActivity(), main.class);
                startActivity(i);
            }
        });
        iv = (ImageView) v.findViewById(R.id.iv);
        btn_cek = (Button) v.findViewById(R.id.btncek);
        btn_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent i = new Intent(sharefood.this.getActivity(),yemekresim.class);
                startActivity(i);*/

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });
        y_adi = (EditText) v.findViewById(R.id.txtyemekadi);
        y_mlz = (EditText) v.findViewById(R.id.txtmalzemeler);
        y_kisi = (Spinner) v.findViewById(R.id.sp_kisi);
        y_sure = (EditText) v.findViewById(R.id.txtsure);
        y_trf = (EditText) v.findViewById(R.id.txttarif);
        y_ack = (EditText) v.findViewById(R.id.txtaciklama);




        btn_paylas = (Button) v.findViewById(R.id.btn_paylas);
        btn_paylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                HashMap<String, String> user = s.getUserDetails();
                username = user.get(SessionManagement.KEY_USERNAME);

                yadi = y_adi.getText().toString();
                ymlz = y_mlz.getText().toString();
                ykisi = y_kisi.getSelectedItem().toString();
                ysure = y_sure.getText().toString();
                ytrf = y_trf.getText().toString();
                yack = y_ack.getText().toString();



                try {

                    if (yadi.isEmpty() || ymlz.isEmpty() || ysure.isEmpty() || ytrf.isEmpty())
                        Toast.makeText(sharefood.this.getActivity(), "Fields Are Empty !!", Toast.LENGTH_SHORT).show();

                    else {

                        yemekbilgiler y1 = new yemekbilgiler(yadi, ymlz, ykisi, ysure, ytrf, yack, username,imageview(iv));
                        database db = new database(sharefood.this.getActivity());
                        db.yemekEkle(y1);
                        Intent intent = new Intent(sharefood.this.getActivity(), main.class);
                        startActivity(intent);
                        Toast.makeText(sharefood.this.getActivity(), "Sharing Succesfuly", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    Toast.makeText(sharefood.this.getActivity(), "Error!!!\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        return v;

    }

    private byte[] imageview(ImageView iv) {
            Bitmap bitmap = ((BitmapDrawable)iv.getDrawable()).getBitmap();
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, s);
            byte[] byt = s.toByteArray();
            return  byt;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iv.setImageBitmap(imageBitmap);
        }
    }
}