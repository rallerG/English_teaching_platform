package com.gruppe.englishteachingplatfrom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyPaymentRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentRecyclerViewAdapter.PaymentViewHolder> {

    private List<PaymentDummyBackend.TeacherDummy> teacherPaymentRequestList;
    private PaymentActiveFragment.OnFragmentInteractionListener mListener;

    public MyPaymentRecyclerViewAdapter(List<PaymentDummyBackend.TeacherDummy> teacherPaymentRequestList, PaymentActiveFragment.OnFragmentInteractionListener listener) {
        this.teacherPaymentRequestList = teacherPaymentRequestList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_payment_request_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder paymentViewHolder, int i) {
        PaymentDummyBackend.TeacherDummy teacherProfile = teacherPaymentRequestList.get(i);

        paymentViewHolder.textViewName.setText(teacherPaymentRequestList.get(i).getName());
        paymentViewHolder.textViewPrice.setText(Integer.toString(teacherPaymentRequestList.get(i).getPrice()));
        paymentViewHolder.textViewDate.setText(teacherPaymentRequestList.get(i).getDate());
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
