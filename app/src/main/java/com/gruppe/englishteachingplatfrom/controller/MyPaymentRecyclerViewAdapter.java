package com.gruppe.englishteachingplatfrom.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.PaymentDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.PaymentDocument;
import com.gruppe.englishteachingplatfrom.model.Payment;
import com.gruppe.englishteachingplatfrom.view.PaymentActiveFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyPaymentRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentRecyclerViewAdapter.PaymentViewHolder> {

    private List<Payment> activePaymentList;
    private PaymentActiveFragment.OnFragmentInteractionListener mListener;

    public MyPaymentRecyclerViewAdapter(ArrayList<Payment> paymentDummies, PaymentActiveFragment.OnFragmentInteractionListener listener) {
        this.activePaymentList = paymentDummies;
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
    public void onBindViewHolder(@NonNull final PaymentViewHolder paymentViewHolder, final int i) {
//        Singleton.TeacherDummy teacherProfile = activePaymentList.get(i);

        paymentViewHolder.textViewName.setText(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getTeacher().getName());
        paymentViewHolder.textViewPrice.setText(Integer.toString(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getPrice())+" DKK");
        //TODO Make get date again
        paymentViewHolder.textViewDate.setText(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getRequestDate());
        paymentViewHolder.imageView.setImageResource(R.drawable.teacher_1);
        System.out.println("MyPaymentRecyclerViewAdapter.java:" + activePaymentList.get(paymentViewHolder.getAdapterPosition()).getId());


        //Accept button onClick
        paymentViewHolder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("MyPaymentRecyclerViewAdapter.java: Knap " + paymentViewHolder.getAdapterPosition());
                Toast.makeText(v.getContext(),"Accept nr. " + paymentViewHolder.getAdapterPosition() + " som hedder: " + activePaymentList.get(paymentViewHolder.getAdapterPosition()).getTeacher().getName(), Toast.LENGTH_SHORT).show();

//                Payment.payTransaction(activePaymentList.get(paymentViewHolder.getAdapterPosition()));
                final Payment payment = activePaymentList.get(paymentViewHolder.getAdapterPosition());
                payment.setActive(false);
                payment.setPayed(true);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String thePaymentDate = (dateFormat.format(date)).toString();
                payment.setPaymentDate(thePaymentDate);

                PaymentDocument paymentDocument = new PaymentDocumentImpl();
                paymentDocument.update(payment.getId(), payment, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        payment.getStudent().getHistoryPaymentDummies().add(payment);
                        payment.getStudent().getActivePaymentDummies().remove(payment);

                        payment.getTeacher().getHistoryPaymentDummies().add(payment);
                        payment.getTeacher().getActivePaymentDummies().remove(payment);
                        notifyItemRemoved(paymentViewHolder.getAdapterPosition());
                    }
                });
            }
        });

        //Reject button onClick
        paymentViewHolder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("MyPaymentRecyclerViewAdapter.java: Knap " + paymentViewHolder.getAdapterPosition());
                Toast.makeText(v.getContext(),"Reject nr. " + paymentViewHolder.getAdapterPosition() + " som hedder: " + activePaymentList.get(paymentViewHolder.getAdapterPosition()).getTeacher().getName(), Toast.LENGTH_SHORT).show();

//                Payment.deleteTransaction(activePaymentList.get(paymentViewHolder.getAdapterPosition()));
                final Payment payment = activePaymentList.get(paymentViewHolder.getAdapterPosition());
                PaymentDocument paymentDocument = new PaymentDocumentImpl();
                paymentDocument.delete(payment.getId(), new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        payment.getStudent().getActivePaymentDummies().remove(payment);
                        payment.getTeacher().getActivePaymentDummies().remove(payment);
                        notifyItemRemoved(paymentViewHolder.getAdapterPosition());
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return activePaymentList.size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewPrice, textViewDate;
        Button acceptButton, rejectButton;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.studentProfilePicture);
            textViewName = itemView.findViewById(R.id.studentName);
            textViewPrice = itemView.findViewById(R.id.amount);
            textViewDate = itemView.findViewById(R.id.date);

            acceptButton = (Button) itemView.findViewById(R.id.accept_btn);
            rejectButton = (Button) itemView.findViewById(R.id.reject_btn);


        }
    }
}
