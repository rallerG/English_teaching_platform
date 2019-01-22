package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.PaymentDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.PaymentDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.controller.MyPaymentRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.model.Payment;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.List;


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

    TextView noActivePayment;

    private OnFragmentInteractionListener mListener;

    private Singleton p = Singleton.getInstance();

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_overview, container, false);

        activeButton = (Button) view.findViewById(R.id.activeButton);
        historyButton = (Button) view.findViewById(R.id.historyButton);
        noActivePayment = (TextView) view.findViewById(R.id.noActivePayment);
        noActivePayment.setVisibility(View.INVISIBLE);

        activeButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);

        activeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        //Teacher
        if (p.getCurrrentStudent() == null && p.getCurrrentTeacher() != null) {
            p.getCurrrentTeacher().getActivePaymentDummies().clear();
            p.getCurrrentTeacher().getHistoryPaymentDummies().clear();
            System.out.println("PaymentOverviewFragment.java: Teacher part");
            PaymentDocument paymentDocument = new PaymentDocumentImpl();
            paymentDocument.getAll(new CallbackList<Payment>() {
                @Override
                public void onCallback(final List<Payment> listOfObjects) {
                    for (final Payment pay : listOfObjects) {
                        if (pay.getTeacherId().equals(p.getCurrrentTeacher().getId())) {
                            if (pay.isActive()) {
//                                p.getCurrrentTeacher().getActivePaymentDummies().add(pay);
                                StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                studentsDocument.get(pay.getStudentId(), new Callback<StudentProfile>() {
                                    @Override
                                    public void onCallback(StudentProfile object) {
                                        pay.setStudent(object);
                                        object.setProfilePictures();
                                        pay.setTeacher(p.getCurrrentTeacher());
                                        p.getCurrrentTeacher().getActivePaymentDummies().add(pay);
//                                        p.getCurrrentTeacher().getActivePaymentDummies().get(p.getCurrrentTeacher().getActivePaymentDummies().indexOf(pay)).setStudent(object);

                                        if (listOfObjects.indexOf(pay) == (listOfObjects.size() - 1) && p.getCurrrentTeacher().getActivePaymentDummies().size() != 0) {
                                            // Begin the transaction
                                            noActivePayment.setVisibility(View.INVISIBLE);
                                            FragmentTransaction ft = getFragmentManager().beginTransaction()
                                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                            ft.replace(R.id.paymentLists, new PaymentActiveFragment());
                                            activeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                                            ft.commit();
                                        } else {
                                            noActivePayment.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            } else {
//                                p.getCurrrentTeacher().getHistoryPaymentDummies().add(pay);
                                StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                studentsDocument.get(pay.getStudentId(), new Callback<StudentProfile>() {
                                    @Override
                                    public void onCallback(StudentProfile object) {
                                        pay.setStudent(object);
                                        object.setProfilePictures();
                                        pay.setTeacher(p.getCurrrentTeacher());
                                        p.getCurrrentTeacher().getHistoryPaymentDummies().add(pay);
//                                        p.getCurrrentTeacher().getHistoryPaymentDummies().get(p.getCurrrentTeacher().getHistoryPaymentDummies().indexOf(pay)).setStudent(object);

                                        if (listOfObjects.indexOf(pay) == (listOfObjects.size() - 1) && p.getCurrrentTeacher().getActivePaymentDummies().size() != 0) {
                                            // Begin the transaction
                                            noActivePayment.setVisibility(View.INVISIBLE);
                                            FragmentTransaction ft = getFragmentManager().beginTransaction()
                                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                            ft.replace(R.id.paymentLists, new PaymentActiveFragment());
                                            activeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                                            ft.commit();
                                        } else {
                                            noActivePayment.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });

            //Student
        } else {
            p.getCurrrentStudent().getActivePaymentDummies().clear();
            p.getCurrrentStudent().getHistoryPaymentDummies().clear();
            System.out.println("PaymentOverviewFragment.java: Student part");
            PaymentDocument paymentDocument = new PaymentDocumentImpl();
            paymentDocument.getAll(new CallbackList<Payment>() {
                @Override
                public void onCallback(final List<Payment> listOfObjects) {
                    for (final Payment pay : listOfObjects) {
                        System.out.println("PaymentOverviewFragment.java: Student part - Pay student id: " + pay.getStudentId());
                        System.out.println("PaymentOverviewFragment.java: Student part - current student id: " + p.getCurrrentStudent().getId());
                        if (pay.getStudentId().equals(p.getCurrrentStudent().getId())) {
                            System.out.println("PaymentOverviewFragment.java: Student part - found pay match on id");
                            if (pay.isActive()) {
//                                p.getCurrrentStudent().getActivePaymentDummies().add(pay);
                                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                teachersDocument.get(pay.getTeacherId(), new Callback<TeacherProfile>() {
                                    @Override
                                    public void onCallback(TeacherProfile object) {
                                        pay.setTeacher(object);
                                        object.setProfilePictures();
                                        pay.setStudent(p.getCurrrentStudent());
                                        p.getCurrrentStudent().getActivePaymentDummies().add(pay);
//                                        p.getCurrrentStudent().getActivePaymentDummies().get(p.getCurrrentStudent().getActivePaymentDummies().indexOf(pay)).setTeacher(object);

                                        if (listOfObjects.indexOf(pay) == (listOfObjects.size() - 1) && p.getCurrrentStudent().getActivePaymentDummies().size() != 0) {
                                            // Begin the transaction
                                            noActivePayment.setVisibility(View.INVISIBLE);
                                            FragmentTransaction ft = getFragmentManager().beginTransaction()
                                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                            ft.replace(R.id.paymentLists, new PaymentActiveFragment());
                                            activeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                                            ft.commit();
                                        } else {
                                            noActivePayment.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            } else {
//                                p.getCurrrentStudent().getHistoryPaymentDummies().add(pay);
                                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                teachersDocument.get(pay.getTeacherId(), new Callback<TeacherProfile>() {
                                    @Override
                                    public void onCallback(TeacherProfile object) {
                                        pay.setTeacher(object);
                                        object.setProfilePictures();
                                        pay.setStudent(p.getCurrrentStudent());
                                        p.getCurrrentStudent().getHistoryPaymentDummies().add(pay);
//                                        p.getCurrrentStudent().getHistoryPaymentDummies().get(p.getCurrrentStudent().getHistoryPaymentDummies().indexOf(pay)).setTeacher(object);

                                        if (listOfObjects.indexOf(pay) == (listOfObjects.size() - 1) && p.getCurrrentStudent().getActivePaymentDummies().size() != 0) {
                                            // Begin the transaction
                                            noActivePayment.setVisibility(View.INVISIBLE);
                                            FragmentTransaction ft = getFragmentManager().beginTransaction()
                                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                            ft.replace(R.id.paymentLists, new PaymentActiveFragment());
                                            activeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                                            ft.commit();
                                        } else {
                                            noActivePayment.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }


//        // Begin the transaction
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.paymentLists, new PaymentActiveFragment());
//        ft.commit();

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == activeButton) {

            //Set the text if list empty or full
            SetEmptyListText(v);

            //Change test color
            activeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            historyButton.setTextColor(ContextCompat.getColor(getContext(), R.color.Black));

            // Begin the transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.paymentLists, new PaymentActiveFragment());
            ft.commit();


        } else if (v == historyButton) {

            //Set the text if list empty or full
            SetEmptyListText(v);

            //Change text color
            historyButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            activeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.Black));

            // Begin the transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
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

    //Method for setting the "Yoo have no payments" text
    public void SetEmptyListText(View v) {

        if (v == activeButton) {
            //If student
            if (p.getCurrrentStudent() != null && p.getCurrrentTeacher() == null) {

                if (p.getCurrrentStudent().getActivePaymentDummies().size() != 0) {
                    noActivePayment.setVisibility(View.INVISIBLE);
                } else {
                    noActivePayment.setVisibility(View.VISIBLE);
                    noActivePayment.setText("You have no active payments");
                }
                //If teacher
            } else if (p.getCurrrentStudent() == null && p.getCurrrentTeacher() != null) {
                if (p.getCurrrentTeacher().getActivePaymentDummies().size() != 0) {
                    noActivePayment.setVisibility(View.INVISIBLE);
                } else {
                    noActivePayment.setVisibility(View.VISIBLE);
                    noActivePayment.setText("You have no active payments");
                }
            }

        } else if (v == historyButton) {
            //If student
            if (p.getCurrrentStudent() != null && p.getCurrrentTeacher() == null) {

                if (p.getCurrrentStudent().getHistoryPaymentDummies().size() != 0) {
                    noActivePayment.setVisibility(View.INVISIBLE);
                } else {
                    noActivePayment.setVisibility(View.VISIBLE);
                    noActivePayment.setText("You have no previous payments");
                }
                //If teacher
            } else if (p.getCurrrentStudent() == null && p.getCurrrentTeacher() != null) {
                if (p.getCurrrentTeacher().getHistoryPaymentDummies().size() != 0) {
                    noActivePayment.setVisibility(View.INVISIBLE);
                } else {
                    noActivePayment.setVisibility(View.VISIBLE);
                    noActivePayment.setText("You have no previous payments");
                }
            }
        }
    }
}