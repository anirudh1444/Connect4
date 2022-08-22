import java.util.Scanner;
import java.awt.*;

public class Connect4 {

    public static DrawingPanel panel;
    public static Graphics g;

    public static String[][] board = new String[6][7];

    public static int [] placements = new int[7];

    public static void main (String [] args) {

        panel = new DrawingPanel(1200, 650);

        g = panel.getGraphics();

        g.drawRect(250, 12, 705, 600);
        g.setColor(new Color(227, 217, 18));
        g.fillRect(250, 12, 705, 600);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                g.drawOval(300 + 90 * i, 25 + 100 * j, 65, 65);
                g.setColor(Color.white);
                g.fillOval(300 + 90 * i, 25 + 100 * j, 65, 65);
                board[j][i] = "0";
            }
        }

        Scanner console = new Scanner(System.in);

        System.out.print("Player 1 (Red) Name: ");
        String name1 = console.nextLine();

        System.out.print("Player 2 (Black) Name: ");
        String name2 = console.nextLine();

        int turn = 1;

        while (!Endgame()) {
            if (turn % 2 == 1) {
                System.out.print(name1 + "'s move: ");
                String move_s = console.next();

                while (!isValid(move_s)) {
                    System.out.print("Invalid input. Re-enter column number. ");
                    move_s = console.next();
                }

                int move = move_s.charAt(0) - '0';

                board[placements[move - 1]][move - 1] = "R";
                fillCircle(move - 1, "red");
                turn++;

            } else {
                System.out.print(name2 + "'s move: ");
                String moveS = console.next();

                while (!isValid(moveS)) {
                    System.out.print("Invalid input. Re-enter column number.");
                    moveS = console.next();
                }

                int move = moveS.charAt(0) - '0';

                board[placements[move - 1]][move - 1] = "B";
                fillCircle(move - 1, "black");
                turn++;
            }
        }

        g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 100));
        g.setColor(Color.blue);

        if ((turn - 1) % 2 == 1) {
            g.drawString(name1, 350, 100);
            g.drawString("wins!", 500, 200);
        } else {
            g.drawString(name2, 350, 100);
            g.drawString("wins!", 500, 200);
        }
    }

    public static boolean isValid (String column) {
        if (column.length() != 1) {
            return false;
        }
        int ascii = column.charAt(0) - '0';
        if (ascii <= 0 || ascii > 7) {
            return false;
        }
        if (placements[ascii - 1] == 6) {
            return false;
        }
        return true;
    }

    public static void fillCircle (int column, String color) {
        int height = placements[column];
        if (color.equals("red")) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.black);
        }
        g.fillOval(300 + 90 * column, 25 + 100 * (5 - height), 67, 67);
        placements[column]++;
    }

    public static boolean Endgame () {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 6; j++) {
                if (board[i][j].equals("R") || board[i][j].equals("B")) {
                    if (board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j]) && board[i][j].equals(board[i + 3][j])) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 3; j++) {
                if (board[i][j].equals("R") || board[i][j].equals("B")) {
                    if (board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2]) && board[i][j].equals(board[i][j + 3])) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 3; j++) {
                if (board[i][j].equals("R") || board[i][j].equals("B")) {
                    if (board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2]) && board[i][j].equals(board[i + 3][j + 3])) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i <= 2; i++) {
            for (int j = 6; j >= 3; j--) {
                if (board[i][j].equals("R") || board[i][j].equals("B")) {
                    if (board[i][j].equals(board[i + 1][j - 1]) && board[i][j].equals(board[i + 2][j - 2]) && board[i][j].equals(board[i + 3][j - 3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

