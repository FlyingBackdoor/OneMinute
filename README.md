# OneMinute
[![N|Solid](https://raw.githubusercontent.com/github/explore/ae48d1ca3274c0c3a90f872e605eaef069a16771/topics/jetpack-compose/jetpack-compose.png)](#)
## _A project to showcase jetpack compose UI and state management_

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

This project demonstrates how to create a simple timer app that runs for one minute and displays a circular progress bar that shrinks as the time elapses.
When the timer reaches zero, the app sends a notification to the user until they return to the app or close it.

## Screenshot
![image](https://github.com/FlyingBackdoor/OneMinute/assets/31269655/4859055e-097d-4a3f-be91-13b86fb416c4)


## Libraries used:
- Jetpack compose
- Material3
- Lifecycle
- Koin for dependency injection

## Build a debug apk
```sh
gradlew assembleDebug
```

## Project Strructure

```sh
|   AndroidManifest.xml
|
+---java
|   \---dev
|       \---sohair
|           \---oneminute
|               |   MainActivity.kt
|               |   TimerApp.kt
|               |
|               +---data
|               |       TimerState.kt
|               |
|               +---di
|               |       AppModules.kt
|               |
|               +---ui
|               |   +---components
|               |   |       HomeScreen.kt
|               |   |       HomeScreenViewModel.kt
|               |   |       TimerUi.kt
|               |   |
|               |   \---theme
|               |           Color.kt
|               |           Theme.kt
|               |           Type.kt
|               |
|               \---utils
|                       NotificationUtils.kt
|
\---res
    +---drawable
    |       ic_launcher_background.xml
    |       ic_launcher_foreground.xml
    |
    +---mipmap-anydpi-v26
    |       ic_launcher.xml
    |       ic_launcher_round.xml
    |
    +---mipmap-hdpi
    |       ic_launcher.webp
    |       ic_launcher_round.webp
    |
    +---mipmap-mdpi
    |       ic_launcher.webp
    |       ic_launcher_round.webp
    |
    +---mipmap-xhdpi
    |       ic_launcher.webp
    |       ic_launcher_round.webp
    |
    +---mipmap-xxhdpi
    |       ic_launcher.webp
    |       ic_launcher_round.webp
    |
    +---mipmap-xxxhdpi
    |       ic_launcher.webp
    |       ic_launcher_round.webp
    |
    +---values
    |       colors.xml
    |       strings.xml
    |       themes.xml
    |
    \---xml
            backup_rules.xml
            data_extraction_rules.xml


```
