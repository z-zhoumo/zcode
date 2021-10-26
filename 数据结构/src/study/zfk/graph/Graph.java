package study.zfk.graph;

import javax.swing.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点的集合
    private int [][] edges;//存储图对应的邻结矩阵
    private int numOfEdges;//表示边的数目
    //定义给数组boolean[]，记录某个结点是否被访问
    private boolean[] isVisited ;

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

        //测试dfs遍历
//        System.out.println("深度遍历");
//        graph.dfs();

        System.out.println("\n****广度优先******");
        graph.bfs();
    }


    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];

    }

    //得到第一个邻接结点的下标 w

    /**
     *
     * @param index
     * @return 如果存在就返沪对应的下标，否则返回 -1
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
                return i ;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个临界结点
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2 +1; i < vertexList.size(); i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 第一次为 0
    public void dfs(boolean [] isVisited,int i){
        //首先我们访问该结点，输出
        System.out.print(getValueByIndex(i)+"--> ");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点 i 的第一个邻接结点 w
        int w = getFirstNeighbor(i);
        while (w != -1){
            if(! isVisited[w]){
                dfs(isVisited,w);
            }
            //如果 w 结点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }


    //对 dfs 进行一个重载，遍历哦我们所有的结点，并进行 dfs
    public void dfs(){
        //遍历所有的结点，进行 dfs （回溯）
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(! isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited,int i){
        int u ; // 表示队列的头结点对应下标
        int w ; //邻接结点 w
        // 队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点的信息
        System.out.print(getValueByIndex(i)+ "==> ");
        //标记为已访问
        queue.addLast(i);

        while( ! queue.isEmpty()){
            //取出队列头结点的下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻结点的下标 w
            w = getFirstNeighbor(u);
            while (w != -1){//找到
                //是否访问过
                if(! isVisited[w]){
                    System.out.print(getValueByIndex(w) + "==> ");
                    //标记已经访问过
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以 u 为前驱，找 w 后面的下一个邻结点
                w = getNextNeighbor(u,w);//体现广度优先
            }
        }
    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(! isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex(){
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
