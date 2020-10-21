package AnimalsSystem;
//20级，杨浩 2020211855
//没有预先添加任何动物信息
//使用Animals类定义动物信息，用AnimalsOperation对动物信息进行操作
//使用动物编号来锁定动物信息
//查看动物信息时，信息将按年龄大到小顺序输出

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalsManagementMain {
    public static void main(String[] args) {
        ArrayList<Animals> list = new ArrayList<>();
        lo:
        while (true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("------动物信息管理系统------");
            System.out.println("1.添加动物");
            System.out.println("2.修改动物");
            System.out.println("3.删除动物");
            System.out.println("4.查看动物");
            System.out.println("5.退出系统");
            switch (sc.nextInt())
            {
                case 1:
                    System.out.println("--添加动物--");
                    AnimalsOperation.addanimals(list);
                    break;
                case 2:
                    System.out.println("--修改动物--");
                    AnimalsOperation.editanimals(list);
                    break;
                case 3:
                    System.out.println("--删除动物--");
                    AnimalsOperation.deleteanimals(list);
                    break;
                case 4:
                    System.out.println("--查看动物--");
                    AnimalsOperation.qurrayanimals(list);
                    break;
                case 5:
                    System.out.println("--感谢您的使用！--");
                    break lo;
            }
        }
    }
}
