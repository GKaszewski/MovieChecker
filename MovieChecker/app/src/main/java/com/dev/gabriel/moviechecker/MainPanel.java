package com.dev.gabriel.moviechecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.*;

public class MainPanel extends AppCompatActivity
{
    private TmdbApi api;
    String actorName, movieTitle;
    List<String> genresList;
    ImageView poster;
    ImageButton profileButton, settingsButton, aboutButton;
    Button searchButton, nextButton, prevButton;
    CheckBox option1, option2, option3, option4, option5, option6;
    EditText titleTextBox, actorTextBox;

    private void LoadAllComponents()
    {
        api = new TmdbApi(AppData.apiKey);
        poster = findViewById(R.id.poster);
        actorTextBox = findViewById(R.id.actorTextbox);
        titleTextBox = findViewById(R.id.titleTextbox);
        profileButton = findViewById(R.id.profile_Button);
        settingsButton = findViewById(R.id.settings_Button);
        aboutButton = findViewById(R.id.about_Button);
        searchButton = findViewById(R.id.search_Button);
        nextButton = findViewById(R.id.next_Button);
        prevButton = findViewById(R.id.prev_Button);
        option1 = findViewById(R.id.topRatedMovies_Checkbox);
        option2 = findViewById(R.id.upcomingMovies_Checkbox);
        option3 = findViewById(R.id.nowPlaying_Checkbox);
        option4 = findViewById(R.id.popularMovies_Checkbox);
        option5 = findViewById(R.id.topRatedTvShows_Checkbox);
        option6 = findViewById(R.id.onAirTvShows_Checkbox);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_panel);

        LoadAllComponents();

        TmdbGenre apiGenre = api.getGenre();
        //List<Genre> genres = apiGenre.getGenreList(AppData.language);

        genresList = Arrays.asList(getResources().getStringArray(R.array.genresList));

        /*for (Genre genre : genres)
        {
            genresList.add(genre.getName());
        }*/


        actorName = actorTextBox.getText().toString();
        movieTitle = titleTextBox.getText().toString();

        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SearchByTitle(movieTitle);
            }
    });

    
}

    public void SearchByTitle(String title)
    {

    }

    private void LoadPoster()
    {

    }
}
