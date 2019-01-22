package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherPendingsDocument;
import com.gruppe.englishteachingplatfrom.controller.MyFavoriteRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.controller.MyRequestRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.ArrayList;
import java.util.List;

public class RequestFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private MyRequestRecyclerViewAdapter myRequestRecyclerViewAdapter;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private RecyclerView recycler;
    private Singleton p = Singleton.getInstance();
    MyRequestRecyclerViewAdapter mReqAdapter;
    public static boolean clicked = false;

    public RequestFragment() {
    }

    public static RequestFragment newInstance(int columnCount) {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_request_list, container, false);

        recycler = view.findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(getContext());

      //  mAdapter = new MyRequestRecyclerViewAdapter(p.getCurrrentStudent().getPendingProfiles());
/*        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new MyRequestRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Fragment F = new PendingRequestFragment();
                Bundle bundle = new Bundle();
                F.setArguments(bundle);
                //remember information and description text (when objects are used)
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, F).
                        addToBackStack(null).commit();
            }
        });*/



        TeacherPendingsDocument teacherPendingsDocument = new TeacherPendingsDocumentImpl(p.getCurrrentTeacher().getId());
        teacherPendingsDocument.getAll(new CallbackList<StudentProfile>() {
            @Override
            public void onCallback(List<StudentProfile> listOfObjects) {
                p.getCurrrentTeacher().getPendingProfiles().clear();
                for (final StudentProfile studentProfile : listOfObjects) {
                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
                    studentsDocument.get(studentProfile.getId(), new Callback<StudentProfile>() {
                        @Override
                        public void onCallback(StudentProfile object) {
                            p.getCurrrentTeacher().getPendingProfiles().add(object);
                            mReqAdapter = new MyRequestRecyclerViewAdapter(p.getCurrrentTeacher().getPendingProfiles());
                            recycler.setHasFixedSize(true);
                            recycler.setLayoutManager(layoutManager);
                            recycler.setAdapter(mReqAdapter);
                            mReqAdapter.setOnItemClickListener(new MyRequestRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    if (!clicked) {
                                        System.out.println("You clicked on " + position);
                                        Fragment F = new PendingRequestFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", p.getCurrrentTeacher().getPendingProfiles().get(position).getId());
                                        bundle.putString("name", p.getCurrrentTeacher().getPendingProfiles().get(position).getName());
                                        bundle.putInt("picture", p.getCurrrentTeacher().getPendingProfiles().get(position).getProfilePicture());
                                        F.setArguments(bundle);
                                        //remember information and description text (when objects are used)
                                        getActivity().getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.fragmentContent, F)
                                                .addToBackStack(null)
                                                .commit();
                                    }
                                }
                            });
                        }
                    });
                }

                // Set the adapter
               /* if (view instanceof RecyclerView) {
                    Context context = view.getContext();
                    RecyclerView recyclerView = (RecyclerView) view;
                    if (mColumnCount <= 1) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    } else {
                        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                    }
                    recyclerView.setAdapter(new MyRequestRecyclerViewAdapter(getContext(), studentProfiles));
                }*/
            }
        });
        return view;
    }
}
