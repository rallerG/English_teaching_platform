package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilePageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePageFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Singleton p = Singleton.getInstance();

    TextView studentName, studentEmail, studentPassword, editProfile, editTextName, editTextEmail, editTextPassword;
    ViewSwitcher switcher, switcher2, switcher3;
    Button confirmEdit, cancelEdit;
    ImageView profilePicture;
    private long mLastClickTime = 0;

    public ProfilePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilePageFragment newInstance(String param1, String param2) {
        ProfilePageFragment fragment = new ProfilePageFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile_page, container, false);

        editProfile = (TextView) view.findViewById(R.id.editProfile);
        studentName = (TextView) view.findViewById(R.id.student_profile_name);
        studentEmail = (TextView) view.findViewById(R.id.student_profile_email);
        studentPassword = (TextView) view.findViewById(R.id.student_profile_password);
        switcher = (ViewSwitcher) view.findViewById(R.id.my_switcher);
        switcher2 = (ViewSwitcher) view.findViewById(R.id.my_switcher2);
        switcher3 = (ViewSwitcher) view.findViewById(R.id.my_switcher3);
        confirmEdit = (Button) view.findViewById(R.id.confirmEdit);
        cancelEdit = (Button) view.findViewById(R.id.buttonCancel);
        profilePicture = view.findViewById(R.id.studentProfilePicture);

        studentName.setText(p.getCurrrentStudent().getName());
        studentEmail.setText(p.getCurrrentStudent().getEmail());
        studentPassword.setText(p.getCurrrentStudent().getPassword());
        profilePicture.setImageResource(p.getCurrrentStudent().getProfilePicture());

        confirmEdit.setVisibility(View.INVISIBLE);
        cancelEdit.setVisibility(View.INVISIBLE);

        editProfile.setOnClickListener(this);
        confirmEdit.setOnClickListener(this);
        cancelEdit.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (v == editProfile) {
            System.out.println("Edit this profile");

            editProfile.setVisibility(View.INVISIBLE);

            switcher.showNext(); //or switcher.showPrevious();
            editTextName = (TextView) switcher.findViewById(R.id.hidden_edit_view);
            editTextName.setText(p.getCurrrentStudent().getName());

            switcher2.showNext(); //or switcher.showPrevious();
            editTextEmail = (TextView) switcher2.findViewById(R.id.hidden_edit_view2);
            editTextEmail.setText(p.getCurrrentStudent().getEmail());

            switcher3.showNext(); //or switcher.showPrevious();
            editTextPassword = (TextView) switcher3.findViewById(R.id.hidden_edit_view3);
            editTextPassword.setText(p.getCurrrentStudent().getPassword());

            confirmEdit.setVisibility(View.VISIBLE);
            cancelEdit.setVisibility(View.VISIBLE);
//            editProfile.setClickable(false);

        } else if (v == confirmEdit) {
            System.out.println("Confirm edit");

            if (!editTextName.getText().toString().equals("") && !editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
            //Update singleton
            p.getCurrrentStudent().setName(String.valueOf(editTextName.getText()));
            p.getCurrrentStudent().setEmail(String.valueOf(editTextEmail.getText()));
            p.getCurrrentStudent().setPassword(String.valueOf(editTextPassword.getText()));
//            p.getCurrrentStudent().setProfilePicture(p.getCurrrentStudent().getProfilePicture());

            //Update backend
            StudentsDocument studentsDocument = new StudentsDocumentImpl();
            studentsDocument.update(p.getCurrrentStudent().getId(), p.getCurrrentStudent(), new CallbackSuccess() {
                @Override
                public void onCallback() {
                    //callback
                    studentName.setText(p.getCurrrentStudent().getName());
                    studentEmail.setText(p.getCurrrentStudent().getEmail());
                    studentPassword.setText(p.getCurrrentStudent().getPassword());

                    switcher.showPrevious();
                    switcher2.showPrevious();
                    switcher3.showPrevious();

                    confirmEdit.setVisibility(View.INVISIBLE);
                    cancelEdit.setVisibility(View.INVISIBLE);
                    editProfile.setVisibility(View.VISIBLE);

                    Toast.makeText(getContext(), "Profile updated!", Toast.LENGTH_SHORT).show();

                }
            });
            } else {
                Toast.makeText(getContext(), "All fields must be entered!", Toast.LENGTH_SHORT).show();
            }
        } else if (v == cancelEdit) {
            switcher.showPrevious();
            switcher2.showPrevious();
            switcher3.showPrevious();
            confirmEdit.setVisibility(View.INVISIBLE);
            cancelEdit.setVisibility(View.INVISIBLE);
//            editProfile.setClickable(true);
            editProfile.setVisibility(View.VISIBLE);

            Toast.makeText(getContext(), "Edit cancelled!", Toast.LENGTH_SHORT).show();

        }
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


    public void test() {
        TeachersDocument teachersDocument = new TeachersDocumentImpl();
        teachersDocument.update(p.getCurrrentTeacher().getId(), p.getCurrrentTeacher(), new CallbackSuccess() {
            @Override
            public void onCallback() {
                //callback
            }
        });


        
        StudentsDocument studentsDocument = new StudentsDocumentImpl();
        studentsDocument.update(p.getCurrrentStudent().getId(), p.getCurrrentStudent(), new CallbackSuccess() {
            @Override
            public void onCallback() {
                //callback
            }
        });
    }
}
