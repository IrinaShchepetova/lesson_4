package lesson4;

import java.util.Random;
import java.util.Scanner;

public class lesson4 {
    public static char[][] map;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 4;

    public static final char DOT_X = 'X';
    public static final char DOT_O = '0';
    public static final char DOT_EMPTY = '.';

    public static void main(String[] args) {
        initMap();
        showMap();

        while (true) {
            humanTurn();
            showMap();
            if (isWinner(DOT_X)) {
                System.out.println("Победил человек!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            showMap();
            if (isWinner(DOT_O)) {
                System.out.println("Победил Т-1000!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

    // 1. Инициализация игрового поля.
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                map[row][column] = DOT_EMPTY;
            }
        }
    }

    // 2. Вывод игрового поля в консоль.
    public static void showMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 3. Ход человека
    public static void humanTurn() {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        do {
            System.out.println("Введите координаты в формате X и Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    // 5. Ход ИИ
    public static void aiTurn() {
        Random random = new Random();
        int x;
        int y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));

        System.out.println("ИИ сходил в координаты: X: " + (x + 1) + " Y: " + (y + 1));
        map[y][x] = DOT_O;
    }

    // 4. Проверка ячейки.
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }

        return map[y][x] == DOT_EMPTY;
    }

    // 6. Алгоритм проверки победы
    public static boolean isWinner(char symb) {
        for (int i=0; i < SIZE;i++)
            if ((map[i][0] == symb && map[i][1] == symb && map[i][2] == symb) || (map[0][i] == symb && map[1][i] == symb && map[2][i] ==symb))
                return true;
        if ((map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) || (map[2][0] == symb && map[1][1] == symb && map[0][2] ==symb))
            return true;
        return false;
    }


    // 7. Алгоритм проверки ничьи
    public static boolean isMapFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (map[row][column] == DOT_EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }
}
