package dbmysql;
import Interfaces.IFSubstanceInfo;
import dataclasses.*;
import java.sql.*;

public class SubstAdapterMySQL implements IFSubstanceInfo{
    private static SubstAdapterMySQL ourInstance = new SubstAdapterMySQL();

    public static SubstAdapterMySQL getInstance() {
        return ourInstance;
    }

    private SubstAdapterMySQL() {
    }

    private final String URL = "jdbc:mysql://localhost:3306/ChemicalLab";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private String template="SELECT * FROM Substance where formula=\"";


    public Substance getSubstance(String formula) {
        String query=template+formula;
        query+="\"";
        ResultSet set=null;
        Substance substance=new Substance();
        substance.setFormula(formula);

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {e.printStackTrace();}

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            set= statement.executeQuery(query);
            while (set.next()){
                substance.setElements(Elements.getElements(formula));
                substance.setMolarMass(Elements.getMolarMass(substance.getElements()));
                substance.setDensity(set.getDouble("density"));
                substance.setName(set.getString("name"));
                substance.setState(State.valueOf(set.getString("state").toUpperCase()));
            }

        } catch (SQLException e){}
        return substance;
    }


}
