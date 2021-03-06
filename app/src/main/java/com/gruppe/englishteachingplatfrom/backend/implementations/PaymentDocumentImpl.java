package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.PaymentDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.model.Payment;

public class PaymentDocumentImpl extends DAOImpl<Payment> implements PaymentDocument {
    public PaymentDocumentImpl() {
        super("payment");
    }
}
