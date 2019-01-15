package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.backend.implementations.StudentFavoritesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentFavoritesDocument;
import android.widget.Switch;

import com.gruppe.englishteachingplatfrom.backend.implementations.StudentFavoritesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentFavoritesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentMatchesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentPendingsDocument;
import com.gruppe.englishteachingplatfrom.controller.MyFavoriteRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
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

        switch (listId) {
            case R.id.nav_matches:
                StudentMatchesDocument studentMatchesDocument = new StudentMatchesDocumentImpl("1");
                studentMatchesDocument.getAll(new CallbackList<TeacherProfile>() {
                    @Override
                    public void onCallback(List<TeacherProfile> listOfObjects) {
                        p.getCurrrentStudent().getMatchProfiles().clear();
                        p.getCurrrentStudent().getMatchProfiles().addAll(listOfObjects);
                        mAdapter = new MyFavoriteRecyclerViewAdapter(p.getCurrrentStudent().getMatchProfiles());
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MyFavoriteRecyclerViewAdapter.OnItemClickListener() {

                            @Override
                            public void onItemClick(int position) {
                                if(!clicked) {
                                    //What to do in click
//                                    System.out.println("Du har trykket på : " + p.getCurrrentStudent().getMatchProfiles().get(position).getName());
//                                    System.out.println("Du har trykket på : " + p.getCurrrentStudent().getName());
                                    Intent i = new Intent(getActivity(), TeacherInfoActivity.class);
//                                    i.putExtra("name", p.getTeacherDummies().get(position).getName());
//                                    i.putExtra("price", p.getTeacherDummies().get(position).getPrice());
//                                    i.putExtra("rate", p.getTeacherDummies().get(position).getRating());
//                                    i.putExtra("language", p.getTeacherDummies().get(position).getLanguage());
                                    i.putExtra("name", p.getCurrrentStudent().getMatchProfiles().get(position).getName());
                                    i.putExtra("price", p.getCurrrentStudent().getMatchProfiles().get(position).getPrice());
                                    i.putExtra("rate", p.getCurrrentStudent().getMatchProfiles().get(position).getRating());
                                    i.putExtra("language", p.getCurrrentStudent().getMatchProfiles().get(position).getLanguage());
                                    //remember information and description text (when objects are used)
                                    startActivity(i);
                                    clicked = true;
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.nav_favorites:
                StudentFavoritesDocument studentFavoritesDocument = new StudentFavoritesDocumentImpl("1");
                studentFavoritesDocument.getAll(new CallbackList<TeacherProfile>() {
                    @Override
                    public void onCallback(List<TeacherProfile> listOfObjects) {
                        System.out.println(listOfObjects);
                        p.getCurrrentStudent().getFavoriteProfiles().clear();
                        p.getCurrrentStudent().getFavoriteProfiles().addAll(listOfObjects);
                        mAdapter = new MyFavoriteRecyclerViewAdapter(p.getCurrrentStudent().getFavoriteProfiles());
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MyFavoriteRecyclerViewAdapter.OnItemClickListener() {


                            @Override
                            public void onItemClick(int position) {
                                if(!clicked) {
                                    //What to do in click
                                    System.out.println("Du har trykket på : " + p.getCurrrentStudent().getFavoriteProfiles().get(position).getName());
                                    Intent i = new Intent(getActivity(), TeacherInfoActivity.class);
//                                    i.putExtra("name", p.getTeacherDummies().get(position).getName());
//                                    i.putExtra("price", p.getTeacherDummies().get(position).getPrice());
//                                    i.putExtra("rate", p.getTeacherDummies().get(position).getRating());
//                                    i.putExtra("language", p.getTeacherDummies().get(position).getLanguage());
                                    i.putExtra("name", p.getCurrrentStudent().getFavoriteProfiles().get(position).getName());
                                    i.putExtra("price", p.getCurrrentStudent().getFavoriteProfiles().get(position).getPrice());
                                    i.putExtra("rate", p.getCurrrentStudent().getFavoriteProfiles().get(position).getRating());
                                    i.putExtra("language", p.getCurrrentStudent().getFavoriteProfiles().get(position).getLanguage());
                                    //remember information and description text (when objects are used)
                                    startActivity(i);
                                    clicked = true;
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.nav_pending:
                StudentPendingsDocument studentPendingsDocument = new StudentPendingsDocumentImpl("1");
                studentPendingsDocument.getAll(new CallbackList<TeacherProfile>() {
                    @Override
                    public void onCallback(List<TeacherProfile> listOfObjects) {
                        p.getCurrrentStudent().getPendingProfiles().clear();
                        p.getCurrrentStudent().getPendingProfiles().addAll(listOfObjects);
                        mAdapter = new MyFavoriteRecyclerViewAdapter(p.getCurrrentStudent().getPendingProfiles());
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MyFavoriteRecyclerViewAdapter.OnItemClickListener() {


                            @Override
                            public void onItemClick(int position) {
                                if(!clicked) {
                                    //What to do in click
                                    System.out.println("Du har trykket på : " + p.getCurrrentStudent().getPendingProfiles().get(position).getName());
                                    Intent i = new Intent(getActivity(), TeacherInfoActivity.class);
//                                    i.putExtra("name", p.getTeacherDummies().get(position).getName());
//                                    i.putExtra("price", p.getTeacherDummies().get(position).getPrice());
//                                    i.putExtra("rate", p.getTeacherDummies().get(position).getRating());
//                                    i.putExtra("language", p.getTeacherDummies().get(position).getLanguage());
                                    i.putExtra("name", p.getCurrrentStudent().getPendingProfiles().get(position).getName());
                                    i.putExtra("price", p.getCurrrentStudent().getPendingProfiles().get(position).getPrice());
                                    i.putExtra("rate", p.getCurrrentStudent().getPendingProfiles().get(position).getRating());
                                    i.putExtra("language", p.getCurrrentStudent().getPendingProfiles().get(position).getLanguage());
                                    //remember information and description text (when objects are used)
                                    startActivity(i);
                                    clicked = true;
                                }
                            }
                        });
                    }
                });
                break;
            default:
                System.out.println("Intet valgt i ListFragment.java");
                break;
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
//                    Intent i = new Intent(getActivity(), TeacherInfoActivity.class);
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
