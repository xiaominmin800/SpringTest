package example;

import javax.swing.*;
//import net.sourceforge.pinyin4j.*;

/**
 * created by chenminqing
 */
public class PalindromeIgnoreNonAlphanumeric {
    public static void main(String[] args) {
        //System.out.println(ToFirstChar("汉字转换为拼音").toUpperCase());
        String s = JOptionPane.showInputDialog("请输入输入字符串:");
        boolean isPalindrome = PalindromeIgnoreNonAlphanumeric.IsPalindrome(s);
        System.out.println((PalindromeIgnoreNonAlphanumeric.IsPalindrome(s)?"是":"不是")+"回文。");

    }

    public static Boolean IsPalindrome(String s){

        for (int i =0; i <=(s.length()/2-1);i++){
            if (s.charAt(i) != s.charAt(s.length()-i-1)){
                return false;
            }
        }
        return true;
    }

}
