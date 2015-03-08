package mx.citydevs.hackcdmx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import mx.citydevs.hackcdmx.adapters.PublicationPagerAdapter;
import mx.citydevs.hackcdmx.fragments.RankFragment;
import mx.citydevs.hackcdmx.views.CustomViewPager;

/**
 * Created by zace3d on 3/7/15.
 */
public class RankActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        initUI();
    }

    private void initUI() {
        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.rank_pager);

        PublicationPagerAdapter mPagerAdapter = new PublicationPagerAdapter(getSupportFragmentManager());

        ArrayList<String> listQuestions = new ArrayList<>();
        listQuestions.add("Pregunta 1");
        listQuestions.add("Pregunta 2");
        listQuestions.add("Pregunta 3");
        listQuestions.add("Pregunta 4");
        listQuestions.add("Pregunta 5");

        for (int i = 0; i < listQuestions.size(); i++) {
            mPagerAdapter.addFragment(RankFragment.newInstance(i, listQuestions.get(i)));
        }

        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageMargin(0);

        // Viewpager indicator
        CirclePageIndicator titleIndicator = (CirclePageIndicator) findViewById(R.id.rank_pager_indicator);
        titleIndicator.setViewPager(viewPager);
    }

    public void setResultQuestion(String result) {
        if (result.equals("OK")) {

        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_officers:
                startOfficersIntent();

                break;
            case R.id.main_btn_infractions:
                break;
        }
    }

    private void startOfficersIntent() {
        Intent intent = new Intent(getBaseContext(), OfficersActivity.class);
        startActivity(intent);
    }
}
