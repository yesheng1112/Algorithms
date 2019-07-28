package homework3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ex11 {
    public static void EvaluatePostfix(String ss) {
        ss = ex10.InfixToPostfix(ss);
        Stack<Integer> stack = new Stack<>();
        for (String s : ss.split("")) {
            if ("+".equals(s)) stack.push(stack.pop() + stack.pop());
            else if ("-".equals(s)) {
                int number2 = stack.pop();
                int number1 = stack.pop();
                stack.push(number1 - number2);
            }
            else if ("*".equals(s)) stack.push(stack.pop() * stack.pop());
            else if ("/".equals(s)) {
                int number2 = stack.pop();
                int number1 = stack.pop();
                stack.push(number1/number2);
            }
            else stack.push(Integer.parseInt(s));
        }
        StdOut.println("结果:"+stack.pop());
    }

    public static void main(String[] args) {
        EvaluatePostfix("2*3/(2-1)+3*(4-1)");
    }
}
