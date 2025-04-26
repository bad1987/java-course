/**
 * Exercise 3: Media Player with Interfaces
 * 
 * Instructions:
 * 1. Create the following interfaces:
 *    - Playable: with methods play(), pause(), stop()
 *    - Recordable: with methods startRecording(), stopRecording()
 *    - Displayable: with methods display(), setResolution(int width, int height)
 * 
 * 2. Create the following classes that implement one or more interfaces:
 *    - AudioPlayer: implements Playable
 *      * Properties: filename, currentPosition, volume
 *      * Implements all required methods for audio playback
 * 
 *    - VideoPlayer: implements Playable, Displayable
 *      * Properties: filename, currentPosition, volume, width, height
 *      * Implements all required methods for video playback and display
 * 
 *    - VoiceRecorder: implements Recordable, Playable
 *      * Properties: recordingName, recordingLength, isRecording
 *      * Implements all required methods for recording and playback
 * 
 *    - Camera: implements Recordable, Displayable
 *      * Properties: model, resolution, isRecording
 *      * Implements all required methods for recording and display
 * 
 * 3. Create a MediaController class with the following static methods:
 *    - playMedia(Playable media): plays any playable media
 *    - recordMedia(Recordable media): starts recording on any recordable media
 *    - displayMedia(Displayable media): displays any displayable media
 * 
 * 4. In the main method:
 *    - Create instances of each media class
 *    - Use the MediaController to control different types of media
 *    - Demonstrate polymorphism by using interface references
 *    - Create an array of Playable objects containing different media types
 */
public class Exercise3 {
    public static void main(String[] args) {
        // TODO: Create instances of different media classes
        
        // TODO: Use the MediaController to control different media
        
        // TODO: Demonstrate polymorphism with interface references
        
        // TODO: Create and use an array of Playable objects
    }
}

// TODO: Create the Playable interface

// TODO: Create the Recordable interface

// TODO: Create the Displayable interface

// TODO: Create the AudioPlayer class

// TODO: Create the VideoPlayer class

// TODO: Create the VoiceRecorder class

// TODO: Create the Camera class

// TODO: Create the MediaController class
