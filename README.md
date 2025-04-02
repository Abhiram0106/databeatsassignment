This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


# DataBeatAssessment

DataBeatAssessment is a Kotlin Multiplatform (KMP) project designed to fetch and display news articles from a remote API. It demonstrates modern Android development practices, clean architecture principles, and efficient data handling.

## Features

- **Reactive UI using coroutines**
- **News Article Fetching:** Fetches news articles from a remote API.
- **Data Mapping:** Efficiently maps data transfer objects (DTOs) from the API response to domain models.
- **UI Components:** Provides reusable UI components.
- **Clean Architecture:** Follows clean architecture principles to separate concerns and enhance maintainability.
- **Kotlin Multiplatform:** Leverages KMP to share code between different platforms (Android and iOS).
- **Unit Testing:** Includes unit tests for core logic.
- **Jetpack Compose:** Uses Jetpack Compose for building the user interface.
- **Kotlinx.datetime**: Uses kotlinx.datetime to manage the date and time.
- **Support for Light/Dark Theme and Portrait/Landscape Mode**
- **Paginated data fetching**

## Project Structure

The project is organized into the following modules:

-   **`composeApp/src/commonMain`**
  - Contains the application code.
  - Implements the user interface using Jetpack Compose.
  - Implements networking with Ktor.
  - Implements dependency with Koin.
  - Includes `commonTest` for unit tests.

## Core Components

-  **`core`:**
  - This directory manages the global state of the app.
  - Includes HttpClient, Koin, Coroutine Dispatcher initialization.
  - Includes the Screens, Graphs and Navigation Host
  - Sets the app color theme and typography

-   **`feature_home`:**
  -   This directory manages the news display feature, its data layer and its domain layer.
  -   **Data Layer:**
    - `ArticlesDto`: Data transfer object for news articles.
    - `ArticlesDto`: Data transfer object for the source of the news.
    - `GetNewsResponseDto`: Data transfer object for the API response containing news articles.
  -   **Domain Layer:**
    -   `NewsItem`: Domain model for a news article.
    -   `GetNewsResponse`: Domain model for the news response.
    -   `Mappers`: Functions to map DTOs to domain models (e.g., `ArticlesDto.toDomain()`).
  - **Presentation**:
    - `CountryPickerDialog` : A composable function to pick the country.
    - `NewsListItem`: A composable function that shows the articles as items in a list.
    - `HomeUiState`: Encapsulates the UI state of home screen.
    - `HomeUiAction`: Encapsulates the possible UI actions perform-able by the user.
    - `HomeRoot`: Contains `HomeScreen` which is  the main screen that displays news articles as well as filtering options.
    - `HomeViewModel`: Responsible for loading and managing state of `HomeRoot`
  - **Util**:
    - `stringToLocalDate` : A function that converts a string to `LocalDateTime?`.
    - `LocalDateTime.toFormattedString(am: String, pm: String)` an extension function that converts LocalDateTime into a string such as `15 November 2023, 03:30 Evening`


-  **`commonTest`:**
  - Contains the test cases `HelperFunctionsTest` and `MappersTest`



https://github.com/user-attachments/assets/eceb565f-1a44-4c4c-9d8f-abc5f5f0f24d



https://github.com/user-attachments/assets/7351b23b-2f47-4bf6-8d20-311ad9a74572


