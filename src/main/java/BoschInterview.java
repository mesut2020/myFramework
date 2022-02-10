import java.util.HashSet;
import java.util.Set;

public class BoschInterview {
    public static void main(String[] args) {
        example7();

    }
    /*
    a. s1==s2: true
    b. s1==s2: false
    c. true
    d. false
    e. exception
     */
    static void example1() {
        String s1 = "abc";
        String s2 = "abc";

        System.out.println(s1 == s2);
        System.out.println("s1==s2: " + s1 == s2);
        System.out.println("s1==s2: " + s1.equals(s2));
    }
    static void example1b() {
        System.out.println("s1==s2: " + false);
    }

    // What will be the output, if flag is true or false.
    static void example2() {
        boolean flag = true;
        try {
            if (flag) {
                while (true) {
                }
            } else {
                System.exit(1);
            }
        } finally {
            System.out.println("Finally finished...");
        }
    }
    /*
        a. [1,2,2,3,3,3,4,4,4,4,5,5,5,5,1,2,3,3,2]
        b. [1,2,3,4,5,1,2,3,2]
        c. [1,2,3,4,5]
        d. exception
    */
    static void example3() {
        int[] arr = {1,2,2,3,3,3,4,4,4,4,5,5,5,5,1,2,3,3,2};
        Set set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        System.out.println(set);
    }

    /*
        a. 100
        b. 0
        c. 1
        d. exception
    */
    static void example4() {
        Set set = new HashSet();
        for (short i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i-1);
        }

        System.out.println(set.size());
    }

    static void example5() {
        HashSet set = new HashSet();
        for (short i = 0; i < 100; i++) {
            set.add(i);
            System.out.print(set);
            set.remove((short)(i - 1));
            System.out.println(set);
        }
        System.out.println(set.size());
    }

    static void example6(){
        HashSet shortSet = new HashSet();

        for (short i = 0; i < 5; i++) {

            shortSet.add(i);

            boolean wasRemoved = shortSet.remove(i - 1);

            System.out.println(String.format("Is %s short: %s", i, Short.class.isInstance(i)));
            System.out.println(String.format("Is %s int: %s", i, Integer.class.isInstance(i)));
            System.out.println(String.format("Is i-1 short: %s", Short.class.isInstance(i-1)));
            System.out.println(String.format("Element %s was removed: %s", i-1, wasRemoved));
            System.out.println();
        }

         System.out.println(shortSet.size());
    }

    static void example7(){
        Integer int1 = 1;
        Integer int2 = 1;

        System.out.println(int1);
        System.out.println(int2);

        System.out.println(int1.equals(int2));
        System.out.println(true);

        String str1 = 123 + "";
        String str2 = "123" ;

        System.out.println(str1==(str2));
        System.out.println();

    }

}
