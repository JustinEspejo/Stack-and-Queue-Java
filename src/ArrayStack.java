

public class ArrayStack {
	
	private int[] theArray;
	private double[] thePrice;
	private int topOfStack;
	private static final int DEFAULT_CAPACITY = 10;
	
	
    public ArrayStack() {
        theArray = new int[DEFAULT_CAPACITY];
        thePrice = new double[DEFAULT_CAPACITY];
        topOfStack = -1;
    }
    
    
    
    public void push(int x, double y) {
        if (topOfStack + 1 == theArray.length)
            doubleArray();
       // System.out.println("adding to the stack " +x); //just a check
        ++topOfStack;
        theArray[topOfStack] = x;
        thePrice[topOfStack] = y;

    }
    
    public void pop() throws Exception {
        if (isEmpty())
            throw new Exception("ArrayStack pop");
        topOfStack--;
    }
    
    public int top () {
        if (isEmpty())
			try {
				throw new Exception( "ArrayStack top" );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return theArray[topOfStack];
    }
    
    public int topAndPop() throws Exception {
        if (isEmpty()) throw new Exception("ArrayStack topAndPop");
       // System.out.println("poping the top " +theArray[topOfStack]);
        return theArray[topOfStack--];
    }
    
    public double getPrice(){
    	return thePrice[topOfStack];
    }
    
    public boolean isEmpty() {
        return topOfStack == -1;
    }
    
    public void makeEmpty() {
        topOfStack = -1;
    }
    
    private void doubleArray() {
        int[] newArray = new int[theArray.length * 2];
        
        for (int i = 0; i < theArray.length; i++)
            newArray[i] = theArray[i];
        theArray = newArray;
    }
}