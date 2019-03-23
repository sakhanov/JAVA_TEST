package com.company;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here
        for(int range = 10; range <= 30; range += 10){
        int MyNumber = (int)(Math.random()* range);
        playlevel(range, MyNumber);
        }
        System.out.println("Игра угадай число");
    scanner.close();
    }
    private static  void playlevel(int range, int MyNumber){
        int step = 0;
        while(true){
            step ++;
            System.out.println("Введите число  от 0 " + range);
            int TestNumber = scanner.nextInt();
            if( TestNumber == MyNumber){
                System.out.println("Вы угадали число за " + step + " шагов");
                break;
            }else if(TestNumber > MyNumber){
                System.out.println("Меньше");
            }else{
                System.out.println("Больше");

            }
        }

    }
}
