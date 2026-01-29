<p align="center">
  <img
    src="https://github.com/user-attachments/assets/2f08acf5-2049-4f86-8c17-edcb846598b1"
    alt="Android Developer Illustration"
    width="600"
    />
</p>

# New-Zealand-Guide

New Zealand Guide is an Android travel guide app for New Zealand, built with Kotlin and Jetpack Compose.
It provides users with information about cities, events, and weather, featuring local caching and smooth incremental data loading.

<a href="https://play.google.com/store/apps/details?id=denys.diomaxius.newzealandguide&hl=en">
  <img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" width="200"/>
</a>

## Screenshots
<img width="31%" height="625" alt="Screenshot#1"  src="https://github.com/user-attachments/assets/56b29edd-775d-4943-ae71-6692ce7b7ee8" />
<img width="31%" height="625" alt="Screenshot#2" src="https://github.com/user-attachments/assets/f56714df-f6dc-4df0-9cca-006f5304fcf2" />
<img width="31%" height="625" alt="Screenshot#3" src="https://github.com/user-attachments/assets/933416b0-c5fd-4079-a6cd-c61032b3518a" />

## Project Overview

- **City & Event Listings**: Users can browse cities and events happening in them.  
- **Incremental Data Loading**: Events are loaded **as the user scrolls**, reducing server load and saving bandwidth.  
- **Local Caching with Room**: Data is cached locally for offline use and faster UI response.  
- **Weather Information**: Displays forecasts for selected cities.  
- **Useful Māori Phrases**: Fun facts and basic local language phrases.

---

## Tech Stack

- **Android**: Jetpack Compose, Room, RemoteMediator, Hilt, Paging 3, Coil, MVVM  
- **Backend**: Firebase Firestore, Firebase Functions, App Check

---

## Implementation Highlights

- **RemoteMediator + Paging 3**: Events are loaded incrementally, ensuring smooth scrolling and minimal server requests.  
- **Room**: Maintains a local copy of data for fast access and offline functionality.  
- **Firebase**: Stores up-to-date city info, events, and media while keeping network requests optimized.  
- **Serverless Integrations**: Weather and events APIs are invoked via Firebase Cloud Functions.  
- **Secure Access**: Firebase App Check ensures that only the official app (from Google Play or authorized debug devices) can access the server.  
- **Modern Android Stack**: Full MVVM architecture, dependency injection with Hilt, and Jetpack Compose UI.
- **Offline-first**: Cached data ensures the app works smoothly even without internet.

---

## Note
* New-Zealand-Guide is currently under active development. Features and UI may change as the app evolves.
* Simply copying this repository will **not** grant access to backend data.

## License

This project is licensed under the [MIT License](./LICENSE).
