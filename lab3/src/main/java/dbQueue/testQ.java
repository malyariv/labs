package dbQueue;

import testData.TestData;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class testQ {
    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
    public static void main(String[] args) {
        Queue<TestData> q=new DBQueue<>(adr, false);

        int s=3;
        TestData data=new TestData();
        for (int i=0;i<s;i++) {
            TestData d=new TestData();
            System.out.println(d);
            q.offer(d);
            q.offer(d);
            data=d;
        }
        System.out.println("size="+q.size());

        Set<TestData> set=new HashSet<>();
        set.add(data);
        q.retainAll(set);

        for (TestData d:q){
            System.out.println(d);
        }

    }
}
