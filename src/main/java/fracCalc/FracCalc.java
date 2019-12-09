/**
* @author Mr. Rasmussen
*/

package fracCalc;

import java.util.*;
public class FracCalc {

    public static String f1;
    public static String op;
    public static String f2;
    public static int w1;
    public static int w2;
    public static int n1;
    public static int n2;
    public static int d1;
    public static int d2;
    public static int newn;
    public static int newd;

    public static void main(String[] args) {
        String input = "";
        System.out.println("Welcome to the Fraction calculator!");
        Scanner console = new Scanner(System.in);
        System.out.print("Enter an expression (or \"quit\"): ");

        f1 = console.next();
        if (f1.equalsIgnoreCase("quit")) {
            System.out.println("Goodbye!");
        }
        while (!f1.equalsIgnoreCase("quit")) {
            input = "";
            input += f1 + " ";
            op = console.next();
            f2 = console.next();
            input += op + " " + f2;
            System.out.println(produceAnswer(input));
            System.out.print("Enter an expression (or \"quit\"): ");
            f1 = console.next();
            if (f1.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
            }
        }
    }


    public static String produceAnswer(String input) {
        String res = "";
        if (input.contains(" ")) {
            f1 = input.substring(0, input.indexOf(" "));
            input = input.substring(input.indexOf(" ") + 1);
            op = input.substring(0, 1);
            f2 = input.substring(2);
        }
        int sign1 = 1;
        if (f1.substring(0, 1).equals("-")) {
            sign1 = -1;
            f1 = f1.substring(1);
        }

        if (f1.contains("_")) {
            w1 = Integer.parseInt(f1.substring(0, f1.indexOf("_")));
            n1 = Integer.parseInt(f1.substring(f1.indexOf("_") + 1, f1.indexOf("/")));
            d1 = Integer.parseInt(f1.substring(f1.indexOf("/") + 1));
            n1 = (w1 * d1) + n1;
        } else if (f1.contains("/")) {
            n1 = Integer.parseInt(f1.substring(0, f1.indexOf("/")));
            d1 = Integer.parseInt(f1.substring(f1.indexOf("/") + 1));
        } else {
            w1 = Integer.parseInt(f1.substring(0));
            n1 = w1;
            d1 = 1;
        }
        n1 = n1 * sign1;

        int sign2 = 1;
        if (f2.substring(0, 1).equals("-")) {
            sign2 = -1;
            f2 = f2.substring(1);
        }
        if (f2.contains("_")) {
            w2 = Integer.parseInt(f2.substring(0, f2.indexOf("_")));
            n2 = Integer.parseInt(f2.substring(f2.indexOf("_") + 1, f2.indexOf("/")));
            d2 = Integer.parseInt(f2.substring(f2.indexOf("/") + 1));
            n2 = w2 * d2 + n2;
        } else if (f2.contains("/")) {
            n2 = Integer.parseInt(f2.substring(0, f2.indexOf("/")));
            d2 = Integer.parseInt(f2.substring(f2.indexOf("/") + 1));
        } else {
            w2 = Integer.parseInt(f2.substring(0));
            n2 = w2;
            d2 = 1;
        }
        n2 = n2 * sign2;
        if (d1 == 0 || d2 == 0) {
            System.out.println("ERROR: Cannot divided by zero");
        } else {
            res = dotheMath(n1, n2, d1, d2, op);
        }
        return res;
    }

    public static String dotheMath(int n1, int n2, int d1, int d2, String op) {
        String result = "";
        if (op.equals("+")) {
            result = add(n1, n2, d1, d2);
        } else if (op.equals("-")) {

            result = subtract(n1, n2, d1, d2);
        } else if (op.equals("*")) {
            result = multiply(n1, n2, d1, d2);
        } else {
            int x = n2;
            int y = d2;
            d2 = x;
            n2 = y;
            result = multiply(n1, n2, d1, d2);
        }

        newn = Integer.parseInt(result.substring(0, result.indexOf("/")));
        newd = Integer.parseInt(result.substring(result.indexOf("/") + 1));
        //System.out.println(newn+", "+newd);
        if (Math.abs(newn) > newd) {
            int d = findGCD(Math.abs(newn), newd);
            newn = newn / d;
            newd = newd / d;
            if (newd != 1)
                result = newn + "/" + newd;
            else
                result = newn + "";
        } else if (Math.abs(newn) == newd) {
            newn = newn / newd;
            result = newn + "";
        }

        return result;
    }

    public static String add(int n1, int n2, int d1, int d2) {
        int newn = (n1 * d2) + (n2 * d1);
        int newd = d1 * d2;
        String answer = newn + "/" + newd;
        return answer;
    }
    public static String subtract(int n1, int n2, int d1, int d2) {
        int newn = (n1 * d2) - (n2 * d1);
        int newd = d1 * d2;
        String answer = newn + "/" + newd;
        return answer;
    }

    public static String multiply(int n1, int n2, int d1, int d2) {
        int newn = n1 * n2;
        int newd = d1 * d2;
        String answer = newn + "/" + newd;
        return answer;
    }

    public static int findGCD(int number1, int number2) {

        if (number2 == 0) {
            return number1;
        }
        return findGCD(number2, number1 % number2);
    }

}
