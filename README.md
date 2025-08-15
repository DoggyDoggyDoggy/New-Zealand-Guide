<p align="center">
  <img
    src="https://github.com/user-attachments/assets/2f08acf5-2049-4f86-8c17-edcb846598b1"
    alt="Android Developer Illustration"
    width="600"
    />
</p>

# New-Zealand-Guide

Next-generation NZGuide app reimagined with a cloud-driven architecture powered by Firebase. All city, events, and weather data are now stored and served via Firebase services, with third-party API integrations handled by server-side functions. The Android client fetches and displays this data, resulting in a lighter, faster, and more maintainable application.

**🛠️ Note:** New-Zealand-Guide is currently under active development. Features and UI may change as the app evolves.

## Screenshots

<img width="31%" height="625" alt="Screenshot#1" src="https://github.com/user-attachments/assets/d89595a5-6efb-48cf-bf95-0ea758ac71be" />
<img width="31%" height="625" alt="Screenshot#2" src="https://github.com/user-attachments/assets/540d036f-2304-424c-9749-af6fcc1afebd" />
<img width="31%" height="625" alt="Screenshot#3" src="https://github.com/user-attachments/assets/0342f5be-dc38-40d2-aca2-fc8309c72f9b" />

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
