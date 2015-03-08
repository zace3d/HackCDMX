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
import mx.citydevs.hackcdmx.views.CustomTextView;

/**
 * Created by zace3d on 3/7/15.
 */
public class OfficerResultActivity extends ActionBarActivity {

    private String TAG_CLASS = OfficerResultActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_result);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Officer officer = (Officer) bundle.getSerializable("officer");
            setSupportActionBar();
            initUI(officer);
        }
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

    private void initUI(Officer officer) {
        CustomTextView tvName = (CustomTextView) findViewById(R.id.officer_result_name);
        tvName.setText(officer.getNombre());

        CustomTextView tvPlaca = (CustomTextView) findViewById(R.id.officer_result_name);
        tvPlaca.setText(officer.getNombre());
    }
}