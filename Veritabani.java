package ismek.smekszlk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {

    public static final String VT_ADI="ismek";
    public static final String TABLO_ADI="sozluk";
    public Context mcontext;


    public Veritabani(Context context) {
        super(context, VT_ADI, null, 1);
        this.mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            String sqlCumlesi = "CREATE TABLE IF NOT EXISTS "+TABLO_ADI+" (ID INTEGER PRIMARY KEY , English TEXT, Turkish TEXT" + ")";

            db.execSQL(sqlCumlesi);

         veritabaniniKopyala(db);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            veritabaniniKopyala(db);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public void veritabaniniKopyala( SQLiteDatabase vt) throws IOException {


        InputStream insertsStream = mcontext.getResources().openRawResource(R.raw.sozluk);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));


        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();
            vt.execSQL(insertStmt);


        }
        insertReader.close();



    }



    public List<Sozluk> kelimeleriGetir (String aranacak) {
        List<Sozluk> kelimeler = new ArrayList<Sozluk>();

        SQLiteDatabase vt =this.getWritableDatabase();

        String sqlCumlesi="SELECT * FROM sozluk WHERE English LIKE '"+aranacak+"%' OR Turkish LIKE'"+aranacak+"%'";

        Cursor cursor = vt.rawQuery(sqlCumlesi,null);

        while (cursor.moveToNext()) {

            Sozluk kelime = new Sozluk();
            kelime.setEnglish(cursor.getString(1));
            kelime.setTurkish(cursor.getString(2));
            kelimeler.add(kelime);
        }

        return kelimeler;

    }

}
