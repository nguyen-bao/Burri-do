package com.baoandjon.burri_do;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
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

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout lin_layout;
    private ImageButton btn_new;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Database.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lin_layout = findViewById(R.id.lin_layout);
        btn_new = findViewById(R.id.btn_new);

        db.update();
        db.updateProjects(new Database.OnGetDataListener() {
            @Override
            public void onSuccess() {
                for (Project project : db.getProjects()) {
                    addButton(project);
                }
            }

            @Override
            public void onFailure() {

            }
        });

        for (Project project : db.getProjects()) {
            addButton(project);
        }

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EditProjectActivity.class);
                startActivity(intent);
            }
        });
    }

    // creates new button for project and adds it to layout
    public void addButton(Project project) {
        ImageButton newButton = new ImageButton(this);
        newButton.setLayoutParams(btn_new.getLayoutParams());
        newButton.setImageDrawable(ContextCompat.getDrawable(this, project.getIconRef()));
        newButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Drawable transBackground = ContextCompat.getDrawable(this, project.getIconRef());
        transBackground.setAlpha(0);
        newButton.setBackground(transBackground);

        TextView newTitle = new TextView(this);
        newTitle.setText(project.getTitle());
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.gravity = Gravity.CENTER;

        newTitle.setLayoutParams(titleParams);

        lin_layout.addView(newButton, lin_layout.getChildCount()-1);
        lin_layout.addView(newTitle, lin_layout.getChildCount()-1);
    }
}
