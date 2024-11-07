# Vibrate on Button Tap using Jetpack Compose

## Overview

This document provides a guide on implementing device vibration upon tapping a button using **Jetpack Compose*** and the  **Android Framework's ```VibratorManager```**. The code snippet demonstrates two approaches for vibration feedback: Jetpack Compose’s ```HapticFeedback``` and the Android Framework’s ```VibratorManager```.

## Requirements
- **Minimum SDK**: API Level 31
- **Jetpack Compose** is required.
  
## Add the VIBRATE permission
The vibrate method of Android Framework’s ```VibratorManager``` requires the ```VIBRATE``` permission. The permission can be added to the application manifest file.

```xml
<uses-permission android:name= "android.permission.VIBRATE" />
```
Note: This permission is not required if using the ```View.performHapticFeedback``` method to generate haptic feedback.

## Code Implementation

```kotlin
@Composable
fun VibrationCookbook() {
    Column(
    ...
    ) {
	  // Vibrate using LocalHapticFeedback
        val haptic = LocalHapticFeedback.current
        Button(
            onClick = { haptic.performHapticFeedback(HapticFeedbackType.LongPress) }
        ) {
            Text("Long Press Vibration")
          }

	  // Vibrate using VibratorManager
        val context = LocalContext.current
        val vibrator = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        val combinedVibration = CombinedVibration.createParallel(vibrationEffect)

        Button(
            onClick = { vibrator.vibrate(combinedVibration) }
        ) {
            Text("System Service Vibration")
          }

       }
}
```
## Key Points
### UI Structure:
```VibrationCookbook```: Defines UI components for buttons to perform two different vibration techniques:
- **Jetpack Compose Haptic Feedback**:
Uses ```LocalHapticFeedback``` to create a long-press vibration with ```HapticFeedbackType.LongPress```. 
- **Android Framework VibratorManager**:
  - Uses ```VibratorManager``` to create a predefined vibration such as ```VibrationEffect.EFFECT_CLICK```.
  - **Caution**: We strongly discourage using older Vibrator methods employing ```createOneshot``` or ```createWaveform```, even if they appear to be supported on the device. These modes are often too loud for regular haptic feedback; use them only as a fallback if you need to highlight an extremely important action.

## Running the Code
- Clone the repository from GitHub: https://github.com/HCLDevTeam/VibrationComposeCUJ
- Open the project in **Android Studio**.
- Connect a physical device or launch an emulator.
- Click the **Run** button to start the app.
  
This will compile and launch the app, allowing you to test the vibration functionality on button taps.                                         
              
