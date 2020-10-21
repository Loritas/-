package AnimalsSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalsOperation {

            public static void addanimals(ArrayList<Animals> list)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入动物的编号：");
                String id = sc.next();
                int index = getIndex(list,id);
                if (index != -1)
                {
                    System.out.println("编号已存在！正在返回主菜单页面");
                    return;
                }
                System.out.println("请输入动物的性别：");
                String sex = sc.next();
                System.out.println("请输入动物的种类：");
                String kind = sc.next();
                System.out.println("请输入动物的年龄：");
                int age = sc.nextInt();
                Animals ani = new Animals(sex,kind,age,id);
                list.add(ani);
                System.out.println("动物信息添加成功！");
            }

            public static void qurrayanimals(ArrayList<Animals> list)
            {
                if (list.size()==0)
                {
                    System.out.println("您还没有添加任何动物信息！正在返回主菜单页面");
                    return;
                }
                System.out.println("种类\t性别\t年龄\t编号");
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
                            Animals ani3 = new Animals(ani1.getsex(), ani1.getKind(), ani1.getAge(), ani1.getId());
                            Animals ani4 = new Animals(ani2.getsex(),ani2.getKind(), ani2.getAge(), ani2.getId());
                            list.set(j,ani4);
                            list.set(j+1,ani3);
                            ani1 = list.get(j);
                        }
                        list1.add(ani1);
                        ani2 = list.get(j+1);
                    }
                }
                for (int i = 0; i < list.size(); i++)
                {
                    System.out.println(list.get(i).getKind() + "\t" + list.get(i).getsex() + "\t" + list.get(i).getAge()
                    + "\t" + list.get(i).getId());
                }
                System.out.println("输出完毕，正在返回主菜单");
            }

            public static void  deleteanimals(ArrayList<Animals> list)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入您想删除的动物的编号：");
                String sid = sc.next();
                int index = getIndex(list,sid);
                if (index == -1)
                {
                    System.out.println("编号不存在！正在返回主菜单页面");
                    return;
                }
                System.out.println("正在删除……");
                list.remove(index);
                System.out.println("删除成功！");
                return;
            }

            public static void editanimals(ArrayList<Animals> list)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入需要修改的动物编号：");
                String sid = sc.next();
                int index = getIndex(list,sid);
                if (index == -1)
                {
                    System.out.println("编号不存在！正在返回主菜单页面");
                    return;
                }
                System.out.println("请输入新的动物性别：");
                String sex = sc.next();
                System.out.println("请输入新的动物种类：");
                String kind = sc.next();
                System.out.println("请输入新的动物年龄：");
                int age = sc.nextInt();
                Animals ani = new Animals(sex,kind,age,sid);
                list.set(index,ani);
                System.out.println("修改成功！");
                return;
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
