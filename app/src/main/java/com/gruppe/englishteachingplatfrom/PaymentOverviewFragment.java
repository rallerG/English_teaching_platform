package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.Teacher_slider.ViewPagerFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaymentOverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentOverviewFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button activeButton, historyButton;

    private OnFragmentInteractionListener mListener;

    private PaymentDummyBackend p = PaymentDummyBackend.getInstance();

    public PaymentOverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentOverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentOverviewFragment newInstance(String param1, String param2) {
        PaymentOverviewFragment fragment = new PaymentOverviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        p.createList();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_overview, container, false);

        activeButton = (Button) view.findViewById(R.id.activeButton);
        historyButton = (Button) view.findViewById(R.id.historyButton);

        activeButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);

        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.paymentLists, new PaymentActiveFragment());
        ft.commit();

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == activeButton){
            Toast.makeText(getActivity(),"Activity", Toast.LENGTH_SHORT).show();
            // Begin the transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.paymentLists, new PaymentActiveFragment());
            ft.commit();

        } else if (v == historyButton){
            Toast.makeText(getActivity(),"History", Toast.LENGTH_SHORT).show();
            // Begin the transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.paymentLists, new PaymentHistoryFragment());
            ft.commit();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
