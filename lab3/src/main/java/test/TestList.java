package test;

import dbList.DBList;
import dbList.DBCacheList;
import testData.TestData;

import java.util.*;

public class TestList {
    List<TestData> myList;
    public static void main(String[] args) {

        new TestList().run();
    }
    private void run(){
        System.out.println("Эта программа предназначена для тестирования dbList, позволяющей хранить данные на диске.");
        System.out.println("Для инициализации dbList необходимо указать имя папки для хранения. \nПРЕДУПРЕЖДАЮ! Данные в папке будут удалены при инициализации!");
        System.out.println("В качесте тестовых данных будет выступать данные о людях. Размер одного объекта полрядка 2.2 kB. Всего 12285 уникальных варианта.");
        System.out.println(new TestData());
        System.out.println("Для работы может потребоваться около 300 MB на жестком диске.");

        System.out.println("\nВведите название папки для хранения");
        Scanner scanner=new Scanner(System.in);
        String folder=scanner.nextLine();
        System.out.println("Есть две реализации List: одна пишет каждый элемент на диск, что безопасно, у второй имеется кеш, что немного быстрее.");
        System.out.println("Какую реализацию выбираете? Первую(1) или вторую(2)");
        int i=Integer.valueOf(scanner.nextLine());
        if (i==1) {
            myList=new DBList<TestData>(folder,false);
        }
        else {
            myList=new DBCacheList<TestData>(folder,false);
        }
        mainMenu();
        label:
        while (true){
            switch (read()){
                case 1: work(); break;
                case 2: compare(); mainMenu();break;
                default: break label;
            }
        }

    }
    private int read(){
        Scanner scanner=new Scanner(System.in);
        return Integer.valueOf(scanner.nextLine());
    }
    private void mainMenu(){
        System.out.println("1. Поработать с dbList");
        System.out.println("2. Сравнить dbList с ArrayList");
        System.out.println("3. Выйти");
    }
    private void work(){
//        myList.clear();
        workMenu();
        label1:
        while (true){
            switch (read()){
                case 1: addMenu();break;
                case 2: removeMenu(); break;
                case 3: System.out.println("Размер dbList - "+myList.size()); break;
                case 4: print(); break;
                default: break label1;
            }
            workMenu();
        }
        mainMenu();

    }
    private void workMenu(){
        System.out.println("1. Добавить элементы в dbList");
        System.out.println("2. Удалить элементы из dbList");
        System.out.println("3. Вывести размер dbList");
        System.out.println("4. Вывести содержимое dbList");
        System.out.println("5. Выйти");
    }
    private void addMenu(){
        System.out.println("Сколько элементов добавить?");
        Scanner scanner=new Scanner(System.in);
        int q=Integer.valueOf(scanner.nextLine());
        int i=0;
        while (i<q){
            if (myList.add(new TestData())) i++;
        }
    }
    private void removeMenu(){
        System.out.println("Сколько элементов удалить?");
        Scanner scanner=new Scanner(System.in);
        int q=Integer.valueOf(scanner.nextLine());
        int i=0;
        Iterator<TestData> iterator=myList.iterator();
        while (i<q&&iterator.hasNext()){
            iterator.next();
            iterator.remove();
            i++;
        }
    }
    private void print(){
        for (TestData data:myList){
            System.out.println(data);
        }
        System.out.println();
    }
    private void compare(){
        if (!myList.isEmpty()){
            myList.clear();
        }
        TestData element=new TestData();
        int arraySize=150_721;
        TestData[] array=new TestData[arraySize];
        for (int i=0;i<arraySize;i++){
            array[i]=new TestData();
        }
        System.out.println("Был создан массив, содержащий "+arraySize+" случайных элементов.");

        long time=System.currentTimeMillis();
        List<TestData> trueList=new ArrayList<>(Arrays.asList(array));
        time=System.currentTimeMillis()-time;
        System.out.println("Время заполнения ArrayList составило "+time+" ms");
        System.out.println("Размер ArrayList составил "+trueList.size());
        System.out.println();

        time=System.currentTimeMillis();
        for (TestData data:array){
            myList.add(data);
        }
        time=System.currentTimeMillis()-time;
        System.out.println("Время заполнения dbList составило "+time/1000+" s");
        System.out.println("Размер dbList составил "+trueList.size());


        System.out.println("Поиск вхождений элемента "+element);
        time=System.currentTimeMillis();
        int ind=trueList.indexOf(element);
        System.out.println("Индекс первого вхождения - "+ind);
        time=System.currentTimeMillis()-time;
        System.out.println("Время поиска в ArrayList составило "+time+" ms");


        time=System.currentTimeMillis();
        System.out.println("Индекс последнего вхождения - "+trueList.lastIndexOf(element));
        time=System.currentTimeMillis()-time;
        System.out.println("Время поиска в ArrayList составило "+time+" ms");

        time=System.currentTimeMillis();
        System.out.println("Индекс первого вхождения - "+myList.indexOf(element));
        time=System.currentTimeMillis()-time;
        System.out.println("Время поиска в dbList составило "+time+" ms");

        time=System.currentTimeMillis();
        System.out.println("Индекс последнего вхождения - "+myList.lastIndexOf(element));
        time=System.currentTimeMillis()-time;
        System.out.println("Время поиска в dbList составило "+time+" ms");


        time=System.currentTimeMillis();
        int limit=trueList.size();
        for (int i=0;i<limit;i++){
            trueList.remove(0);
        }
        time=System.currentTimeMillis()-time;
        System.out.println("Время удаления по элементам remove(0) из ArrayList составило "+time+" ms");
        System.out.println("Размер ArrayList составил "+trueList.size());
        System.out.println();



        time=System.currentTimeMillis();
        for (int i=0;i<arraySize;i++){
            myList.remove(0);
        }
        time=System.currentTimeMillis()-time;
        System.out.println("Время удаления по элементам из dbList составило "+time/1000+" s");
        System.out.println("Размер dbList составил "+myList.size());
        System.out.println();
    }
}
