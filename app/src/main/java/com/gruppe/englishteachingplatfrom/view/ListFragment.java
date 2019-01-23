package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.backend.implementations.StudentFavoritesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentFavoritesDocument;

import com.gruppe.englishteachingplatfrom.backend.implementations.StudentMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentMatchesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentPendingsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherMatchesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.controller.MyFavoriteRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.controller.MyStudentMatchesRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.controller.MyTeacherMatchesRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.List;

public class ListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private int listId;
    public static boolean clicked = false;
//    private OnListFragmentInteractionListener mListener;

    RecyclerView mRecyclerView;
    MyFavoriteRecyclerViewAdapter mAdapter;
    MyStudentMatchesRecyclerViewAdapter mStudentMatchesAdapter;
    MyTeacherMatchesRecyclerViewAdapter mTeacherAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Singleton p = Singleton.getInstance();
    private OnListFragmentInteractionListener mListener;


    public ListFragment() {
    }


    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
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
            listId = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(getContext());

        if (p.getCurrrentStudent() == null && p.getCurrrentTeacher() != null) {
            TeacherMatchesDocument teacherMatchesDocument = new TeacherMatchesDocumentImpl(p.getCurrrentTeacher().getId());
            teacherMatchesDocument.getAll(new CallbackList<StudentProfile>() {
                @Override
                public void onCallback(final List<StudentProfile> listOfObjects) {
                    p.getCurrrentTeacher().getMatchProfiles().clear();
                    for (final StudentProfile student : listOfObjects) {
                        StudentsDocument studentsDocument = new StudentsDocumentImpl();
                        studentsDocument.get(student.getId(), new Callback<StudentProfile>() {
                            @Override
                            public void onCallback(StudentProfile object) {
                                p.getCurrrentTeacher().getMatchProfiles().add(object);
                                object.setProfilePictures();
                                if (listOfObjects.indexOf(student) == (listOfObjects.size()-1)) {
                                    mTeacherAdapter = new MyTeacherMatchesRecyclerViewAdapter(p.getCurrrentTeacher().getMatchProfiles());
                                    mRecyclerView.setHasFixedSize(true);
                                    mRecyclerView.setLayoutManager(mLayoutManager);
                                    mRecyclerView.setAdapter(mTeacherAdapter);
                                }
//                                mTeacherAdapter.setOnItemClickListener(new MyTeacherMatchesRecyclerViewAdapter.OnItemClickListener() {
//
//                                    @Override
//                                    public void onItemClick(int position) {
//                                        //TODO Redefine onClick for teacher matches
////                                        if(!clicked) {
////                                            //What to do in click
////                                            System.out.println("ListFragment.java: Du har trykket på : " + p.getCurrrentStudent().getMatchProfiles().get(position).getName());
////                                            Intent i = new Intent(getActivity(), TeacherInfoFragment.class);
////                                            i.putExtra("name", p.getCurrrentStudent().getMatchProfiles().get(position).getName());
////                                            i.putExtra("price", p.getCurrrentStudent().getMatchProfiles().get(position).getPrice());
////                                            i.putExtra("rate", p.getCurrrentStudent().getMatchProfiles().get(position).getRating());
////                                            i.putExtra("language", p.getCurrrentStudent().getMatchProfiles().get(position).getLanguage());
////                                            //remember information and description text (when objects are used)
////                                            startActivity(i);
////                                            clicked = true;
////                                        }
//                                    }
//                                });
                            }
                        });

                    }
                }
            });
        } else {

            switch (listId) {
                case R.id.nav_matches:
                    StudentMatchesDocument studentMatchesDocument = new StudentMatchesDocumentImpl(p.getCurrrentStudent().getId());
                    studentMatchesDocument.getAll(new CallbackList<TeacherProfile>() {
                        @Override
                        public void onCallback(final List<TeacherProfile> listOfObjects) {
                            p.getCurrrentStudent().getMatchProfiles().clear();
                            for (final TeacherProfile teacher : listOfObjects) {
                                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                teachersDocument.get(teacher.getId(), new Callback<TeacherProfile>() {
                                    @Override
                                    public void onCallback(TeacherProfile object) {
                                        p.getCurrrentStudent().getMatchProfiles().add(object);
                                        object.setProfilePictures();
                                        if (listOfObjects.indexOf(teacher) == (listOfObjects.size()-1)) {
                                            mStudentMatchesAdapter = new MyStudentMatchesRecyclerViewAdapter(p.getCurrrentStudent().getMatchProfiles());
                                            mRecyclerView.setHasFixedSize(true);
                                            mRecyclerView.setLayoutManager(mLayoutManager);
                                            mRecyclerView.setAdapter(mStudentMatchesAdapter);
                                            mStudentMatchesAdapter.setOnItemClickListener(new MyStudentMatchesRecyclerViewAdapter.OnItemClickListener() {

                                                @Override
                                                public void onItemClick(int position) {
                                                    if (!clicked) {
                                                        //What to do in click
                                                        System.out.println("ListFragment.java: Du har trykket på : " + p.getCurrrentStudent().getMatchProfiles().get(position).getName());
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("name", p.getCurrrentStudent().getMatchProfiles().get(position).getFirstName());
                                                        bundle.putInt("price", p.getCurrrentStudent().getMatchProfiles().get(position).getPrice());
                                                        bundle.putFloat("rate",(float) p.getCurrrentStudent().getMatchProfiles().get(position).getRating());
                                                        bundle.putString("language", p.getCurrrentStudent().getMatchProfiles().get(position).getLanguage());
                                                        bundle.putString("id", p.getCurrrentStudent().getMatchProfiles().get(position).getId());
                                                        bundle.putString("from", "matches");
                                                        bundle.putInt("pic", p.getCurrrentStudent().getMatchProfiles().get(position).getProfilePic());
                                                        bundle.putString("id", p.getCurrrentStudent().getMatchProfiles().get(position).getId());
                                                        bundle.putString("information", p.getCurrrentStudent().getMatchProfiles().get(position).getDescription());

                                                        Fragment F = new TeacherInfoFragment();
                                                        F.setArguments(bundle);
                                                        //remember information and description text (when objects are used)
                                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                                                                addToBackStack(null).commit();
                                                        clicked = true;
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    });
                    break;
                case R.id.nav_favorites:
                    StudentFavoritesDocument studentFavoritesDocument = new StudentFavoritesDocumentImpl(p.getCurrrentStudent().getId());
                    studentFavoritesDocument.getAll(new CallbackList<TeacherProfile>() {
                        @Override
                        public void onCallback(final List<TeacherProfile> listOfObjects) {
                            p.getCurrrentStudent().getFavoriteProfiles().clear();
                            for (final TeacherProfile teacher : listOfObjects) {
                                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                teachersDocument.get(teacher.getId(), new Callback<TeacherProfile>() {
                                    @Override
                                    public void onCallback(TeacherProfile object) {
                                        p.getCurrrentStudent().getFavoriteProfiles().add(object);
                                        object.setProfilePictures();
                                        if (listOfObjects.indexOf(teacher) == (listOfObjects.size()-1)) {
                                            mAdapter = new MyFavoriteRecyclerViewAdapter(p.getCurrrentStudent().getFavoriteProfiles());
                                            mRecyclerView.setHasFixedSize(true);
                                            mRecyclerView.setLayoutManager(mLayoutManager);
                                            mRecyclerView.setAdapter(mAdapter);
                                            mAdapter.setOnItemClickListener(new MyFavoriteRecyclerViewAdapter.OnItemClickListener() {


                                                @Override
                                                public void onItemClick(int position) {
                                                    if (!clicked) {
                                                        //What to do in click
                                                        System.out.println("ListFragment.java: Du har trykket på : " + p.getCurrrentStudent().getFavoriteProfiles().get(position).getName());
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("name", p.getCurrrentStudent().getFavoriteProfiles().get(position).getFirstName());
                                                        bundle.putInt("price", p.getCurrrentStudent().getFavoriteProfiles().get(position).getPrice());
                                                        bundle.putFloat("rate", (float) p.getCurrrentStudent().getFavoriteProfiles().get(position).getRating());
                                                        bundle.putString("language", p.getCurrrentStudent().getFavoriteProfiles().get(position).getLanguage());
                                                        bundle.putString("from", "favorites");
                                                        bundle.putInt("pic", p.getCurrrentStudent().getFavoriteProfiles().get(position).getProfilePic());
                                                        bundle.putString("id", p.getCurrrentStudent().getFavoriteProfiles().get(position).getId());
                                                        bundle.putString("information", p.getCurrrentStudent().getFavoriteProfiles().get(position).getDescription());

                                                        Fragment F = new TeacherInfoFragment();
                                                        F.setArguments(bundle);
                                                        //remember information and description text (when objects are used)
                                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                                                                addToBackStack(null).commit();
                                                        clicked = true;
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    });
                    break;
                case R.id.nav_pending:
                    StudentPendingsDocument studentPendingsDocument = new StudentPendingsDocumentImpl(p.getCurrrentStudent().getId());
                    studentPendingsDocument.getAll(new CallbackList<TeacherProfile>() {
                        @Override
                        public void onCallback(final List<TeacherProfile> listOfObjects) {
                            p.getCurrrentStudent().getPendingProfiles().clear();
                            for (final TeacherProfile teacher : listOfObjects) {
                                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                teachersDocument.get(teacher.getId(), new Callback<TeacherProfile>() {
                                    @Override
                                    public void onCallback(TeacherProfile object) {
                                        p.getCurrrentStudent().getPendingProfiles().add(object);
                                        object.setProfilePictures();
                                        if (listOfObjects.indexOf(teacher) == (listOfObjects.size()-1)) {
                                            mAdapter = new MyFavoriteRecyclerViewAdapter(p.getCurrrentStudent().getPendingProfiles());
                                            mRecyclerView.setHasFixedSize(true);
                                            mRecyclerView.setLayoutManager(mLayoutManager);
                                            mRecyclerView.setAdapter(mAdapter);
                                            mAdapter.setOnItemClickListener(new MyFavoriteRecyclerViewAdapter.OnItemClickListener() {

                                                @Override
                                                public void onItemClick(int position) {
                                                    if (!clicked) {
                                                        //What to do in click
                                                        System.out.println("ListFragment.java: Du har trykket på : " + p.getCurrrentStudent().getPendingProfiles().get(position).getName());
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("name", p.getCurrrentStudent().getPendingProfiles().get(position).getFirstName());
                                                        bundle.putInt("price", p.getCurrrentStudent().getPendingProfiles().get(position).getPrice());
                                                        bundle.putFloat("rate", (float) p.getCurrrentStudent().getPendingProfiles().get(position).getRating());
                                                        bundle.putString("language", p.getCurrrentStudent().getPendingProfiles().get(position).getLanguage());
                                                        bundle.putString("from", "pending");
                                                        bundle.putInt("pic", p.getCurrrentStudent().getPendingProfiles().get(position).getProfilePic());
                                                        bundle.putString("id", p.getCurrrentStudent().getPendingProfiles().get(position).getId());
                                                        bundle.putString("information", p.getCurrrentStudent().getPendingProfiles().get(position).getDescription());

                                                        Fragment F = new TeacherInfoFragment();
                                                        F.setArguments(bundle);
                                                        //remember information and description text (when objects are used)
                                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                                                                addToBackStack(null).commit();
                                                        clicked = true;
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    });
                    break;
                default:
                    System.out.println("ListFragment.java: Intet valgt i ListFragment.java");
                    break;
            }
        }


//        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mAdapter = new MyFavoriteRecyclerViewAdapter(p.getTeacherDummies());

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new MyFavoriteRecyclerViewAdapter.OnItemClickListener() {
//
//
//            @Override
//            public void onItemClick(int position) {
//                if(!clicked) {
//                    //What to do in click
//                    System.out.println("Du har trykket på : " + p.getTeacherDummies().get(position).getName());
//                    Intent i = new Intent(getActivity(), TeacherInfoFragment.class);
//                    i.putExtra("name", p.getTeacherDummies().get(position).getName());
//                    i.putExtra("price", p.getTeacherDummies().get(position).getPrice());
//                    i.putExtra("rate", p.getTeacherDummies().get(position).getRating());
//                    i.putExtra("language", p.getTeacherDummies().get(position).getLanguage());
//                    //remember information and description text (when objects are used)
//                    startActivity(i);
//                    clicked = true;
//                }
//            }
//        });

            return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(TeacherProfile item);
    }
}







//        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//                System.out.println(listId);
//            switch (getArguments().getInt("id")) {
//                case R.id.nav_matches:
//                    recyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(p.getTeacherDummies(), mListener));
//                    break;
//                case R.id.nav_favorites:
//                    recyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(p.getTeacherDummies(), mListener));
//                    break;
//                case R.id.nav_pending:
//                    recyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(p.getTeacherDummies(), mListener));
//                    break;
//            }

//        getAc
//tivity().setTitle("List");
