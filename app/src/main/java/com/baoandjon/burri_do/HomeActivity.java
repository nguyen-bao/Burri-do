package com.baoandjon.burri_do;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout lin_layout;
    private ImageButton btn_new;
    private ArrayList<Project> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lin_layout = findViewById(R.id.lin_layout);
        btn_new = findViewById(R.id.btn_new);
        projects = new ArrayList<>(); // replace with data retrieval

        for (Project project : projects) {
            addProject(project);
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
    public void addProject(Project project) {
        ImageButton newButton = new ImageButton(this);
        newButton.setLayoutParams(btn_new.getLayoutParams());
        newButton.setImageDrawable(project.getIcon());
        newButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Drawable transBackground = project.getIcon();
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
