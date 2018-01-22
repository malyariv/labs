package dbSet;

import concurrent.DBSynchronizedSet;
import testData.MyData;

import java.util.*;

public class Main {
    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
    public static void main(String[] args) {
//        long time=System.currentTimeMillis();
//        new Main().run();
//        System.out.println("\n Required time "+(System.currentTimeMillis()-time)/100+" ds");

        DBSynchronizedSet<String> set1=new DBSynchronizedSet<>(new DBSet<String>(adr,false));
        set1.add("0");
        set1.add("1");
        set1.add("2");
        set1.add("3");
        set1.add("4");
        set1.add("5");
        set1.add("6");
        System.out.println("set1="+set1);

        set1.remove("1");
        set1.remove("0");
        System.out.println("set1="+set1);



//        set1.save();

//        DBSet<MyData> set2=new DBSet<MyData>(adr,true);
//        System.out.println("set2="+Arrays.toString(set2.toArray()));

//        set1=null;
//        System.out.println("set1="+set1);
//        System.out.println("set2="+Arrays.toString(set2.toArray()));

//        long freeMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
//        System.out.println(freeMemory/1024/1024);
    }
    private void run(){

        String adr="/home/ivan/NetCracker/MapDB/src/main/java/Data/";
//        DBSet<MyData> set=new DBSet<MyData>(adr,false);
//        for (int i=0;i<150_000;i++) {
//            set.add(new MyData());
//        }
        Set<MyData> trueSet=new HashSet<>();

        int s=150_000;
        MyData[] data=new MyData[s];
        for(int i=0;i<s;i++) {
            data[i]=new MyData();
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

        DBSet<MyData> newSet=new DBSet<>(adr,false);

//        time=System.currentTimeMillis();
        for (int i=s-1;i>=0;i--) {
            newSet.add(data[i]);
        }
//        for (int i=1; i<=150_000;i++){
//            newSet.add(new MyData());
//        }
        System.out.println("mySetSize="+newSet.size());
//        time=System.currentTimeMillis()-time;
//        System.out.println("Time of mySet filling ="+time/10+" cs");
//        trueSet.remove(new MyData());
//        System.out.println(newSet.removeAll(trueSet));
        newSet.removeAll(trueSet);
        System.out.println(newSet.size());


//        DBSet<MyData> set=new DBSet<MyData>("/home/ivan/NetCracker/MapDB/src/main/java/data2/",trueSet);
//        System.out.println(set.add(new MyData()));

//        time=System.currentTimeMillis();
//        for(MyData d:trueSet){
//            newSet.remove(d);
//        }
//        System.out.println("Time of mySet removing ="+(System.currentTimeMillis()-time)/100+" ds");
//        System.out.println("After remove mySetSize="+newSet.size());
//        System.out.println("Time to remove from file "+FileUtils.removeFromFoleTime/1000+" sec");
//        System.out.println(Arrays.toString(newSet.toArray()));
//        System.out.println("Iterator time="+(System.currentTimeMillis()-time)/100+" ds");
    }


}

