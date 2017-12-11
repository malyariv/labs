package businesslogic;
/**
 * The class {@code ReactionAdapterDB}, implementing interface {@code ReactionAdapter},
 * provides receiving information about chemical reaction from MySQL datbase
 */
import interfaces.ReactionAdapter;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class ReactionAdapterDB implements ReactionAdapter {
    /** an address if a database*/
    private final String URL = "jdbc:mysql://localhost:3306/ChemicalLab";
    /** a username in database*/
    private final String USERNAME = "root";
    /** a password in database*/
    private final String PASSWORD = "root";

    /**
     * Implementation of method of interface {@code ReactionAdapter}.
     */
    @Override
    public List<String> getProducts(String s1, String s2) {
        String query ="SELECT Products FROM ";
        query+=s1+" WHERE COMPONENT=\'"+s2+"\'";
        ResultSet set=null;
        String product=null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {}

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            set= statement.executeQuery(query);
            while (set.next()){
                product=set.getString("Products");
            }

        } catch (SQLException e){e.printStackTrace();}

        if (product==null) return null;
        return Arrays.asList(product.split("\\+"));
    }
}
