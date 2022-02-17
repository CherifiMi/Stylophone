<h1 align="center">Stylophone</h1>

<p align="center">  
  this app is a synthesizer app and an a musical notes Oscilloscope.
</p>
</br>

![Frame 3](https://user-images.githubusercontent.com/98290339/154515965-97cf0cdd-d2e9-4781-bec6-164952d22226.svg)

#### Ui Made in [Figma](https://www.figma.com/file/mtCF10n9wphsv1JdKG00uQ?embed_host=share&kind=&node-id=276%3A243&viewer=1)



## Tech stack & Open-source libraries

- Minimum SDK level 26
- Architecture
  -MVVM Architecture (View - ViewBinding - ViewModel - Model)
- (Processing)[https://processing.org/] for the [Sine Waves Oscilloscope](https://github.com/CherifiMi/sine-waves)
- [Audio Track](https://developer.android.com/reference/android/media/AudioTrack)
- [Full screen mode](https://developer.android.com/training/system-ui/immersive)
- [setOnTouchListener](https://developer.android.com/reference/android/view/View.OnTouchListener)
- [landscape mode](https://developer.android.com/reference/androidx/browser/trusted/ScreenOrientation)



## What I learned
- using [Audio Track](https://developer.android.com/reference/android/media/AudioTrack) to creat sound from a sine wave
- using [setOnTouchListener](https://developer.android.com/reference/android/view/View.OnTouchListener) to detect if the btn is pressed or released
- using [Full screen mode](https://developer.android.com/training/system-ui/immersive) and hiding system and navigation bars
- setting the app to be only in [landscape mode](https://developer.android.com/reference/androidx/browser/trusted/ScreenOrientation)


## Final app




## Architecture
Stylophone is based on the MVVM architecture and the Repository pattern.
![image](https://user-images.githubusercontent.com/98290339/152096381-2a8898d3-c351-4032-979d-ebc836e46332.png)
