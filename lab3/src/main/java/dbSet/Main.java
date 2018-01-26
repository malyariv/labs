package dbSet;

import testData.TestData;

import java.util.*;

public class Main {
    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
    public static void main(String[] args) {
//        long time=System.currentTimeMillis();
//        new Main().run();
//        System.out.println("\n Required time "+(System.currentTimeMillis()-time)/100+" ds");

        DBSet<String> set1=new DBSet<>(adr,false);
    }
    private void run(){

        String adr="/home/ivan/NetCracker/MapDB/src/main/java/Data/";
//        DBSet<TestData> set=new DBSet<TestData>(adr,false);
//        for (int i=0;i<150_000;i++) {
//            set.add(new TestData());
//        }
        Set<TestData> trueSet=new HashSet<>();

        int s=150_000;
        TestData[] data=new TestData[s];
        for(int i=0;i<s;i++) {
            data[i]=new TestData();
        }

//        long time=System.currentTimeMillis();
//        trueSet.addAll(Arrays.asList(data));
//        System.out.println("trueSetSize="+trueSet.size());
//        time=System.currentTimeMillis()-time;
//        System.out.println("Time of trueSet filling ="+time/10+" cs");

        for (int i=0;i<s;i++){
            trueSet.add(data[i]);
        }
        System.out.println("trueSetSize="+trueSet.size());

        DBSet<TestData> newSet=new DBSet<>(adr,false);

//        time=System.currentTimeMillis();
        for (int i=s-1;i>=0;i--) {
            newSet.add(data[i]);
        }
//        for (int i=1; i<=150_000;i++){
//            newSet.add(new TestData());
//        }
        System.out.println("mySetSize="+newSet.size());
//        time=System.currentTimeMillis()-time;
//        System.out.println("Time of mySet filling ="+time/10+" cs");
//        trueSet.remove(new TestData());
//        System.out.println(newSet.removeAll(trueSet));
        newSet.removeAll(trueSet);
        System.out.println(newSet.size());


//        DBSet<TestData> set=new DBSet<TestData>("/home/ivan/NetCracker/MapDB/src/main/java/data2/",trueSet);
//        System.out.println(set.add(new TestData()));

//        time=System.currentTimeMillis();
//        for(TestData d:trueSet){
//            newSet.remove(d);
//        }
//        System.out.println("Time of mySet removing ="+(System.currentTimeMillis()-time)/100+" ds");
//        System.out.println("After remove mySetSize="+newSet.size());
//        System.out.println("Time to remove from file "+FileUtils.removeFromFoleTime/1000+" sec");
//        System.out.println(Arrays.toString(newSet.toArray()));
//        System.out.println("Iterator time="+(System.currentTimeMillis()-time)/100+" ds");
    }


}

