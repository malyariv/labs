package businesslogic;
/**
 * The class {@code ReactionAdapterDB}, implementing interface {@code ReactionAdapter},
 * provides receiving information about chemical reaction from text file
 */
import interfaces.ReactionAdapter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReactionAdapterFile implements ReactionAdapter{
    /**
     * Implementation of method of interface {@code ReactionAdapter}.
     */
    @Override
    public List<String> getProducts(String s1, String s2) {
        String products=null;
        String hash="#"+s1;
        try (Scanner scanner=new Scanner(new File("reactions.txt"));){
            label:
            while (scanner.hasNext()){
                if(scanner.nextLine().equals(hash)){
                    String s="";
                    while(scanner.hasNext()&&!s.startsWith("#")) {
                        s = scanner.nextLine();
                        if (s.startsWith(s2)) {
                            products = s;
                            break label;
                        }
                    }
                }
            }
        }catch (IOException e){e.printStackTrace();}
        if (products ==null) return null;
        products=products.substring(s2.length()+1,products.length());
        return Arrays.asList(products.split("\\+"));
    }
}
