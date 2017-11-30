package dbmysql;

import Interfaces.IFReactionInfo;
import dataclasses.Condition;
import dataclasses.Reaction;

import java.sql.*;
import java.util.*;

public class ReactionAdapterMySQL implements IFReactionInfo {
    private final String URL = "jdbc:mysql://localhost:3306/ChemicalLab";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private String query1 ="SELECT * FROM Reaction";
    private String query2 ="SELECT * FROM Names";



    public Map<String, Reaction> getReactions() {
        ResultSet set=null;
        Map<String, Reaction> reactions=new HashMap<>();

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {}

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            set= statement.executeQuery(query1);
            while (set.next()){
                String name=set.getString("mainProduct");
                Reaction reaction=getReaction(set.getString("equation"));
                reaction.setEquation(set.getString("equation"));
                reaction.setYield(set.getDouble("yield"));
                Condition condition=new Condition();
                if (!set.getString("temperature").equals("")){
                    condition.setTemperature(set.getInt("temperature"));
                }
                if (set.getBoolean("electrolysis")==true) {
                    condition.setElectrolysis(true);
                }
                if (set.getBoolean("exhaust")==true) {
                    condition.setExhaust(true);
                }
                if (set.getString("catalyst")!=null) {
                    condition.setCatalyst(set.getString("catalyst"));
                }
                reaction.setCondition(condition);
                reactions.put(name, reaction);
            }

        } catch (SQLException e){e.printStackTrace();}
        return reactions;
    }

    @Override
    public Map<String, String> getNames() {
        ResultSet set=null;
        Map<String,String> names=new HashMap<>();

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {}

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            set= statement.executeQuery(query2);
            while (set.next()){
                names.put(set.getString("name"),set.getString("chemName"));
            }
        }catch (SQLException e){e.printStackTrace();}
        return names;
    }

    private Reaction getReaction(String s){
        Reaction reaction=new Reaction();
        List<String> chems=new ArrayList<>();
        List<Integer> coeff=new ArrayList<>();
        String[] parts=s.split("->");
        for (int j=0;j<2;j++) {
            for (String str : parts[j].split("\\W+")) {
                try {
                    if (Integer.valueOf(str) != null) coeff.add(Integer.valueOf(str));
                } catch (Exception e) {
                    if (str.length() > 0) chems.add(str);
                }
            }
            if (j==0) {
                reaction.setReagents(chems);
                reaction.setReagentsCoeff(coeff.toArray(new Integer[coeff.size()]));
                chems=new ArrayList<>();
                coeff=new ArrayList<>();
            }
            else {
                reaction.setProducts(chems);
                reaction.setProductsCoeff(coeff.toArray(new Integer[coeff.size()]));
            }
        }
        return reaction;
    }



}
