package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex4 {
    static class Stack<Item> {
        //栈顶（最近添加的元素）
        private Node first;

        //元素数量
        private int N;

        public Item peek() {
            if (isEmpty()) return null;
            return first.item;
        }

        private class Node {
            //定义的节点的嵌套类
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null; //或:N==0
        }

        public int size() {
            return N;
        }

        public void push(Item item) {
            //向栈顶添加元素
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        @SuppressWarnings("unchecked")
        public Item pop() {
            //从栈顶删除元素
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    public static boolean Parentheses(String s){
        Stack<String> stack = new Stack<>();
        boolean result = true;
        for (String ss : s.split("")) {
            if ("[".equals(ss)) stack.push(ss);
            else if ("(".equals(ss)) stack.push(ss);
            else if ("{".equals(ss)) stack.push(ss);
            else if("]".equals(ss)) result = "[".equals(stack.pop());
            else if(")".equals(ss)) result = "(".equals(stack.pop());
            else if("}".equals(ss)) result = "{".equals(stack.pop());
            if (!result) return false;
        }
        return true;
    }

    /*
     * 检查括号是否匹配
     * 一坨屎，别人写的代码
     */
    public static boolean isParenthesesPairing(String s) {
        Stack<String> stack = new Stack<>();
        for(String ss : s.split(""))
            if (stack.isEmpty())
                stack.push(ss);
            else if ((stack.peek().equals("(") && ss.equals(")")) ||
                    (stack.peek().equals("{") && ss.equals("}")) ||
                    (stack.peek().equals("[") && ss.equals("]")))
                stack.pop();
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        StdOut.println(Parentheses("[()]{}{[()()]()}"));
        StdOut.println(Parentheses("[(])]"));
        StdOut.println(isParenthesesPairing("[()]{}{[()()]()}"));
        StdOut.println(isParenthesesPairing("[(})]"));
    }
}
