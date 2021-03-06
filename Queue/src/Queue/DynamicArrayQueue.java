package Queue;

public class DynamicArrayQueue {
	
	private int capacity;
	private int front;
	private int back;
	private char[] array;
	
	public DynamicArrayQueue(){
		capacity = 1;
		front = -1;
		back = -1;
		array = new char[capacity];
	}
	
	public static DynamicArrayQueue createDynamicArrayQueue(){
		return new DynamicArrayQueue();
	}
	
	public boolean isEmpty(){
		return (front == -1);
	}
	
	public boolean isFull(){
		// assume that the array starts at index 0
		return ((back)%(capacity-1) == front);
	}
	
	public int getQueueSize(){
		int size = 0;
		while(array[front]!=('\u0000')){
			size ++;		
			front++;
		}
		return size;
	}
	
	private void resizeQueue(){
			int initCapacity = capacity;
			capacity = capacity * 2;
			char [] oldArray = array;
			array = new char[this.capacity];
			for(int i = 0; i < oldArray.length; i++){
				array[i] = oldArray[i];
			}
			
			if(back<front){
				// reallocate the elements of the array that are positioned back < front to
				// the addresses after front
				for(int i = 0; i<front; i++){
					array[i+initCapacity] = this.array[i];
					array[i] = '\u0000';
				}
				// back pointer now re-valued
				back = back + initCapacity;
			}
	}
	
	public void enQueue(char input){
		if(isFull()){
			resizeQueue();
		}
		
		if(isEmpty()){
			back ++;
			front ++;
			array[back] = input;
			back ++;
		}
		array[back] = input;
		back = (back + 1) % capacity;
				
	}
	
	public char deQueue() throws EmptyQueueException{
		if(isEmpty()){
			throw new EmptyQueueException("Queue is empty, cannot dequeue any elements");
		}
		
		char dequeuedElement = array[front];
		front = (front+1) % capacity;
		return dequeuedElement;
	}

}
