/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

/**
 *
 * @author wowul
 */
public class Alarm {

    public Alarm(Timer timer, int hours, int minutes, int seconds) {
        int time = hours * 3600 + minutes * 60 + seconds;
        timer.setAlarm(time);
    }

    public void createAlarm(Timer timer, int hours, int minutes, int seconds) {
        int time = hours * 3600 + minutes * 60 + seconds;
        timer.setAlarm(time);
    }
}
