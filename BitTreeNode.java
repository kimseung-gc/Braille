/**
 * Nodes in a binary tree.
 * 
 * @source https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/labs/tree-traversal.html
 */
class BitTreeNode<T> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The value in this node.
   */
  T value;

  /**
   * The left subtree.
   */
  BitTreeNode<T> left;

  /**
   * The right subtree.
   */
  BitTreeNode<T> right;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  public BitTreeNode(T value, BitTreeNode<T> left, BitTreeNode<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  } // BitTreeNode(T, BitTreeNode, BitTreeNode)

  public BitTreeNode(T value) {
    this(value, null, null);
  } // BitTreeNode(T)
} // class BitTreeNode<T>
