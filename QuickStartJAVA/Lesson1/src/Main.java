import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите операцию \n1 - Сложение\n2 - вычитание \n3- умножение\n4 - деление");
        Scanner scaner = new Scanner(System.in);
        int operation = scaner.nextInt();
        int[] a = new int[2];
        int result = 0, i = 0;
        for (int element: a){
            System.out.println("Ведите " + (i + 1) + " число");
            a[i] = scaner.nextInt();
            i++;
        }
        switch (operation){
            case 1:
                result = a[0] + a[1];
                break;
            case  2:
                result = a[0] - a[1];
                break;
            case 3:
                result = a[0] * a[1];
                break;
            case 4:
                result =  a[0] / a[1];
                break;
                default:
                    System.out.println("Опрерация не определена");
                    return;
        }
        System.out.println("Результат = " + result);
    }
}
