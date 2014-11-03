package pef.kit.hotel;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private ArrayList<String> data;
    private Boolean br;
    private Boolean back = false;
    private int day;
    private final static String P1 = "data";
    private final static String P2 = "breakfast";
    private final static String P3 = "day";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param data Parameter 1.
     * @return A new instance of fragment ResultsFragment.
     */
    public static ResultsFragment newInstance(ArrayList<String> data,Boolean br, int day) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(P1, data);
        args.putBoolean(P2, br);
        args.putInt(P3, day);
        fragment.setArguments(args);
        return fragment;
    }

    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getStringArrayList(P1);
            br = getArguments().getBoolean(P2);
            day = getArguments().getInt(P3);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mListener.toast("onConfigurationChanged");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_results, container, false);
        ((TextView)v.findViewById(R.id.r_name)).setText(data.get(0));
        ((TextView)v.findViewById(R.id.r_address)).setText(data.get(1));
        ((TextView)v.findViewById(R.id.r_phone)).setText(data.get(2));
        ((TextView)v.findViewById(R.id.r_email)).setText(data.get(3));
        ((TextView)v.findViewById(R.id.r_datum)).setText(data.get(4));
        ((TextView)v.findViewById(R.id.r_days)).setText(String.valueOf(day));
        ((TextView)v.findViewById(R.id.r_breakfast)).setText(String.valueOf(br));

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        back = true;
        mListener.toast("onPause");
    }

    @Override
    public void onResume() {
        super.onPause();
        if (back) {
            mListener.toast("onResume");
            back = false;
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void toast(String toast);
    }

}
