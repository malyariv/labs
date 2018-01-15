package test;

import dbList.DBSmartList;
import testData.MyData;

public class TestMain {
    public static void main(String[] args) {
        String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
        DBSmartList<MyData> list=new DBSmartList<>(adr,false);
        MyData myData=new MyData();
        System.out.println(myData+"\n");
        list.add(new MyData());
        list.add(new MyData());
        list.add(myData);
        list.add(new MyData());
        list.add(new MyData());
        list.add(new MyData());
        list.add(new MyData());
        list.add(new MyData());

        for (MyData data:list){
            System.out.println(data);
        }
        System.out.println();

        list.remove(5);
        for (MyData data:list){
            System.out.println(data);
        }
        System.out.println(list.lastIndexOf(myData));





//        System.out.println("\nset to element 1 - "+list.set(1, myData));
//        System.out.println("\ninsert into beginning ");
//        list.add(0, myData);
//
//        for (MyData data:list){
//            System.out.println(data);
//        }
//
//        System.out.println(list.indexOf(myData));

    }
}
