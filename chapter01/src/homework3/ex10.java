package homework3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ex10 {

    //将算数表达式的中序表达式转成后序表达式
    public static String InfixToPostfix(String ss) {
        Stack<String> operationStack = new Stack<>();
        String result = "";
        for (String s : ss.split("")) {
            if ("+".equals(s) || "-".equals(s)) {
                if (operationStack.isEmpty()) operationStack.push(s);
                else if ("(".equals(operationStack.peek())) operationStack.push(s);
                else if ("+".equals(operationStack.peek()) || "-".equals(operationStack.peek())
                        || "*".equals(operationStack.peek()) || "/".equals(operationStack.peek())) {
                    while ("+".equals(operationStack.peek()) || "-".equals(operationStack.peek())
                            || "*".equals(operationStack.peek()) || "/".equals(operationStack.peek())) {
                        result += operationStack.pop();
                        if (operationStack.isEmpty()) break;
                    }
                    operationStack.push(s);
                }else operationStack.push(s);
            } else if ("*".equals(s) || "/".equals(s)) {
                if (operationStack.isEmpty()) operationStack.push(s);
                else if ("(".equals(operationStack.peek())) operationStack.push(s);
                else if ("*".equals(operationStack.peek()) || "/".equals(operationStack.peek())) {
                    while ("*".equals(operationStack.peek()) || "/".equals(operationStack.peek())) {
                        result += operationStack.pop();
                        if (operationStack.isEmpty()) break;
                    }
                    operationStack.push(s);
                } else operationStack.push(s);

            } else if ("(".equals(s)) operationStack.push(s);
            else if (")".equals(s)) {
                while (!"(".equals(operationStack.peek())) result += operationStack.pop();
                operationStack.pop();
            } else {
                result += s;
            }
        }
        while (!operationStack.isEmpty()) {
            if (!operationStack.peek().equals("(")) result += operationStack.pop();
        }
        StdOut.println("后序:"+result);
        return result;
    }

    //将算数表达式的中序表达式转成前序表达式
    public static void InfixToPrefix(String ss) {
        StringBuffer str = new StringBuffer(ss).reverse();
        Stack<String> operationStack = new Stack<>();
        String result = "";
        for (String s : str.toString().split("")) {
            if ("+".equals(s) || "-".equals(s)) {
                if (operationStack.isEmpty()) operationStack.push(s);
                else if (")".equals(operationStack.peek())) operationStack.push(s);
                else if ("*".equals(operationStack.peek()) || "/".equals(operationStack.peek())) {
                    while ("*".equals(operationStack.peek()) || "/".equals(operationStack.peek())) {
                        result += operationStack.pop();
                        if (operationStack.isEmpty()) break;
                    }
                    operationStack.push(s);
                }else operationStack.push(s);
            } else if ("*".equals(s) || "/".equals(s)) {
                if (operationStack.isEmpty()) operationStack.push(s);
                else if (")".equals(operationStack.peek())) operationStack.push(s);
                else if ("*".equals(operationStack.peek()) || "/".equals(operationStack.peek())) {
                    operationStack.push(s);
                } else operationStack.push(s);

            } else if (")".equals(s)) operationStack.push(s);
            else if ("(".equals(s)) {
                while (!")".equals(operationStack.peek())) result += operationStack.pop();
                operationStack.pop();
            } else {
                result += s;
            }
        }
        while (!operationStack.isEmpty()) {
            if (!operationStack.peek().equals(")")) result += operationStack.pop();
        }
        StdOut.println("前序:"+new StringBuilder(result).reverse().toString());
    }

    public static void main(String[] args) {
        StdOut.println("中序:2*3/(2-1)+3*(4-1)");
        InfixToPostfix("2*3/(2-1)+3*(4-1)");
        InfixToPrefix("2*3/(2-1)+3*(4-1)");
    }
}
