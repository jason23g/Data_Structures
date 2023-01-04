package separateChainingHashing;

public class Link {
	
	private int data;
	  public Link next;

	  public Link(int d) {
	    data = d;
	  }

	  public int getKey() {
	    return data;
	  }

	  public void displayLink() {
	    System.out.print(data + " ");
	  }
}
