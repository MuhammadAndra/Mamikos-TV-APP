# Code Review
in this Internship i applied as Mobile Engineer Intern (Android). So i will try to list every problem i find and how i fix it. this below is the code that needed to be fixed

class MovieViewModel : ViewModel() {
  var movies: List<Movie> = emptyList()
  fun loadMovies() {
    val url = URL("https://api.example.com/movies")
    val data = url.readText()
    movies = parseMovies(data)
  }
}

## 1. there's only public variable to be accessed from View.
what i meant by that is this code only give one variable which is var movies to be accessed from View, and this variable also is the same one that being changed in loadMovies() function. as far as i know, in a case like this where we use viewmodel to get a data we need at least to variable. one private and one public. so the private one will be used when there is new data. for example data from load movies should be stored in this private variable. the public variable in the other hand will point to the private variable and will be set to cannot be changed. this is the variable we will use in View. the reason for this approach is to maintain separation of concern because View should only be used to display data, while data changes can be done in ViewModel

## 2. there's no coroutine done while getting the data from API
i think we should use coroutine when dealing with long period task for example communication with API like this. because if not we can potentially blocked the main thread and trigger Application Not Responding (ANR)

## 3. we shouldn't leave BASE_URL in viewModel
if we leave the URL scattered around like this in the viewmodel. it will make code maintaining process far more troublesome in the future.

## 4. no repositories used and no state preparation for View
i think we could add repository and let it handle the data loading from API. View Model can be use to parse the data from repository to UI State so that it can be used on View. with this we can implement MVVM architecture correctly 

below is the code i think i will use in cases like above. 

class MovieViewModel : ViewModel(
  private val repository: MovieRepository 
) {
  private val _movies =  MutableStateFlow<UiState<List<Movie>>>(UiState.Idle)
  val movies = _movies
  fun loadMovies() {
    _moview.values = UiState.Loading
    viewModelScope.launch(Dispatchers.IO){ 
      when (val result = repository.getMovies()) {
        is DataResult.Success -> _shows.value = UiState.Success(result.data)
        is DataResult.Error -> {
          val error = result.error
          _shows.value = UiState.Error(error)
       }
        is DataResult.Empty -> _shows.value = UiState.Empty
      }
    }
  }
}
