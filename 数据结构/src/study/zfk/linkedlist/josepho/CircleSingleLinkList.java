package study.zfk.linkedlist.josepho;

/**
 * @create 09-13 20:43
 */
public class CircleSingleLinkList {
    private Boy first = null;

    //添加小孩节点
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = boy;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }


    //遍历小孩结点
    public void show(){
        if(first == null){
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.println("boy*****"+curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    //根据用户的输入，计算出圈的顺序
    /**
     * @param startNo   从第几个小孩开始数数
     * @param countNum  数多少下
     * @param nums      表示最初有多少小孩在圈中
     *
     * 1 需求创建一个辅助指针变量，指向first，事先应该指向环形链表得最后一个节点
     * 2 当开始报数时，让first和helper同时移动 m = countNum-1
     * 3 这时就可以将first指向小孩节点出圈
     *                  first = first。next;
     *                  helper.next = first;
     */
    public void countBoy(int startNo,int countNum,int nums){
        //校验数据
        if(first == null || startNo < 1 || startNo >nums){
            System.out.println("数据输入有误");
            return;
        }
        //创建辅助指针，帮助小孩完成出圈  让helper指向最后的小孩节点
        Boy helper = first;
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        while (true) {
            if(first == helper)break;//圈中只有一个节点

        //开始报数时，让first和helper同时移动 m = countNum-1
        for (int i = 0; i < countNum - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        System.out.println("小孩【"+first.getNo()+"】出圈");
      first = first.getNext();
        helper.setNext(first);
        }
        //圈中小孩数量
        System.out.println("圈中小孩"+first.getNo());
    }





}
