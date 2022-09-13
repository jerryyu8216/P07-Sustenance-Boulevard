//////////////// P07 Sustenance Boulevard //////////////////////////
//
// Title: RestaurantOrders
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
 * This class implements the SortedListADT interface and utilizes it to create a LinkedList that
 * carries all of the restaurant's orders. The list can then be manipulated with the methods defined
 * within this class
 *
 */
public class RestaurantOrders implements SortedListADT<Order> {
  private LinkedOrder head; // the first value in the list
  private LinkedOrder tail; // the last value in the list
  private int size; // the number of values in the list
  private final int CAPACITY; // the total number of values allowed in a list

  /**
   * A no parameter constructor that creates a new RestaurantOrders list with a capacity of 20
   * 
   */
  public RestaurantOrders() {
    // initializes all instance fields
    this.head = null;
    this.tail = null;
    CAPACITY = 20;
  }

  /**
   * Creates a new LinkedOrder carrying the given order
   * 
   * @param capacity the maximum amount of orders that can be stored in this list
   */
  public RestaurantOrders(int capacity) {
    // initializes all instance fields
    this.head = null;
    this.tail = null;
    // checks that capacity is a valid value
    if (capacity <= 0) {
      throw new IllegalArgumentException("Error on capacity");
    } else {
      CAPACITY = capacity;
    }
  }

  /**
   * A public accessor method for the private final instance field CAPACITY
   * 
   * @return the value of CAPACITY
   */
  public int capacity() {
    return CAPACITY;
  }

  /**
   * An public instance method that creates a string that contains all of the values of the list
   * 
   * @return the string representation of the list
   */
  public String readForward() {
    // creates a string to be manipulated
    String forwardList = "";
    // iterates through the list and adds all values to the string
    LinkedOrder curr = head;
    while (curr != null) {
      forwardList = forwardList + " " + curr.getOrder().getDishes();
      curr = curr.getNext();
    }
    // returns a base string if list is empty
    if (size == 0) {
      return "The list contains 0 order(s)";
    }
    // returns string with all values of the list and the size of the list
    else {
      return "The list contains " + size + " order(s): [" + forwardList + " ]";
    }
  }

  /**
   * An public instance method that creates a string that contains all of the values of the list in
   * reverse order
   * 
   * @return the string representation of the list in reverse order
   */
  public String readBackward() {
    // creates a string to be manipulated
    String backwardList = "";
    // iterates through the list and adds all values to the string in reverse order
    LinkedOrder curr = head;
    while (curr != null) {
      backwardList = " " + curr.getOrder().getDishes() + backwardList;
      curr = curr.getNext();
    }
    // returns a base string if list is empty
    if (size == 0) {
      return "The list contains 0 order(s)";
    }
    // returns string with all values of the list and the size of the list
    else {
      return "The list contains " + size + " order(s): [" + backwardList + " ]";
    }
  }

  /**
   * An overridden public instance method that checks if the list is empty
   * 
   * @return true if the list is empty and false otherwise
   */
  @Override
  public boolean isEmpty() {
    // checks if the list is empty
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * An overridden public instance method that adds the new order into its sorted spot
   * 
   */
  @Override
  public void placeOrder(Order newOrder) {
    // checks if the order inputed is null and throws an IllegalArgumentException if so
    if (newOrder == null) {
      throw new IllegalArgumentException("newOrder is null");
    }
    // implements the private instance method check to see if an order with the same timestamp
    // already exists in the list
    if (check(newOrder) == true) {
      throw new IllegalArgumentException("Timestamp already exists");
    }
    // checks that the size is less than the capacity
    if (size < CAPACITY) {
      LinkedOrder add = new LinkedOrder(newOrder);
      // case 1: list is empty
      if (size == 0) {
        // adds new order to the list and initializes the head and tail
        head = add;
        tail = add;
        // increments size
        size++;
      }
      // case 2: adding to the front of the list
      else if (newOrder.compareTo(head.getOrder()) == -1) {
        // adds new order to the front of the list and initializes head
        add.setNext(head);
        head = add;
        // increments size
        size++;
      }
      // case 3: adding to the end of the list
      else if (newOrder.compareTo(tail.getOrder()) == 1) {
        // adds new order to the end of the list and initializes tail
        tail.setNext(add);
        tail = add;
        // increments size
        size++;
      }
      // case 4: adding to the middle of the list
      else if (size > 1) {
        // iterates through the list to find the correct spot for the new order
        LinkedOrder curr = head;
        for (int i = 0; i < size; i++) {
          if (newOrder.compareTo(curr.getOrder()) == 1
              && newOrder.compareTo(curr.getNext().getOrder()) == -1) {
            // adds the new order to the linked list and initializes the LinkedOrders around it
            LinkedOrder next = curr.getNext();
            next.setPrevious(add);
            add.setNext(next);
            add.setPrevious(curr);
            curr.setNext(add);
            // increments size
            size++;
          } else {
            curr = curr.getNext();
          }
        }
      }
    }
  }

  /**
   * An private instance method that checks whether or not an order in the list has the same
   * timestamp as the inputed order
   * 
   * @return true if there is an order in the list with the same timestamp and false otherwise
   */
  private boolean check(Order newOrder) {
    // iterates through the list to see if any of the orders have the same timestamp
    LinkedOrder curr = head;
    for (int i = 0; i < size; i++) {
      if (newOrder.compareTo(curr.getOrder()) == 0) {
        return true;
      } else {
        curr = curr.getNext();
      }
    }
    return false;
  }

  /**
   * An overridden public accessor method for the private instance field size
   * 
   * @return the value of size
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * An overridden public method that clears the list
   * 
   */
  @Override
  public void clear() {
    // resets all instance fields
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * An overridden public instance method that gets the order at the given index
   * 
   * @return the order at the given index
   */
  @Override
  public Order get(int index) {
    // iterates through the list to get the value at the given index
    int i = 0;
    LinkedOrder curr = head;
    while (i < index && curr.getNext() != null) {
      curr = curr.getNext();
      i++;
    }
    return curr.getOrder();
  }

  /**
   * An overridden public instance method that gets the index of the given order
   * 
   * @return the index of the order
   */
  @Override
  public int indexOf(Order findObject) {
    // iterates through the list to find the index of the given order
    LinkedOrder curr = head;
    for (int i = 0; i < size; i++) {
      if (curr.getOrder().equals(findObject)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * An overridden public instance method that removes the order at the given index
   * 
   * @return the order that was removed
   */
  @Override
  public Order removeOrder(int index) {
    // checks that the index and size are both valid
    if (size <= 0 || index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index is invalid");
    }
    // case 1: removing from a list with only one element
    if(size == 1) {
      LinkedOrder temp = this.head;
      clear();
      return temp.getOrder();
    }
    // case 2: removing the first order
    else if (index == 0 && this.size > 0) {
      LinkedOrder temp = this.head;
      this.head = this.head.getNext();
      // decrements size
      size--;
      return temp.getOrder();
    } 
    // case 2: removing the last order
    else if (index == this.size - 1) {
      // iterates through the list to remove the last order
      int i = 0;
      LinkedOrder curr = this.head;
      while (i < index - 1) {
        i++;
        curr = curr.getNext();
      }
      LinkedOrder temp = curr.getNext();
      curr.setNext(null);
      this.tail = null;
      // decrements size
      size--;
      return temp.getOrder();
    } 
    // case 3: removing an order that's in between two orders
    else {
      // iterates through the list to remove the order at the given index
      int i = 0;
      LinkedOrder curr = this.head;
      while (i < index - 1) {
        i++;
        curr = curr.getNext();
      }
      LinkedOrder toReturn = curr.getNext();
      curr.setNext(toReturn.getNext());
      // decrements size
      size--;
      return toReturn.getOrder();
    }
  }
}
