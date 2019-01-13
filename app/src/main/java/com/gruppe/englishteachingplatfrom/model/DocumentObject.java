package com.gruppe.englishteachingplatfrom.model;

import java.util.List;
import java.util.Map;

public abstract class DocumentObject {
    public abstract Map<String, Object> toMap();
    public abstract DocumentObject toObject(String documentId, Map<String, Object> mapOfObject);
}
