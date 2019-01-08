package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;

public class TeachersDocumentImpl extends DAOImpl implements TeachersDocument {
    public TeachersDocumentImpl() {
        super("teachers");
    }
}
