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
public class RankActivity extends ActionBarActivity {

    private CustomViewPager viewPager;
    private int current_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        setSupportActionBar();
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        viewPager = (CustomViewPager) findViewById(R.id.rank_pager);

        PublicationPagerAdapter mPagerAdapter = new PublicationPagerAdapter(getSupportFragmentManager());

        ArrayList<String> listQuestions = new ArrayList<>();
        listQuestions.add("¿El oficial se identificó con nombre y apellido?");
        listQuestions.add("¿El oficial se identificó con nombre y apellido?");
        listQuestions.add("¿El oficial se identificó con nombre y apellido?");
        listQuestions.add("¿El oficial se identificó con nombre y apellido?");
        listQuestions.add("¿El oficial se identificó con nombre y apellido?");

        for (int i = 0; i < listQuestions.size(); i++) {
            mPagerAdapter.addFragment(RankFragment.newInstance(i, listQuestions.get(i)));
        }

        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageMargin(0);

        // Viewpager indicator
        CirclePageIndicator titleIndicator = (CirclePageIndicator) findViewById(R.id.rank_pager_indicator);
        titleIndicator.setViewPager(viewPager);
        titleIndicator.setOnPageChangeListener(null);
    }

    public void setResultQuestion(String result) {
        if (result.equals("OK")) {
            viewPager.setCurrentItem(++current_index, true);
        }
    }
}
