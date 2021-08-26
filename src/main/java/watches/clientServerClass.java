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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wowul
 */

public class clientServerClass extends Thread {

    Socket cs;
    watchesControl wc;
    InputStream is;
    OutputStream os;
    DataInputStream dis;
    DataOutputStream dos;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public clientServerClass(Socket cs, watchesControl wc) {
        this.cs = cs;
        this.wc = wc;
        try {
            os = cs.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException ex) {
            Logger.getLogger(clientServerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.start();
        
    }

    @Override
    public void run() {
        try {
            is = cs.getInputStream();
            dis = new DataInputStream(is);
            while (true) {
                String obj = dis.readUTF();
                TypesHMS type = gson.fromJson(obj, TypesHMS.class);
                wc.setalarm(type);
            }
        } catch (IOException ex) {
            Logger.getLogger(clientServerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendObj (Obj obj){
        try {
            String str = gson.toJson(obj);
            dos.writeUTF(str);    
        } catch (IOException ex) {
            Logger.getLogger(clientServerClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
