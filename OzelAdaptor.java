package ismek.smekszlk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class OzelAdaptor extends BaseAdapter {

    public List<Sozluk> kelimeler;
    public LayoutInflater mInflater;

    @Override
    public int getCount() {
        return kelimeler.size();
    }

    @Override
    public Object getItem(int position) {
        return kelimeler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View satirEkrani =mInflater.inflate(R.layout.satir,null );

        TextView ustTaraf = satirEkrani.findViewById(R.id.ingilizce);

        TextView altTaraf = satirEkrani.findViewById(R.id.turkce);

        Sozluk kelime = kelimeler.get(position);

        ustTaraf.setText(kelime.getEnglish());
        altTaraf.setText(kelime.getTurkish());
        return satirEkrani;
    }

    public OzelAdaptor(Activity activity,List<Sozluk> kelimeler) {
        this.kelimeler = kelimeler;
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

    }


}
