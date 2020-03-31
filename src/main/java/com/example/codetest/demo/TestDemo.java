package com.example.codetest.demo;

import java.util.*;

public class TestDemo {
    public static void main(String[] args) {
        //. Input: "ilikeicecreamandmango
        //"Output: i like ice cream and man go
        String[] dictionary = {"i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "man go"};

        String input = "ilikesamsungmobile";
            //To A simple solution
//        mothed1(input, dictionary);
        List<String> dictionaryList = Arrays.asList(dictionary);
        Set<String> dictionarySet = new HashSet<>(dictionaryList);
        List<String> strings = mothed2(input, dictionarySet);
        for (String string : strings) {
            System.out.println(string + " ");
        }

    }

    /**
     * A simple solution
     * @param input
     * @param dictionary
     */
    public static void mothed1(String input, String[] dictionary) {
        List<String> list = new ArrayList();
        for (int i = 0; i < dictionary.length; i++) {
            if (input.contains(dictionary[i])) {
                list.add(dictionary[i]);
                input = input.replaceFirst(dictionary[i], "");
            }
        }
        for (String s : list) {
            System.out.print(s + " ");
        }
    }

    /**
     * The final solution
     * @param inPut
     * @param dictionarySet
     * @return
     */
    public static List<String> mothed2(String inPut, Set<String> dictionarySet) {
        List<String> ultimatelyList = new ArrayList<String>();
        if (inPut== null || inPut.length() == 0) {
            return ultimatelyList;
        }

        if (isBreak(inPut, dictionarySet)) {
            helper(inPut, dictionarySet, 0, "", ultimatelyList);
        }
        return ultimatelyList;
    }


    private static void helper(String inPut, Set<String> wordDict, int start, String item, List<String> ultimatelyList) {
        if (start ==inPut.length()) {
            ultimatelyList.add(item);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < inPut.length(); i++) {
            stringBuilder.append(inPut.charAt(i));
            if (wordDict.contains(stringBuilder.toString())) {
                String str = "";    //copy
                if (item.length() == 0) {
                    str = stringBuilder.toString();
                } else {
                    str = item + " " + stringBuilder.toString();
                }
                helper(inPut, wordDict, i + 1, str, ultimatelyList);
            }
        }

    }

    private static boolean isBreak(String inPut, Set<String> dictionarySet) {
        if (inPut == null || inPut.length() == 0) {
            return true;
        }
        boolean[] check = new boolean[inPut.length() + 1];
        check[0] = true;
        for (int i = 1; i < check.length; i++) {
            StringBuilder stringBuilder = new StringBuilder(inPut.substring(0, i));
            for (int j = 0; j < i; j++) {
                if (check[j] && dictionarySet.contains(stringBuilder.toString())) {
                    check[i] = true;
                }
                stringBuilder.deleteCharAt(0);
            }
        }
        return check[check.length - 1];
    }
}
