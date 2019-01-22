package com.gruppe.englishteachingplatfrom.backend.implementations;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackError;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Collection;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Document;
import com.gruppe.englishteachingplatfrom.backend.interfaces.DocumentQuery;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.Review;
import com.gruppe.englishteachingplatfrom.model.Payment;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public abstract class DAOImpl <T extends DocumentObject> implements Document, Collection, DocumentQuery {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection;
    private DocumentObject objectToReturn;
    ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
    Class<T> type = (Class<T>) superClass.getActualTypeArguments()[0];
    private List<T> listOfObjectsToReturn = new ArrayList<>();

    public DAOImpl(String collectionReference) {
        this.collection = db.collection(collectionReference);
        objectToReturn = null;
    }

    @Override
    public void add(final DocumentObject document, final CallbackSuccess callbackSuccess, final CallbackError callbackError) {
       if (type == Review.class) {
           Map<String, Object> mapFeedback = document.toMap();
           DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
           mapFeedback.remove("student_id");
           mapFeedback.put("student",documentReferenceStudent);
           collection.document()
                   .set(mapFeedback)
                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                           callbackSuccess.onCallback();
                       }
                   })
                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Log.w(TAG, "--add()-- Error adding document", e);
                           callbackError.onCallback();
                       }
                   });
       }

       else if (type == Payment.class) {
           Map<String, Object> mapFeedback = document.toMap();
           DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
           DocumentReference documentReferenceTeacher = db.collection("teacher").document((String) mapFeedback.get("teacher_id"));
           mapFeedback.remove("student_id");
           mapFeedback.remove("teacher_id");
           mapFeedback.put("student",documentReferenceStudent);
           mapFeedback.put("teacher",documentReferenceTeacher);
           collection.document()
                   .set(mapFeedback)
                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                           callbackSuccess.onCallback();
                       }
                   })
                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Log.w(TAG, "--add()-- Error adding document", e);
                           callbackError.onCallback();
                       }
                   });
       }
       else {
           collection.document()
                   .set(document.toMap())
                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                           callbackSuccess.onCallback();
                       }
                   })
                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Log.w(TAG, "--add()-- Error adding document", e);
                           callbackError.onCallback();
                       }
                   });
       }
    }

    @Override
    public void add(final DocumentObject document, final CallbackSuccess callbackSuccess) {
        if (type == Review.class) {
            Map<String, Object> mapFeedback = document.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            mapFeedback.remove("student_id");
            mapFeedback.put("student",documentReferenceStudent);
            collection.document()
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--add()-- Error adding document", e);
                        }
                    });
        }

        else if (type == Payment.class) {
            Map<String, Object> mapFeedback = document.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            DocumentReference documentReferenceTeacher = db.collection("teacher").document((String) mapFeedback.get("teacher_id"));
            mapFeedback.remove("student_id");
            mapFeedback.remove("teacher_id");
            mapFeedback.put("student",documentReferenceStudent);
            mapFeedback.put("teacher",documentReferenceTeacher);
            collection.document()
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--add()-- Error adding document", e);
                        }
                    });
        }
        else {
            collection.document()
                    .set(document.toMap())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--add()-- Error adding document", e);
                        }
                    });
        }
    }

    @Override
    public void add(final DocumentObject document) {
        if (type == Review.class) {
            Map<String, Object> mapFeedback = document.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            mapFeedback.remove("student_id");
            mapFeedback.put("student",documentReferenceStudent);
            collection.document()
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--add()-- Error adding document", e);
                        }
                    });
        }

        else if (type == Payment.class) {
            Map<String, Object> mapFeedback = document.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            DocumentReference documentReferenceTeacher = db.collection("teacher").document((String) mapFeedback.get("teacher_id"));
            mapFeedback.remove("student_id");
            mapFeedback.remove("teacher_id");
            mapFeedback.put("student",documentReferenceStudent);
            mapFeedback.put("teacher",documentReferenceTeacher);
            collection.document()
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--add()-- Error adding document", e);
                        }
                    });
        }
        else {
            collection.document()
                    .set(document.toMap())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--add()-- Error adding document", e);
                        }
                    });
        }
    }

    @Override
    public void add(String referenceId, boolean isTeacher) {
        Map<String, Object> map = new HashMap<>();
        if (isTeacher) {
            DocumentReference documentReference = db.collection("teachers").document(referenceId);
            map.put("teacher",documentReference);
        }
        else {
            DocumentReference documentReference = db.collection("students").document(referenceId);
            map.put("student", documentReference);
        }
        collection.document()
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "--add()-- Error adding document", e);
                    }
                });
    }

    @Override
    public void add(String referenceId, boolean isTeacher, final CallbackSuccess callbackSuccess) {
        Map<String, Object> map = new HashMap<>();
        if (isTeacher) {
            DocumentReference documentReference = db.collection("teachers").document(referenceId);
            map.put("teacher",documentReference);
        }
        else {
            DocumentReference documentReference = db.collection("students").document(referenceId);
            map.put("student", documentReference);
        }
        collection.document()
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                        callbackSuccess.onCallback();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "--add()-- Error adding document", e);
                    }
                });
    }


    @Override
    public void add(String referenceId, boolean isTeacher, final CallbackSuccess callbackSuccess, final CallbackError callbackError) {
        Map<String, Object> map = new HashMap<>();
        if (isTeacher) {
            DocumentReference documentReference = db.collection("teachers").document(referenceId);
            map.put("teacher",documentReference);
        }
        else {
            DocumentReference documentReference = db.collection("students").document(referenceId);
            map.put("student", documentReference);
        }
        collection.document()
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, " --add()-- DocumentSnapshot successfully added!");
                        callbackSuccess.onCallback();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "--add()-- Error adding document", e);
                        callbackError.onCallback();
                    }
                });
    }


    @Override
    public void update(String documentId, DocumentObject newDocument) {
        if (type == Review.class) {
            Map<String, Object> mapFeedback = newDocument.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            mapFeedback.remove("student_id");
            mapFeedback.put("student",documentReferenceStudent);
            collection.document(documentId)
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                        }
                    });
        }

        else if (type == Payment.class) {
            Map<String, Object> mapFeedback = newDocument.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            DocumentReference documentReferenceTeacher = db.collection("teacher").document((String) mapFeedback.get("teacher_id"));
            mapFeedback.remove("student_id");
            mapFeedback.remove("teacher_id");
            mapFeedback.put("student",documentReferenceStudent);
            mapFeedback.put("teacher",documentReferenceTeacher);
            collection.document(documentId)
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                        }
                    });
        }
        else {
            collection.document(documentId)
                    .set(newDocument.toMap())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                        }
                    });
        }
    }

    @Override
    public void update(String documentId, DocumentObject newDocument, final CallbackSuccess callbackSuccess) {
        if (type == Review.class) {
            Map<String, Object> mapFeedback = newDocument.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            mapFeedback.remove("student_id");
            mapFeedback.put("student",documentReferenceStudent);
            collection.document(documentId)
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                        }
                    });
        }

        else if (type == Payment.class) {
            Map<String, Object> mapFeedback = newDocument.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            DocumentReference documentReferenceTeacher = db.collection("teacher").document((String) mapFeedback.get("teacher_id"));
            mapFeedback.remove("student_id");
            mapFeedback.remove("teacher_id");
            mapFeedback.put("student",documentReferenceStudent);
            mapFeedback.put("teacher",documentReferenceTeacher);
            collection.document(documentId)
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                        }
                    });
        }
        else {
            collection.document(documentId)
                    .set(newDocument.toMap())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                        }
                    });
        }
    }


    @Override
    public void update(String documentId, DocumentObject newDocument, final CallbackSuccess callbackSuccess, final CallbackError callbackError) {
        if (type == Review.class) {
            Map<String, Object> mapFeedback = newDocument.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            mapFeedback.remove("student_id");
            mapFeedback.put("student",documentReferenceStudent);
            collection.document(documentId)
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                            callbackError.onCallback();
                        }
                    });
        }

        else if (type == Payment.class) {
            Map<String, Object> mapFeedback = newDocument.toMap();
            DocumentReference documentReferenceStudent = db.collection("student").document((String) mapFeedback.get("student_id"));
            DocumentReference documentReferenceTeacher = db.collection("teacher").document((String) mapFeedback.get("teacher_id"));
            mapFeedback.remove("student_id");
            mapFeedback.remove("teacher_id");
            mapFeedback.put("student",documentReferenceStudent);
            mapFeedback.put("teacher",documentReferenceTeacher);
            collection.document(documentId)
                    .set(mapFeedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                            callbackError.onCallback();
                        }
                    });
        }
        else {
            collection.document(documentId)
                    .set(newDocument.toMap())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()-- DocumentSnapshot successfully added!");
                            callbackSuccess.onCallback();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "--update()-- Error adding document", e);
                            callbackError.onCallback();
                        }
                    });
        }
    }

    @Override
    public void get(final String documentId, final Callback callback) {
        try {
            objectToReturn = getInstance();
        } catch (Exception e) {
            System.out.println("Could not define subclass of superclass: "+e);
        }
        collection.document(documentId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                            if (document.exists()) {
                                final Map<String, Object> map = new HashMap<>();
                                for (Map.Entry<String, Object> element: document.getData().entrySet()) {
                                    if (element.getKey().equals("student")) {
                                        DocumentReference studentReference = (DocumentReference) element.getValue();
                                        Log.d(TAG, "--get()-- " + "Reference data " + studentReference.getId() + " " + element.getValue());
                                        map.put("student_id",studentReference.getId());
                                    } else if (element.getKey().equals("teacher")) {
                                        DocumentReference teacherReference = (DocumentReference) element.getValue();
                                        Log.d(TAG, "--get()-- " + "Reference data " + teacherReference.getId() + " " + element.getValue());
                                        map.put("teacher_id", teacherReference.getId());
                                    } else {
                                        Log.d(TAG, "--get()-- " +""+element.getKey()+ " DocumentSnapshot data: " + element.getValue());
                                        if (type == StudentProfile.class) {
                                            map.put("student_"+element.getKey(),element.getValue());
                                        }
                                        else if (type == TeacherProfile.class) {
                                            map.put("teacher_"+element.getKey(),element.getValue());
                                        }
                                        else {
                                            map.put(element.getKey(),element.getValue());
                                        }
                                    }
                                }
                                objectToReturn.toObject(document.getId(),map);
                                callback.onCallback(objectToReturn);
                            } else {
                                Log.d(TAG, "--get()-- No such document");
                            }
                    }
        });
    }

    @Override
    public void get(final String documentId, final Callback callback, final CallbackError callbackError) {
        try {
            objectToReturn = getInstance();
        } catch (Exception e) {
            System.out.println("Could not define subclass of superclass: "+e);
        }
        collection.document(documentId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        if (document.exists()) {
                            final Map<String, Object> map = new HashMap<>();
                            for (Map.Entry<String, Object> element: document.getData().entrySet()) {
                                if (element.getKey().equals("student")) {
                                    DocumentReference studentReference = (DocumentReference) element.getValue();
                                    Log.d(TAG, " --get()-- " + "Reference data " + studentReference.getId() + " " + element.getValue());
                                    map.put("student_id",studentReference.getId());
                                } else if (element.getKey().equals("teacher")) {
                                    DocumentReference teacherReference = (DocumentReference) element.getValue();
                                    Log.d(TAG, " --get()-- " + "Reference data " + teacherReference.getId() + " " + element.getValue());
                                    map.put("teacher_id", teacherReference.getId());
                                } else {
                                    Log.d(TAG, " --get()-- " +""+element.getKey()+ " DocumentSnapshot data: " + element.getValue());
                                    if (type == StudentProfile.class) {
                                        map.put("student_"+element.getKey(),element.getValue());
                                    }
                                    else if (type == TeacherProfile.class) {
                                        map.put("teacher_"+element.getKey(),element.getValue());
                                    }
                                    else {
                                        map.put(element.getKey(),element.getValue());
                                    }
                                }
                            }
                            objectToReturn.toObject(document.getId(),map);
                            callback.onCallback(objectToReturn);
                        } else {
                            Log.d(TAG, "--get()-- No such document");
                            callbackError.onCallback();
                        }
                    }
                });
    }

    @Override
    public void delete(String documentId) {
        collection.document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "--delete()-- DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "--delete()-- Error deleting document", e);
                    }
                });
    }

    @Override
    public void delete(String documentId, final CallbackSuccess callbackSuccess, final CallbackError callbackError) {
        collection.document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, " --delete()-- DocumentSnapshot successfully deleted!");
                        callbackSuccess.onCallback();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "--delete()-- Error deleting document", e);
                        callbackError.onCallback();
                    }
                });
    }

    @Override
    public void delete(String documentId, final CallbackSuccess callbackSuccess) {
        collection.document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, " --delete()-- DocumentSnapshot successfully deleted!");
                        callbackSuccess.onCallback();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "--delete()-- Error deleting document", e);
                    }
                });
    }

    @Override
    public void deleteEqualTo (String equalToId, boolean isTeacher) {
        if (isTeacher) {
            final DocumentReference documentReference = db.collection("teachers").document(equalToId);
            Query query = collection.whereEqualTo("teacher", documentReference);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            collection.document(document.getId()).delete();
                            System.out.println("--deleteEqualTo()-- element: "+document.getId());
                        }
                    } else {
                        Log.d(TAG, "--deleteEqualTo()-- Error getting documents: ", task.getException());
                    }
                }
            });
        }
        else {
            DocumentReference documentReference = db.collection("students").document(equalToId);
            Query query = collection.whereEqualTo("student", documentReference);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            collection.document(document.getId()).delete();
                            System.out.println("--deleteEqualTo()-- element: "+document.getId());
                        }
                    } else {
                        Log.d(TAG, "--deleteEqualTo()-- Error getting documents: ", task.getException());
                    }
                }
            });
        }
    }

    @Override
    public void deleteEqualTo(String equalToId, boolean isTeacher, final CallbackSuccess callbackSuccess) {
        if (isTeacher) {
            final DocumentReference documentReference = db.collection("teachers").document(equalToId);
            Query query = collection.whereEqualTo("teacher", documentReference);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            collection.document(document.getId()).delete();
                            System.out.println("--deleteEqualTo()-- element: "+document.getId());
                        }
                        callbackSuccess.onCallback();
                    } else {
                        Log.d(TAG, "--deleteEqualTo()-- Error getting documents: ", task.getException());
                    }
                }
            });
        }
        else {
            DocumentReference documentReference = db.collection("students").document(equalToId);
            Query query = collection.whereEqualTo("student", documentReference);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            collection.document(document.getId()).delete();
                            System.out.println("--deleteEqualTo()-- element: "+document.getId());
                        }
                        callbackSuccess.onCallback();
                    } else {
                        Log.d(TAG, "--deleteEqualTo()-- Error getting documents: ", task.getException());
                    }
                }
            });
        }
    }

    @Override
    public void deleteEqualTo(String equalToId, boolean isTeacher, final CallbackSuccess callbackSuccess, final CallbackError callbackError) {
        if (isTeacher) {
            final DocumentReference documentReference = db.collection("teachers").document(equalToId);
            Query query = collection.whereEqualTo("teacher", documentReference);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            collection.document(document.getId()).delete();
                            System.out.println("--deleteEqualTo()-- element: "+document.getId());
                        }
                        callbackSuccess.onCallback();
                    } else {
                        Log.d(TAG, "--deleteEqualTo()-- Error getting documents: ", task.getException());
                        callbackError.onCallback();
                    }
                }
            });
        }
        else {
            DocumentReference documentReference = db.collection("students").document(equalToId);
            Query query = collection.whereEqualTo("student", documentReference);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            collection.document(document.getId()).delete();
                            System.out.println("--deleteEqualTo()-- element: "+document.getId());
                        }
                        callbackSuccess.onCallback();
                    } else {
                        Log.d(TAG, "--deleteEqualTo()-- Error getting documents: ", task.getException());
                        callbackError.onCallback();
                    }
                }
            });
        }
    }


    @Override
    public void getAll(final CallbackList callbackList) {
        collection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    objectToReturn = getInstance();
                                } catch (Exception e) {
                                    System.out.println("Could not define subclass of superclass: "+e);
                                }

                                final Map<String, Object> map = new HashMap<>();
                                for (Map.Entry<String, Object> element: document.getData().entrySet()) {
                                    if (element.getKey().equals("student")) {
                                        DocumentReference studentReference = (DocumentReference) element.getValue();
                                        Log.d(TAG, "--getAll()-- " + "Reference data " + studentReference.getId() + " " + element.getValue());
                                        map.put("student_id",studentReference.getId());
                                    } else if (element.getKey().equals("teacher")) {
                                        DocumentReference teacherReference = (DocumentReference) element.getValue();
                                        Log.d(TAG, "--getAll()-- " + "Reference data " + teacherReference.getId() + " " + element.getValue());
                                        map.put("teacher_id",teacherReference.getId());
                                    } else {
                                        Log.d(TAG, "--getAll()-- " +""+element.getKey()+ " DocumentSnapshot data: " + element.getValue());
                                        if (type == StudentProfile.class) {
                                            map.put("student_"+element.getKey(),element.getValue());
                                        }
                                        else if (type == TeacherProfile.class) {
                                            map.put("teacher_"+element.getKey(),element.getValue());
                                        }
                                        else {
                                            map.put(element.getKey(),element.getValue());
                                        }
                                    }
                                }
                                objectToReturn.toObject(document.getId(), map);
                                listOfObjectsToReturn.add((T) objectToReturn);
                            }
                            callbackList.onCallback(listOfObjectsToReturn);
                        } else {
                            Log.w(TAG, "--getAll()-- Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void getAll(final CallbackList callbackList, final CallbackError callbackError) {
        collection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    objectToReturn = getInstance();
                                } catch (Exception e) {
                                    System.out.println("Could not define subclass of superclass: "+e);
                                }

                                final Map<String, Object> map = new HashMap<>();
                                for (Map.Entry<String, Object> element: document.getData().entrySet()) {
                                    if (element.getKey().equals("student")) {
                                        DocumentReference studentReference = (DocumentReference) element.getValue();
                                        Log.d(TAG, "--getAll()-- " + "Reference data " + studentReference.getId() + " " + element.getValue());
                                        map.put("student_id",studentReference.getId());
                                    } else if (element.getKey().equals("teacher")) {
                                        DocumentReference teacherReference = (DocumentReference) element.getValue();
                                        Log.d(TAG, "--getAll()-- " + "Reference data " + teacherReference.getId() + " " + element.getValue());
                                        map.put("teacher_id",teacherReference.getId());
                                    } else {
                                        Log.d(TAG, "--getAll()-- " +""+element.getKey()+ " DocumentSnapshot data: " + element.getValue());
                                        if (type == StudentProfile.class) {
                                            map.put("student_"+element.getKey(),element.getValue());
                                        }
                                        else if (type == TeacherProfile.class) {
                                            map.put("teacher_"+element.getKey(),element.getValue());
                                        }
                                        else {
                                            map.put(element.getKey(),element.getValue());
                                        }
                                    }
                                }
                                objectToReturn.toObject(document.getId(), map);
                                listOfObjectsToReturn.add((T) objectToReturn);
                            }
                            callbackList.onCallback(listOfObjectsToReturn);
                        } else {
                            Log.w(TAG, "--getAll()-- Error getting documents.", task.getException());
                            callbackError.onCallback();
                        }
                    }
                });
    }

    public T getInstance() throws Exception {
        return type.newInstance();
    }
}
