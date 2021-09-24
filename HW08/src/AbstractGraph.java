
public abstract class AbstractGraph implements Graph {

  // Data Fields
  /** The number of vertices */
  private int numV;

  // Constructor
  public AbstractGraph() {
    this.numV = numV;
  }
  /** Construct a graph
      @param numV The number of vertices
   */
  public AbstractGraph(int numV) {
    this.numV = numV;
  }

  // Accessor Methods
  /** Return the number of vertices.
      @return The number of vertices
   */
  public int getNumV() {
    return numV;
  }

}
