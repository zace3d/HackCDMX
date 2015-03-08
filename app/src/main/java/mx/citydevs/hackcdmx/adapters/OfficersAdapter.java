package mx.citydevs.hackcdmx.adapters;

import android.app.Activity;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.citydevs.hackcdmx.R;
import mx.citydevs.hackcdmx.beans.Officer;

/**
 * Created by zace3d on 3/7/15.
 */
public class OfficersAdapter extends ArrayAdapter<Officer> {
    private Activity activity;
    private List<Officer> values;
    private String regex = "";

    public OfficersAdapter(Activity activity, ArrayList<Officer> values) {
        super(activity, R.layout.item_officers, values);
        this.activity = activity;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;

        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_officers, null);

            // configure view holder
            holder = new ViewHolder();
            holder.text = (TextView) rowView.findViewById(R.id.item_officers_name);
            rowView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        Officer officer = getItem(position);
        String officerName = officer.getNombre();
        holder.text.setText(officerName);

        if (officerName != null && !officerName.equals("") && !regex.equals(""))
            if (setColor(holder.text, officerName, regex, activity.getResources().getColor(R.color.colorGreen)))
                holder.text.setVisibility(View.GONE);
            else
                holder.text.setVisibility(View.VISIBLE);

        return rowView;
    }

    private boolean setColor(TextView view, String fulltext, String subtext, int color) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        if (fulltext.indexOf(subtext) != -1) {
            int i = fulltext.indexOf(subtext);
            str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty(EditText view) {
        return view.getText().toString().trim().length() == 0;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Officer getItem(int position) {
        return values.get(position);
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    static class ViewHolder {
        public TextView text;
    }
}
