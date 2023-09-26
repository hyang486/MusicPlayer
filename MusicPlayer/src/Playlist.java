//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MusicPlayer - Playlist
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
import java.util.Scanner;

/**
 * A FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 * 
 * @author JeeYoun Jung, Hyeonmin Yang
 *
 */
public class Playlist implements QueueADT<Song> {
  private SongNode first; // The current first song in the queue; the next one up to play (front of
                          // the queue)
  private SongNode last; // The current last song in the queue; the most-recently added one (back of
                         // the queue)
  private int numSongs; // The number of songs currently in the queue

  /**
   * Constructs a new, empty playlist queue
   * <p>
   * 
   * @param no parameter
   * @return no return value
   * 
   */
  public Playlist() {
    first = null;
    last = null;
    numSongs = 0;
  }

  /**
   * Adds a new song to the end of the queue
   * <p>
   * 
   * @param element - the song to add to the Playlist
   * @return no return value
   * 
   */
  public void enqueue(Song element) {
    SongNode node = new SongNode(element);
    // if queue is empty
    if (isEmpty()) {
      // point first with node
      first = node;
      // last first with node
      last = node;
      // increase the number of songs
      numSongs++;
    } else {
      // if queue is not an empty, then set the node at the last part of queue.
      last.setNext(node);
      // add number of song
      numSongs++;
      // linked last to the node
      last = node;
    }

  }

  /**
   * Removes the song from the beginning of the queue
   * <p>
   * 
   * @param no parameter
   * @return the song that was removed from the queue, or null if the queue is empty
   * 
   */
  public Song dequeue() {
    // if queue is empty
    if (isEmpty() || first == null) {
      return null;
    }
    // if there is only one song
    if (first == last) {
      Song justForReturnFirstNode = first.getSong();
      // make first to null
      first = null;
      // make last to null
      last = null;
      // decrease the size
      numSongs--;
      return justForReturnFirstNode;
    } else {
      // keep the first song in the removalFirstNode
      Song removalFirstNode = first.getSong();
      // mark next song as the first
      first = first.getNext();
      // remove number of song
      numSongs--;
      return removalFirstNode;
    }
  }

  /**
   * Returns the song at the front of the queue without removing it
   * <p>
   * 
   * @param no parameter
   * @return the song that is at the front of the queue, or null if the queue is empty
   * 
   */
  public Song peek() {
    // if there is not node to peek, return null
    if (isEmpty()) {
      return null;
    }
    // otherwise, return the first node.
    return this.first.getSong();
  }

  /**
   * Returns true if and only if there are no songs in this queue
   * <p>
   * 
   * @param no parameter
   * @return true if this queue is empty, false otherwise
   * 
   */
  public boolean isEmpty() {
    if (numSongs == 0 && first == null && last == null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the number of songs in this queue
   * <p>
   * 
   * @param no parameter
   * @return the number of songs in this queue
   * 
   */
  public int size() {
    return numSongs;
  }

  /**
   * Creates and returns a formatted string representation of this playlist, with the string version
   * of each song in the list on a separate line. For example: "He's A Pirate" (1:21) by Klaus
   * Badelt "Africa" (4:16) by Toto "Waterloo" (2:45) by ABBA "All I Want For Christmas Is You"
   * (4:10) by Mariah Carey "Sandstorm" (5:41) by Darude "Never Gonna Give You Up" (3:40) by Rick
   * Astley
   * <p>
   * 
   * @param no parameter
   * @return the string representation of this playlist
   * 
   */
  @Override
  public String toString() {
    // make empty string
    String strPlaylist = "";
    // if list is empty, then return empty string
    if (isEmpty()) {
      return strPlaylist;
    }
    // if there is only one song in the playlist it will return one song
    if (numSongs == 1) {
      strPlaylist += first.getSong().toString();
      return strPlaylist;
    } else { // if list has more then one element
      // set the first as temp
      SongNode temp = first;
      // iterate while loop until next node is null
      while (temp.getNext() != null) {
        // store each element with string formatting
        strPlaylist += temp.getSong().toString() + "\n";
        // then move temp to next node for next iteration
        temp = temp.getNext();
      }
      // add the last node in the playlist
      strPlaylist += temp.getSong().toString();
      return strPlaylist;
    }
  }

}
