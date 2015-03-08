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
import android.widget.Toast;

import java.util.ArrayList;

import mx.citydevs.hackcdmx.R;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.dialogues.Dialogues;
import mx.citydevs.hackcdmx.httpconnection.HttpConnection;

/**
 * Created by zace3d on 3/7/15.
 */
public class AgendaloPageFragment extends Fragment {
    public static final String TAG_CLASS = AgendaloPageFragment.class.getName();

    private static final String OFFICERS = "officers";
    private static final String INDEX = "index";

    private ArrayList<Officer> officers;
    private int index;

    /**
     * Instances a new fragment
     *
     * @param officers
     *            list of items
     * @param index
     *            index page
     * @return a new page
     */
    public static Fragment newInstance(int index, ArrayList<Officer> officers) {

        // Instantiate a new fragment
        Fragment fragment = new AgendaloPageFragment();

        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        bundle.putSerializable(OFFICERS, officers);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Load parameters when the initial creation of the fragment is done
        this.officers = (ArrayList<Officer>) ((getArguments() != null) ? getArguments().getSerializable(OFFICERS) : null);
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
        if (view != null && officers != null && officers.size() > 0) {

        }

        return view;
    }

    private class GetDestacadosPublicationsAsyncTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        public GetDestacadosPublicationsAsyncTask() {}

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage(getResources().getString(R.string.getdata_entérate_loading));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = HttpConnection.GET(HttpConnection.URL + HttpConnection.OFFICERS);
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
                    ArrayList<Publication> listPublications = (ArrayList<Publication>) GsonParser.getListPublicationsFromJSON(result);

                    if (listPublications != null) {
                        setPublications(listPublications);

                        ((MainActivity) getActivity()).notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}