package businesslogic;

import businesslogic.reflection.ChemLib;
import businesslogic.reflection.ReactLibDB;
import businesslogic.reflection.ReactLibTxt;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Settings {

    @ChemLib("substances.txt")
    public File getChemicalsLibrary(String s){
        return new File(s);
    }

    @ReactLibTxt("reactions.txt")
    public File getReactionAdapterFile(String s){
        return new File(s);
    }

    @ReactLibDB(address = "jdbc:mysql://localhost:3306/ChemicalLab",
            username = "root", password = "root")
    public Connection getReactorAdapterConnection(String a, String u, String p) throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {}

        return DriverManager.getConnection(a, u, p);
    }

}
