public class StacksAndQueues {

	public static void main(String[] args) {
        testStacks();
        testQueue();
    }

    public static void testStacks() {
        Stacks.CreateStack(); // Creating a stack with a maximum size of 5
        Stacks.PushStack("A");
        Stacks.PushStack("B");
        Stacks.PushStack("C");

        System.out.println("Popping from stack: " + Stacks.PopStack()); // Should print C
        System.out.println("Popping from stack: " + Stacks.PopStack()); // Should print B
        System.out.println("Popping from stack: " + Stacks.PopStack()); // Should print A
        System.out.println("Popping from stack: " + Stacks.PopStack()); // Should print an empty string as the stack is empty
    }

    public static void testQueue() {
        Queue.CreateQueue(); // Creating a queue with a maximum size of 5
        Queue.AddToQueue("X");
        Queue.AddToQueue("Y");
        Queue.AddToQueue("Z");

        System.out.println("Removing from queue: " + Queue.RemoveFromQueue()); // Should print X
        System.out.println("Removing from queue: " + Queue.RemoveFromQueue()); // Should print Y
        System.out.println("Removing from queue: " + Queue.RemoveFromQueue()); // Should print Z
        System.out.println("Removing from queue: " + Queue.RemoveFromQueue()); // Should print an empty string as the queue is empty
    }

}

class Stacks {

	// Variables
	private static int MAX_STACK_SIZE = 5; // max size of the stack
	private static int baseOfStackPointer;
	private static int topOfStackPointer;
	private static String[] stack;
	

	public static void CreateStack() { // creating a stack

		baseOfStackPointer = 0; // bottom of the stack
		topOfStackPointer = -1; // top of the stack

		stack = new String[MAX_STACK_SIZE]; // creating a stack

	}
	
	public static void PushStack(String item) { // add the value to the top of the stuck ( after the element with max index )

		if (topOfStackPointer < MAX_STACK_SIZE - 1) {

			topOfStackPointer = topOfStackPointer + 1; // increasing index of top stack pointer
			stack[topOfStackPointer] = item; // put the value to this index

		}

		
	}
	
	public static String PopStack() { // deleting a top of stack ( max index )

		String item = "";

		if (topOfStackPointer > -1) { // if the stack is not empty

			item = stack[topOfStackPointer]; // we are getting the element which is the top of a stack
			topOfStackPointer = topOfStackPointer - 1; // decrease the top of the stack pointer

		}

		return item; // returning this element 

		// * we don't really need to delete this element from the array as we only need to change the top of the stack pointer
		//   because we won't be able to access 'deleted' element anyway and also while pushing the new element we will change it

	}

}


class Queue{

	// Variables
	private static int MAX_QUEUE_SIZE = 5; // max size of queue
	private static int endOfQueuePointer;
	private static int frontOfQueuePointer;
	private static String[] queue;


	public static void CreateQueue() { // creating a queue
		
		frontOfQueuePointer = 0; // front of the queue ( index 0 )
		endOfQueuePointer = -1; // end of the queue ( last index )

		queue = new String[MAX_QUEUE_SIZE]; // creating a queue

	}
	
	public static void AddToQueue(String item) {
		
		if (endOfQueuePointer < MAX_QUEUE_SIZE - 1) {

			endOfQueuePointer = endOfQueuePointer + 1; // increasing index of end of queue pointer ( max index )
			queue[endOfQueuePointer] = item; // put the value to this index

		}

	}
	
	public static String RemoveFromQueue() {
		
		String item = "";

		if (frontOfQueuePointer > -1) {

			item = queue[frontOfQueuePointer]; // we are getting the element which is the front of the queue
			frontOfQueuePointer = frontOfQueuePointer + 1; // increase as now the pointer will be the second value

		}

		return item; // returning this element 

	}


}