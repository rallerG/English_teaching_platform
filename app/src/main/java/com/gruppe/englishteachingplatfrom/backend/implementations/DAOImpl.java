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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Collection;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Document;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public abstract class DAOImpl <T extends DocumentObject> implements Document, Collection {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection;
    private Class<T> type;

    public DAOImpl(String collectionReference, Class<T> type) {
        this.collection = db.collection(collectionReference);
        this.type = type;
    }

    @Override
    public void add(DocumentObject document) {
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
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public void update(String documentId, DocumentObject newDocument) {
        collection.document(documentId)
                    .set(newDocument.toMap())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "--update()--"+"DocumentSnapshot successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error updating document", e);
                        }
                    });
    }

    @Override
    public DocumentObject get(String documentId) {
            collection.document(documentId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    //DocumentObject objectToReturn = document.toObject(type); //missing .class
                                    Log.d(TAG, " --get()-- "+"DocumentSnapshot data: " + document.getData());

                                    DocumentReference studentReference = (DocumentReference) document.get("student");
                                    DocumentReference teacherReference = (DocumentReference) document.get("student");
                                    if (studentReference != null) {
                                        Log.d(TAG, " --get()-- "+"Reference data " + studentReference.getId()+ " "+studentReference.getPath());
                                        StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                        studentsDocument.get(studentReference.getId());
                                    }
                                    else if (teacherReference != null) {
                                        Log.d(TAG, " --get()-- "+"Reference data " + teacherReference.getId() + " "+teacherReference.getPath());
                                        TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                        teachersDocument.get(studentReference.getId());
                                    }
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
        });
        return null;
    }

    @Override
    public void delete(String documentId) {
        collection.document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, " --delete()-- DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    @Override
    public List<T> getAll() {
        collection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG," --getAll()-- "+ document.getId() + " => " + document.getData());

                                DocumentReference studentReference = (DocumentReference) document.get("student");
                                DocumentReference teacherReference = (DocumentReference) document.get("student");
                                if (studentReference != null) {
                                    Log.d(TAG, " --getAll()-- "+"Reference data " + studentReference.getId()+ " "+studentReference.getPath());
                                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                    studentsDocument.get(studentReference.getId());
                                }
                                else if (teacherReference != null) {
                                    Log.d(TAG, " --getAll()-- "+"Reference data " + teacherReference.getId() + " "+teacherReference.getPath());
                                    TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                    teachersDocument.get(studentReference.getId());
                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return null;
    }
}
