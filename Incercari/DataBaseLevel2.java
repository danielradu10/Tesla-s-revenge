package Incercari;

import Levels.TileMap;
import entities.Player;

import java.sql.*;

public class DataBaseLevel2 {

    int x_backup = 50;
    String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
    public void create_table()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        String sql = "CREATE TABLE IF NOT EXISTS Map2Tiles (\n"
                + "	id integer PRIMARY KEY,\n"
                + " tileX integer NOT NULL,\n"
                + " tileY integer NOT NULL \n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {


            // create a new table
            stmt.execute(sql);




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


//    public void insert_in_table()
//    {
//
//        try
//        {
//            Class.forName("org.sqlite.JDBC");
//
//        } catch (Exception e) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//
//
//
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//
//              String INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (50, 600);";
//              stmt.executeUpdate(INSERT);
//
//
//
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (630, 250);";
//            stmt.executeUpdate(INSERT);
//
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (50, 250);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (170, 300);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (350, 325);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (500, 280);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (745, 300);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (845, 500);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (200, 580);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (345, 560);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (485, 540);";
//            stmt.executeUpdate(INSERT);
//            INSERT = "INSERT INTO Map2Tiles(tileX, tileY)" + "VALUES (655, 520);";
//            stmt.executeUpdate(INSERT);
//
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public TileMap search_in_table_set_tile(int id, String tableName)
    {

        TileMap tileMap = null;

        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            //caut in tabel tilempul cu id ul respectiv
            String verify = "SELECT * FROM " + tableName + " WHERE id = '" + id + "' ";

            try(ResultSet rs = stmt.executeQuery(verify)) {


                if (rs.next()) {
                    //preiau coordonatele
                    int x = rs.getInt("tileX");
                    int y = rs.getInt("tileY");
                    tileMap = new TileMap(x, y, 64, 128, 3);
                } else {
                    //daca nu gasesc
                    System.out.println("Nu am gasit asemenea id ");
                }
            }catch (SQLException e)
            {
                //prin exceptia aruncata in cazul in care nu a fost gasit tabelul
                if(tableName != "Map2Tiles")
                {
                    //creez niste tilemapuri de backup din 50 in 50
                    tileMap = new TileMap(x_backup, 550, 64, 128, 3);
                    x_backup = x_backup + 128;
                    //arunc exceptia creata
                    throw new NotFoundException("Tilempaurile se afla in tabelul" + tableName);
                }

            }
        } catch (SQLException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return  tileMap;
    }
}
