package data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataNew {
    private static DataNew ourInstance = new DataNew();
    public static DataNew getInstance() {
        return ourInstance;
    }

    private Map<String,String> names=new HashMap<>();
    private Map<String,Boolean> solubility=new HashMap<>();

    {
        names.put("NaCl", "salt");
        names.put("KCl", "salt");
        names.put("MgCl2", "salt");
        names.put("CaCl2", "salt");
        names.put("CuCl2", "salt");
        names.put("ZnCl2", "salt");
        names.put("FeCl2", "salt");
        names.put("AgCl", "salt");
    }

    private DataNew() {
        fillSolubilities();
    }

    public boolean getSolubility(String formula){
        return solubility.get(formula);
    }

    private void fillSolubilities(){
        File file=new File("solubility.txt");
        try(Scanner scanner=new Scanner(new FileReader(file))){
            while (scanner.hasNext()){
                String[] d=scanner.nextLine().split(" ");
                if(d[1].equals("y")) solubility.put(d[0],true);
                else solubility.put(d[0],false);
            }
        }catch (IOException e){e.printStackTrace();}
    }

}
