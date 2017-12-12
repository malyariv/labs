package businesslogic;
/**
 * The class {@code ReactionAdapterFile}, implementing interface {@code IReactionAdapter},
 * provides receiving information about chemical reaction from text file
 */
import businesslogic.reflection.ChemLib;
import businesslogic.reflection.ReactLibTxt;
import dataclasses.ConnectionProblemException;
import interfaces.IReactionAdapter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReactionAdapterFile implements IReactionAdapter {
    /**
     * Implementation of method of interface {@code IReactionAdapter}.
     */
    @Override
    public List<String> getProducts(String s1, String s2) {
        String products=null;
        String hash="#"+s1;
        try (Scanner scanner=new Scanner(getFilename())){
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
        }catch (Exception e){
            System.out.println("Sorry. We have a problem with a library of chemical reactions");
            throw new ConnectionProblemException();
        }
        if (products ==null) return null;
        products=products.substring(s2.length()+1,products.length());
        return Arrays.asList(products.split("\\+"));
    }

    private File getFilename() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Settings> cls=Settings.class;
        for(Method meth:cls.getDeclaredMethods()){
            if (meth.isAnnotationPresent(ReactLibTxt.class)){
                return (File) meth.invoke(cls.newInstance(), meth.getAnnotation(ReactLibTxt.class).value());
            };
        }
        return null;
    }
}
