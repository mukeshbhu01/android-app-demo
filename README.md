# Demo app

## Overview
This is an app that provides a listing of images on search for query via using lexica.art API. The project is structured using MVI (Model-View-Intent) architecture combined with the CLEAN architecture principles. It leverages various modern Android development tools and libraries such as Retrofit, OkHttp, Flows(StateFlow/SharedFlow) for managing state/intent within the ViewModel.

# Tech Stack and Architecture

## Application Modules:

**App Module**: The entry point of the application. It contains the `AppDemoApplication` class which is responsible for initializing the overall app components.

**Presentation Module**: Holds the UI components, ViewModels, and contracts defining the communication between the UI and ViewModel layers. It utilizes the MVI architecture pattern for a clear separation of concerns.

**Data Module**: Contains the API service definitions, data sources, data transfer objects (DTOs), and repositories which abstract the data layer. It's responsible for network calls and data delivery.

**Domain Module**: Defines the use cases, business models, and repository interfaces which encapsulate the business logic of the application.

**network Module**: Includes network configuration, dependency required for fetch network resources.

## Tech Stack:

**Kotlin**: The primary programming language used in the application.

**Coroutines & Flow**: For asynchronous operations and data state handling.

**Retrofit**: As the HTTP client for making network requests.

**Dagger-Hilt**: For dependency injection to provide required classes and manage object lifecycles.

**Jetpack Compose**: The modern toolkit for building native UI.

**ViewModel**: To manage UI-related data in a lifecycle-conscious way.

**Navigation Component**: For handling in-app navigation.

**JUnit & MockK**: For unit testing the application components.

**Turbine**: To test Kotlin Flows.

## Architecture:

- **MVI (Model-View-Intent)**: The architectural pattern used in the Presentation module for decoupling UI code from business logic.

- **Clean Architecture**: The overall architectural approach to separate code into layers with distinct roles and dependencies, making the code more modular, scalable, and testable.


## API

- https://lexica.art/ for providing the API service.
