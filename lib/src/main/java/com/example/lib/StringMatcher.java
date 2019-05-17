package com.example.lib;

/**
 * 字符串匹配
 * 朴素
 * KMP
 */
public class StringMatcher {

    /**
     * 如果当前字符匹配成功（即S[i] == P[j]），则i++，j++，继续匹配下一个字符；
     * 如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0。相当于每次匹配失败时，i 回溯，j 被置为0。
     *
     * @param s
     * @param p
     * @return 字符串位置
     */
    static int ViolentMatch(char[] s, char[] p) {
        int sLen = s.length;
        int pLen = p.length;

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < sLen && j < pLen) {
            k++;
            if (s[i] == p[j]) {
                //①如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
                i++;
                j++;
            } else {
                //②如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0，回溯
                i = i - j + 1;
                j = 0;
            }
        }
        //匹配成功，返回模式串p在文本串s中的位置，否则返回-1
        if (j == pLen) {
            System.out.println("循环次数：" + k);
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * ??????????
     * KMP算法实现
     *
     * @param s
     * @param p
     * @return
     */
    static int kmpMatch(char[] s, char[] p) {
        int i = 0;
        int j = 0;
        int sLength = s.length;
        int pLength = p.length;
        while (i < sLength && j < pLength) {
            //如果j=-1或者当前字符匹配成功（即s[i]=p[j]）,都会令i++,j++
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else {
                //如果j!=-1,且当前字符串匹配失败（即s[i]!=p[j]）,则令i不变，j=next[j];
                //next[j]即为j所对应的next值
//                j = next[j];
            }
        }
        if (j == pLength) {
            return i - j;
        } else {
            return -1;
        }
    }
}
