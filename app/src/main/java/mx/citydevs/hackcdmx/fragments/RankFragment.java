package mx.citydevs.hackcdmx.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.citydevs.hackcdmx.R;
import mx.citydevs.hackcdmx.RankActivity;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.dialogues.Dialogues;
import mx.citydevs.hackcdmx.httpconnection.HttpConnection;
import mx.citydevs.hackcdmx.views.CustomTextView;

/**
 * Created by zace3d on 3/7/15.
 */
public class RankFragment extends Fragment {
    public static final String TAG_CLASS = RankFragment.class.getName();

    private static final String QUESTION = "question";
    private static final String INDEX = "index";

    private String question;
    private int index;

    /**
     * Instances a new fragment
     *
     * @param question
     *            list of items
     * @param index
     *            index page
     * @return a new page
     */
    public static Fragment newInstance(int index, String question) {

        // Instantiate a new fragment
        Fragment fragment = new RankFragment();

        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        bundle.putSerializable(QUESTION, question);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Load parameters when the initial creation of the fragment is done
        this.question = (getArguments() != null) ? getArguments().getString(QUESTION) : null;
        this.index = (getArguments() != null) ? getArguments().getInt(INDEX) : -1;
    }

    private ViewGroup rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_rank, container, false);

        createView(rootView);

        return rootView;
    }

    private View createView(View view) {
        if (view != null && question != null) {
            CustomTextView tvQuestion = (CustomTextView) view.findViewById(R.id.fragment_rank_question);
            tvQuestion.setText(question);

            view.findViewById(R.id.fragment_rank_btn_yes).setOnClickListener(RankOnClickListener);
            view.findViewById(R.id.fragment_rank_btn_no).setOnClickListener(RankOnClickListener);
        }

        return view;
    }

    View.OnClickListener RankOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_rank_btn_yes:
                    setAnswerId(1, index);
                    break;
                case R.id.fragment_rank_btn_no:
                    setAnswerId(0, index);
                    break;
            }
        }
    };

    private void setAnswerId(int answerId, int index) {
        ((RankActivity) getActivity()).setAnswerToQuestion(answerId);

        // Dialogues.Toast(getActivity(), "ANSWER: " + answerId + ", INDEX: " + index, Toast.LENGTH_SHORT);
    }
}