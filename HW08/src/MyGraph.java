import java.io.*;

public class MyGraph extends AbstractGraph {

  //region DataMembers
  /**
   * edges in file
   */
  private int[][] edges;
  /**
   * all vertices in file
   */
  private Vertex[] vertexObjects;
  /**
   * total vertices number
   */
  private int vertexNum = 0;
  /**
   * total edges number in file
   */
  private int edgeNum = 0;
  /**
   * total edges number
   */
  private int allEdgeNum = 0;
  /**
   * all vertices
   */
  private String[] allEdges = null;
  //endregion DataMembers

  //region Consructors
  /** Construct a graph with the specified number of
      vertices and directionality
      @param fileName filename
   */
  public MyGraph(String  fileName) throws IOException {
    // find vertices
    this.addVertex(fileName);
    //initialize the edges array
    edges = new int[vertexNum][vertexNum];
      for (int i = 0; i < vertexNum; ++i) {
        for (int j = 0; j < vertexNum ; ++j) {
          edges[i][j] =  0;
        }
      }
    this.addAllEdgesFromFile(fileName);
    findAllPossibleEdges();
  }
  //endregion Consructors
  //region Methods

  /**
   * Add edge between given source vertex and given target vertex
   * @param sourceVertex beginning vertex
   * @param targetVertex ending vertex
   */
  public void addEdge(Vertex sourceVertex, Vertex targetVertex) {

    int sourceIndex = 0;
    while (vertexObjects[sourceIndex] !=null && !sourceVertex.label.equals(vertexObjects[sourceIndex].label)){
      ++sourceIndex;
    }

    int targetIndex = 0;
    while (vertexObjects[targetIndex] !=null && !targetVertex.label.equals(vertexObjects[targetIndex].label)){
      ++targetIndex;
    }

     edges[sourceIndex][targetIndex] = 1;
  }

  /**
   * add all edges from file into string array
   * @param fileName filename
   */
  public void addAllEdgesFromFile(String fileName){
    // get width and length
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      int flag = 0;
      allEdges = new String[vertexNum*vertexNum];
      int index = 0;
      while (line != null) {
        ++flag;
        String [] splitted;
        splitted = line.split(" ");
        if(flag != 1){
          addEdge(new Vertex(splitted[0]),new Vertex(splitted[1]));

          allEdges[index] = splitted[0] + " " + splitted[1];
          ++index;
        }
        // read next line
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * read file and find unique vertices and add all in Vertex array
   * @param fileName input.txt
   * @throws IOException io
   */
  public void addVertex(String fileName) throws IOException {
  // get width and length
  BufferedReader reader;
  try {
    reader = new BufferedReader(new FileReader(fileName));
    String line = reader.readLine();
    int flag = 0;
    int index = 0;
    while (line != null) {
      ++flag;
      String [] splitted;
      splitted = line.split(" ");
      if(flag == 1){
        vertexNum = Integer.parseInt(splitted[0]);
        edgeNum = Integer.parseInt(splitted[1]);
        allEdgeNum = edgeNum;
        // add vertex
        vertexObjects = new Vertex[vertexNum];
      }
      if(flag != 1){
        int flagForVertex = 0;
        if(vertexObjects[0]!= null){
          int i = 0;
            while(flagForVertex != 1 && vertexObjects[i] != null){
              if (vertexObjects[i].label.equals(splitted[0])){
                ++flagForVertex;
              }
              ++i;
            }

            if(flagForVertex == 0) {
              // add vertex
              vertexObjects[index] = new Vertex(splitted[0]);
              ++index;
            }
            //second vertex
        flagForVertex = 0;
          i = 0;
          while(flagForVertex != 1 && vertexObjects[i] != null){
            if (vertexObjects[i].label.equals(splitted[1])){
              ++flagForVertex;
            }
            ++i;
          }

            if(flagForVertex == 0){
              // add vertex
              vertexObjects[index] = new Vertex(splitted[1]);
              ++index;
            }
        }
        //if is first vertex, add vertex array
        else{
          vertexObjects[0] = new Vertex(splitted[0]);
          ++index;

        }
      }
      // read next line
      line = reader.readLine();
    }
    reader.close();
  } catch (IOException e) {
    e.printStackTrace();
  }

}

  /**
   * All vertices in graph
   * @return vertices
   */
  public String toString(){
    String line = "All vertices in graph -> ";
    for (Vertex item : vertexObjects){
      line += item.label + " ";

    }
    return line;
}

  /**
   * all edges in a string
   * @return edges
   */
  public String toStringOfEdges(){
    String returning = "";
    returning += "All edges in graph :";
    for (int i = 0; i < allEdgeNum; ++i) {
      returning += allEdges[i] + ", ";
    }
    return returning;
  }

  /***
   * Matrix of graph
   * @return matrix
   */
  public String toStringOfMatrix(){
    String returning = "Matrix of graph :\n";
    for (int i = 0; i < vertexNum; ++i) {
      for (int j = 0; j < vertexNum ; ++j) {
        returning += edges[i][j];
      }
      returning += '\n';
    }
    return returning;
  }

  /**
   * search all possible ways from an egde to another edge
   * if there is a way but there is no edge between them, connnect them
   */
  void findAllPossibleEdges() {

    for (int i = 0; i < allEdges.length; ++i) {
      for (int j = 0; j < allEdges.length; ++j) {
        if(allEdges[i]!= null && allEdges[j] != null) {
          String[] splitted = allEdges[i].split(" ");
          String[] splitted2 = allEdges[j].split(" ");
          if (splitted[1].equals(splitted2[0])) {
            int flag = 0;
            for (int k = 0; k < allEdges.length; ++k) {
              if (allEdges[k] != null && allEdges[k].equals(splitted[0] + " " + splitted2[1])) {
                ++flag;
              }
            }
            if (flag == 0 && !splitted[0].equals(splitted2[1])) {
              allEdges[allEdgeNum] = splitted[0] + " " + splitted2[1];
              addEdge(new Vertex(splitted[0]), new Vertex(splitted2[1]));
              ++allEdgeNum;
            }
          }
        }
      }
    }

  }

  /**
   * Find max visited edge and count visiting times.
   * If there is max count more than one, add it to popular count.
   * @return count of popular
   */
  public int findMostPopular(){

    int countPopular = 0;
    int count = 0;
    int temp = 0;
    //find max visited count
    for (int i = 0; i < vertexNum; ++i) {
      count = 0;
      for (int j = 0; j < vertexNum ; ++j) {
        if(edges[j][i] == 1){
          ++count;
        }

      }
      if(count > temp)
        temp = count;
    }
    //find popular count
    for (int i = 0; i < vertexNum; ++i) {
      int count2 = 0;
      for (int j = 0; j < vertexNum ; ++j) {
        if(edges[j][i] == 1){
          ++count2;
        }

      }
      if(count2 == temp)
        ++countPopular;

    }

    return countPopular;
  }
  //endregion Methods
}