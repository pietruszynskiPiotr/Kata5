/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 *
 * @author piotr
 */
public class Kata5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Kata5 app = new Kata5();
        app.createNewTable();
        List<String> input = app.input();
        app.process(input);
    }

    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS direcc_email (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " direccion text NOT NULL);";
        try ( Connection conn = this.connect();  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String email) {
        String sql = "INSERT INTO direcc_email(direccion) VALUES(?)";
        try ( Connection conn = this.connect();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:Kata5.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connexión a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private List<String> input() throws IOException {
        MailListReader reader = new MailListReader();
        return reader.read("emails.txt");
    }

    private void process(List<String> mails) {
        mails.forEach(m -> insert(m));
    }

}
