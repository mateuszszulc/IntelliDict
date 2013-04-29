package testing.stringformat;

/**
 * Created by IntelliJ IDEA.
 * User: wro00305
 * Date: 23.04.12
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.util.Date;

public class StringFormatterTest {

    public static void main(String[] args) {
        int i = 0;
        long prev_time = System.currentTimeMillis();
        long time;

        for (i = 0; i < 100000; i++) {
            String s = new String("Blah ") + i + new String(" Blah");
        }
        time = System.currentTimeMillis() - prev_time;

        System.out.println("Time after for loop " + time);

        prev_time = System.currentTimeMillis();
        for (i = 0; i < 100000; i++) {
            String s = String.format("Blah %d Blah", i);
        }
        time = System.currentTimeMillis() - prev_time;
        System.out.println("Time after for loop " + time);

    }
}

