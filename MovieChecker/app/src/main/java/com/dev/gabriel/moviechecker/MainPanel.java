package com.dev.gabriel.moviechecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainPanel extends AppCompatActivity
{
    String actorName, movieTitle;
    String[] genresArray;
    ImageView poster;
    ImageButton profileButton, settingsButton, aboutButton;
    Button searchButton, nextButton, prevButton;
    CheckBox option1, option2, option3, option4, option5, option6;
    EditText titleTextBox, actorTextBox;

    OkHttpClient client = new OkHttpClient();

    private void LoadAllComponents()
    {
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

        ArrayAdapter<String> adapter;
        genresArray = getResources().getStringArray(R.array.genresList);
    }

    public void SearchByTitle(String title)
    {
        String query = "https://api.themoviedb.org/3/search/movie?api_key=" + AppData.apiKey + "&language=en-US&query=" + title + "&page=1&include_adult=false";

        Request request = new Request.Builder()
                .url(query)
                .build();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if (response.isSuccessful())
                {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    Movie movie = gson.fromJson(result, Movie.class);

                    MainPanel.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                        }
                    });
                }
            }
        });
    }

    private void LoadPoster()
    {

    }
}
