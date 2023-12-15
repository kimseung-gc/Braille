import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * A tree made of bits with pointers to the currentNode.
 * 
 * @author Seunghyeon (Hyeon) Kim
 * @version 0.0.3
 */

public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The root of the tree
   */
  BitTreeNode<String> root;

  /**
   * Describes the current node pointer
   */
  BitTreeNode<String> currentNode;

  /**
   * The number of layers for the trees
   */
  int layers;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * A constructor for BitTree that initializes the root.
   * 
   * @param layers
   */
  public BitTree(int layers) {
    // Initialize root by creating a new tree node with null value
    this.root = new BitTreeNode<String>(null);
    // Initialize the layers.
    this.layers = layers;
  } // BitTree(int)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sets the value at the node at bits
   * 
   * @param bits
   * @param value
   * @throws IllegalArgumentException
   */
  public void set(String bits, String value) throws IllegalArgumentException {
    // Check whether the bits are valid
    if (!checkBits(bits)) {
      // Otherwise, throw error
      throw new IllegalArgumentException("Invalid bit input");
    } // if
    // create nodes while navigating to bits
    createAndNavigate(bits);
    // After navigating, set the currentNode (navigated node)'s value to the desired
    // value
    currentNode.value = value;
  } // set(String, String)

  /**
   * It retrieves the string value in the bits
   * 
   * @param bits
   * @return
   */
  public String get(String bits) throws IllegalArgumentException {
    // Navigate to the bits with given directions (1 is right and 0 is left)
    String temp = navigate(bits);
    // When the navigation function maps to null
    if (temp == null) {
      // It means the element cannot be navigated, so throw an error
      throw new IllegalArgumentException("Cannot find \'" + bits + "\'");
    } // if
    // Otherwise, return the navigated node's string
    return temp;
  } // get (String)

  /**
   * It dumps(prints) the tree in the form of csv format
   * 
   * @param pen
   */
  public void dump(PrintWriter pen) {
    dump(pen, root, "");
  } // dump (PrintWriter)

  /**
   * It loads the whatever value inside the source as bits and values.
   * 
   * @param source
   * @throws IOException
   */
  public void load(InputStream source) throws IOException {
    // read each of the characters as integer and store it as temp
    int temp = source.read();
    /* ====================== File to temporary string ======================== */
    // create an empty string
    String tempString = "";
    // While not reached EOF,
    while (temp != -1) {
      // characterize temp and concatenate it to tempString
      tempString += (char) temp;
      // then read the next character
      temp = source.read();
    } // while
    /* ======================================================================== */
    // Split the string with respective to new lines
    String[] tempArr = tempString.split("\n");
    for (String eachString : tempArr) {
      // split ','s
      String[] tempTempArr = eachString.split(",");
      // the first index are the bits
      String bits = tempTempArr[0];
      // the second index are the values associated
      String value = tempTempArr[1];
      // then set the bits with desired values
      this.set(bits, value);
    } // for
  } // load(InputStream)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * A function to navigate to a specific node with a given bits String
   * 
   * @param bits
   */
  private String navigate(String bits) {
    // Trim the zeroes of the left side of the string (required for efficient
    // storage)
    bits = trimZeroesLeft(bits);
    // Resetting the current node to origin
    this.currentNode = this.root;
    for (char eachChar : bits.toCharArray()) {
      // for each of the char, make it numeric value
      int i = (int) (eachChar - '0');
      if (i == 0) {
        // When 0, navigate to left
        this.currentNode = this.currentNode.left;
      } else if (i == 1) {
        // When 1, navigate to right
        this.currentNode = this.currentNode.right;
      } // if/else
      if (this.currentNode == null) {
        // When current node is null, it cannot move further, so return null
        return null;
      } // if
    } // for
    // Otherwise return the value associated with the node that the program has
    // navigated to.
    return this.currentNode.value;
  } // navigate(String)

  /**
   * Private method that helps the tree to create missing nodes and navigate to
   * it.
   * 
   * @param bits
   */
  private void createAndNavigate(String bits) {
    // Trim the zeroes of the left side of the string (required for efficient
    // storage)
    bits = trimZeroesLeft(bits);
    this.currentNode = this.root;
    for (char eachChar : bits.toCharArray()) {
      int i = (int) (eachChar - '0');
      createANode(i, this.currentNode, null);
      if (i == 0) {
        this.currentNode = this.currentNode.left;
      } else if (i == 1) {
        this.currentNode = this.currentNode.right;
      } // if/else
    } // for
  } // createAndNavigate(String)

  /**
   * Private method that trims the zeroes in the bits from right.
   * 
   * @param bits
   * @return
   */
  private String trimZeroesLeft(String bits) {
    int trimmingInd = 0;
    while (bits.charAt(trimmingInd) == '0') {
      trimmingInd++;
      if (trimmingInd == bits.length() - 1) {
        return bits.substring(trimmingInd, bits.length());
      } // if
    } // while
    return bits.substring(trimmingInd, bits.length());
  } // trimZeroesLeft(String)

  /**
   * The helper function that creates a node in the given direction. If there
   * already is a value, it leaves the node.
   * 
   * @param direction
   * @param current
   * @param val
   */
  private void createANode(int direction, BitTreeNode<String> current, String val) {
    if (direction == 0) {
      if (current.left != null) {
        return;
      } // if
      current.left = new BitTreeNode<String>(val);
    } else if (direction == 1) {
      if (current.right != null) {
        return;
      } // if
      current.right = new BitTreeNode<String>(val);
    } // if/else
  } // createAdditionalNodes(int, BitTreeNode<Integer, V>)

  /**
   * A private method that goes through the bits and check whether the bits really
   * only contains bits.
   * 
   * @param bits
   * @return
   */
  private boolean checkBits(String bits) {
    if (bits.length() != this.layers) {
      return false;
    } // if
    for (char each : bits.toCharArray()) {
      if (!(each == '0' || each == '1')) {
        return false;
      } // if
    } // for
    return true;
  } // checkBits(String)

  /**
   * Dump a portion of the tree to some output location.
   */
  private void dump(PrintWriter pen, BitTreeNode<String> node, String preString) {
    if (node == null) {
      return;
    } else {
      String additionalString = "";
      for (int i = 0; i < this.layers - preString.length(); i++) {
        additionalString += "0";
      } // for
      pen.println(preString + additionalString + node.value);
    } // if/else
    if ((node.left != null) || (node.right != null)) {
      dump(pen, node.left, preString + "0");
      dump(pen, node.right, preString + "1");
    } // if has children
  } // dump
} // class BitTree
