/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import static watches.SetType.Hours;
import static watches.SetType.Minutes;
import static watches.SetType.Seconds;

/**
 *
 * @author wowul
 */
public class watchesControl {

    Timer watches;
    Thread thread;
    Socket cs;
    InputStream is;
    OutputStream os;
    DataInputStream dis;
    DataOutputStream dos;
    Alarm alarm;
    JLabel timeLabel;
    JLabel statusLabel;
    boolean startBool;
    boolean pauseBool = false;
    ArrayList<clientServerClass> list;
    Obj obj;
    dataBase database;
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public watchesControl(Timer timer) {
        database = new dataBase();
        list = new ArrayList<clientServerClass>();
        obj = new Obj("",false);
        this.watches = timer;
    }

    public void stop() {
        if (thread != null) {
            statusLabel.setText("Stop");
            thread.interrupt();
            thread = null;
            try {
                watches.setTime(0, Hours);
                watches.setTime(0, Minutes);
                watches.setTime(0, Seconds);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void pause() {
        pauseBool = true;
    }

    public void contin() {
        synchronized (thread) {
            statusLabel.setText("Continue");
            thread.notifyAll();
        }
    }

    public void start(Socket s) {
        this.cs = s;
        try {
            os = cs.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException ex) {
            Logger.getLogger(watchesControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (thread == null) {
            //statusLabel.setText("Start");
            thread = new Thread() {
                @Override
                public void run() {
                    startBool = true;
                    while (startBool == true) {
                        try {
                            check(watches.getRowTime());
                            for(clientServerClass i :list) {
                                obj.Time=watches.getTime();
                                obj.Cost = watches.getPrice();
                                obj.Name = watches.getName();
                                i.sendObj(obj);
                            }
                            obj.Alarm = false;
                            watches.starter();
                            Thread.sleep(1000);
                            //send(watches.getTime() + "");
                        } catch (InterruptedException ex) {
                            startBool = false;
                        }
                    }
                }
            };
            thread.start();
        }
    }

    /*public void send(String s) {
        try {
            dos.writeUTF(s);
        } catch (IOException ex) {
            Logger.getLogger(clientServerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    public void setalarm(TypesHMS type) {
        if(type.DEL == true)
        {
            int time = type.HOURS * 3600 + type.MINS * 60 + type.SECS;
            database.deleteAlarm(time);
        }
        else
        {
        database.saveMsgtoDb(type.HOURS,type.MINS,type.SECS);
        }
    }
    
    public void check(int time) {
            ArrayList<Integer> alarm = database.getListFromDb();
            for(int i : alarm) {
                if(i == time) {
                    obj.Alarm = true;
                    database.deleteAlarm(time);
            }
        }  
    }
}
