package AnimalsSystemVol2;

/**
 *
 *  2020211855 杨浩 20级
 *  数据存储在data.ini文件（后缀是ini纯粹是因为咱比较喜欢玩ra2）
 *  启动程序时反序列化读取文件内数据存储到ArrayList集合，退出程序时先清空文件数据
 *          再写入新的文件数据，以便保证每次都读取到了最新的数据集合
 *  查看动物信息时，将按照年龄大小顺序排序输出
 *  （排序这块做的比较弱，处理大量数据的时候会非常sb，学长手下留情😀）
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalsManagementMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean FILE_EXISTED = true;
        try {
            InputStream is = new FileInputStream("AnimalsSystemVol2/data.ini");
        } catch (IOException e) {
            System.err.println("数据文件缺失！将会创建一个新的空白数据文件。");
            OutputStream ous = new FileOutputStream("AnimalsSystemVol2/data.ini",true);
            FILE_EXISTED = false;
            ous.close();
        }
        InputStream ins = new FileInputStream("AnimalsSystemVol2/data.ini");
        ObjectInputStream ois;
        ArrayList<Animals> list;
        if (FILE_EXISTED && ins.available() != 0){
            ois = new ObjectInputStream(ins);
            list = (ArrayList<Animals>)ois.readObject();
        }else {
            list = new ArrayList<>();
        }
        OutputStream os = new FileOutputStream("AnimalsSystemVol2/data.ini",true);
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
                    AnimalOperation.addAnimals(list);
                    break;
                case 2:
                    System.out.println("--修改动物--");
                    AnimalOperation.editAnimals(list);
                    break;
                case 3:
                    System.out.println("--删除动物--");
                    AnimalOperation.deleteAnimals(list);
                    break;
                case 4:
                    System.out.println("--查看动物--");
                    AnimalOperation.qurrayAnimals(list);
                    break;
                case 5:
                    System.out.println("--感谢您的使用！--");
                    OutputStream oss = new FileOutputStream("AnimalsSystemVol2/data.ini");
                    oss.close();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(list);
                    oos.close();
                    break lo;
            }
        }
    }
}
