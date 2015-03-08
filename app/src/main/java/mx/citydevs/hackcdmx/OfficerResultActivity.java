package mx.citydevs.hackcdmx;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import mx.citydevs.hackcdmx.adapters.ListViewAdapter;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.dialogues.Dialogues;
import mx.citydevs.hackcdmx.httpconnection.HttpConnection;
import mx.citydevs.hackcdmx.parser.GsonParser;

/**
 * Created by zace3d on 3/7/15.
 */
public class OfficerResultActivity extends ActionBarActivity {

    private String TAG_CLASS = OfficerResultActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_result);

        setSupportActionBar();
        getOfficersData();
    }

    protected void setSupportActionBar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.actionbar);
        mToolbar.setTitle("");
        mToolbar.getBackground().setAlpha(255);
        TextView actionbarTitle = (TextView) mToolbar.findViewById(R.id.actionbar_title);
        actionbarTitle.setText("");
        actionbarTitle.setTextColor(getResources().getColor(R.color.colorWhite));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI(ArrayList<Officer> listOfficers) {
        ListView lvOfficers = (ListView) findViewById(R.id.officers_lv);

        // final OfficersAdapter adapter = new OfficersAdapter(this, listOfficers);
        // lvOfficers.setAdapter(adapter);

        /*AutoCompleteTextView actvDestination = (AutoCompleteTextView) findViewById(R.id.officers_actv_places);
        actvDestination.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.places_list_item));
        actvDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

        ArrayList<Officer> newList = new ArrayList();
        for (Officer officer : listOfficers) {
            if (officer.getNombre() != null)
                newList.add(officer);
        }

        final ListViewAdapter adapter = new ListViewAdapter(this, newList);
        lvOfficers.setAdapter(adapter);

        final EditText actvOfficers = (EditText) findViewById(R.id.officers_et_officers);
        actvOfficers.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable s) {
                String text = actvOfficers.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    /*private View getItemView() {

    }*/

    private void getOfficersData() {
        GetOfficersPublicationsAsyncTask task =  new GetOfficersPublicationsAsyncTask();
        task.execute();
    }

    private class GetOfficersPublicationsAsyncTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        public GetOfficersPublicationsAsyncTask() {}

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(OfficerResultActivity.this);
            dialog.setMessage(getResources().getString(R.string.getdata_loading));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = HttpConnection.GET(HttpConnection.URL + HttpConnection.OFFICERS);
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
                    ArrayList<Officer> listOfficers = (ArrayList<Officer>) GsonParser.getListFromJSON(result);

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
