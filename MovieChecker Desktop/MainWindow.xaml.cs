using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using MovieChecker_Desktop.Classes;
using TMDbLib.Client;
using TMDbLib.Objects.General;
using TMDbLib.Objects.Genres;
using TMDbLib.Objects.Movies;
using TMDbLib.Objects.Search;

namespace MovieChecker_Desktop
{

    public partial class MainWindow : Window
    {
        private readonly string apiKey = Loader.LoadTextFile("Key/API_Key.txt");

        private TMDbClient client = null;
        private string genre = String.Empty;

        public MainWindow()
        {
            InitializeComponent();
            client = new TMDbClient(apiKey);
            LoadGenres();
        }

        private void SearchButton_OnClick(object sender, RoutedEventArgs e)
        {
            CleanResultsListBox();
            if (genre != String.Empty)
            {
                SearchByGenre();
            }

            if (actorTextBox.Text != String.Empty)
            {
                SearchByPerson(actorTextBox.Text);
            }

            if (directorTextBox.Text != String.Empty)
            {
                SearchByPerson(directorTextBox.Text);
            }
        }

        private void SearchByPerson(string person)
        {
            var results = client.SearchPersonAsync(person).Result;
            foreach (var result in results.Results)
            {
                List<ListBoxItem> newItems = new List<ListBoxItem>();
                foreach (var res in result.KnownFor)
                {
                    ListBoxItem newItem = new ListBoxItem();
                    int id = res.Id;

                    Movie newMovie = client.GetMovieAsync(id).Result;
                    newItem.Content = newMovie.Title;

                    newItems.Add(newItem);
                }

                foreach (var item in newItems)
                {
                    resultsListBox.Items.Add(item);
                }
            }
        }

        private void SearchByGenre()
        {
            var results = client.SearchKeywordAsync(genre).Result;
            foreach (var result in results.Results)
            {
                ListBoxItem newItem = new ListBoxItem();
                newItem.Content = result.Name;
                resultsListBox.Items.Add(newItem);
            }
        }

        private void CleanResultsListBox()
        {
            resultsListBox.Items.Clear();
        }


        private void LoadGenres()
        {
            var genres = client.GetMovieGenresAsync().Result;

            foreach (var genre in genres)
            {
                ComboBoxItem newItem = new ComboBoxItem();
                newItem.Content = genre.Name;
                genreBox.Items.Add(newItem);
            }

        }

        private void genreBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ComboBoxItem currentItem  = (ComboBoxItem)genreBox.SelectedItem;
            genre = currentItem.Content.ToString();
        }
    }
}
