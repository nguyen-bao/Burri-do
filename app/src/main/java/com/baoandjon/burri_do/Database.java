package com.baoandjon.burri_do;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Database {
    private static final Database instance = new Database();

    private FirebaseFirestore db;
    private ArrayList<Project> projects;
    private ArrayList<Tag> availableTags;

    public static Database getInstance() {
        return instance;
    }

    public void update() {
        db = FirebaseFirestore.getInstance();
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    // listener for getData
    public interface OnGetDataListener {
        void onSuccess();
        void onFailure();
    }

    // creates project arraylist from database
    public void updateProjects(final OnGetDataListener listener) {
        projects = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Task<QuerySnapshot> snapshot = db.collection("projects").get();
        snapshot.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshot) {
                Log.d(TAG, "DocumentSnapshot data: " + snapshot.getDocuments());
                extractProjects(snapshot.getDocuments());
                listener.onSuccess();
            }
        });
        snapshot.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "get failed with " + e.getMessage());
                listener.onFailure();
            }
        });
    }

    private void extractProjects(List<DocumentSnapshot> documents) {
        for (DocumentSnapshot doc : documents) {
            String title = doc.getString("title");
            int iconRef = ((Long)doc.get("icon_ref")).intValue();
            String description = doc.getString("description");
            Date deadline = doc.getDate("deadline");

            ArrayList<Tag> tags = (ArrayList<Tag>)doc.get("tags");
            ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>)doc.get("ingredients");

            Project project = new Project(title, iconRef);
            project.setDescription(description);
            project.setDeadline(deadline);
            project.setTags(tags);
            project.setIngredients(ingredients);

            projects.add(project);
        }
        System.out.println("EXTRACT:" + projects.size());
    }

    public void addProject(Project project) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("title", project.getTitle());
        data.put("icon_ref", project.getIconRef());
        data.put("deadline", project.getDeadline());
        data.put("description", project.getDescription());
        data.put("tags", project.getTags());

        db.collection("projects").add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Document added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document");
                    }
                });
    }

    public void removeProject(String title) {

    }

    public ArrayList<Tag> getTags() {
        return availableTags;
    }

    // creates tag arraylist from database
    public void updateTags(final OnGetDataListener listener) {
        availableTags = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Task<QuerySnapshot> snapshot = db.collection("available_tags").get();
        snapshot.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshot) {
                Log.d(TAG, "DocumentSnapshot data: " + snapshot.getDocuments());
                extractTags(snapshot.getDocuments());
                listener.onSuccess();
            }
        });
        snapshot.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "get failed with " + e.getMessage());
                listener.onFailure();
            }
        });
    }

    private void extractTags(List<DocumentSnapshot> documents) {
        for (DocumentSnapshot doc : documents) {
            String name = doc.getString("name");
            int iconRef = (int)doc.get("icon_ref");

            Tag tag = new Tag(name, iconRef);

            availableTags.add(tag);
        }
    }

    public void addTag(Tag tag) {
        db.collection("available_tags").add(tag);
    }

    public void removeTag(String name) {

    }

    private Database() {
        update();
    }
}
