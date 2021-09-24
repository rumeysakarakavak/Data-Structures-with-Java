
public interface Graph {

  // Accessor Methods
  /** Return the number of vertices.
      @return The number of vertices
   */
  int getNumV();

  /**
   * add new edge
   * @param source source
   * @param dest dest
   */

  void addEdge(Vertex source, Vertex dest);
}
