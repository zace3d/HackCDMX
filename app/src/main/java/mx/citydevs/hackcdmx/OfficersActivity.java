package mx.citydevs.hackcdmx;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import mx.citydevs.hackcdmx.adapters.OfficerListViewAdapter;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.database.DBHelper;
import mx.citydevs.hackcdmx.dialogues.Dialogues;
import mx.citydevs.hackcdmx.httpconnection.HttpConnection;
import mx.citydevs.hackcdmx.parser.GsonParser;
import mx.citydevs.hackcdmx.utils.Utils;

/**
 * Created by zace3d on 3/7/15.
 */
public class OfficersActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG_CLASS = OfficersActivity.class.getSimpleName();
    DBHelper BD = null;
    SQLiteDatabase bd = null;
    public static int LOCAL = 0;
    public static int CONSULTA = 1;
    SwipeRefreshLayout swipe_container;
    ListView lvOfficers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officers);
        setSupportActionBar();
        setUpdateSwipeLayout();
        getOfficersData(LOCAL);
    }



    protected void setSupportActionBar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.actionbar);
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBlue));
        mToolbar.getBackground().setAlpha(255);

        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        ImageView actionbarIcon = (ImageView) mToolbar.findViewById(R.id.actionbar_icon);
        actionbarIcon.setVisibility(View.GONE);

        TextView actionbarTitle = (TextView) mToolbar.findViewById(R.id.actionbar_title);
        actionbarTitle.setTextColor(getResources().getColor(R.color.colorAppBlue));

        ImageView actionbar_reload = (ImageView)mToolbar.findViewById(R.id.actionbar_reload);
        actionbar_reload.setVisibility(View.GONE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void setUpdateSwipeLayout(){
        swipe_container = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipe_container.setOnRefreshListener(this);
        swipe_container.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        ((TextView)findViewById(R.id.infoText)).setText(new Utils().getPreferences(OfficersActivity.this, "update_officers", "Para actualizar baje la lista"));
    }

    private void initUI(ArrayList<Officer> listOfficers) {
        lvOfficers = (ListView) findViewById(R.id.officers_lv);

        ArrayList<Officer> newList = new ArrayList();
        for (Officer officer : listOfficers) {
            if (officer.getPlaca() != null)
                newList.add(officer);
        }

        final OfficerListViewAdapter adapter = new OfficerListViewAdapter(this, newList);
        lvOfficers.setAdapter(adapter);

        final EditText actvOfficers = (EditText) findViewById(R.id.officers_et_officers);
        actvOfficers.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String text = actvOfficers.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        lvOfficers.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (lvOfficers == null || lvOfficers.getChildCount() == 0) ?
                                0 : lvOfficers.getChildAt(0).getTop();
                swipe_container.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });
    }

    private void getOfficersData(int val) {
    String cops= null;
        try {
            BD = new DBHelper(OfficersActivity.this);
            bd = BD.loadDataBase(OfficersActivity.this, BD);
            cops =	BD.getPolicias(bd);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if(cops != null && val != CONSULTA){
            Log.d("**************", "local");
            llenaCops(cops);
        }else{
            Log.d("**************", "consulta");
            GetOfficersPublicationsAsyncTask task =  new GetOfficersPublicationsAsyncTask();
            task.execute();
        }
    }

    private class GetOfficersPublicationsAsyncTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        public GetOfficersPublicationsAsyncTask() {}

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(OfficersActivity.this);
            dialog.setMessage(getResources().getString(R.string.getdata_loading_officers));
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
            String result = HttpConnection.GET(HttpConnection.OFFICERS);
            BD.setCops(bd,result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (result != null) {
                llenaCops(result);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
                String currentDateandTime = sdf.format(new Date());

                new Utils().setPreference(OfficersActivity.this,"update_officers",String.format("Actualizado: %s", currentDateandTime));
                ((TextView)findViewById(R.id.infoText)).setText(String.format("Actualizado: %s", currentDateandTime));
            }
        }
    }

    /**
     * llena el lista con array de policias
     * @param result
     */
    public void llenaCops(String result){
        try {
            ArrayList<Officer> listOfficers = (ArrayList<Officer>) GsonParser.getOfficerListFromJSON(result);

            if (listOfficers != null) {
                initUI(listOfficers);
            }
            swipe_container.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BD.close();
    }


    @Override
    public void onRefresh() {
        getOfficersData(CONSULTA);
    }
}
