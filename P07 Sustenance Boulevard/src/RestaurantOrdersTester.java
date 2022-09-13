//////////////// P07 Sustenance Boulevard //////////////////////////
//
// Title: RestaurantOrdersTester
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
 * This class implements unit test methods to check the correctness of LinkedOrders and
 * RestaurantOrders classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming
 * assignment.
 *
 */
public class RestaurantOrdersTester {

  /**
   * This method should test and make use of at least the LinkedOrders constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedOrders() {
    try {
      Order burger = new Order("Burger", 1);
      Order sandwich = new Order("Sandwich", 2);
      LinkedOrder order1 = new LinkedOrder(burger);
      LinkedOrder order2 = new LinkedOrder(sandwich);
      order1.setNext(order2);
      if (order1.getOrder() != burger) {
        return false;
      }
      if (order1.getPrevious() != null) {
        return false;
      }
      if (order1.getNext() != order2) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unknown Exception was thrown");
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of both RestaurantOrders constructors and the instance
   * method isEmpty() when called on an empty restaurant orders object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorIsEmpty() {
    try {
      RestaurantOrders grubHub = new RestaurantOrders();
      RestaurantOrders doorDash = new RestaurantOrders(5);
      if (grubHub.capacity() != 20) {
        return false;
      }
      if (doorDash.capacity() != 5) {
        return false;
      }
      if (grubHub.isEmpty() != true) {
        return false;
      }
      if (doorDash.isEmpty() != true) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unknown Exception was thrown");
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
   * negative int value for the capacity.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorBadInput() {
    try {
      RestaurantOrders doorDash = new RestaurantOrders(-1);
      System.out.println("Exception was not thrown when negative value was passed for capacity");
      return false;
    } catch (IllegalArgumentException ia) {
      // Expected exception to be thrown
    } catch (Exception e) {
      System.out.println("Unknown Exception was thrown");
      return false;
    }
    return true;
  }


  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() method when it is
   * passed bad inputs. This method must consider at least the test scenarios provided in the
   * detailed description of these javadocs. (1) Try adding a null to the list; (2) Try adding an
   * order which carries a negative timestamp. (3) Try adding an order with an existing timestamp to
   * the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAddBadInput() {
    try {
      RestaurantOrders burgerKing = new RestaurantOrders(5);
      try {
        burgerKing.placeOrder(null);
        System.out.println("Exception was not thrown when adding null");
        return false;
      } catch (IllegalArgumentException ia) {
        // Expected exception to be thrown
      }
      try {
        Order burger = new Order("Burger", -1);
        burgerKing.placeOrder(burger);
        System.out.println("Exception was not thrown when adding an order with negative timestamp");
        return false;
      } catch (IllegalArgumentException ia) {
        // Expected exception to be thrown
      }
      try {
        Order burger = new Order("Burger", 1);
        Order fries = new Order("Fries", 1);
        burgerKing.placeOrder(burger);
        burgerKing.placeOrder(fries);
        System.out
            .println("Exception was not thrown when adding an order with an existing timestamp");
        return false;
      } catch (IllegalArgumentException ia) {
        // Expected exception to be thrown
      }
    } catch (Exception e) {
      System.out.println("Unknown Exception was thrown");
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() considering at
   * least the test scenarios provided in the detailed description of these javadocs. (1) Try adding
   * an order to an empty list; (2) Try adding an order which is expected to be added at the head of
   * a non-empty restaurant list; (3) Try adding an order which is expected to be added at the end
   * of a non-empty restaurant list; (4) Try adding an order which is expected to be added at the
   * middle of a non-empty restaurant list. For each of those scenarios, make sure that the size of
   * the list is appropriately updated after a call without errors of the add() method, and that the
   * contents of the list is as expected whatever if list is read in the forward or backward
   * directions. You can also check the correctness of RestaurantOrders.get(int),
   * RestaurantOrders.indexOf(Order), and RestaurantOrders.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAdd() {
 //   try {
      RestaurantOrders mcDonalds = new RestaurantOrders(5);

      // Case 1: Adding an order to an empty list;
      Order o1 = new Order("Burger", 2);
      mcDonalds.placeOrder(o1);
      String case1 = new String("The list contains 1 order(s): [ Burger ]");
      if(!(mcDonalds.readForward().equals(case1))) {
        System.out.println("Error on orders add case 1");
        return false;
      }
      // Case 2: adding an order which is expected to be added at the head of a non-empty list
      Order o2 = new Order("Sandwich", 1);
      mcDonalds.placeOrder(o2);
      String case2 = new String("The list contains 2 order(s): [ Sandwich Burger ]");
      if(!(mcDonalds.readForward().equals(case2))) {
        System.out.println("Error on orders add case 2");
        return false;
      }
      // Case 3: adding an order which is expected to be added at the end of a non-empty list
      Order o3 = new Order("Pizza", 4);
      mcDonalds.placeOrder(o3);
      String case3 = new String("The list contains 3 order(s): [ Sandwich Burger Pizza ]");
      if(!(mcDonalds.readForward().equals(case3))) {
        System.out.println("Error on orders add case 3");
        return false;
      }
      // Case 4: adding an order which is expected to be added at the middle of a non-empty list
      Order o4 = new Order("Noodles", 3);
      mcDonalds.placeOrder(o4);
      String case4 = new String("The list contains 4 order(s): [ Sandwich Burger Noodles Pizza ]");
      if(!(mcDonalds.readForward().equals(case4))) {
        System.out.println("Error on orders add case 4");
        return false;
      }
 /*   } catch (Exception e) {
      System.out.println("Unknown Exception was thrown");
      return false;
    }
   */ return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.removeOrder() considering at
   * least the test scenarios provided in the detailed description of these javadocs. (1) Try
   * removing an order from an empty list or pass a negative index to RestaurantOrders.removeOrder()
   * method; (2) Try removing an order (at position index 0) from a list which contains only one
   * order; (3) Try to remove an order (position index 0) from a list which contains at least 2
   * orders; (4) Try to remove an order from the middle of a non-empty list containing at least 3
   * orders; (5) Try to remove the order at the end of a list containing at least two orders. For
   * each of those scenarios, make sure that the size of the list is appropriately updated after a
   * call without errors of the add() method, and that the contents of the list is as expected
   * whatever if list is read in the forward or backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersRemove() {
    try {
      RestaurantOrders mcDonalds = new RestaurantOrders();
      // Case 1: removing an order from an empty list or pass a negative index
      try {
        mcDonalds.removeOrder(-1);
        System.out.println(
            "Exception was not thrown when removing order from empty list or when negative index was inputed");
        return false;
      } catch (IndexOutOfBoundsException io) {
        // Expected exception to be thrown
      }
      // Case 2: removing an order from a list which contains only one order
      Order burger = new Order("Burger", 1);
      mcDonalds.placeOrder(burger);
      mcDonalds.removeOrder(0);
      String case2 = new String("The list contains 0 order(s)");
      if(!(mcDonalds.readForward().equals(case2))||mcDonalds.isEmpty() != true || mcDonalds.size() != 0) {
        return false;
      }
      // Case 3: removing an order from a list which contains at least 2 orders
      Order sandwich = new Order("Sandwich", 2);
      mcDonalds.placeOrder(burger);
      mcDonalds.placeOrder(sandwich);
      mcDonalds.removeOrder(0);
      String case3 = new String("The list contains 1 order(s): [ Sandwich ]");
      if(!(mcDonalds.readForward().equals(case3)) || mcDonalds.size() != 1) {
        return false;
      }
      // Case 4: removing an order from the middle of a non-empty list containing at least 3 orders
      Order pizza = new Order("Pizza", 3);
      mcDonalds.placeOrder(burger);
      mcDonalds.placeOrder(pizza);
      mcDonalds.removeOrder(1);
      String case4 = new String("The list contains 2 order(s): [ Burger Pizza ]");
      if(!(mcDonalds.readForward().equals(case4)) || mcDonalds.size() != 2) {
        return false;
      }
      // Case 5: remove the order at the end of a list containing at least two orders
      mcDonalds.placeOrder(sandwich);
      mcDonalds.removeOrder(2);
      String case5 = new String("The list contains 2 order(s): [ Burger Sandwich ]");
      if(!(mcDonalds.readForward().equals(case5)) || mcDonalds.size() != 2) {
        return false;
      }

    } catch (Exception e) {
      System.out.println("Unknown Exception was thrown");
      return false;
    }
    return true;
  }


  /**
   * This method calls all the test methods defined and implemented in your RestaurantOrdersTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (testLinkedOrders() && testRestaurantOrdersConstructorIsEmpty()
        && testRestaurantOrdersConstructorBadInput() && testRestaurantOrdersAddBadInput()
        && testRestaurantOrdersAdd() && testRestaurantOrdersRemove()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Driver method defined in this RestaurantOrdersTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + runAllTests());
  }
 }
