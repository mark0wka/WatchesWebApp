/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wowul
 */
public class serverClass {

    int port = 3123;
    InetAddress host;
    watchesControl wc;
    Timer watches = new watches2Class("CASIO", 9990, 15, 31, 14);
    Thread thread;

    public serverClass() {
        wc = new watchesControl(watches);
        try {
            watches.setTime(0, SetType.Hours);
            watches.setTime(0, SetType.Minutes);
            watches.setTime(0, SetType.Seconds);
        } catch (Exception ex) {
            Logger.getLogger(serverClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(serverClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        thread = new Thread() {
            @Override
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(port, 0, host);
                    System.out.println("Server started");
                    int count = 0;

                    while (true) {
                        Socket cs = ss.accept();
                        count++;
                        System.out.println("Ñlient connected");

                        if (count == 1) {
                            wc.start(cs);
                        }

                        clientServerClass csc = new clientServerClass(cs, wc);
                        wc.list.add(csc);
                        
                    }

                } catch (IOException ex) {
                    Logger.getLogger(serverClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        thread.start();
    }

    public static void main(String[] args) {
        new serverClass();
    }
}
