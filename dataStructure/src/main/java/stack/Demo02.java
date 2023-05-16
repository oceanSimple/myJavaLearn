package stack;

import java.util.ArrayDeque;

/**
 * 中缀表达式、后缀表达式、前缀表达式的相互转换
 */
public class Demo02 {

    public static void main(String[] args) {

    }

    // 中缀表达式转后缀表达式
    public static String midToBack(String str) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            if (isOperator(str.charAt(i))) {

            }else {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }

    /**
     * 判断两个运算符的优先级，后来的运算符是否优先级更大。
     * @param newChar   后来的运算符
     * @param pop   栈中弹出的运算符
     * @return  true-后来的运算符优先级大
     */
    public static boolean judgePriority(char newChar, char pop) {
        int i = judgeType(newChar);
        int j = judgeType(pop);
        return (i - j) >= 0;
    }

    /**
     * 通过运算符的符号类型,给优先级赋值.值越大,优先级越高
     * @param c 运算符
     * @return
     */
    public static int judgeType(char c) {
        int i = 0;
        switch (c) {
            case '+': case '-' :
                i = 1;
                break;
            case '*': case '/' :
                i = 2;
            default:
                break;
        }
        return i;
    }

    /**
     * 判断字符c是否为一个运算符
     * @param c 需要判断的字符
     * @return
     */
    public static boolean isOperator(char c) {
        char[] chars= {'+', '-' , '*', '/', '(', ')', '[', ']', '{', '}'};
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
