package dbList;

import testData.TestData;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
        List<TestData> list=new ArrayList<>();
        Set<TestData> set=new HashSet<>();
        DBList<TestData> myList=new DBList<>(adr,false);

        int length=150_000;
        TestData t=new TestData();
        System.out.println(t);
        list.add(t);
        myList.add(new TestData());


        TestData[] a={t,t,t};
        System.out.println(Arrays.toString(a));
        System.out.println();
        TestData[] b=myList.toArray(new TestData[0]);

//        for (int i=0;i<length;i++) {
//            a[i]=new TestData();
//        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        System.out.println(b.length);
    }
}
