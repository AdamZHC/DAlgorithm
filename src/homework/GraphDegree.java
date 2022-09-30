package homework;

public class GraphDegree {
    public int[][] stat(int[][] graph) {
        int vertexDegree[][] = new int[graph.length][2];
        for(int i = 0; i < graph.length; ++i) {
            for(int j = 0; j < graph.length; ++j) {
                vertexDegree[i][0] += graph[i][j];
                vertexDegree[j][1] += graph[i][j];
            }
        }
        return vertexDegree;
    }
}
