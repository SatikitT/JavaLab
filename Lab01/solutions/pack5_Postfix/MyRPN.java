package solutions.pack5_Postfix;

import java.util.StringTokenizer;

import java.util.regex.Pattern;

public class MyRPN {
    
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null)
            return false;
        return pattern.matcher(strNum).matches();
    }

    private static double getTopAndPop(MyStackA stack){
        double value = stack.top();
        stack.pop();
        return value;
    }

    public static double computeRPN(String rpn) {
        MyStackA result = new MyStackA();
        StringTokenizer st = new StringTokenizer(rpn);
        String t = "";
        while (st.hasMoreTokens()) {
            double a = 0.0;
            double b = 0.0;
            t = st.nextToken();
            if (isNumeric(t)){
                result.push(Double.parseDouble(t));
            } else {
                b = getTopAndPop(result);
                a = getTopAndPop(result);
                //System.out.println(a + ", " + b);
                if (t.equals("-")){
                    result.push(a - b);
                }
                else if (t.equals("+")){
                    result.push(a + b);
                }
                else if (t.equals("*")){
                    result.push(a * b);
                }
                else if (t.equals("/")){
                    result.push(a / b); 
                }
            }
        }
        return result.top();
    }
}
