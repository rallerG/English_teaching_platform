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
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Collection;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Document;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public abstract class DAOImpl <T extends DocumentObject> implements Document, Collection {
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
    public void get(String documentId, final Callback callback) {
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
                                DocumentReference studentReference = (DocumentReference) document.get("student");
                                DocumentReference teacherReference = (DocumentReference) document.get("teacher");
                                if (studentReference != null) {
                                    Log.d(TAG, " --get()-- "+"Reference data " + studentReference.getId()+ " "+studentReference.getPath());
                                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                    //studentsDocument.get(studentReference.getId());
                                    objectToReturn.toObject(document.getId(),document.getData());
                                }
                                else if (teacherReference != null) {
                                    Log.d(TAG, " --get()-- "+"Reference data " + teacherReference.getId() + " "+teacherReference.getPath());
                                    TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                    //teachersDocument.get(studentReference.getId());
                                    objectToReturn.toObject(document.getId(),document.getData());
                                }
                                else {
                                    Log.d(TAG, " --get()-- "+"DocumentSnapshot data: " + document.getData());
                                    objectToReturn.toObject(document.getId(),document.getData());
                                    callback.onCallback(objectToReturn);
                                }
                            } else {
                                Log.d(TAG, "No such document");
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

                                DocumentReference studentReference = (DocumentReference) document.get("student");
                                DocumentReference teacherReference = (DocumentReference) document.get("teacher");
                                if (studentReference != null) {
                                    Log.d(TAG, " --getAll()-- "+"Reference data " + studentReference.getId()+ " "+studentReference.getPath());
                                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                    //studentsDocument.get(studentReference.getId());
                                    objectToReturn.toObject(document.getId(),document.getData());
                                    listOfObjectsToReturn.add((T) objectToReturn);
                                }
                                else if (teacherReference != null) {
                                    Log.d(TAG, " --getAll()-- "+"Reference data " + teacherReference.getId() + " "+teacherReference.getPath());
                                    TeachersDocument teachersDocument = new TeachersDocumentImpl();
                                    //teachersDocument.get(studentReference.getId());
                                    objectToReturn.toObject(studentReference.getId(),document.getData());
                                    listOfObjectsToReturn.add((T) objectToReturn);
                                }
                                else {
                                    Log.d(TAG," --getAll()-- "+ document.getId() + " => " + document.getData());
                                    objectToReturn.toObject(document.getId(),document.getData());
                                    listOfObjectsToReturn.add((T) objectToReturn);
                                }
                            }
                            callbackList.onCallback(listOfObjectsToReturn);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public T getInstance() throws Exception {
        return type.newInstance();
    }
}
