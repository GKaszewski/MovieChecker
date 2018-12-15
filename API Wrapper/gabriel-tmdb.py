import requests, urllib.parse, json

example_api = 'f15d8142120b340d997e986cacb5b085'

def SearchMovie(api, title, year, isAdult):
    base = 'https://api.themoviedb.org/3/search/movie?'
    query = base + urllib.parse.urlencode({'api_key':api, 'query':title,'include_adult':isAdult, 'year':year})
    print(query)
    json_data = requests.get(query).json()
    print(json_data)

SearchMovie(example_api, 'Avengers', 2012, False)