package Advanced.Binary_Search_Tree;

public class BinarySearchTree {
    private TreeNode root;

    // Constructor to initialize the tree
    public BinarySearchTree() {
        root = null;
    }

    // Insert a new node in the BST
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Helper function for insertion
    private TreeNode insertRec(TreeNode root, int data) {
        // Base case: if the tree is empty, create a new node
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        // Recur down the tree to find the correct position
        if (data < root.data) {
            root.left = insertRec(root.left, data);  // Insert in left subtree
        } else if (data > root.data) {
            root.right = insertRec(root.right, data); // Insert in right subtree
        }

        return root;
    }

    // Search for a node with a specific value
    public boolean search(int data) {
        return searchRec(root, data);
    }

    // Helper function for searching
    private boolean searchRec(TreeNode root, int data) {
        // Base case: root is null or data is found
        if (root == null || root.data == data) {
            return root != null;
        }

        // Recur down the tree
        if (data < root.data) {
            return searchRec(root.left, data);
        }
        return searchRec(root.right, data);
    }

    // Delete a node from the tree
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // Helper function for deletion
    private TreeNode deleteRec(TreeNode root, int data) {
        // Base case: if the tree is empty
        if (root == null) {
            return root;
        }

        // Find the node to be deleted
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node to be deleted is found
            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case 2: One child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Case 3: Two children
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    // Find the node with the minimum value (used in delete operation)
    private int minValue(TreeNode root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // In-order traversal (Left, Root, Right)
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.data + " ");
            inOrderRec(root.right);
        }
    }

    // Pre-order traversal (Root, Left, Right)
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Post-order traversal (Left, Right, Root)
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(TreeNode root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Main method to test the BST
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Insert nodes into the tree
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // In-order traversal (sorted order)
        System.out.println("In-order traversal:");
        tree.inOrder();

        // Pre-order traversal
        System.out.println("Pre-order traversal:");
        tree.preOrder();

        // Post-order traversal
        System.out.println("Post-order traversal:");
        tree.postOrder();

        // Search for a value
        System.out.println("Searching for 40: " + (tree.search(40) ? "Found" : "Not Found"));
        System.out.println("Searching for 25: " + (tree.search(25) ? "Found" : "Not Found"));

        // Delete a node
        System.out.println("Deleting node 20");
        tree.delete(20);
        tree.inOrder();  // Check the tree after deletion
    }
}
