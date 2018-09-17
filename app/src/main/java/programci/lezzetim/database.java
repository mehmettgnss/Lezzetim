package programci.lezzetim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "YemekVt";

    public static final String TABLE_KULLANICI = "kullanicilar";
    public final String SUTUN_ID = "kullanici_id";
    public final String SUTUN_ADI = "kullanici_ad";
    public final String SUTUN_KULLANICI_ADI = "kullanici_adi";
    public final String SUTUN_EMAIL = "kullanici_email";
    public final String SUTUN_SIFRE = "kullanici_sifre";

    public static final String TABLE_YEMEKLER = "yemekler";
    public final String YEMEK_ID  = "yemek_id";
    public final String YEMEK_ADI = "yemek_adi";
    public final String YEMEK_MALZEMELER = "yemek_malzeme";
    public final String YEMEK_KACKISI = "yemek_kisi";
    public final String YEMEK_SURE = "yemek_sure";
    public final String YEMEK_TARIF = "yemek_tarif";
    public final String YEMEK_ACIKLAMA = "yemek_aciklama";
    public final String SUTUN_YEMEK_ADI = "kisi_yemek";
    public final String YEMEK_BEGENI = "yemek_begeni";
    public final String YEMEK_FOTO = "yemek_foto";

    public static final String TABLE_BEGENENLER = "begenenler";
    public final String BEGENEN_ID = "begenen_id";
    public final String BEGENEN_ADI = "begenen_adi";
    public final String BEGENEN_YEMEK_ADI = "yemekin_adi";
    public final String BEGENEN_YEMEK_ID = "yemekin_id";



    public database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String TabloKullanici = " CREATE TABLE " + TABLE_KULLANICI +
            "(" + SUTUN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SUTUN_ADI + " TEXT, " +
            SUTUN_KULLANICI_ADI + " TEXT, " +
            SUTUN_EMAIL + " TEXT, " +
            SUTUN_SIFRE + " TEXT )";

    String TabloYemekler = " CREATE TABLE " + TABLE_YEMEKLER +
            "(" + YEMEK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            YEMEK_ADI + " TEXT, " +
            YEMEK_MALZEMELER + " TEXT, " +
            YEMEK_KACKISI + " TEXT, " +
            YEMEK_SURE + " TEXT, " +
            YEMEK_TARIF + " TEXT, " +
            YEMEK_ACIKLAMA + " TEXT, " +
            SUTUN_YEMEK_ADI + " TEXT, " +
            YEMEK_BEGENI + " TEXT, " +
            YEMEK_FOTO + " TEXT )";

    String TabloYemekBegeni = " CREATE TABLE " + TABLE_BEGENENLER +
            "(" + BEGENEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BEGENEN_ADI + " TEXT, " +
            BEGENEN_YEMEK_ADI+ " TEXT, " +
            BEGENEN_YEMEK_ID + " TEXT )";




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TabloKullanici);
        db.execSQL(TabloYemekler);
        db.execSQL(TabloYemekBegeni);
    }
    String DROP_USER_TABLEK = "DROP TABLE IF EXISTS " + TABLE_KULLANICI;
    String DROP_USER_TABLEY = "DROP TABLE IF EXISTS " + TABLE_YEMEKLER;
    String DROP_USER_TABLEB = "DROP TABLE IF EXISTS " + TABLE_BEGENENLER;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLEK);
        db.execSQL(DROP_USER_TABLEY);
        db.execSQL(DROP_USER_TABLEB);
        onCreate(db);
    }

    public long usereg(String kad, String kadi, String kemail, String ksifre)    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(SUTUN_ADI,kad);
        cv.put(SUTUN_KULLANICI_ADI,kadi);
        cv.put(SUTUN_EMAIL,kemail);
        cv.put(SUTUN_SIFRE,ksifre);
        long kontrol=db.insert(TABLE_KULLANICI,null,cv);
        db.close();
        return kontrol;

    }

    public String passcont(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_KULLANICI, null, SUTUN_KULLANICI_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(SUTUN_SIFRE));
        cursor.close();

        return password;
    }
    public String adgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_KULLANICI, null, SUTUN_KULLANICI_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(SUTUN_ADI));
        cursor.close();

        return password;
    }
    public String idgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_KULLANICI, null, SUTUN_KULLANICI_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(SUTUN_ID));
        cursor.close();

        return password;
    }
    public String emailgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_KULLANICI, null, SUTUN_KULLANICI_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(SUTUN_EMAIL));
        cursor.close();

        return password;
    }


    public long yemekEkle(yemekbilgiler k1)    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(YEMEK_ADI, k1.getYemekadi());
        cv.put(YEMEK_MALZEMELER, k1.getYemekmalzeme());
        cv.put(YEMEK_KACKISI, k1.getYemekisi());
        cv.put(YEMEK_SURE, k1.getYemeksure());
        cv.put(YEMEK_TARIF, k1.getYemektarif());
        cv.put(YEMEK_ACIKLAMA, k1.getYemekack());
        cv.put(SUTUN_YEMEK_ADI, k1.getYemekkull());
        cv.put(YEMEK_FOTO, k1.getYemekfoto());
        long kontrol=db.insert(TABLE_YEMEKLER,null,cv);
        db.close();
        return kontrol;

    }


    public List<String> YemekListele(){
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {"yemek_adi" };
        Cursor c = db.query(TABLE_YEMEKLER, sutunlar, null, null, null, null, null);
        while (c.moveToNext()){
            veriler.add(c.getString(0));
        }
        return veriler;
    }
    public boolean updateData(String id, String name, String username, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUTUN_ID, id);
        contentValues.put(SUTUN_ADI, name);
        contentValues.put(SUTUN_KULLANICI_ADI, username);
        contentValues.put(SUTUN_EMAIL, email);
        db.update(TABLE_KULLANICI, contentValues, "kullanici_id = ?",new String[] { id });
        return true;
    }

    public boolean updatePas(String id, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUTUN_ID, id);
        contentValues.put(SUTUN_SIFRE, pass);
        db.update(TABLE_KULLANICI, contentValues, "kullanici_id = ?",new String[] { id });
        return true;
    }

    public String yemekmlzgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String al= cursor.getString(cursor.getColumnIndex(YEMEK_MALZEMELER));
        cursor.close();

        return al;
    }
    public String yemekkisigetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String al= cursor.getString(cursor.getColumnIndex(YEMEK_KACKISI));
        cursor.close();

        return al;
    }
    public String yemeksuregetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String al= cursor.getString(cursor.getColumnIndex(YEMEK_SURE));
        cursor.close();

        return al;
    }
    public String yemektarifgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String al= cursor.getString(cursor.getColumnIndex(YEMEK_TARIF));
        cursor.close();

        return al;
    }
    public String yemekackgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String al= cursor.getString(cursor.getColumnIndex(YEMEK_ACIKLAMA));
        cursor.close();

        return al;
    }
    public Bitmap yemekresimgetir(String gelenad)
    {
        Bitmap bitmap = null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();

        }
        cursor.moveToFirst();
        byte[] al = cursor.getBlob(cursor.getColumnIndex(YEMEK_FOTO));
        bitmap = BitmapFactory.decodeByteArray(al, 0, al.length);
        cursor.close();

        return bitmap;
    }
    public String yemeklikegetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String al= cursor.getString(cursor.getColumnIndex(YEMEK_BEGENI));
        cursor.close();

        return al;
    }
    public String yemekidgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_YEMEKLER, null, YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String al= cursor.getString(cursor.getColumnIndex(YEMEK_ID));
        cursor.close();

        return al;
    }

    public int delete (String gelanad){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_YEMEKLER, " yemek_adi = ? " , new String[] {gelanad} );
    }

    public List<String> benimyemeklerim(String gelenad){
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {"yemek_adi" };
        Cursor c = db.query(TABLE_YEMEKLER, sutunlar, SUTUN_YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        while (c.moveToNext()){
            veriler.add(c.getString(0));
        }
        return veriler;
    }
    public boolean kiyasla(String gelenad){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_KULLANICI, null, SUTUN_KULLANICI_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return false;
        }
        cursor.moveToFirst();
        cursor.close();

        return true;
    }
    public long begenen(String yemekadi,String begenenadi,String yemekid)    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(BEGENEN_ADI,yemekadi);
        cv.put(BEGENEN_YEMEK_ADI, begenenadi);
        cv.put(BEGENEN_YEMEK_ID, yemekid);
        long kontrol=db.insert(TABLE_BEGENENLER,null,cv);
        db.close();
        return kontrol;

    }
    public int say (String gelenid){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_BEGENENLER, null, BEGENEN_YEMEK_ID+"=?", new String[]{gelenid}, null, null, null);
        if(c.getCount()<1)
        {
            c.close();
            return 0;
        }
        c.moveToFirst();
        int total = c.getCount();
        c.close();

        return total;

    }
    public boolean updateyemek(String id, String mlz, String kisi, String sure, String tarif, String acik) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(YEMEK_MALZEMELER, mlz);
        contentValues.put(YEMEK_KACKISI, kisi);
        contentValues.put(YEMEK_SURE, sure);
        contentValues.put(YEMEK_TARIF, tarif);
        contentValues.put(YEMEK_ACIKLAMA, tarif);
        db.update(TABLE_YEMEKLER, contentValues, "yemek_id = ?",new String[] { id });
        return true;
    }

    public int deletelike (String gelenid){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BEGENENLER, " begenen_id = ? " , new String[] {gelenid} );
    }
    public String bidgetir(String gelenad)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_BEGENENLER, null, BEGENEN_YEMEK_ADI+"=?", new String[]{gelenad}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "Kayıt Bulunamadı";
        }
        cursor.moveToFirst();
        String id= cursor.getString(cursor.getColumnIndex(BEGENEN_ID));
        cursor.close();

        return id;
    }
    public boolean kiyaslalike(String gelenad, String gelenid){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_BEGENENLER, null, BEGENEN_YEMEK_ADI+"=?"+" AND " + BEGENEN_YEMEK_ID+"=?" , new String[]{gelenad,gelenid}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return false;
        }
        cursor.moveToFirst();
        cursor.close();

        return true;
    }

}


