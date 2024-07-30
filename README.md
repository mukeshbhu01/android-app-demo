# Demo app

## Overview
This is an app that provides a listing of images on search for query via using lexica.art API. The project is structured using MVI (Model-View-Intent) architecture combined with the CLEAN architecture principles. It leverages various modern Android development tools and libraries such as Retrofit, OkHttp, Flows(StateFlow) for managing state/intent within the ViewModel.

## Features
- Fetch and display a list of images based on search.
- View details of a selected image.
- Navigate between the list and detail screens.
- Handle state management using MVI architecture.

## Architecture
### MVI (Model-View-Intent) + CLEAN
The app follows the MVI architecture pattern to manage the state and events. The architecture ensures a unidirectional data flow and separation of concerns, making the app scalable and maintainable.

### Libraries and Tools Used
- **Retrofit**: For making HTTP requests to the API call.
- **OkHttp**: For networking.
- **Asynchronous Programming**: Coroutines + Flow.
- **StateFlows**: For managing and observing state in a lifecycle-aware manner.
- **Compose**: For Ui design.
- **Hilt**: For dependency injection.

### Running the App
- On launching the app, see the search box with no image.
- On enter search query and click on search icon, you will see a list of images.
- Clicking on any image will navigate you to the detail screen, where you can view the selected image in detail.

### UI Layer
- **HomeScreen**: Displays the list of images in grid view(2 items).
- **DetailScreen**: Displays details of the selected image.

### ViewModel Layer
- **HomeViewModel**: Handles the app ui data state for the Home and Detail screens.

### Model Layer
- **ImageModelDto**: Data class representing the response from the API.
- **ImageResponseModel**: Data class representing the response for specific use case.

### Intent and State
- **HomeScreenIntent**: Represents user actions.
- **HomeScreenState**: Represents the state of the UI.

### Permissions
- Internet : android.permission.INTERNET - For api call
- Network state: android.permission.ACCESS_NETWORK_STATE - For network status

## API
- https://lexica.art/ for providing the API service.