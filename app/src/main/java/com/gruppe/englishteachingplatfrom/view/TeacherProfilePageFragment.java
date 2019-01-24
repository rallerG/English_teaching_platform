package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.Singleton;

public class TeacherProfilePageFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TeacherProfilePageFragment.OnFragmentInteractionListener mListener;

    Singleton p = Singleton.getInstance();

    TextView teacherName, teacherEmail, teacherPassword, editProfile, editTextName, editTextEmail, editTextPassword, description, editDescription;
    ViewSwitcher switcher, switcher2, switcher3, switcher4;
    Button confirmEdit, cancelEdit;
    ImageView profilePicture, greenBox;

    public TeacherProfilePageFragment() {
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
    public static TeacherProfilePageFragment newInstance(String param1, String param2) {
        TeacherProfilePageFragment fragment = new TeacherProfilePageFragment();
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
        View view = inflater.inflate(R.layout.teacher_profile_page_fragment, container, false);

        editProfile = (TextView) view.findViewById(R.id.editProfileTeacher);
        teacherName = (TextView) view.findViewById(R.id.teacher_profile_name);
        teacherEmail = (TextView) view.findViewById(R.id.teacher_profile_email);
        teacherPassword = (TextView) view.findViewById(R.id.teacher_profile_password);
        switcher = (ViewSwitcher) view.findViewById(R.id.my_switcher_teacher);
        switcher2 = (ViewSwitcher) view.findViewById(R.id.my_switcher2_teacher);
        switcher3 = (ViewSwitcher) view.findViewById(R.id.my_switcher3_teacher);
        switcher4 = (ViewSwitcher) view.findViewById(R.id.my_switcher4_teacher);
        confirmEdit = (Button) view.findViewById(R.id.confirmEdit_teacher);
        cancelEdit = (Button) view.findViewById(R.id.buttonCancel_teacher);
        greenBox = (ImageView) view.findViewById(R.id.greenBox);
        profilePicture = view.findViewById(R.id.teacher_ProfilePicture);
        description = view.findViewById(R.id.teacher_profile_description);

        teacherName.setText(p.getCurrrentTeacher().getName());
        teacherEmail.setText(p.getCurrrentTeacher().getEmail());
        teacherPassword.setText(p.getCurrrentTeacher().getPassword());
        profilePicture.setImageResource(p.getCurrrentTeacher().getProfilePic());

        description.setText(p.getCurrrentTeacher().getDescription());

        if (description.getText().toString().equals("")) {
            description.setText("You have no description... Add one bu pressing 'edit profile'");
        }


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
        if (context instanceof TeacherProfilePageFragment.OnFragmentInteractionListener) {
            mListener = (TeacherProfilePageFragment.OnFragmentInteractionListener) context;
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
        if (v == editProfile) {
            System.out.println("Edit this profile");

            switcher.showNext(); //or switcher.showPrevious();
            editTextName = (TextView) switcher.findViewById(R.id.hidden_edit_view_teacher);
            editTextName.setText(p.getCurrrentTeacher().getName());

            switcher2.showNext(); //or switcher.showPrevious();
            editTextEmail = (TextView) switcher2.findViewById(R.id.hidden_edit_view2_teacher);
            editTextEmail.setText(p.getCurrrentTeacher().getEmail());

            switcher3.showNext(); //or switcher.showPrevious();
            editTextPassword = (TextView) switcher3.findViewById(R.id.hidden_edit_view3_teacher);
            editTextPassword.setText(p.getCurrrentTeacher().getPassword());

            switcher4.showNext();
            editDescription = (TextView) switcher4.findViewById(R.id.hidden_edit_view4_teacher);
            editDescription.setText(p.getCurrrentTeacher().getDescription());

            confirmEdit.setVisibility(View.VISIBLE);
            cancelEdit.setVisibility(View.VISIBLE);
            profilePicture.setVisibility(View.GONE);
            greenBox.setVisibility(View.GONE);
            editProfile.setVisibility(View.INVISIBLE);

        } else if (v == confirmEdit) {
            System.out.println("Confirm edit");

            if (!editTextName.getText().toString().equals("") && !editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                //Update singleton
                p.getCurrrentTeacher().setName(String.valueOf(editTextName.getText()));
                p.getCurrrentTeacher().setEmail(String.valueOf(editTextEmail.getText()));
                p.getCurrrentTeacher().setPassword(String.valueOf(editTextPassword.getText()));
                p.getCurrrentTeacher().setDescription(String.valueOf(editDescription.getText()));

                //Update backend
                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                teachersDocument.update(p.getCurrrentTeacher().getId(), p.getCurrrentTeacher(), new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        //callback
                        teacherName.setText(p.getCurrrentTeacher().getName());
                        teacherEmail.setText(p.getCurrrentTeacher().getEmail());
                        teacherPassword.setText(p.getCurrrentTeacher().getPassword());
                        description.setText(p.getCurrrentTeacher().getDescription());

                        switcher.showPrevious();
                        switcher2.showPrevious();
                        switcher3.showPrevious();
                        switcher4.showPrevious();

                        confirmEdit.setVisibility(View.INVISIBLE);
                        profilePicture.setVisibility(View.VISIBLE);
                        greenBox.setVisibility(View.VISIBLE);

                        Toast.makeText(getContext(), "Profile updated!", Toast.LENGTH_SHORT).show();
                        editProfile.setVisibility(View.VISIBLE);


                    }
                });
            } else {
                System.out.println("Alt skal udfyldes");
                Toast.makeText(getContext(), "All fields must be entered! Try again", Toast.LENGTH_SHORT).show();
                editProfile.setVisibility(View.VISIBLE);
            }
        } else if (v == cancelEdit) {
            switcher.showPrevious();
            switcher2.showPrevious();
            switcher3.showPrevious();
            switcher4.showPrevious();
            confirmEdit.setVisibility(View.INVISIBLE);
            cancelEdit.setVisibility(View.INVISIBLE);
            editProfile.setClickable(true);
            Toast.makeText(getContext(), "Edit cancelled!", Toast.LENGTH_SHORT).show();
            profilePicture.setVisibility(View.VISIBLE);
            greenBox.setVisibility(View.VISIBLE);
            editProfile.setVisibility(View.VISIBLE);
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
}

