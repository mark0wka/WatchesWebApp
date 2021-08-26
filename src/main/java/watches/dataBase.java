/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wowul
 */
public class dataBase {

    Connection c;

    public dataBase() {
        connect();
    }

    void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\wowul\\Desktop\\Proga\\Java\\lab1\\alarmBase.db");
            } catch (SQLException ex) {
                Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No connection");
            }
            System.out.println("DataBase opened");
        } catch (ClassNotFoundException ex) {
            System.out.println("No driver");
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    ArrayList<Integer> getListFromDb() {
        ArrayList<Integer> resList = new ArrayList<Integer>();
        int rowTime;
        try {
            Statement st = c.createStatement();
            ResultSet res = st.executeQuery("select * from alarmList");
            while (res.next()) {
                rowTime = 0;
                rowTime = res.getInt("Hours") * 3600 + res.getInt("Minutes") * 60 + res.getInt("Seconds");
                resList.add(rowTime);
                System.out.println("Alarm: " + rowTime);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resList;
    }

    void saveMsgtoDb(int H, int M, int S) {
        try {
            PreparedStatement pst = c.prepareStatement("INSERT INTO alarmList(Hours, Minutes, Seconds) VALUES(?,?,?)");
            pst.setInt(1, H);
            pst.setInt(2, M);
            pst.setInt(3, S);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void deleteAlarm(int tiSec) {
        try {
            int m = (tiSec % 3600) / 60;
            int h = (tiSec - (tiSec % 3600)) / 3600;
            int s = (tiSec % 3600) % 60;
            PreparedStatement st = c.prepareStatement("DELETE FROM alarmList WHERE Hours =" + h + " and  Minutes =" + m + " and Seconds =" + s);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
