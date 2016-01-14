
public class ArrayQueue {

	private int[] theArray;
    private int currentSize = 0;
    private int front = 0;
    private int back = -1;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue() {
        theArray = new int[DEFAULT_CAPACITY];
    }
    
    public void enqueue(int x) {
        if (currentSize == theArray.length)
            doubleQueue();
        back = ++back % theArray.length;
        System.out.println("adding to the queue " +x);
        theArray[back] = x;
        currentSize++;
    }
    
    public int getFront() {
        if(isEmpty())
			try {
				throw new Exception("ArrayQueue getFront");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return theArray[front];
    }
    
    public int dequeue() {
        if (isEmpty())
			try {
				throw new Exception( "ArrayQueue dequeue" );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        currentSize--;
        int returnValue = theArray[front];
        front = ++front % theArray.length;
        return returnValue;
    }
    
    public boolean isEmpty() {
        return currentSize == 0;
    }
    
    public void makeEmpty() {
        currentSize = 0;
        front = 0;
        back = -1;
    }
    
    private void doubleQueue() {
        int [] newArray = new int[theArray.length * 2];
        
        for (int i = 0; i < currentSize; i++) {
            newArray[i] = theArray[front];
        	front = front % theArray.length;
        }
        
        theArray = newArray;
        front = 0;
        back = currentSize - 1;
    }
    public int getFrontIndex(){
    	return front;
    }

}