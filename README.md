# BooksApp

BooksApp is a simple Android application that allows users to browse a list of books, view book details, and search for books locally from the database. The app follows an offline-first approach, including caching and swipe-to-refresh functionality.

## Features

- Browse a list of books
- View detailed information for each book
- Search books locally from the database
- Offline-first caching
- Swipe-to-refresh

## Architecture & Tech Stack

- Clean Architecture
- MVVM pattern
- Kotlin Coroutines & Flow
- Hilt for Dependency Injection
- Retrofit + OkHttp for network requests
- Room for local database caching

## Installation

1. Open Android Studio
2. Go to `File > New > Project from Version Control`
3. Enter repository URL: `https://github.com/AgungKusumma/Books-App.git`
4. Click `Clone` to download the project
5. Wait for the project to open and perform Gradle sync to download dependencies
6. Ensure your emulator or device is ready to run the application

## Usage

- Run the app on an emulator or device
- Browse the list of books
- Click on a book to view its details
- Use the search bar to filter books locally

## Notes

- LeakCanary is integrated to detect memory leaks, but it only runs in the **dev branch**
- The app follows an offline-first caching strategy
- Clean Architecture is used to separate concerns and make the app maintainable
- MVVM pattern is applied for UI and data handling 
- Supports Android 8.0 (API level 26) and above