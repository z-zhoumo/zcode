package study.zfk.hashtab;


import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //简单菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.printf("add添加   ");
            System.out.printf("list显示   ");
            System.out.printf("exit退出   ");
            System.out.printf("find查找   ");
            System.out.printf("delete删除   ");
            key = scanner.next();

            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名");
                    String name = scanner.next();
                    hashTab.add(new Emp(id,name));
                    break;
                case"list":
                    hashTab.list();
                    break;
                case"find":
                    System.out.println("输入要查找的id");
                    int selectId = scanner.nextInt();
                    hashTab.findEmpById(selectId);
                    break;
                case"exit":
                    flag = false;
                    break;
                case"delete":
                    System.out.println("输入要删除的id");
                    int deleteId = scanner.nextInt();
                    hashTab.deleteById(deleteId);
                    break;
                default:
                    System.out.println("输入有误");
            }
        }

    }




}


//创建HashTab，管理多条链表
class HashTab{
    int size;
    EmpLinkedList[] linkedListArray;

    public HashTab(int size){
        this.size = size;
        linkedListArray = new EmpLinkedList[size];
        //初始化每一条链表
        for (int i = 0; i < size; i++) {
            linkedListArray[i] = new EmpLinkedList();
        }
    }


    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工的位置
        int empLinkListNO = hashFun(emp.id);
        //将emp添加到对应的链表中
        linkedListArray[empLinkListNO].add(emp);

    }

    //遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            linkedListArray[i].list(i);
        }
    }



    //编写散列函数，使用一个简单的去摸法
    public int hashFun(int id){
        return id % size;
    }

    //根据id查找雇员
    public void findEmpById(int id){
        //使用散列函数找
        int empLinkListNO = hashFun(id);
        Emp emp = linkedListArray[empLinkListNO].findEmpById(id);
        if(emp!=null){
            System.out.println("在第"+(empLinkListNO+1)+"  条链表中找到该雇员 id =" + id);
        }else {
            System.out.println("没有找到该雇员");
        }
    }



    // 根据id删除雇员
    public void deleteById(int id){
        int empLinkListNO = hashFun(id);
        boolean b = linkedListArray[empLinkListNO].deleteById(id);
        if(b){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }



}








class EmpLinkedList{
    private Emp head;

    //添加
    public void add(Emp emp){
        if(head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head == null){
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"条链表为   ");
        Emp curEmp = head;
        while (true){
            System.out.printf("==> id=%d,name=%s\t",curEmp.id,curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();

    }


    //根据id查询Emp
    //查找到，返回Emp，没有找到，返回null
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }

        //辅助指针帮助查找
        Emp curEmp = head;
        while (true){
            if(curEmp.id == id){
                break;//curEmp就指向查找雇员
            }
            if(curEmp.next == null){
                //找不到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;


    }


    //删除雇员        完成失败/(ㄒoㄒ)/~~
    public boolean deleteById(int id){
       if(head == null){
           System.out.println("链表为空");
           return false;
       }

       Emp curEmp = head;
       Emp temp;
       while (true){
        // 当前节点的id是否是要删除的id
          if(curEmp.id == id){
              if(curEmp.next != null){
                  curEmp = curEmp.next;
                  return true;
              }else{
                  curEmp = null;
                  return true;
              }

          }
          if(curEmp.next == null){//没有下一个节点，找不到该id
              return false;
          }
          //指针后移
          curEmp = curEmp.next;
       }
    }


}


class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id,String name){
        super();
        this.id = id;
        this.name = name;
    }

}
