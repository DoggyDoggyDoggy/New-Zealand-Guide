# New-Zealand-Guide

Next-generation NZGuide app reimagined with a cloud-driven architecture powered by Firebase. All city, events, and weather data are now stored and served via Firebase services, with third-party API integrations handled by server-side functions. The Android client fetches and displays this data, resulting in a lighter, faster, and more maintainable application.

**🛠️ Note:** New-Zealand-Guide is currently under active development. Features and UI may change as the app evolves.

## Project Overview

New-Zealand-Guide is a complete rebuild of the original [NZGuide](https://github.com/DoggyDoggyDoggy/NZGuide), shifting all data storage and external API calls to Firebase:

* **Server-side data storage**: Cities, events, and weather are managed in Firestore and Cloud Storage.
* **Serverless integrations**: Weather and events APIs are invoked via Firebase Cloud Functions.
* **Client-focused UI**: The Android app (Jetpack Compose) only reads and displays data from Firebase, eliminating direct calls to external services.

---

## Features

* **City Guide**: Explore New Zealand cities with descriptions, photos, and travel tips.
* **Weather Data**: Fresh weather updates pulled and cached via Cloud Functions.
* **Event Listings**: Up-to-date local events stored in Firestore.
* **Offline Ready**: Firestore’s offline persistence for core data.
* **Secure by Design**: API keys are stored server-side; no secrets in the client.

---

## Tech Stack

* **Android**: Kotlin, Jetpack Compose, MVVM
* **Backend as a Service**: Firebase Firestore, Сloudinary Storage, Firebase Functions

## License

This project is licensed under the [MIT License](./LICENSE).
