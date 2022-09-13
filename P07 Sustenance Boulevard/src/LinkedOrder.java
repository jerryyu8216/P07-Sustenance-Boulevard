//////////////// P07 Sustenance Boulevard //////////////////////////
//
// Title: LinkedOrder
// Course: CS 300 Fall 2020
//
// Author: Jerry Yu
// Email: jcyu4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class models and implements a LinkedOrder that contains an order given and links it to other
 * orders
 *
 */
public class LinkedOrder {
  private final Order ORDER; // the order the LinkedOrder carries
  private LinkedOrder previous; // the order in front of this order
  private LinkedOrder next; // the order behind this order

  /**
   * Creates a new LinkedOrder carrying the given order
   * 
   * @param order the order the LinkedOrder will be carrying
   */
  public LinkedOrder(Order order) {
    // initializes all instance fields
    this.previous = null;
    this.next = null;
    this.ORDER = order;
    // checks that the timestamp is valid
    Order n = new Order(null, 0);
    if (order.compareTo(n) == -1) {
      throw new IllegalArgumentException("Error: Timestamp is negative");
    }
  }

  /**
   * Creates a new LinkedOrder carrying the given order, the previous, and next orders
   * 
   * @param order the order the LinkedOrder will be carrying
   * @param prev  the previous LinkedOrder in the list
   * @param next  the next LinkedOrder in the list
   */
  public LinkedOrder(Order order, LinkedOrder prev, LinkedOrder next) {
    // initializes all instance fields
    this.ORDER = order;
    this.previous = prev;
    this.next = next;
    // checks that the timestamp is valid
    Order n = new Order(null, 0);
    if (order.compareTo(n) == -1) {
      throw new IllegalArgumentException("Error: Timestamp is negative");
    }
  }

  /**
   * A public accessor method for the private final instance field ORDER
   * 
   * @return the value of ORDER
   */
  public Order getOrder() {
    return ORDER;
  }

  /**
   * A public accessor method for the private instance field previous
   * 
   * @return the value of previous
   */
  public LinkedOrder getPrevious() {
    return previous;
  }
  /**
   * A public accessor method for the private instance field next
   * 
   * @return the value of next
   */
  public LinkedOrder getNext() {
    return next;
  }
  /**
   * A public setter method for the private instance field previous
   * 
   * @param the new value of previous
   */
  public void setPrevious(LinkedOrder previous) {
    this.previous = previous;
  }
  /**
   * A public setter method for the private instance field next
   * 
   * @param the new value of next
   */
  public void setNext(LinkedOrder next) {
    this.next = next;
  }
}
