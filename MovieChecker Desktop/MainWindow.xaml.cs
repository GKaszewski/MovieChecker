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
using TMDbLib.Objects.Search;

namespace MovieChecker_Desktop
{

    public partial class MainWindow : Window
    {
        private readonly string apiKey = Loader.LoadTextFile("Key/API_Key.txt");

        enum Genres
        {

        }

        private TMDbClient client = null;
        private bool genreChosen = false;
        private Genres genresEnum;

        public MainWindow()
        {
            InitializeComponent();
            client = new TMDbClient(apiKey);
            LoadGenres();
        }

        private void SearchButton_OnClick(object sender, RoutedEventArgs e)
        {
            if ()
            {
                
            }
        }

        private bool CheckFields()
        {



            return false;
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
            genreChosen = true;

        }
    }
}
