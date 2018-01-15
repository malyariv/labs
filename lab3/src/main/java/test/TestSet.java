package test;

import dbSet.DBSet;
import testData.MyData;

import java.util.*;

public class TestSet {
    DBSet<MyData> mySet=null;
    public static void main(String[] args) {
        new TestSet().run();
    }
    private void run(){
        System.out.println("Эта программа предназначена для тестирования dbSet, позволяющей хранить данные на диске.");
        System.out.println("Для инициализации dbSet необходимо указать имя папки для хранения. \nПРЕДУПРЕЖДАЮ! Данные в папке будут удалены при инициализации!");
        System.out.println("В качесте тестовых данных будет выступать данные о людях. Размер одного объекта полрядка 2.2 kB. Всего 12285 уникальных варианта.");
        System.out.println(new MyData());
        System.out.println("Для работы потребуется около 30 MB на жестком диске.");
        System.out.println("\nВведите название папки для хранения");
        Scanner scanner=new Scanner(System.in);
        String folder=scanner.nextLine();
        mySet=new DBSet<MyData>(folder,false);
        mainMenu();
        label:
        while (true){
            switch (read()){
                case 1: work(); break;
                case 2: compare(); break;
                default: break label;
            }
        }

    }
    private int read(){
        Scanner scanner=new Scanner(System.in);
        return Integer.valueOf(scanner.nextLine());
    }

    private void mainMenu(){
        System.out.println("1. Поработать с dbSet");
        System.out.println("2. Сравнить dbSet с HashSet");
        System.out.println("3. Выйти");
    }
    private void work(){
        mySet.clear();
        workMenu();
        label:
        while (true){
            switch (read()){
                case 1: addMenu(); break;
                case 2: removeMenu(); break;
                case 3:
                    System.out.println("Размер dbSet - "+mySet.size()); break;
                case 4: print(); break;
                default: break label;
            }
            workMenu();
        }

    }
    private void workMenu(){
        System.out.println("1. Добавить элементы в dbSet");
        System.out.println("2. Удалить элементы из dbSet");
        System.out.println("3. Вывести размер dbSet");
        System.out.println("4. Вывести содержимое dbSet");
        System.out.println("5. Выйти");
    }
    private void addMenu(){
        System.out.println("Сколько элементов добавить?");
        Scanner scanner=new Scanner(System.in);
        int q=Integer.valueOf(scanner.nextLine());
        int i=0;
        while (i<q){
            if (mySet.add(new MyData())) i++;
        }
    }
    private void removeMenu(){
        System.out.println("Сколько элементов удалить?");
        Scanner scanner=new Scanner(System.in);
        int q=Integer.valueOf(scanner.nextLine());
        int i=0;
        Iterator<MyData> iterator=mySet.iterator();
        while (i<q&&iterator.hasNext()){
            iterator.next();
            iterator.remove();
            i++;
        }
    }
    private void print(){
        for (MyData data:mySet){
            System.out.println(data);
        }
        System.out.println();
    }
    private void compare(){
        mySet.clear();
        int arraySize=150_000;
        MyData[] array=new MyData[arraySize];
        for (int i=0;i<arraySize;i++){
            array[i]=new MyData();
        }
        System.out.println("Был создан массив, содержащий "+arraySize+" случайных элементов.");

        long time=System.currentTimeMillis();
        Set<MyData> trueSet=new HashSet<>(Arrays.asList(array));
        time=System.currentTimeMillis()-time;
        System.out.println("Время заполнения HashSet составило "+time+" ms");
        System.out.println("Размер HashSet составил "+trueSet.size());
        System.out.println();

        time=System.currentTimeMillis();
        for (MyData data:array){
            mySet.add(data);
        }
        time=System.currentTimeMillis()-time;
        System.out.println("Время заполнения dbSet составило "+time+" ms");
        System.out.println("Размер dbSet составил "+trueSet.size());
        System.out.println();

        Set<MyData> newSet=new HashSet<>(trueSet);
        time=System.currentTimeMillis();
        for (MyData data:newSet){
            trueSet.remove(data);
        }
        time=System.currentTimeMillis()-time;
        System.out.println("Был создан еще один HashSet. Время удаления по элементам из первого HashSet составило "+time+" ms");
        System.out.println("Размер HashSet составил "+trueSet.size());
        System.out.println();

        time=System.currentTimeMillis();
        for (MyData data:newSet){
            mySet.remove(data);
        }
        time=System.currentTimeMillis()-time;
        System.out.println("Был создан еще один HashSet. Время удаления по элементам из dbSet составило "+time+" ms");
        System.out.println("Размер dbSet составил "+mySet.size());
        System.out.println();
        mainMenu();
    }
}
