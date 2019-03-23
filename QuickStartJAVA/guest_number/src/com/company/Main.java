package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Игра угадай число\n Введите диапазон чисел");
        Scanner scanner = new Scanner(System.in);
        int range = scanner.nextInt();
        int MyNumber = (int)(Math.random()* range);
        int step = 0;
        while(true){
            step ++;
            System.out.println("Введите число");
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
