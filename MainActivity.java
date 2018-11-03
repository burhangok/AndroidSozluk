package ismek.smekszlk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView liste;

    public Button aramaButonu;

    public EditText aranacak;

    public String aranacakKelime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liste=findViewById(R.id.kelimeler);

        aramaButonu=findViewById(R.id.araButon);

        aranacak=findViewById(R.id.kelime);


        final Veritabani vt = new Veritabani(MainActivity.this);
        aramaButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aranacakKelime=aranacak.getText().toString();

                List<Sozluk> kelimeler =vt.kelimeleriGetir(aranacakKelime);

                OzelAdaptor adptr = new OzelAdaptor(MainActivity.this,kelimeler);
                liste.setAdapter(adptr);
            }
        });



    }
}
