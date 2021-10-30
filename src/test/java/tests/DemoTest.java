package tests;

import org.testng.annotations.Test;
import pages.Demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

/**
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both
 * {@link Demo#main(String[])} and
 * {@link Demo#isTriangle(double, double, double)}).
 */

public class DemoTest {

    @Test
    public void test_isTriangle_1(){
        assertTrue(Demo.isTriangle(5,12,13));
    }

    @Test
    public void test_isTriangle_2(){
        assertTrue(Demo.isTriangle(3,4,5));
    }

    @Test
    public void test_isTriangle_3(){
        assertTrue(Demo.isTriangle(10,6,8));
    }

    @Test
    public void test_isNOT_Triangle_1(){
        assertFalse(Demo.isTriangle(12,5,18));
    }

    @Test
    public void test_isNOT_Triangle_2(){
        assertFalse(Demo.isTriangle(1,2,-1));
    }

    @Test
    public void mainTestPos_1(){

        ByteArrayInputStream in = new ByteArrayInputStream("2 12 13".getBytes());
        System.setIn(in);


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String [] args = {};
        Demo.main(args);

        String consoleOutput = "Enter side 1: \n";
        consoleOutput += "Enter side 2: \n";
        consoleOutput += "Enter side 3: \n";
        consoleOutput += "This is a triangle.\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    public void mainTestNeg_2(){

        ByteArrayInputStream in = new ByteArrayInputStream("1 12 13".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String [] args = {};
        Demo.main(args);

        String consoleOutput = "Enter side 1: \n";
        consoleOutput += "Enter side 2: \n";
        consoleOutput += "Enter side 3: \n";
        consoleOutput += "This is not a triangle.\n";

        assertEquals(consoleOutput, out.toString());
    }


}
