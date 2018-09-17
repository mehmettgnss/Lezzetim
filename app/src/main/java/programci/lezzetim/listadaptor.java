package programci.lezzetim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class listadaptor extends BaseAdapter {

    private List list;
    LayoutInflater layoutInflater;
    Context context;
    SessionManagement s;
    yemekbilgiler yb;
    public listadaptor(Context context, List list) {
        this.context = context;
        // Layout Inflater tanımlanıyor...
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView = layoutInflater.inflate(R.layout.listduzen, null);

        // Öğelerimizi satirView'dan çağırıyoruz.
        TextView kulad = (TextView) satirView.findViewById(R.id.textViewUserName);
        ImageView yemekresmi = (ImageView) satirView.findViewById(R.id.imageViewUserPicture);
        TextView yemekadi = (TextView) satirView.findViewById(R.id.textViewyad);


        // Öğelerimize verilerimizi yüklüyoruz.
        yemekresmi.setImageResource(R.drawable.images);
        kulad.setText(yb.getYemekkull());
        yemekadi.setText(yb.getYemekadi());


        return null;
    }
}
