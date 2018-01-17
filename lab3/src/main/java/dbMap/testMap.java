package dbMap;

import testData.MyData;
import java.util.Map;

public class testMap {

    private static final String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";

    public static void main(String[] args) {
        Map<String,MyData> map=new DBSafeMap<>(adr,false);

        map.put("1", new MyData());
        map.put("2", new MyData());
        map.put("3", new MyData());
        map.put("4", new MyData());

        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }

        System.out.println("\n"+map.put("1",new MyData())+"\n");

        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }

        System.out.println("\n"+map.put("1",new MyData())+"\n");

        for (Map.Entry<String,MyData> entry:map.entrySet()){
            System.out.println(entry);
        }



    }
}
