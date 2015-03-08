package mx.citydevs.hackcdmx;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import mx.citydevs.hackcdmx.adapters.InfractionsListViewAdapter;
import mx.citydevs.hackcdmx.adapters.OfficerListViewAdapter;
import mx.citydevs.hackcdmx.beans.Infraction;
import mx.citydevs.hackcdmx.dialogues.Dialogues;
import mx.citydevs.hackcdmx.httpconnection.HttpConnection;
import mx.citydevs.hackcdmx.parser.GsonParser;

/**
 * Created by zace3d on 3/7/15.
 */
public class InfractionsActivity extends ActionBarActivity {

    private String TAG_CLASS = InfractionsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infractions);

        setSupportActionBar();
        getInfractionsData();
    }

    protected void setSupportActionBar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.actionbar);
        mToolbar.setTitle("");
        mToolbar.getBackground().setAlpha(255);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        ImageView actionbarIcon = (ImageView) mToolbar.findViewById(R.id.actionbar_icon);
        actionbarIcon.setVisibility(View.GONE);
        TextView actionbarTitle = (TextView) mToolbar.findViewById(R.id.actionbar_title);
        actionbarTitle.setText("");
        actionbarTitle.setTextColor(getResources().getColor(R.color.colorWhite));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI(ArrayList<Infraction> listOfficers) {
        ListView lvInfractions = (ListView) findViewById(R.id.infractions_lv);

        ArrayList<Infraction> newList = new ArrayList();
        for (Infraction infraction : listOfficers) {
            if (infraction.getInfraccion() != null)
                newList.add(infraction);
        }

        final InfractionsListViewAdapter adapter = new InfractionsListViewAdapter(this, newList);
        lvInfractions.setAdapter(adapter);

        final EditText actvInfractions = (EditText) findViewById(R.id.infractions_et_filter);
        actvInfractions.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String text = actvInfractions.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void getInfractionsData() {
        GetInfractionsPublicationsAsyncTask task =  new GetInfractionsPublicationsAsyncTask();
        task.execute();
    }

    private class GetInfractionsPublicationsAsyncTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        public GetInfractionsPublicationsAsyncTask() {}

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(InfractionsActivity.this);
            dialog.setMessage(getResources().getString(R.string.getdata_loading_infractions));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = HttpConnection.GET(HttpConnection.URL + HttpConnection.INFRACTIONS);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

            Dialogues.Log(TAG_CLASS, "Result: " + result, Log.INFO);

            if (result != null) {
                try {
                    ArrayList<Infraction> listOfficers = (ArrayList<Infraction>) GsonParser.getInfractionsListFromJSON(result);

                    if (listOfficers != null) {
                        initUI(listOfficers);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
