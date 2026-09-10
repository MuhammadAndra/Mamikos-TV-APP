# TV App

A simple Android TV show browser application built with Kotlin and Jetpack Compose using the TVMaze API.

## Features

- Browse TV shows from TVMaze API
- Display show poster, title, and rating
- View TV show details
- Display show summary and premiere date
- Share TV show information using Android Share
- Loading, success, and error states with retry

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- Retrofit
- Coroutines
- Coil
- Hilt

## How to Run

1. Clone this repository.
2. Open the project in Android Studio.
3. Wait for Gradle sync to finish.
4. Run the application on an Android emulator or physical Android device.
5. No additional API key or backend configuration is required.

The application uses the public TVMaze API.

## Architecture
The architecture that i use in this application are MVVM. i use this architecture because this is the general architecture that usually used in android development. In MVVM Architecture we split the app into 3 component according to their responsibility. which is:
1. Model: Responsible for retrieving data. in this project i separated this component into API to get data from network, and repository to prepare the data for ViewModel. there's only 1 repository and 1 API Service in this project, because we only working with Show Related Data. if we improve the feature into including Season, Episode, and Cast, maybe we can add more.
2. View: Responsible for displaying data. in this project i use Jetpack Compose as UI Framework. there's two screen in this app. one for displaying show list, and one for displaying sho detail
3. ViewModel: Responsible for managing UI State and handle communication between Model and View. in this project there's 2 view model. one for each screen.  

## What I Would Improve With More Time
In this project i could only implement the required functionality because of time constraint. If i had more time i will improve the project by:
- adding pagination into the show list because, in my observation there's a long loading time when we enter the show list screen because of a lot of data that need to load (~250 shows)
- i also would add more information into the detail page such as Season, Episode, and Cast. i already read the documentation for the API, we could get season data from this url: /shows/:id/seasons, episode data from this url: /shows/:id/episodes, and cast data from this url:  /shows/:id/cast. regarding this improvement. i intentionally want to implement the feature. i even prepare the UI to place the data using TabRow. 
- next i also want to learn more about Unit Test. To be honest this is the first time i implement Unit Test in my Android code. this technical test bring so much lessons for me. if there's more time i want to add more unit test especially for different repository and API failure scenarios
- lastly i want to improve accessibility and handling in different screen sizes/orientation.

## Limitations

The following optional features were not implemented due to the time constraint:

- Pagination
- Season information✅
- Episode information
- Cast information✅

## Update

I able to squeeze some time and add some optional task by showing season and cast information
