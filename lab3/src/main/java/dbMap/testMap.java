package dbMap;

import concurrent.DBSynchronizedMap;
import testData.TestData;

import java.util.Map;

public class testMap {

    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";

    public static void main(String[] args) {
        Map<String, TestData> map=new DBSynchronizedMap<>(new DBMap<>(adr,false));
//        Map<String,TestData> map=new DBMap<>(adr,false);

        map.put("1", new TestData());
        map.put("2", new TestData());
        map.put("3", new TestData());
        map.put("4", new TestData());
        System.out.println(map);

        for (Map.Entry<String, TestData> entry:map.entrySet()){
            System.out.println(entry);
        }

        map.clear();
        for (Map.Entry<String, TestData> entry:map.entrySet()){
            System.out.println(entry);
        }
        System.out.println(map.size());

        map.put("1", new TestData());
        map.put("2", new TestData());
        map.put("3", new TestData());
        map.put("4", new TestData());

        for (Map.Entry<String, TestData> entry:map.entrySet()){
            System.out.println(entry);
        }

        map.clear();
        for (Map.Entry<String, TestData> entry:map.entrySet()){
            System.out.println(entry);
        }
        System.out.println(map.size());
        map.remove("1");
        map.remove("1");
        map.remove("2");
        map.remove("3");
        map.remove("4");

        for (Map.Entry<String, TestData> entry:map.entrySet()){
            System.out.println(entry);
        }

//
//        System.out.println("\n"+map.put("1",new TestData())+"\n");
//
//        for (Map.Entry<String,TestData> entry:map.entrySet()){
//            System.out.println(entry);
//        }
//
//        System.out.println("\n"+map.put("1",new TestData())+"\n");
//
//        for (Map.Entry<String,TestData> entry:map.entrySet()){
//            System.out.println(entry);
//        }



    }
}
