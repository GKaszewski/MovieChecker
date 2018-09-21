package com.dev.gabriel.moviechecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class MainPanel extends AppCompatActivity {

    String[] genresArray;
    ImageView poster;
    Button searchButton, nextButton, prevButton;
    CheckBox option1, option2, option3, option4, option5, option6;
    EditText titleTextBox, actorTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_panel);

        ArrayAdapter<String> adapter;
        genresArray = getResources().getStringArray(R.array.genresList);
    }
}
