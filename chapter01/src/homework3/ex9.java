package homework3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ex9 {

    public static void printMediumOrder(String s) {
        //初始化两个栈，一个操作符栈，一个结果栈
        Stack<String> resultStack = new Stack<>();
        Stack<String> operationStack = new Stack<>();
        for (String result : s.split("")) {
            if (result.equals("+") || result.equals("-") || result.equals("*") || result.equals("/"))
                operationStack.push(result);
            else if (result.equals(")")) {
                String number2 = resultStack.pop();
                String number1 = resultStack.pop();
                String operation = operationStack.pop();
                String ss = "(" + number1 + operation + number2 + ")";
                resultStack.push(ss);
            } else {
                resultStack.push(result);
            }
        }
        StdOut.println(resultStack.pop());
    }

    public static void main(String[] args) {
        String ss = "1+2)*3-4)*5-6)))";
        StdOut.println("没有补齐左括号:" + ss);
        StdOut.print("补齐左括号:");
        printMediumOrder(ss);
    }
}
