package mx.citydevs.hackcdmx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.views.CustomTextView;

/**
 * Created by zace3d on 3/7/15.
 */
public class OfficerResultActivity extends ActionBarActivity implements View.OnClickListener {

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
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBlue));
        mToolbar.getBackground().setAlpha(255);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        ImageView actionbarIcon = (ImageView) mToolbar.findViewById(R.id.actionbar_icon);
        actionbarIcon.setVisibility(View.GONE);
        actionbarIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TextView actionbarTitle = (TextView) mToolbar.findViewById(R.id.actionbar_title);
        actionbarTitle.setTextColor(getResources().getColor(R.color.colorAppBlue));
        ImageView actionbar_reload = (ImageView)mToolbar.findViewById(R.id.actionbar_reload);
        actionbar_reload.setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI(Officer officer) {
        CustomTextView tvName = (CustomTextView) findViewById(R.id.officer_result_name);
        tvName.setText(officer.getNombre());

        CustomTextView tvPlaca = (CustomTextView) findViewById(R.id.officer_result_placa);
        tvPlaca.append(officer.getPlaca());

        findViewById(R.id.officer_result_evaluate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.officer_result_evaluate:
                startRankIntent();
                break;
        }
    }

    private void startRankIntent() {
        Intent intent = new Intent(getBaseContext(), RankActivity.class);
        startActivity(intent);
    }
}