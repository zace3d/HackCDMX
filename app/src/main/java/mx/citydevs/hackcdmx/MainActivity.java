package mx.citydevs.hackcdmx;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zace3d on 3/7/15.
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }


    private void initUI() {
        findViewById(R.id.main_btn_officer).setOnClickListener(this);
        findViewById(R.id.main_btn_infraction).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_officer:
                startOfficersIntent();

                break;
            case R.id.main_btn_infraction:
                startInfractionsIntent();
                break;
        }
    }

    private void startOfficersIntent() {
        Intent intent = new Intent(getBaseContext(), OfficersActivity.class);
        startActivity(intent);
    }

    private void startInfractionsIntent() {
        Intent intent = new Intent(getBaseContext(), InfractionsActivity.class);
        startActivity(intent);
    }
}
