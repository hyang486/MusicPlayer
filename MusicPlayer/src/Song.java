//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MusicPlayer - Song
// Course: CS 300 Fall 2022
//
// Author: JeeYoun Jung
// Email: jjung83@wisc.edu
// Lecturer: Hobbes LeGault
//
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Hyeonmin Yang
// Partner Email: hyang486@wisc.edu
// Lecturer: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _x_ Write-up states that pair programming is allowed for this assignment.
// _x_ We have both read and understand the course Pair Programming Policy.
// _x_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: TA
// Online Sources:
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A representation of a single Song. Interfaces with the provided AudioUtility class, which uses
 * the javax.sound.sampled package to play audio to your computer's audio output device
 * 
 * @author JeeYoung Jung, Hyeonmin Yang
 *
 */
public class Song {
  private String title; // The title of this song
  private String artist; // The artist of this song
  private int duration; // The duration of this song in number of seconds
  private AudioUtility audioClip; // This song's AudioUtility interface to avax.sound.sampled

  /**
   * Initializes all instance data fields according to the provided values
   * <p>
   * 
   * @param title - the title of the song, set to empty string if null artist - the artist of this
   *              song, set to empty string if null filepath - the full relative path to the song
   *              file, begins with the "audio" directory for P08
   * @return no return value
   * @throws IllegalArgumentException - if the song file cannot be read
   * 
   */
  public Song(String title, String artist, String filepath) throws IllegalArgumentException {

    // if title and artist is null --> set to empty string
    if (title == null) {
      title = "";
    }
    if (artist == null) {
      artist = "";
    }

    // else set the title and the artist of the song
    this.title = title;
    this.artist = artist;

    // if filepath is null -> throw an IllegalArgumentException
    if (filepath == null) {
      throw new IllegalArgumentException("Cannot read the song file.");
    }

    try {
      // set the songfile's path
      this.audioClip = new AudioUtility(filepath);
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot read the song file.");
    }

    // set the duration
    this.duration = this.audioClip.getClipLength();

  }

  /**
   * Tests whether this song is currently playing using the AudioUtility
   * <p>
   * 
   * @param no parameter
   * @return true if the song is playing, false otherwise
   * 
   */
  public boolean isPlaying() {
    if (audioClip.isRunning()) {
      return true;
    }
    return false;
  }

  /**
   * Accessor method for the song's title
   * <p>
   * 
   * @param no parameter
   * @return the title of this song
   * 
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Accessor method for the song's artist
   * <p>
   * 
   * @param no parameter
   * @return the artist of this song
   * 
   */
  public String getArtist() {
    return this.artist;
  }

  /**
   * Uses the AudioUtility to start playback of this song, reopening the clip for playback if
   * necessary
   * <p>
   * 
   * @param no parameter
   * @return no return value
   * 
   */
  public void play() {
    // need to check once again
    if (!audioClip.isReadyToPlay()) {
      audioClip.reopenClip();
    }
    audioClip.startClip();
    System.out.println("Playing " + this.toString());
  }

  /**
   * Uses the AudioUtility to stop playback of this song
   * <p>
   * 
   * @param no parameter
   * @return no return value
   * 
   */
  public void stop() {
    audioClip.stopClip();
  }

  /**
   * Creates and returns a string representation of this Song, for example: "Africa" (4:16) by Toto
   * The title should be in quotes, the duration should be split out into minutes and seconds
   * (recall it is stored as seconds only!), and the artist should be preceded by the word "by". It
   * is intended for this assignment to leave single-digit seconds represented as 0:6, for example,
   * but if you would like to represent them as 0:06, this is also allowed.
   * <p>
   * 
   * @param no parameter
   * @return a formatted string representation of this Song
   * 
   */
  @Override
  public String toString() {

    // modify duration as second and minute
    // if we have x seconds, then we can get y min z seconds with
    // y = (x / 60)
    // z = (x % 60)
    int secDuration = this.duration % 60;
    int minDuration = (this.duration / 60) % 60;

    String aboutTheSong =
        "\"" + title + "\" (" + minDuration + ":" + secDuration + ")" + " by " + artist;
    return aboutTheSong;
  }

}
