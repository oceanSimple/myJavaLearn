package stack;

import java.util.ArrayDeque;

/**
 * 栈的使用
 * 判断表达式中括号是否匹配
 */
public class Demo01 {
    private static ArrayDeque<Character> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println(method_1("({{}})"));
    }

    /**
     * 标答，但有瑕疵。
     * 例如字符串"({{}})"，结果是true，如果要区分不同括号的优先级，需要增加if判断。
     * 后续步骤暂略...
     * @param str
     * @return
     */
    public static boolean method_1(String str) {
        boolean flag = true;
        int index = 0;
        while (index < str.length() && flag) {
            switch (str.charAt(index)){
                case '(': case '[': case '{' :
                    deque.push(str.charAt(index));
                    break;
                case ')': case ']' : case '}' :
                    if (deque.isEmpty())    //避免pop方法因为空栈产生的异常
                        flag = false;
                    else {
                        char pop = deque.pop();
                        flag = isMatch(pop, str.charAt(index));
                    }
                    break;
                default:
                    break;
            }
            index++;
        }
        return flag;
    }

    public static boolean isMatch(char left, char right) {
        return  (left == '(' && right == ')') ||
                (left == '[' && right == ']') ||
                (left == '{' && right == '}');
    }



}
