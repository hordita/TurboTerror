package main;

import java.sql.*;
public class DataBase {
    public static Connection conn = null;
    public static Statement stmt = null;

    public DataBase(){
        try{
            String dbname = "org.sqlite.JDBC";
            Class.forName(dbname);
            String dbPath = "jdbc:sqlite:TurboTerror.db";
            conn = DriverManager.getConnection(dbPath);
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Scores" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Score INT NOT NULL)";
            stmt.execute(sql);
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("DataBase objected created successfully");
    }
    public void InsertScore( int Score) {
        try{
            conn.setAutoCommit(false);
            String sql = "INSERT OR IGNORE INTO Scores (Score) VALUES (?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,Score);

            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Insertion in table was successfully");
    }
    public int SelectForJTable(){
        try
        {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Scores order by ID desc ");
            int Score= rs.getInt("Score");
            rs.close();
            stmt.close();
            return Score;
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("SelectTable operation successfully");
        return 0;
    }
}


