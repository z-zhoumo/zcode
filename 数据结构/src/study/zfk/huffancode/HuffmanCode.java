package study.zfk.huffancode;



import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/*

 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
//        System.out.println(bytes.length);
//
//        List<Node> nodes = getNodes(bytes);
//        for (Node node : nodes) {
//            System.out.println(node);
//        }
//        //创建二叉树
//        Node huffmanTree = createHuffmanTree(nodes);
//        System.out.println("***********前序遍历**");
//        preOrder(huffmanTree);
//
//        System.out.println("*****huffman编码***********");
//        Map<Byte, String> codes = getCodes(huffmanTree);
//        System.out.println(codes);
//
//        //
//        byte[] zip = zip(bytes, huffmanCodes);
        byte[] zip = huffmanZip(bytes);
        double b =1.0- (double)zip.length/(double)bytes.length;
        System.out.println(b+"///"+zip.length+",,最后的数组"+Arrays.toString(zip));
//        String s = byteToBitString((byte) 1);
//        System.out.println(s);
        byte[] decode = decode(huffmanCodes, zip);
        System.out.println("得到的字符串"+ new String(decode));


    }

    //测试压缩文件的代码
    @Test
    public void FileTest(){
        String srcFile = "G://Bilibili_Study//尚硅谷数据结构//资料//压缩测试文件//txtTest.txt";
        String dstFile = "G://Bilibili_Study//尚硅谷数据结构//资料//压缩测试文件//outputTXT";
        zipFile(srcFile,dstFile);
    }

    //测试 解压 文件的代码
    @Test
    public void UnZipFileTest(){
        String srcFile = "G://Bilibili_Study//尚硅谷数据结构//资料//压缩测试文件//outputTXT";
        String dstFile = "G://Bilibili_Study//尚硅谷数据结构//资料//压缩测试文件//txtTest2.txt";
        unZipFile(srcFile,dstFile);
    }

    //编写方法，将一个文件进行压缩

    /**
     *
     * @param srcFile  传入文件
     * @param dstFile   输出文件
     */
    public static void zipFile(String srcFile,String dstFile){
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的 byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //获取文件对应的 huffman 编码表
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放输出流
            os = new FileOutputStream(dstFile);
            //创建一个和文件关联的 ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把 huffman 编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //用对象流的方式，写入 huffman 的编码，为了恢复源文件的时候使用
            //！！！ 将huffman 编码写入压缩文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    //编写解压方法

    /**
     *
     * @param zipFile   待解压文件
     * @param dstFile   文件解压位置
     */
    public static void unZipFile(String zipFile,String dstFile){
        //定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is =  new FileInputStream(zipFile);
            //创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取 byte 数组 huffmnaBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取 huffman 编码表
            Map<Byte,String> huffmanCodes= (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将 bytes 写入目标文件
            os = new FileOutputStream(dstFile);
            //写出数据
            os.write(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }






    //完成数据的解压
    /*1 将huffmanCodeBytes
        重写先转成 huffman对应的二进制字符串
    * */

    /**
     *
     * @param huffmanCodes  huffman编码表的 map
     * @param huffmanBytes  huffman编码得到的字节数组
     * @return  就是原理的字符串对应的数组
     */
    private static byte [] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        // 1 先得到 huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte 数组转为二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //编写一个方法，完成对压缩数据的解码
        //把huffman编码进行调换，反向查找
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry: huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
//        System.out.println("decode-Map=="+map);

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//计数器
            boolean flag = true;
            Byte b = null;
            while (flag){
                //取出一个 0 或 1
                String key = stringBuilder.substring(i,i+count);
                //i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if(b ==null){//未匹配
                    count++;

                }else {
                    //匹配
                    flag = false;
                }
            }
            list.add(b);
            i+= count;
        }
        //当 for 结束，list 存放了所有的字符
        //将 list 中的数据放入 byte[]
        byte [] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }



    /**
     * 将一个byte 转成一个二进制的字符串
     * @param flag  标注是否需要补高位，如果是false表示不补
     * @param b     传入的 byte
     * @return  是该 b 对应的二进制字符串，（按补码返回 ）
     */
    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存 b
        int temp = b;//将bit转换位int

        //如果正数我们还存在补高位
        if(flag){
        temp |= 256;//按位与 256 1 0000 0000
        // 0000 0001  ===》 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的时temp对应的二进制的补码
//        System.out.println("str="+str);
        if(str.length() -8 <= 0){
            return str;
        }
        return str.substring(str.length() - 8);
    }

    /**
     *
     * @param bytes     原始的字符串对应的字节数组
     * @return      经过huffman编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[]bytes){
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建 Huffman树
        Node huffmanTree = createHuffmanTree(nodes);
        // 生成对应的 huffman 编码
        Map<Byte, String> codes = getCodes(huffmanTree);
        // 根据生成的 huffman编码，压缩得到 huffman字节数组
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }



    /*将字符串对应的byte[]数组，通过生成huffman编码表，
        返回一个huffman编码，压缩后的byte[]
    * */

    /**
     *
     * @param bytes     原始字符串对应的byte[]
     * @param huffmanCodes     huffmanCodes 生成的huffman编码map
     * @return  huffman编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){

        // 1 利用huffmanCodes 将 bytes 转成huffman编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历 bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("Test StringBuilder"+stringBuilder.toString());
        //将”10101...."转换为byte[]
        //统计返回 byte[] huffmanCodeBytes 长度
//        int len;
        int len = (stringBuilder.length()+7)/8;
//        if(stringBuilder.length()%8 == 0){
//            len = stringBuilder.length()/8;
//        }else {
//            len = stringBuilder.length()/8 +1;
//        }
        //创建存储压缩后的byte数组
        byte [] huffmanCodeBytes = new byte[len];
        int index = 0;//记录第几个 byte
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            //因为每 8 位对应一个byte，所以步长 +8
            String strByte;
            if(i+8 > stringBuilder.length()){
                //组后没有8位数
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i,i+8);
            }
            //将strbyte 转成一个byte，放入 huffmanCodeBytes
            huffmanCodeBytes[index++] =
                    (byte) Integer.parseInt(strByte,2);

        }
        return huffmanCodeBytes;
    }


    private static List<Node> getNodes(byte[] bytes) {
        // 1 创建一个ArraysList
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历bytes ， 统计每一个byte出现的次数 -》 map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) { //遍历字节数组
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);//取出数据不存在，存入
            } else {//取出数据存在，让 对应值++
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个Node 对象，并加入到 nodes 集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }


    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，它的根结点，没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //去掉处理完的两颗二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        //最后的结点就是
//        return parent;
        return nodes.get(0);
    }


    /*生成huffman树对应的huffman编码
    1 将huffman编码表存放在 Map<Byte , String> 形式
        32-》01 97 -》100 .。。
    2 在生成赫夫曼编码表时，需要去拼接路劲，定义一个StringBuilder
      存储某个叶子结点的路劲
    *
    * */
    private static Map<Byte, String> huffmanCodes = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入node结点的所有叶子结点的huffman编码得到
     *
     * @param node          传入结点
     * @param code          路劲：左子节点是 0 右子节点是 1
     * @param stringBuilder 用于拼接路劲
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//如果node == null不处理
            //判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {//非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else {//说明为叶子结点
                //找到叶子结点的最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }
    private static Map<Byte,String> getCodes(Node root){
        if(root == null){
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",stringBuilder);
        //处理右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }


    //前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("huffman树为空");
        }
    }
}

//创建Node,待数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据（字符）本身，比如 'a' = 97   ' ' = 32
    int weight; //权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
