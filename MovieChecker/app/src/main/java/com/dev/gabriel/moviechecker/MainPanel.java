package com.dev.gabriel.moviechecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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

    private void LoadAllComponents()
    {
        poster = findViewById(R.id.poster);
        actorTextBox = findViewById(R.id.actorTextbox);
        titleTextBox = findViewById(R.id.titleTextbox);
        profileButton = (findViewById(R.id.profile_Button);
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

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/configuration/countries?api_key=f15d8142120b340d997e986cacb5b085")
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
                    final String resp = response.body().string();

                    MainPanel.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            titleTextBox.setText(resp);
                        }
                    });
                }
            }
        });

        ArrayAdapter<String> adapter;
        genresArray = getResources().getStringArray(R.array.genresList);

        actorName = actorTextBox.getText().toString();
        movieTitle = titleTextBox.getText().toString();

        movieTitle = AppData.apiKey;
    }
}
