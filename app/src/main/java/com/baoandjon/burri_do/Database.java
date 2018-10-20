package com.baoandjon.burri_do;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class Database {
    private static final Database instance = new Database();
    private static FirebaseFirestore db;
    private static ArrayList<Project> projects;
    private static ArrayList<Tag> tags;

    public static Database getInstance() {
        return instance;
    }

    public static void update() {
        db = FirebaseFirestore.getInstance();
    }

    // creates project arraylist from database
    private static void updateProjects() {
        projects = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final ArrayList<Project> projects_final;

        Task<QuerySnapshot> snapshot = db.collection("projects").get();
        snapshot.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshot) {
                Log.d(TAG, "DocumentSnapshot data: " + snapshot.getDocuments());
                for (DocumentSnapshot doc : snapshot.getDocuments()) {
                    String title = doc.getString("title");
                    Drawable icon = (Drawable)doc.get("icon");
                    String description = doc.getString("description");
                    Date deadline = doc.getDate("deadline");
                    ArrayList<Tag> tags = new ArrayList((Collection<Tag>)doc.get("tags"));
                    ArrayList<Ingredient> ingredients = new ArrayList((Collection<Ingredient>)doc.get("ingredients"));

                    Project project = new Project(title, icon);
                    project.setDescription(description);
                    project.setDeadline(deadline);
                    project.setTags(tags);
                    project.setIngredients(ingredients);

                    projects.add(project);
                }
            }
        });
        snapshot.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "get failed with " + e.getMessage());
            }
        });
    }

    public static ArrayList<Project> getProjects() {
        updateProjects();
        return projects;
    }

    public static void addProject(Project project) {
        db.collection("projects").add(project);
    }

    public static void removeProject() {

    }

    // creates tag arraylist from database
    private static void updateTags() {
        tags = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final ArrayList<Project> projects_final;

        Task<QuerySnapshot> snapshot = db.collection("tags").get();
        snapshot.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshot) {
                Log.d(TAG, "DocumentSnapshot data: " + snapshot.getDocuments());
                for (DocumentSnapshot doc : snapshot.getDocuments()) {
                    String name = doc.getString("name");
                    Drawable icon = (Drawable)doc.get("icon");

                    Tag tag = new Tag(name, icon);

                    tags.add(tag);
                }
            }
        });
        snapshot.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "get failed with " + e.getMessage());
            }
        });
    }

    public static ArrayList<Tag> getTags() {
        updateTags();
        return tags;
    }

    private Database() {
        update();
    }
}
