package watches;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class watches2Class extends watches1Class {

    int seconds;
    Thread thread;
    boolean flag = true;
    
    public watches2Class(String name, int price, int hours, int minutes, int seconds) {
        super(name, price, hours, minutes);
        if (hours > 0 & hours < 24 & minutes > 0 & minutes < 60 & seconds > 0 & seconds < 60) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        } else {
            System.out.println("Error: Incorrect time! Time set to 00:00:00");
            this.hours = 0;
            this.minutes = 0;
            this.seconds = 0;
        }
    }
    
    @Override
    public void starter() {
        super.starter();
    }
    
    @Override
    public String getTime() {
        int m = (tiSec % 3600) / 60;
        int h = (tiSec - (tiSec % 3600)) / 3600;
        int s = (tiSec % 3600) % 60;
        return h + ":" + m + ":" + s;
    }
    
    public int getRowTime(){
        return tiSec;
    }
    
    @Override
    public void setTime(int val, SetType t) throws Exception {
        try {
        super.setTime(val, t);
        return;
        } catch (Exception e) {
            if (t == SetType.Seconds) {
                seconds = val;
                tiSec += seconds;
                tick();
                return;
            }
        }
        throw new Exception("error");
    }

    @Override
    public void addTime(int val, SetType t) throws Exception {
        if (t == SetType.Hours || t == SetType.Minutes) {
            super.addTime(val, t);
            return;
        }
        if (t == SetType.Seconds && seconds + val < 60 & seconds + val > 0) {
            seconds += val;
            return;
        }
        throw new Exception("incorrect value");
    }
    
    @Override
    public void setAlarm(int val) {
        alarms.add(val);
    }
    
    @Override
    public void tick() {
        super.tick();
        /*for (int i : alarms) {
            if (i == tiSec) {
                if (flag) {
                    thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                JPanel pane = new JPanel();
                                pane.setVisible(true);
                                Thread.sleep(1);
                                JOptionPane.showMessageDialog(pane, "Time to get up", "Alarm Notification", JOptionPane.INFORMATION_MESSAGE);
                            } catch (InterruptedException e) {
                                flag = false;
                            }
                        }
                    };
                    thread.start();
                    System.out.println("Alarm");
                }
                if (tiSec == 86400) {
                    thread.interrupt();
                    thread = null;
                }
            }
        }*/
    }
}
