# ThmanyahTask

This project demonstrates the implementation of **HomeScreen** and **SearchScreen** using modern Android development practices and technologies. It is built with a clean and scalable architecture, leveraging **Jetpack Compose** for UI and **MVVM architecture**.

---

## Features

- **HomeScreen**: Displays categorized sections with a variety of layouts (e.g., grid, queue, big square).
- **SearchScreen**: Allows users to search and view filtered results.
- **Paging**: Efficiently handles large datasets with pagination.

---

## Tech Stack

This project uses the following technologies and tools:

- **Kotlin**: Language used for all development.
- **MVVM Architecture**: Ensures separation of concerns and testability.
- **Coroutines & Flow**: For asynchronous programming and reactive data streams.
- **Hilt**: Dependency injection for easy and scalable code management.
- **Retrofit & OkHttp**: Networking libraries for API calls.
- **Jetpack Compose**: Modern UI toolkit for building declarative UIs.
- **Paging 3**: Simplifies pagination and data loading.
- **JUnit**: Unit testing framework.
- **Compose UI Tooling**: For testing Jetpack Compose UIs.
- **MockK**: Mocking library for testing.
- **KotlinFixture**: For generating test data.

---

## Architecture

The project follows the **MVVM (Model-View-ViewModel)** architecture:
1. **Presentation Layer**: Built with Jetpack Compose, using composables for rendering screens, **ViewModel** manages UI state and interacts with the use cases.
2. **Domain Layer**: Contains Repository contract and use cases.
5. **Data Layer**: Handles data operations and ensures separation of concerns.

---

## Key Components

### **HomeScreen**
- Displays categorized sections using different layouts:
  - **SquareSection**
  - **TwoLinesGridSection**
  - **BigSquareSection**
  - **QueueSection**
- Utilizes the **Paging 3** library for efficient data loading.

### **SearchScreen**
- Implements search functionality with results fetched via the API.
- Uses **Flow** for real-time data updates.

---

## Testing

The project includes comprehensive testing:
- **Unit Tests**:
  - **JUnit** used for testing ViewModel and repository logic.
  - **MockK** used for mocking dependencies.

- **UI Tests**:
  - Created with **Compose UI Tooling**.
  - Tests validate the rendering of composables like `SquareSection` and `QueueSection`.
  - Example test cases include:
    - Rendering sections based on `SectionType`.
    - Handling invalid or empty data.

---

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/your-repo.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and ensure all dependencies are installed.
4. Run the app on an emulator or physical device

---

## Dependencies

Here’s a list of key dependencies used in the project:

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui:1.4.3")
implementation("androidx.compose.material:material:1.4.3")
implementation("androidx.compose.ui:ui-tooling:1.4.3")

// Dependency Injection
implementation("com.google.dagger:hilt-android:2.45")
kapt("com.google.dagger:hilt-compiler:2.45")

// Networking
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.okhttp3:okhttp:4.10.0")

// Paging
implementation("androidx.paging:paging-runtime:3.2.1")
implementation("androidx.paging:paging-compose:3.2.1")

// Testing
testImplementation("junit:junit:4.13.2")
androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
testImplementation("io.mockk:mockk:1.13.5")
testImplementation("com.appmattus.fixture:fixture:1.2.0")
```

---

## License

This project is licensed under the MIT License. You are free to use, modify, and distribute it.

---

## Author

Developed by **[Muhammad Helmi]([https://github.com/your-username](https://github.com/mhelmi))**.
