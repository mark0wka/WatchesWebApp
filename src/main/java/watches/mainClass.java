package watches;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static watches.SetType.Hours;
import static watches.SetType.Minutes;
import static watches.SetType.Seconds;

public class mainClass {

    public static void main(String[] args) throws IOException {

        System.setProperty("console.encoding", "Cp866");
        System.out.println("\n" + "****** Correct example ******" + "\n");
        watches1Class casio = new watches1Class("CASIO", 9990, 15, 31);
        casio.getName();
        casio.getPrice();
        casio.getTime();
        try {
            casio.setTime(23, Hours);
            casio.setTime(23, Minutes);
            casio.getTime();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        /*System.out.println("\n");
        watches2Class gshock = new watches2Class("G-SHOCK", 14990, 9, 55, 34);
        gshock.getName();
        gshock.getPrice();
        gshock.getTime();
        gshock.setTime(12, Hours);
        gshock.setTime(12, Minutes);
        gshock.setTime(54, Seconds);
        gshock.getTime();

        System.out.println("\n" + "****** Incorrect example ******" + "\n");
        watches1Class swatch = new watches1Class("SWATCH", 39990, 15, 64);
        swatch.getName();
        swatch.getPrice();
        swatch.getTime();
        swatch.setTime(30, 33);

        System.out.println("\n");
        watches2Class diesel = new watches2Class("DIESEL", 155000, 25, 34, 34);
        diesel.getName();
        diesel.getPrice();
        diesel.getTime();
        diesel.setTime(12, 77, 21);

        System.out.println("\n" + "****** DataInputStream using example ******" + "\n");
        String timeLoc;
        int a, b, c;
        DataInputStream time1 = new DataInputStream(System.in);
        timeLoc = time1.readLine();
        a = Integer.parseInt(timeLoc);
        DataInputStream time2 = new DataInputStream(System.in);
        timeLoc = time2.readLine();
        b = Integer.parseInt(timeLoc);
        DataInputStream time3 = new DataInputStream(System.in);
        timeLoc = time3.readLine();
        c = Integer.parseInt(timeLoc);

        gshock.getName();
        gshock.getPrice();
        gshock.getTime();
        gshock.setTime(a, b, c);
        System.out.println("\n" + "****** Ending... ******" + "\n");*/
    }
}
