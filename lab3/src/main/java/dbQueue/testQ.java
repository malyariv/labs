package dbQueue;

import testData.MyData;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class testQ {
    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
    public static void main(String[] args) {
        Queue<MyData> q=new DBSafeQueue<>(adr, false);

        int s=3;
        MyData data=new MyData();
        for (int i=0;i<s;i++) {
            MyData d=new MyData();
            System.out.println(d);
            q.offer(d);
            q.offer(d);
            data=d;
        }
        System.out.println("size="+q.size());

        Set<MyData> set=new HashSet<>();
        set.add(data);
        q.retainAll(set);

        for (MyData d:q){
            System.out.println(d);
        }

    }
}
