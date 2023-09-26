//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MusicPlayer - MusicPlayer300
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
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in an
 * interactive console method. This music player can load playlists of music or add individual song
 * files to the queue.
 * 
 * @author JeeYoun Jung, Hyeonmin Yang
 */
public class MusicPlayer300 {
  private Playlist playlist; // The current playlist of Songs
  private boolean filterPlay; // Whether the current playback mode should be filtered by artist;
                              // false by default
  private String filterArtist; // The artist to play if filterPlay is true; should be null otherwise

  /**
   * Creates a new MusicPlayer300 with an empty playlist
   * <p>
   * 
   * @param no parameter
   * @return no return value
   * 
   */
  public MusicPlayer300() {
    filterPlay = false;
    filterArtist = null;
    playlist = new Playlist();
  }

  /**
   * Stops any song that is playing and clears out the playlist
   * <p>
   * 
   * @param no parameter
   * @return no return value
   * 
   */
  public void clear() {
    playlist = new Playlist();
  }

  /**
   * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded.
   * Note that filenames in the provided files do NOT include the audio directory, and will need
   * that added before they are loaded. Print "Loading" and the song's title in quotes before you
   * begin loading a song, and an "X" if the load was unsuccessful for any reason.
   * <p>
   * 
   * @param file - the File object to load
   * @return no return value
   * @throws FileNotFoundException - if the playlist file cannot be loaded
   * 
   */
  public void loadPlaylist(File file) throws FileNotFoundException {
    if (file == null || !file.exists()) {
      throw new FileNotFoundException("playlist file cannot be loaded.");
    }
    // scan the song file
    Scanner scanSongFile = new Scanner(file);
    while (scanSongFile.hasNextLine()) {
      String curr = scanSongFile.nextLine();
      String[] songArray = curr.split(",");
      System.out.println("Loading \"" + songArray[0] + "\"");
      // if load was unsuccessful for any reason print X and throw an exception
      try {
        loadOneSong(songArray[0], songArray[1], "audio" + File.separator + songArray[2]);
      } catch (Exception e) {
        System.out.println("X");
      }
    }


  }

  /**
   * Loads a single song to the end of the playlist given the title, artist, and filepath. Filepaths
   * for P08 must refer to files in the audio directory.
   * <p>
   * 
   * @param title - the title of the song artist - the artist of this song filepath - the full
   *              relative path to the song file, begins with the "audio" directory for P08
   * @return no return value
   * @throws IllegalArgumentException - if the song file cannot be read
   * 
   */
  public void loadOneSong(String title, String artist, String filepath) {
    // throw an IllegalArgumentException if file cannot be read
    if (filepath == null || filepath.isBlank()) {
      throw new IllegalArgumentException("Cannot read the songfile.");
    }
    // load songlist
    Song loadingSong = new Song(title, artist, filepath);
    playlist.enqueue(loadingSong);

  }

  /**
   * Provides a string representation of all songs in the current playlist
   * <p>
   * 
   * @param no parameter
   * @return a string representation of all songs in the current playlist
   * 
   */
  public String printPlaylist() {
    // make empty string and add the playlist's string in the string
    String keepAllPlaylist = "";
    keepAllPlaylist += playlist.toString();
    return keepAllPlaylist;
  }

  /**
   * Creates and returns the menu of options for the interactive console program.
   * <p>
   * 
   * @param no parameter
   * @return the formatted menu String
   * 
   */
  public String getMenu() {

    String menuOfTheSong = ("Enter one of the following options:\r\n"
        + "[A <filename>] to enqueue a new song file to the end of this playlist\r\n"
        + "[F <filename>] to load a new playlist from the given file\r\n"
        + "[L] to list all songs in the current playlist\r\n"
        + "[P] to start playing ALL songs in the playlist from the beginning\r\n"
        + "[P -t <Title>] to play all songs in the playlist starting from <Title>\r\n"
        + "[P -a <Artist>] to start playing only the songs in the playlist by Artist\r\n"
        + "[N] to play the next song\r\n" + "[Q] to stop playing music and quit the program");

    return menuOfTheSong;

  }

  /**
   * Stops playback of the current song (if one is playing) and advances to the next song in the
   * playlist.
   * <p>
   * 
   * @param no parameter
   * @return no return value
   * @throws IllegalStateException - if the playlist is null or empty, or becomes empty at any time
   *                               during this method
   * 
   */
  public void playNextSong() throws IllegalStateException {
    // throw if the playlist is empty.
    if (playlist == null || playlist.isEmpty()) {
      throw new IllegalStateException("This playlist is null or empty.");
    }

    // if the playlist is playing the song
    if (playlist.peek().isPlaying()) {
      // first, stop the song
      playlist.peek().stop();
    }

    // second, delete that song
    playlist.dequeue();

    // if filterplay is true compare the artist between playlist and filterartist
    if (filterPlay == true) {
      while (!playlist.isEmpty() && !filterArtist.equals(playlist.peek().getArtist())) {
        playlist.dequeue();
      }
    }

    // if playlist is not empty play the song
    if (!playlist.isEmpty()) {
      playlist.peek().play();
    } else {
      throw new IllegalStateException("This playlist is null or empty.");
    }

  }

  /**
   * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user. See
   * writeup for details.
   * <p>
   * 
   * @param no parameter
   * @return no return value
   * 
   */
  public void runMusicPlayer300(Scanner in) {
    String option = ""; // initialize option
    System.out.println(getMenu());
    System.out.print("> ");

    while (option != "Q") {

      option = in.nextLine();

      // to enqueue a new song file to the end of this playlist
      if (option.startsWith("A")) {

        // ask about the title and the artist
        String filePathString = option.substring(2);
        System.out.print("title: ");
        String titleString = in.nextLine();
        System.out.print("artist: ");
        String artistString = in.nextLine();

        // try to add song in the list, or throw an exception
        try {
          Song theLastAddSong =
              new Song(titleString, artistString, filePathString);
          playlist.enqueue(theLastAddSong);
        } catch (IllegalArgumentException e) {
          System.out.println("Unable to load that song");
        }
        System.out.println(getMenu());
        System.out.print("> ");

      }

      // to load a new playlist from the given file
      else if (option.startsWith("F")) {
        String fileString = option.substring(2);
        File loadFile = new File(fileString);
        try {
          this.loadPlaylist(loadFile);
        } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
          System.out.println("Unable to load the file");
          e.printStackTrace();
        }
        System.out.println(getMenu());
        System.out.print("> ");

      }

      // to list all songs in the current playlist
      else if (option.startsWith("L")) {
        System.out.println(printPlaylist());
        System.out.println(getMenu());
        System.out.print("> ");

      }

      // [P -t <Title>] to play all songs in the playlist starting from <Title>
      else if (option.startsWith("P -t")) {
        String titleString = option.substring(5);

        for (int i = 0; i < playlist.size(); i++) {
          // Check there is the exact same song title in the playlist or not
          // Song findTheSong = new Song(titleString, "", "");
          // if the song title is different it will dequeue
          if (!playlist.peek().getTitle().equals(titleString)) {
            playlist.dequeue();
          }
          if (playlist.peek().getTitle().equals(titleString)) {
            Song newSong = playlist.peek();
            newSong.play(); // play new song
            break;
          } else {
            System.out.println("Unable to load that song");
          }
        }
        System.out.println(getMenu());
        System.out.print("> ");

      }

      // [P -a <Artist>] to start playing only the songs in the playlist by Artist
      else if (option.startsWith("P -a")) {
        // filterArtist = in.nextLine().split(" ")[2];
        filterArtist = option.substring(5);
        // Check there is the exact same artist in the playlist or not
        for (int i = 0; i < playlist.size(); i++) {

          // if the artist is different it will dequeue
          if (!playlist.peek().getArtist().equals(filterArtist)) {
            playlist.dequeue();
          } else {
            // if there is it will start the song
            filterPlay = true;
            Song newSong = playlist.peek();
            newSong.play();
            if (playlist.size() == 0) {
              System.out.println("Unable to load that song");
              filterPlay = false;
              filterArtist = null;
            }
            break;
          }
        }
        System.out.println(getMenu());
        System.out.print("> ");

      }

      // to start playing ALL songs in the playlist from the beginning
      else if (option.startsWith("P")) {

        // if the playlist is null, it will print No songs left :(
        if (playlist == null || playlist.size() == 0) {
          System.out.println("No songs left :(");
        }

        if (playlist.size() != 0) {
          // otherwise it will play all the song in the playlist
          playlist.peek().play();
        }



        System.out.println(getMenu());
        System.out.print("> ");


      }

      // [N] to play the next song
      else if (option.startsWith("N")) {

        try {
          playNextSong();
          System.out.println(getMenu());
          System.out.print("> ");

        } catch (Exception e) {
          System.out.println("No songs left :(");
          System.out.println(getMenu());
          System.out.print("> ");

        }
      }

      // to stop playing music and quit the program
      else if (option.startsWith("Q")) {
        clear();
        System.out.println("Goodbye!");
        return;
      }
      // else letters will print I don't know how to do that.
      else {
        System.out.println("I don't know how to do that.");
        System.out.println(getMenu());
        System.out.print("> ");


      }
    }

  }


  /**
   * Main method
   * 
   * @param args - list of input arguments if any
   */
  public static void main(String[] args) {

    MusicPlayer300 test = new MusicPlayer300();
    test.runMusicPlayer300(new Scanner(System.in));

  }

}


