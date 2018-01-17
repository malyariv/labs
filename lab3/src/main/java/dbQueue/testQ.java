package dbQueue;

import testData.MyData;

import java.util.Queue;

public class testQ {
    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
    public static void main(String[] args) {
        Queue<MyData> q=new DBSafeQueue<>(adr, false);

        int s=3;
        for (int i=0;i<s;i++) {
            MyData d=new MyData();
            System.out.println(d);
            q.offer(d);
            q.offer(d);
        }
        System.out.println("size="+q.size());
        for (int i=0;i<s*2;i++) {
            System.out.println(q.remove());
        }
        System.out.println("size="+q.size());

    }
}
