/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author musta
 */
public class NewClass {
    public static boolean isDelimiter(char ch) {
            if(ch == ' ' || ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ',' || ch == ';' || ch == '>' || ch == '<' || ch == '=' || ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '{' || ch == '}') {
                    return true;
            }
            return false;
    }
    public static boolean isOperator(char ch) {
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '>' || ch == '<' || ch == '=') {
                    return true;
            }
            return false;
    }

	
    public static boolean validIdentifier(String str) {
            if(str.charAt(0) == '0' || str.charAt(0) == '1' ||str.charAt(0) == '2' || str.charAt(0) == '3' || str.charAt(0) == '4' || str.charAt(0) == '5' || str.charAt(0) == '6' || str.charAt(0) == '7' || str.charAt(0) == '8' || str.charAt(0) == '9' || isDelimiter(str.charAt(0)) == true) {
                    return false;
            }
            return true;
    }

    public static boolean isKeyword(String str) {
            if(str.equals("float") || str.equals("if") || str.equals("else") || str.equals("while") || str.equals("do") || str.equals("break") || str.equals("continue") || str.equals("int") || str.equals("double") || str.equals("float") || str.equals("return") || str.equals("char") || str.equals("case") || str.equals("char") || str.equals("sizeof") || str.equals("long") || str.equals("short") || str.equals("typedef") || str.equals("switch") || str.equals("unsigned") || str.equals("void") || str.equals("static") || str.equals("struct") || str.equals("goto")) {
                    return true;
            }
            return false;
    }
        
    public static boolean isInteger(String str) {
            int len = str.length();

            if(len == 0) {
                    return false;
            }

            for(int i = 0; i < len; i++) {
                    if(str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2' &&str.charAt(i) != '3' && str.charAt(i) != '4' && str.charAt(i) != '5' &&str.charAt(i) != '6' && str.charAt(i) != '7' && str.charAt(i) != '8' && str.charAt(i) != '9' || str.charAt(i) == '-' && i > 0) {
                            return false;
                    }
            }
            return true;
    }
                
    public static char[] subString(char[] str, int left, int right) {
            char[] subStr = new char[right - left + 2];

            for(int i = left; i <= right; i++) {
                    subStr[i - left] = str[i];
            }
            subStr[right - left + 1] = '\0';
            return subStr;
    }
    static String tokens="";
    public static boolean lexicalAnalyzer(String str) {
      int left = 0, right = 0;
      int len = str.length();

      while(right < len && left <= right) {
          if(isDelimiter(str.charAt(right)) == false) {
                  right++;
          }

          if(isDelimiter(str.charAt(right)) == true && left == right) {
                  if(isOperator(str.charAt(right)) == true) {
                          tokens+=" OPERATOR";
                  }
                  right++;
                  left = right;
          }else if(isDelimiter(str.charAt(right))==true&&left!=right || right==len&&left!=right) {                     
              String subStr = str.substring(left,right);                     

                  if (isKeyword(subStr)) {
                      tokens+=" KEYWORD";
                  }
                  else if (isInteger(subStr) == true) {
                      tokens+=" INTEGER";
                  }

                  else if (validIdentifier(subStr) == true && isDelimiter(str.charAt(right-1)) == false) {
                      tokens+=" IDENTIFIER";
                  }
                  else if (validIdentifier(subStr) == false && isDelimiter(str.charAt(right-1)) == false) {
                      return false;
                  }
           left = right; 
        } 
      }
      return true;
    }
    
    public static boolean syntaxAnalyzer(String tree){
        Scanner sn = new Scanner(tree);
        String exp = sn.next();
        if(exp.equals("KEYWORD")){
            exp = sn.next();
            if(exp.equals("IDENTIFIER")){
                exp=sn.next();
                if(exp.equals("OPERATOR")){
                    exp=sn.next();
                    if(exp.equals("IDENTIFIER") || exp.equals("INTEGER")){
                        exp=sn.next();
                        if(exp.equals("OPERATOR") ){
                             exp=sn.next();
                            if(exp.equals("INTEGER") || exp.equals("IDENTIFIER") ){
                                return true;
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }      
    }
                
    public static void main(String[] args) {
        String code = "int a = b  d + c;";   
        boolean lexicalAnalysis = lexicalAnalyzer(code);
        System.out.println("Lexical Analyzer Result: "+lexicalAnalysis);
        
        if(lexicalAnalysis){
            System.out.println("Tokens: "+tokens);
            System.out.println("Syntax Analyzer Result:"+syntaxAnalyzer(tokens));
        }
    }               
}
