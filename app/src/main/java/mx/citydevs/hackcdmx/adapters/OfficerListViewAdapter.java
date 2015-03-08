package mx.citydevs.hackcdmx.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.citydevs.hackcdmx.OfficerResultActivity;
import mx.citydevs.hackcdmx.R;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.views.CustomTextView;

/**
 * Created by zace3d on 3/7/15.
 */
public class OfficerListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<Officer> data = null;
    private ArrayList<Officer> arraylist;
    private String regex = "";

    public OfficerListViewAdapter(Context context, List<Officer> data) {
        mContext = context;
        this.data = data;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(data);
    }

    public class ViewHolder {
        CustomTextView nombre;
        CustomTextView placa;
        CustomTextView word;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Officer getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_officers, null);
            holder.nombre = (CustomTextView) view.findViewById(R.id.item_officers_name);
            holder.word = (CustomTextView) view.findViewById(R.id.item_officers_word);
            holder.placa = (CustomTextView) view.findViewById(R.id.item_officers_placa);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final String nombre = data.get(position).getNombre();

        holder.nombre.setText(nombre);
        // setColor(holder.nombre, nombre, regex, Color.GREEN);

        holder.placa.setText(data.get(position).getPlaca() != null ? data.get(position).getPlaca() : "");

        final Officer officer = data.get(position);

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OfficerResultActivity.class);
                intent.putExtra("officer", officer);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    private void setColor(TextView view, String fulltext, String subtext, int color) {
        if (fulltext != null) {
            view.setText(fulltext, TextView.BufferType.SPANNABLE);
            Spannable str = (Spannable) view.getText();
            int i = fulltext.indexOf(subtext);
            if (i != -1) {
                str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                view.setText(str);
            }
        }
    }

    // Filter Class
    public void filter(String charText) {
        if (charText != null) {
            charText = charText.toLowerCase(Locale.getDefault());
            data.clear();
            if (charText.length() == 0) {
                data.addAll(arraylist);
            } else {
                for (Officer officer : arraylist) {
                    if (officer.getNombre() != null && officer.getPlaca() != null) {
                        if (officer.getNombre().toLowerCase(Locale.getDefault()).contains(charText) ||
                                officer.getPlaca().contains(charText)) {
                            data.add(officer);

                            regex = charText;
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

}
