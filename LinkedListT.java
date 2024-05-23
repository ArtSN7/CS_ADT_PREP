
// List after adding elements:
// A B C D 
// Search result for 'B': B

// List after removing 'B':
// A C D 
// Search result for 'B' after removal: null

public class LinkedListT {

    public static void main(String[] args) {
        testLinkedList();
    }

    public static void testLinkedList() {
        // Create a linked list
        LinkedList.CreateList();

        // Add elements to the list
        LinkedList.AddToList("A");
        LinkedList.AddToList("B");
        LinkedList.AddToList("C");
        LinkedList.AddToList("D");

        // Print the list
        System.out.println("List after adding elements:");
        LinkedList.PrintList();

        // Search for an element
        String searchResult = LinkedList.SearchList("B");
        System.out.println("\nSearch result for 'B': " + searchResult);

        // Remove an element
        LinkedList.RemoveFromList("B");

        // Print the list after removal
        System.out.println("\nList after removing 'B':");
        LinkedList.PrintList();

        // Search for the removed element again
        searchResult = LinkedList.SearchList("B");
        System.out.println("\nSearch result for 'B' after removal: " + searchResult);
    }
}


class ListNode { // record or our linked list

    String data; // data in the list
    int pointer; // our pointer


    // constructor 
    ListNode(String data, int pointer) {

        this.data = data; // data 
        this.pointer = pointer; // pointer to the next value

    }
}


class LinkedList {

    private static final int list_size = 6; // size of the list
    
    private static ListNode[] list = new ListNode[list_size]; // creating a list of the specific size

    private static int startPointer = -1; // declaring starting pointer
    private static int freeListPointer = 0; // declaring free list pointer ( Index of the next available space in the list )
    

    // CreateList() method: This method initializes the linked list by creating nodes and setting up pointers
    // It iterates through the list array and initializes each node with a null data value and a pointer to the next index
    // The last node's pointer is set to -1 to indicate the end of the list


   /* 
    Procedure CreateList()
        for (i = 0 to list_size - 1)
            list[i] <- new ListNode(null, i + 1)
        endfor
        list[list_size - 1].pointer <- -1
    endprocedure 
    */

    public static void CreateList() { // creating a linked list
        
        for (int i = 0; i < list_size; i++) {

            list[i] = new ListNode(null, i + 1); // we add to the list the (data, pointer)

        }

        list[list_size - 1].pointer = -1; // Last node points to null (data, -1)
    }
    

    // AddToList() method: This method adds a new node with the given data to the end of the linked list
    // It checks if there's space in the list by verifying if freeListPointer is not -1
    // It finds the index for the new node by using freeListPointer
    // It updates freeListPointer to point to the next available space in the list
    // If the list is empty, it sets startPointer to the index of the new node
    // Otherwise, it traverses the list to find the last node and updates its pointer to point to the new node


    /* 

    Procedure AddToList(data : String)
        newNodeIndex <- freeListPointer
        freeListPointer <- list[newNodeIndex].pointer
        list[newNodeIndex].data <- data
        If (startPointer = -1) The
            startPointer <- newNodeIndex
            list[newNodeIndex].pointer <- -1
        Else
            current <- startPointer
            While (list[current].pointer <> -1)
                current = list[current].pointer
            EndWhile
            list[current].pointer <- newNodeIndex
            list[newNodeIndex].pointer <- -1
        Endif
    Endprocedure

    */

    public static void AddToList(String data) {

        if (freeListPointer == -1) { return; } // as list is full, and no items can be added 
        

        int newNodeIndex = freeListPointer; // index of the new node is the index of the free space

        freeListPointer = list[newNodeIndex].pointer; // declaring the pointer of the node (null, 1) => pointer = 1

        list[newNodeIndex].data = data; //adding data to the node
        

        if (startPointer == -1) { // if it is the first value

            startPointer = newNodeIndex; // then we declare start pointer as an index of the first value
            list[newNodeIndex].pointer = -1; // 

        } else { // if it is not the first value

            int current = startPointer; // value to go through every value 

            while (list[current].pointer != -1) { // while there are values left

                current = list[current].pointer; // index of the next value

            }

            list[current].pointer = newNodeIndex; // the new added node will point to the available free space 
            list[newNodeIndex].pointer = -1; // the pointer of the free space points to the -1

        }
    }



    // RemoveFromList() Method: This method removes a node with the given data from the linked list
    // It iterates through the list to find the node with the specified data
    // If found, it updates the pointers of the previous and next nodes to bypass the current node
    // It then updates freeListPointer to point to the removed node, effectively marking it as free space

    /*
     
    FUNCTION RemoveFromList(data : STRIN) RETURNS NULL
        current <- startPointer
        previous <- -1 
        While (current <> -1)
            If (list[current].data = data) Then
                If (previous <> -1) Then
                    list[previous].pointer <- list[current].pointer
                Else
                    startPointer <- list[current].pointer
                Endif
                list[current].pointer <- freeListPointer
                freeListPointer <- current
                Return
            Endif
            previous <- current
            current <- list[current].pointer
        Endwhile
        Print "Item was not found in the list"
    Endfunction
    */

    public static void RemoveFromList(String data) {

        if (startPointer == -1) {return;} // list is empty
    
        int current = startPointer;
        int previous = -1;
    
        while (current != -1) { // while there is smth in the list
            
            if (list[current].data.equals(data)) { // if the data of the node is the same as data required

                if (previous != -1) { // if the node is not the first node

                    // node which was pointing to the deleted value will change its pointer to the pointer of the deleted value
                    list[previous].pointer = list[current].pointer; 

                } else { // if this node is the first node

                    startPointer = list[current].pointer; // we declare a new start pointer

                }

                list[current].pointer = freeListPointer;
                freeListPointer = current;
                return;

            }

            previous = current; // new previous value
            current = list[current].pointer; // new current value

        }
    
        System.out.println("Item was not found in the list"); // if there is no such an item in the list
    }


	// searching the item
    /*
     
    Function SearchList(value : STRING) RETURNS STRING
        current <- startPointer 
        While (current <> -1)
            If (list[current].data = value) Then
                Return value
            Endif
            current <- list[current].pointer
        Endwhile
        Return null // Return null if the value was not found in the list
    Endfunction
     */

    public static String SearchList(String value) {

        int current = startPointer;

        while (current != -1) { // while there is smth in the list

            if (list[current].data.equals(value)) { // if the value was found

                return value; // return this value

            }

            current = list[current].pointer; // iterating through the list

        }

        return null; // data was not found in the list

    }


    // printing the list
    /*
    Procedure PrintList()
        current <- startPointer
        While (current <> -1)
            Print (list[current].data + " ")
            current <- list[current].pointer
        Endwhile
    Endprocedure
     */
	public static void PrintList() {

        int current = startPointer;

        while (current != -1) { // while there is smth in the list

            System.out.print(list[current].data + " "); 
            current = list[current].pointer; // iterating through the value

        }
		
	}
    
}

