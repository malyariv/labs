package dbList;

import testData.TestData;

import java.util.*;

public class Test {
    public static void main(String[] args) {

        String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
        List<String> list=new DBList<>(adr,false);
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");

        list.remove(1);
        list.add("4");

        for (String s:list) {
            System.out.print(s+"  ");
        }

        System.out.println();

        for (int i=0;i<list.size();i++) {
            System.out.print(list.get(i)+"  ");
        }


    }
}
