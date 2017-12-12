package businesslogic;
/**
 * The class {@code ReactionAdapterDB}, implementing interface {@code IReactionAdapter},
 * provides receiving information about chemical reaction from MySQL database
 */
import businesslogic.reflection.ReactLibDB;
import businesslogic.reflection.ReactLibTxt;
import dataclasses.ConnectionProblemException;
import interfaces.IReactionAdapter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class ReactionAdapterDB implements IReactionAdapter {

    /**
     * Implementation of method of interface {@code IReactionAdapter}.
     */
    @Override
    public List<String> getProducts(String s1, String s2) {
        String query ="SELECT Products FROM ";
        query+=s1+" WHERE COMPONENT=\'"+s2+"\'";
        ResultSet set=null;
        String product=null;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            set= statement.executeQuery(query);
            while (set.next()){
                product=set.getString("Products");
            }

        } catch (SQLException|IllegalAccessException|InstantiationException
                |InvocationTargetException e) {
            System.out.println("Sorry. We have a problem with a library of chemical reactions");
            throw new ConnectionProblemException();
        }
        if (product==null) return null;
        return Arrays.asList(product.split("\\+"));
    }

    private Connection getConnection() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Settings> cls=Settings.class;
        for(Method meth:cls.getDeclaredMethods()){
            if (meth.isAnnotationPresent(ReactLibDB.class)){
                String a=meth.getAnnotation(ReactLibDB.class).address();
                String u=meth.getAnnotation(ReactLibDB.class).username();
                String s=meth.getAnnotation(ReactLibDB.class).password();
                return (Connection) meth.invoke(cls.newInstance(), a,u,s);
            };
        }
        return null;
    }
}
