# WeatherApp
 
WeatherApp is a mobile application that provides weather information for various cities. Users can search for cities, view the current weather conditions, and get details such as temperature, humidity, wind speed, and more.

<br>

# Table of Contents # 

- [WeatherApp](#weatherapp)
- [Table of Contents](#table-of-contents)
- [Usage](#usage)
- [Features](#features)
- [Libraries Used](#libraries-used)
- [Installation](#installation)
- [Contributing](#contributing)
- [License](#license)

<br>

# Usage
The project uses Retrofit to make a GET request to the [OpenWeather](https://openweathermap.org/api) API and retrieve the weather data for the selected city. The data for the selected city is then displayed inside Home Fragment. User can also select a city from the City Selection Fragment.

To use this project, simply clone or download this repository, and open it in Android Studio. Run the project and you should see current weather for the your selected city.

<br>

# Features

- Search for cities and view their weather information.
- Display current weather conditions, including temperature, humidity, wind speed, and more.
- Update weather data in real-time.
- User-friendly interface with a clean and intuitive design.

<br>

# Libraries Used

The WeatherApp project utilizes the following libraries:

- **Gson**: A Java library for converting Java Objects into JSON representation and vice versa. It is used for parsing JSON data from the weather API response.

- **Retrofit**: A type-safe HTTP client for Android and Java. It simplifies the process of making API requests and handling responses. Retrofit is used for network communication with the weather API.

- **Dagger Hilt**: Dagger Hilt is a dependency injection library for Android that reduces boilerplate code and makes dependency injection easier to implement. It is used for managing dependencies in the WeatherApp project.

<br>

# Installation

To run the WeatherApp project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/Keremerk/weatherapp.git`
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.

Make sure you have the necessary dependencies and SDK versions installed.

<br>

# Contributing
If you find any issues with the code, feel free to open an issue or submit a pull request. I welcome any contributions and feedback on how to improve the project.

<br>

# License

This project is licensed under the [MIT License](LICENSE).
