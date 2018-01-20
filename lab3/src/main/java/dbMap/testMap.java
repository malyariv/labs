package dbMap;

import concurrent.DBSynchronizedMap;
import testData.MyData;

import java.util.Collections;
import java.util.Map;

public class testMap {

    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";

    public static void main(String[] args) {
        Map<String,MyData> map=new DBSynchronizedMap<>(new DBSafeMap<>(adr,false));
//        Map<String,MyData> map=new DBSafeMap<>(adr,false);

        map.put("1", new MyData());
        map.put("2", new MyData());
        map.put("3", new MyData());
        map.put("4", new MyData());
        System.out.println(map);

        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }

        map.clear();
        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }
        System.out.println(map.size());

        map.put("1", new MyData());
        map.put("2", new MyData());
        map.put("3", new MyData());
        map.put("4", new MyData());

        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }

        map.clear();
        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }
        System.out.println(map.size());
        map.remove("1");
        map.remove("1");
        map.remove("2");
        map.remove("3");
        map.remove("4");

        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }

//
//        System.out.println("\n"+map.put("1",new MyData())+"\n");
//
//        for (Map.Entry<String,MyData> entry:map.entrySet()){
//            System.out.println(entry);
//        }
//
//        System.out.println("\n"+map.put("1",new MyData())+"\n");
//
//        for (Map.Entry<String,MyData> entry:map.entrySet()){
//            System.out.println(entry);
//        }



    }
}
