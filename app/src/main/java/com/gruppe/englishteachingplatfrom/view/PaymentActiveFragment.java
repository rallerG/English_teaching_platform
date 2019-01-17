package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.backend.implementations.PaymentDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.PaymentDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.controller.MyPaymentRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.Payment;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaymentActiveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentActiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentActiveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_COLUMN_COUNT = "column-count";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int mColumnCount = 10;
    private View view;
    Singleton p = Singleton.getInstance();


    private OnFragmentInteractionListener mListener;


    public PaymentActiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentActiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentActiveFragment newInstance(String param1, String param2) {
        PaymentActiveFragment fragment = new PaymentActiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_payment_request_list, container, false);

        if (p.getCurrrentStudent() == null && p.getCurrrentTeacher() != null) {
            PaymentDocument paymentDocument = new PaymentDocumentImpl();
            paymentDocument.getAll(new CallbackList<Payment>() {
                @Override
                public void onCallback(List<Payment> listOfObjects) {
                    for (final Payment pay : listOfObjects) {
                        if (pay.getTeacherId() == p.getCurrrentTeacher().getId()) {
                            if (pay.isActive()) {
                                p.getCurrrentTeacher().getActivePaymentDummies().add(pay);
                                StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                studentsDocument.get(pay.getStudentId(), new Callback<StudentProfile>() {
                                    @Override
                                    public void onCallback(StudentProfile object) {
                                        p.getCurrrentTeacher().getActivePaymentDummies().get(p.getCurrrentTeacher().getActivePaymentDummies().indexOf(pay)).setStudent(object);
                                        if (view instanceof RecyclerView) {
                                            Context context = view.getContext();
                                            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                                            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                                            mRecyclerView.setAdapter(new MyPaymentRecyclerViewAdapter(p.getCurrrentTeacher().getActivePaymentDummies(), mListener));
                                        }
                                    }
                                });
                            } else {
                                p.getCurrrentTeacher().getHistoryPaymentDummies().add(pay);
                                StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                studentsDocument.get(pay.getStudentId(), new Callback<StudentProfile>() {
                                    @Override
                                    public void onCallback(StudentProfile object) {
                                        p.getCurrrentTeacher().getHistoryPaymentDummies().get(p.getCurrrentTeacher().getHistoryPaymentDummies().indexOf(pay)).setStudent(object);
                                        if (view instanceof RecyclerView) {
                                            Context context = view.getContext();
                                            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                                            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                                            mRecyclerView.setAdapter(new MyPaymentRecyclerViewAdapter(p.getCurrrentTeacher().getActivePaymentDummies(), mListener));
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });

        } else {
            PaymentDocument paymentDocument = new PaymentDocumentImpl();
            paymentDocument.getAll(new CallbackList<Payment>() {
                @Override
                public void onCallback(List<Payment> listOfObjects) {
                    for (final Payment pay : listOfObjects) {
                        if (pay.getStudentId() == p.getCurrrentStudent().getId()) {
                            if (pay.isActive()) {
                                p.getCurrrentStudent().getActivePaymentDummies().add(pay);
                                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                teachersDocument.get(pay.getTeacherId(), new Callback<TeacherProfile>() {
                                    @Override
                                    public void onCallback(TeacherProfile object) {
                                        p.getCurrrentStudent().getActivePaymentDummies().get(p.getCurrrentStudent().getActivePaymentDummies().indexOf(pay)).setTeacher(object);
                                        if (view instanceof RecyclerView) {
                                            Context context = view.getContext();
                                            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                                            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                                            mRecyclerView.setAdapter(new MyPaymentRecyclerViewAdapter(p.getCurrrentStudent().getActivePaymentDummies(), mListener));
                                        }
                                    }
                                });
                            } else {
                                p.getCurrrentStudent().getHistoryPaymentDummies().add(pay);
                                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                teachersDocument.get(pay.getTeacherId(), new Callback<TeacherProfile>() {
                                    @Override
                                    public void onCallback(TeacherProfile object) {
                                        p.getCurrrentStudent().getHistoryPaymentDummies().get(p.getCurrrentStudent().getHistoryPaymentDummies().indexOf(pay)).setTeacher(object);
                                        if (view instanceof RecyclerView) {
                                            Context context = view.getContext();
                                            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                                            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                                            mRecyclerView.setAdapter(new MyPaymentRecyclerViewAdapter(p.getCurrrentStudent().getActivePaymentDummies(), mListener));
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });







//            // Set the adapter
//            if (view instanceof RecyclerView) {
//                Context context = view.getContext();
//                RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//                mRecyclerView.setAdapter(new MyPaymentRecyclerViewAdapter(p.getCurrrentStudent().getActivePaymentDummies(), mListener));
//            }
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
