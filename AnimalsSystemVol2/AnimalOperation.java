package AnimalsSystemVol2;

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalOperation {
    public static void addAnimals(ArrayList<Animals> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入动物的编号：");
        String id = sc.next();
        int index = getIndex(list,id);
        if (index != -1)
        {
            System.err.println("编号已存在！正在返回主菜单页面");
            return;
        }
        System.out.println("请输入动物的性别：");
        String sex = sc.next();
        System.out.println("请输入动物的种类：");
        String kind = sc.next();
        System.out.println("请输入动物的年龄：");
        int age = sc.nextInt();
        Animals ani = new Animals(sex,kind,id,age);
        list.add(ani);
        System.out.println("动物信息添加成功！");
    }

    public static void deleteAnimals(ArrayList<Animals> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您想删除的动物的编号：");
        String sid = sc.next();
        int index = getIndex(list,sid);
        if (index == -1)
        {
            System.err.println("编号不存在！正在返回主菜单页面");
            return;
        }
        System.out.println("正在删除……");
        list.remove(index);
        System.out.println("删除成功！");
        return;
    }

    public static void editAnimals(ArrayList<Animals> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要修改的动物编号：");
        String sid = sc.next();
        int index = getIndex(list,sid);
        if (index == -1)
        {
            System.err.println("编号不存在！正在返回主菜单页面");
            return;
        }
        System.out.println("请输入新的动物性别：");
        String sex = sc.next();
        System.out.println("请输入新的动物种类：");
        String kind = sc.next();
        System.out.println("请输入新的动物年龄：");
        int age = sc.nextInt();
        Animals ani = new Animals(sex,kind,sid,age);
        list.set(index,ani);
        System.out.println("修改成功！");
        return;
    }

    public static void qurrayAnimals(ArrayList<Animals> list){
        if (list.size()==0)
        {
            System.err.println("您还没有添加任何动物信息！正在返回主菜单页面");
            return;
        }
        System.out.println("种类\t\t\t\t性别\t\t\t\t年龄\t\t\t\t编号");
        ArrayList<Animals> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            Animals ani1;
            Animals ani2;
            for (int j = 0; j < list.size(); j++)
            {
                ani1 = list.get(j);
                if (j==list.size()-1)
                {
                    continue;
                }
                ani2 = list.get(j+1);
                if (ani1.getAge()< ani2.getAge())
                {
                    Animals ani3 = new Animals(ani1.getSex(), ani1.getKind(),  ani1.getId(),ani1.getAge());
                    Animals ani4 = new Animals(ani2.getSex(),ani2.getKind(), ani2.getId(),ani2.getAge());
                    list.set(j,ani4);
                    list.set(j+1,ani3);
                    ani1 = list.get(j);
                }
                list1.add(ani1);
                ani2 = list.get(j+1);
            }
        }
        for (Animals animals : list) {
            System.out.println(animals.getKind() + "\t\t\t\t" + animals.getSex() + "\t\t\t\t" + animals.getAge()
                    + "\t\t\t\t" + animals.getId());
        }
        System.out.println("输出完毕，正在返回主菜单");
    }

    public static int getIndex(ArrayList<Animals> list,String id)
    {
        int index = -1;
        for (int i = 0; i < list.size(); i++)
        {
            Animals ani = list.get(i);
            String sid = ani.getId();
            if (sid.equals(id))
            {
                index = i;
                return index;
            }
        }
        return index;
    }

}
