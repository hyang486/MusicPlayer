//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MusicPlayer - SongNode
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
 * A singly-linked node for our linked queue, which contains a Song object
 * 
 * @author JeeYoun Jung, Hyeonmin Yang
 *
 */
public class SongNode {
  private Song song;
  private SongNode next;

  /**
   * Constructs a single SongNode containing the given data, not linked to any other SongNodes
   * <p>
   * 
   * @param data - the Song for this node
   * @return no return value
   * @throws IllegalArgumentException - if data is null
   */
  public SongNode(Song data) throws IllegalArgumentException {
    // if data is null then throw an IllegalArgumentException
    if (data == null) {
      throw new IllegalArgumentException("data is null");
    }
    this.song = data;
  }

  /**
   * Constructs a single SongNode containing the given data, linked to the specified SongNode
   * <p>
   * 
   * @param data - the Song for this node next - the next node in the queue
   * @return no return value
   * @throws IllegalArgumentException - if data is null
   * 
   */
  public SongNode(Song data, SongNode next) throws IllegalArgumentException {
    // if data is null then throw an IllegalArgumentException
    if (data == null) {
      throw new IllegalArgumentException("data is null");
    }
    this.song = data;
    this.next = next;
  }

  /**
   * Accessor method for this node's data
   * <p>
   * 
   * @param no parameter
   * @return the Song in this node
   * 
   */
  public Song getSong() {
    return this.song;
  }

  /**
   * Accessor method for the next node in the queue
   * <p>
   * 
   * @param no parameter
   * @returnthe SongNode following this one, if any
   * 
   */
  public SongNode getNext() {
    // if next is null then return null
    if (this.next == null) {
      return null;
    }
    return next;
  }

  /**
   * Changes the value of this SongNode's next data field to the given value
   * <p>
   * 
   * @param next - the SongNode to follow this one; may be null
   * @return no return value
   * 
   */
  public void setNext(SongNode next) {
    this.next = next;
  }

}
