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

        //setSupportActionBar();
        initUI();
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
    }

    private void initUI() {
        findViewById(R.id.main_btn_officer).setOnClickListener(this);
        findViewById(R.id.main_btn_infraction).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_aboutme) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
