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
public interface Timer {
    
    void setTime(int val, SetType t) throws Exception;
    
    void addTime(int val, SetType t) throws Exception;
    
    void setAlarm(int val);
    
    public void starter();
    
    public int getRowTime();
    
    String getTime();
    
    String getName();
    
    String getPrice();
}
