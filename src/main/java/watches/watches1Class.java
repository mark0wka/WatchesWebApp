package watches;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class watches1Class implements Timer {

    String name;
    int price;
    int hours;
    int minutes;
    int tiSec = 0;
    ArrayList<Integer> alarms;
    Thread thread;
    boolean flag = true;

    public watches1Class(String name, int price, int hours, int minutes) {
        this.name = name;
        this.price = price;
        alarms = new ArrayList<>();
        if (hours > 0 & hours < 24 & minutes > 0 & minutes < 60) {
            this.hours = hours;
            this.minutes = minutes;
        } else {
            System.out.println("Error: Incorrect time! Time set to 00:00");
            this.hours = 0;
            this.minutes = 0;
        }
    }
    
    public int getRowTime(){
        return this.tiSec;
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price + "$";
    }

    @Override
    public String getTime() {
        int m = (tiSec % 3600) / 60;
        int h = (tiSec - (tiSec % 3600)) / 3600;
        return h + ":" + m;
    }

    @Override
    public void setTime(int val, SetType t) throws Exception {
        if (t == SetType.Hours & val >= 0 & val < 24) {
            hours = val;
            tiSec += val * 3600;
            tick();
            return;
        }
        if (t == SetType.Minutes & val >= 0 & val < 60) {
            minutes = val;
            tiSec += val * 60;
            tick();
            return;
        }
        throw new Exception("error");
    }

    @Override
    public void addTime(int val, SetType t) throws Exception {
        if (t == SetType.Hours & (hours + val) > 0 && (hours + val) <= 24) {
            hours += val;
            return;
        }
        if (t == SetType.Minutes & (minutes + val) > 0 && (minutes + val) <= 60) {
            minutes += val;
            return;
        }
        throw new Exception("incorrect value");
    }

    @Override
    public void setAlarm(int val) {
        alarms.add(val);
    }

    public void tick() {
        for (int i : alarms) {
            if (i == tiSec) {
                if (flag == true) {
                    thread = new Thread() {
                        @Override
                        public void run() {
                            /*try {
                                JPanel AlarmWindow = new JPanel();
                                AlarmWindow.setVisible(true);
                                Thread.sleep(1);
                                JOptionPane.showMessageDialog(AlarmWindow, "Time to get up", "Alarm Notification", JOptionPane.INFORMATION_MESSAGE);
                            } catch (InterruptedException e) {
                                flag = false;
                            }*/
                        }
                    };
                    //thread.start();
                }
                /*if (tiSec == 86400) {
                    thread.interrupt();
                    thread = null;
                }*/
            }
        }
    }

    @Override
    public void starter() {
        tiSec ++;
        tick();
    }
}
