import java.io.PrintWriter;

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
   * @param layers
   */
  public BitTree(int layers){
    this.root = new BitTreeNode<String>(null);
  } // BitTree(int)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sets the value at the node at bits
   * @param bits
   * @param value
   * @throws IllegalArgumentException
   */
  public void set(String bits, String value) throws IllegalArgumentException{
    if(!checkBits(bits)){
      throw new IllegalArgumentException("Invalid bit input");
    } // if
    createAndNavigate(bits);
    currentNode.value = value;
  } // set(String, String)

  /**
   * It retrieves the string value in the bits
   * @param bits
   * @return 
   */
  public String get(String bits){
    return navigate(bits);
  } // get (String)

  /**
   * 
   * @param pen
   */
  public void dump(PrintWriter pen){
    dump(pen, root, "");
  } // dump (PrintWriter)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * A function to navigate to a specific node with a given bits String
   * @param bits
   */
  private String navigate(String bits){
    /* Resetting the current node to origin */
    bits = trimZeroesRight(bits);
    this.currentNode = this.root;
    for(char eachChar : bits.toCharArray()){
      int i = (int)(eachChar - '0');
      if(i == 0){
        this.currentNode = this.currentNode.left;
      }else if(i == 1){
        this.currentNode = this.currentNode.right;
      } // if/else
      if(this.currentNode == null){
        return null;
      } // if
    } // for
    return this.currentNode.value;
  } // navigate(String)

  private void createAndNavigate(String bits){
    /* Resetting the current node to origin */
    bits = trimZeroesRight(bits);
    this.currentNode = this.root;
    for(char eachChar : bits.toCharArray()){
      int i = (int)(eachChar - '0');
      createANode(i, this.currentNode, null);
      if(i == 0){
        this.currentNode = this.currentNode.left;
      }else if(i == 1){
        this.currentNode = this.currentNode.right;
      } // if/else
    } // for
  } // createAndNavigate(String)

  /**
   * Private method that trims the zeroes in the bits from right.
   * @param bits
   * @return
   */
  private String trimZeroesRight(String bits){
    int trimmingInd = bits.length();
    while(bits.charAt(trimmingInd-1) == '0'){
      trimmingInd--;
      if(trimmingInd == 1){
        return bits.substring(0, trimmingInd);
      } // if
    } // while
    return bits.substring(0, trimmingInd);
  } // trimZeroesRight(String)

  /**
   * 
   * @param direction
   * @param current
   * @param val
   */
  private void createANode(int direction, BitTreeNode<String> current, String val){
    if(direction == 0){
      if(current.left != null){
        return;
      } // if
      current.left = new BitTreeNode<String>(val);
    } else if(direction == 1){
      if(current.right != null){
        return;
      } // if
      current.right = new BitTreeNode<String>(val);
    } // if/else
  } // createAdditionalNodes(int, BitTreeNode<Integer, V>)

  /**
   * A private method that goes through the bits and check whether the bits really only contains bits.
   * @param bits
   * @return
   */
  private boolean checkBits(String bits){
    if(bits.length() != this.layers){
      return false;
    } // if
    for(char each: bits.toCharArray()){
      if(!(each == '0' || each == '1')){
        return false;
      } // if
    } // for
    return true;
  } // checkBits(String)

  /**
   * Dump a portion of the tree to some output location.
   */
  private void dump(PrintWriter pen, BitTreeNode<String> node, String indent) {
    if (node == null) {
      return;
    } else {
      pen.println(indent + node.value);
      if ((node.left != null) || (node.right != null)) {
        dump(pen, node.left, indent + "  ");
        dump(pen, node.right, indent + "  ");
      } // if has children
    } // else
  } // dump
} // class BitTree
