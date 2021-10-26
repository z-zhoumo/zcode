package study.zfk.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点的集合
    private int [][] edges;//存储图对应的邻结矩阵
    private int numOfEdges;//表示边的数目

    public static void main(String[] args) {

        int i = 5;//结点的个数
        String vertexValue[] = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(i);
        //循环添加
        for(String value:vertexValue){
            graph.insertVertex(value);
        }
        //添加边 A-B
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();

    }


    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfEVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i（下标）对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWtight(int v1,int v2){
        return edges[v1][v2];
    }
    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }


    //添加边
    /**
     *
     * @param val1 表示点的下标即是第几个顶点
     * @param val2  第二个顶点对应的下标
     * @param weight    表示权值
     */
    public void insertEdge(int val1, int val2,int weight){
        edges[val1][val2] = weight;
        edges[val2][val1] = weight;
        numOfEdges++;

    }
}
