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
import mx.citydevs.hackcdmx.beans.Infraction;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.views.CustomTextView;

/**
 * Created by zace3d on 3/7/15.
 */
public class InfractionsListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<Infraction> data = null;
    private ArrayList<Infraction> arraylist;
    private String regex = "";

    public InfractionsListViewAdapter(Context context, List<Infraction> data) {
        mContext = context;
        this.data = data;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(data);
    }

    public class ViewHolder {
        CustomTextView infraction;
        CustomTextView monto;
        CustomTextView corralon;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Infraction getItem(int position) {
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
            view = inflater.inflate(R.layout.item_infractions, null);
            holder.infraction = (CustomTextView) view.findViewById(R.id.item_infractions_nombre);
            holder.monto = (CustomTextView) view.findViewById(R.id.item_infractions_monto);
            holder.corralon = (CustomTextView) view.findViewById(R.id.item_infractions_corralon);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.infraction.setText(data.get(position).getInfraccion());
        // setColor(holder.infraction, data.get(position).getInfraccion(), regex, Color.GREEN);

        holder.monto.setText(String.format(Locale.getDefault(), "Monto: $ %s", data.get(position).getMonto()));

        holder.corralon.setText(String.format(Locale.getDefault(), "Amerita corralón: %s", data.get(position).getCorralon()));

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
                for (Infraction infraction : arraylist) {
                    if (infraction.getInfraccion() != null && infraction.getInfraccion() != null) {
                        if (infraction.getInfraccion().toLowerCase(Locale.getDefault()).contains(charText)) {
                            data.add(infraction);

                            regex = charText;
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

}
