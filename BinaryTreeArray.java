public class BinaryTreeArray {
    
    private static final int NullPointer = -1; // declaring NullPointer

    
    static class TreeNode { // Declare record type to store data and pointers

        String data; // stores the data for the node
        int leftPointer; // index of the left child node
        int rightPointer; // index of the right child node

        TreeNode(String data, int leftPointer, int rightPointer) { // Constructor to initialize a node with data and pointer
            this.data = data;
            this.leftPointer = leftPointer;
            this.rightPointer = rightPointer;
        }
    }

    private int rootPointer; // Stores the index of the root node
    private int freePtr; // points to the next available free node in the array

    private TreeNode[] tree; // Declare the tree array


    public BinaryTreeArray(int size) { // Procedure to initialize the tree

        tree = new TreeNode[size]; // creating the new tree array
        
        rootPointer = NullPointer; // set start pointer to indicate an empty tree
        freePtr = 0; // set starting position of free list

        // Link all nodes to make free list
        for (int index = 0; index < tree.length - 1; index++) {

            tree[index] = new TreeNode(null, index + 1, NullPointer); // initialises each node with a link to the next free node

        }

        tree[tree.length - 1] = new TreeNode(null, NullPointer, NullPointer); // last node of free list with no links to the next nodes
    }


    // Method to insert data into the tree
    public void insert(String newItem) {

        if (freePtr != NullPointer) { // checks if there is space in the array
        
            int newNodePtr = freePtr; // gets the index of the next free node

            freePtr = tree[freePtr].leftPointer; // updates the free pointer to the next free node ( take left pointer as it points to the free node )


            tree[newNodePtr].data = newItem; // stores the new item in the node
            tree[newNodePtr].leftPointer = NullPointer; // sets the left pointer of the new node to null.
            tree[newNodePtr].rightPointer = NullPointer; // sets the right pointer of the new node to null.

            
            if (rootPointer == NullPointer) { // check if empty tree
                
                rootPointer = newNodePtr; // Insert new node at root, so the first inserted node is our starting one
                
            } else { // if there is already something in the tree
    
                int thisNodePtr = rootPointer; // start at the root of the tree
                int previousNodePtr = NullPointer; // initializes a pointer to remember the previous node
                boolean turnedLeft = false; // tracks whether we turned left or right

                while (thisNodePtr != NullPointer) { // while not a leaf node

                    previousNodePtr = thisNodePtr; // remember this node

                    if (tree[thisNodePtr].data.compareTo(newItem) > 0) { // comparing data, if OLD NODE DATA > NEW ITEM
                        
                        turnedLeft = true; // follow left pointer, as we decrease value
                        thisNodePtr = tree[thisNodePtr].leftPointer; // thisNodePtr is now has value of leftPointer as we go left

                    } else {
                        
                        turnedLeft = false; // follow right pointer, as we increase value
                        thisNodePtr = tree[thisNodePtr].rightPointer; // thisNodePtr is now has value of rightPointer as we go right

                    }
                }


                // Insert the new node
                if (turnedLeft) { // if turned left

                    tree[previousNodePtr].leftPointer = newNodePtr; // then there will be a pointer to the left in parent node

                } else { // if turned right

                    tree[previousNodePtr].rightPointer = newNodePtr; // then there will be a pointer to the right in parent node

                }
            }
        }
    }



    // Method to search for a node in the tree
    public int findNode(String searchItem) {

        int thisNodePtr = rootPointer; // start at the root of the tree

        // While a pointer to follow and search item not found
        while (thisNodePtr != NullPointer && !tree[thisNodePtr].data.equals(searchItem)) {

            if (tree[thisNodePtr].data.compareTo(searchItem) > 0) { // if OLD NODE DATA > SEARCH ITEM

                
                thisNodePtr = tree[thisNodePtr].leftPointer; // Follow left pointer, as SEARCH ITEM is less than data, so it is on the left

            } else {

                thisNodePtr = tree[thisNodePtr].rightPointer; // Follow left pointer, as SEARCH ITEM is more than data, so it is on the right
            }
        }

        return thisNodePtr; // will return null pointer if search item not found
    }


    public static void main(String[] args) {

        BinaryTreeArray tree = new BinaryTreeArray(7);

        // Insert nodes
        tree.insert("50");
        tree.insert("30");
        tree.insert("20");
        tree.insert("40");
        tree.insert("70");
        tree.insert("60");
        tree.insert("80");


        // Search for a node
        String searchItem = "60";
        int nodeIndex = tree.findNode(searchItem);
        if (nodeIndex != NullPointer) {
            System.out.println("Node " + searchItem + " found at index " + nodeIndex);
        } else {
            System.out.println("Node " + searchItem + " not found in the tree");
        }
    }
}


