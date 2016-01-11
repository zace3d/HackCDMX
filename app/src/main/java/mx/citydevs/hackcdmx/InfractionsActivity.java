package mx.citydevs.hackcdmx;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

import mx.citydevs.hackcdmx.adapters.InfractionsListViewAdapter;
import mx.citydevs.hackcdmx.adapters.OfficerListViewAdapter;
import mx.citydevs.hackcdmx.beans.Infraction;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.database.DBHelper;
import mx.citydevs.hackcdmx.dialogues.Dialogues;
import mx.citydevs.hackcdmx.httpconnection.HttpConnection;
import mx.citydevs.hackcdmx.parser.GsonParser;
import mx.citydevs.hackcdmx.utils.Utils;

/**
 * Created by zace3d on 3/7/15.
 */
public class InfractionsActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String TAG_CLASS = InfractionsActivity.class.getSimpleName();
    DBHelper BD = null;
    SQLiteDatabase bd = null;
    public static int LOCAL = 0;
    public static int CONSULTA = 1;
    SwipeRefreshLayout swipe_container;
    ListView lvOfficers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infractions);

        setSupportActionBar();
        setUpdateSwipeLayout();
        getInfractionsData(LOCAL);
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

        ((TextView)findViewById(R.id.infoText)).setText(new Utils().getPreferences(InfractionsActivity.this, "update_infractions", "Para actualizar baje la lista"));
    }


    private void initUI(ArrayList<Infraction> listOfficers) {
        ListView lvInfractions = (ListView) findViewById(R.id.infractions_lv);

        ArrayList<Infraction> newList = new ArrayList();
        for (Infraction infraction : listOfficers) {
            if (infraction.getDescripcion() != null)
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

    private void getInfractionsData(int val) {
        String infraction= null;
        try {
            BD = new DBHelper(InfractionsActivity.this);
            bd = BD.loadDataBase(InfractionsActivity.this, BD);
            infraction = BD.getInfractions(bd);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if(infraction != null && val != CONSULTA){
            Log.d("**************", "local");
            llenaInfractions(infraction);
        }else{
            Log.d("**************", "consulta");
            GetInfractionsPublicationsAsyncTask task =  new GetInfractionsPublicationsAsyncTask();
            task.execute();
        }
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
            String result = HttpConnection.GET(HttpConnection.INFRACTIONS);
            BD.setInfractions(bd,result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (result != null) {
                llenaInfractions(result);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
                String currentDateandTime = sdf.format(new Date());

                new Utils().setPreference(InfractionsActivity.this, "update_infractions", String.format("Actualizado: %s", currentDateandTime));
                ((TextView)findViewById(R.id.infoText)).setText(String.format("Actualizado: %s", currentDateandTime));
            }
        }
    }

    /**
     * llena el lista con array de policias
     * @param result
     */
    public void llenaInfractions(String result){
        try {
            ArrayList<Infraction> listOfficers = (ArrayList<Infraction>) GsonParser.getInfractionsListFromJSON(result);

            if (listOfficers != null) {
                initUI(listOfficers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BD.close();
        swipe_container.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getInfractionsData(CONSULTA);
    }
}
