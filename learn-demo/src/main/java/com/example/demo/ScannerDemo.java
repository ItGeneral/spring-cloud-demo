package com.example.demo;

import java.util.Scanner;

/**
 * @author SongJiuHua.
 * @date Created on 2018/5/9.
 * @description
 */
public class ScannerDemo {


    public static void main(String[] args) {
        //System.in 获取从键盘中输入的
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter some strings");
        if (scanner.hasNext()){
            System.out.println("hasNext print out: " + scanner.next());
        }
        scanner = new Scanner(System.in);
        if (scanner.hasNextLine()){
            System.out.println("hasNextLine print out: " + scanner.nextLine());
        }
    }

}
