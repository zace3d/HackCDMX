package mx.citydevs.hackcdmx;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Locale;

import mx.citydevs.hackcdmx.adapters.PublicationPagerAdapter;
import mx.citydevs.hackcdmx.dialogues.Dialogues;
import mx.citydevs.hackcdmx.fragments.RankFragment;
import mx.citydevs.hackcdmx.httpconnection.HttpConnection;
import mx.citydevs.hackcdmx.views.CustomViewPager;

/**
 * Created by zace3d on 3/7/15.
 */
public class RankActivity extends ActionBarActivity {

    private String TAG_CLASS = RankActivity.class.getSimpleName();

    private CustomViewPager viewPager;
    private int current_index = 0;
    private int COUNT = 0;

    private ArrayList<Integer> listAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initUI();
    }

    private void initUI() {
        viewPager = (CustomViewPager) findViewById(R.id.rank_pager);

        PublicationPagerAdapter mPagerAdapter = new PublicationPagerAdapter(getSupportFragmentManager());

        ArrayList<String> listQuestions = new ArrayList<>();
        listQuestions.add("¿El oficial se identificó con su nombre y número de placa?");
        listQuestions.add("¿El oficial señaló la infracción cometida?");
        listQuestions.add("¿El oficial mostró el artículo del reglamento que lo fundamenta?");
        listQuestions.add("¿La sanción coincidió con la infracción mostrada?");
        listQuestions.add("¿El oficial solicitó y devolvió documentos?");
        listQuestions.add("¿El oficial entregó copia de la infracción?");

        for (int i = 0; i < listQuestions.size(); i++) {
            mPagerAdapter.addFragment(RankFragment.newInstance(i, listQuestions.get(i)));
        }

        COUNT = listQuestions.size();

        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageMargin(0);
        viewPager.setOnPageChangeListener(RankOnPageChangeListener);

        // Viewpager indicator
        CirclePageIndicator titleIndicator = (CirclePageIndicator) findViewById(R.id.rank_pager_indicator);
        titleIndicator.setViewPager(viewPager);
        titleIndicator.setOnPageChangeListener(null);
    }

    private ViewPager.OnPageChangeListener RankOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {}

        @Override public void onPageScrollStateChanged(int state) {}
    };

    public void setAnswerToQuestion(int answer) {
        listAnswers.add(current_index, answer);

        viewPager.setCurrentItem(++current_index, true);

        // Dialogues.Toast(getBaseContext(), "INDEX: " + current_index, Toast.LENGTH_SHORT);

        if (current_index == COUNT) {
            PostQuestionPublicationsAsyncTask task = new PostQuestionPublicationsAsyncTask();
            task.execute();
        }
    }

    private class PostQuestionPublicationsAsyncTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        public PostQuestionPublicationsAsyncTask() {}

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(RankActivity.this);
            dialog.setMessage(getResources().getString(R.string.postrank_loading));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = HttpConnection.URL + String.format(Locale.getDefault(), HttpConnection.RANK,
                    listAnswers.get(0), listAnswers.get(1), listAnswers.get(2), listAnswers.get(3), listAnswers.get(4),
                    listAnswers.get(5));
            Dialogues.Log(TAG_CLASS, "URL RANK: " + url, Log.ERROR);
            String result = HttpConnection.GET(url);
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
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
