package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyPaymentRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentRecyclerViewAdapter.PaymentViewHolder> {

    private List<TeacherProfile> teacherPaymentRequestList;
    private ListFragment.OnListFragmentInteractionListener mListener;

    public MyPaymentRecyclerViewAdapter(List<TeacherProfile> teacherPaymentRequestList, ListFragment.OnListFragmentInteractionListener listener) {
        this.teacherPaymentRequestList = teacherPaymentRequestList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder paymentViewHolder, int i) {
        TeacherProfile teacherProfile = teacherPaymentRequestList.get(i);

        paymentViewHolder.textViewName.setText(teacherProfile.getmName());
        paymentViewHolder.textViewPrice.setText(teacherProfile.getmPrice());
        paymentViewHolder.textViewDate.setText("23.04.2018");
        paymentViewHolder.imageView.setImageResource(R.mipmap.ic_launcher_student_round);


    }


    @Override
    public int getItemCount() {
        return teacherPaymentRequestList.size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewPrice, textViewDate;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView4);
            textViewName = itemView.findViewById(R.id.teacherName);
            textViewPrice = itemView.findViewById(R.id.amount);
            textViewDate = itemView.findViewById(R.id.date);
        }
    }
}
