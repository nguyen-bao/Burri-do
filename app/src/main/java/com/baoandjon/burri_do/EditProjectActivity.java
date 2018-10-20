package com.baoandjon.burri_do;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class EditProjectActivity extends AppCompatActivity {
    private EditText edt_title;
    private CalendarView calendar;
    private EditText edt_description;
    private LinearLayout layout_tags;
    private Button btn_submit;

    private Database db;

    private ArrayList<Tag> availableTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Database.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        edt_title = findViewById(R.id.edt_title);
        calendar = findViewById(R.id.calendar);
        edt_description = findViewById(R.id.edt_description);
        layout_tags = findViewById(R.id.layout_tags);
        btn_submit = findViewById(R.id.btn_submit);

        db.update();
        for (Tag tag : db.getTags()) {
            CheckBox checkbox = new CheckBox(this);
            checkbox.setBackground(tag.getIcon());
            layout_tags.addView(checkbox);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    private void onSubmit () {
        if (edt_title.getText().toString().equals("")) {
            Toast.makeText(this, "Please choose a title and icon", Toast.LENGTH_SHORT).show();
        } else {
            String title = edt_title.getText().toString();
            Date deadline = new Date(calendar.getDate());
            String description = edt_description.getText().toString();

            ArrayList<Tag> tags = new ArrayList<>();
            for (int i = 0; i < layout_tags.getChildCount(); i++) {
                if (layout_tags.getChildAt(i).isActivated()) {
                    tags.add(availableTags.get(i));
                }
            }

            Project newProject = new Project(title, new ColorDrawable(23));
            newProject.setDeadline(deadline);
            newProject.setDescription(description);
            newProject.setTags(tags);

            db.addProject(newProject);

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
