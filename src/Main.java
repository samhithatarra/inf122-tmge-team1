import java.util.*;

public class Main 
{
    public static Controller ctrl = new Controller();
    public static Scanner sc = new Scanner(System.in);
    public static boolean runApp = true;

    public static void main(String[] args) 
    {
        while(runApp)
        {
            System.out.print("1.) Add new user\n" +
                             "2.) Login\n" +
                             "3.) Quit\n" +
                             "Enter Command Number: ");
            int command = sc.nextInt();
            sc.nextLine(); // flush scanner after int input

            switch(command)
            {
                case 1:
                    addNewUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    runApp = false;
                    break;
                default:
                    System.out.println("\nnot an option stoopid \n-becky\n");
            }

        }
    }

    public static void addNewUser()
    {
        while(true)
        {
            System.out.print("\nEnter new username: ");
            String name = sc.nextLine();
            if(ctrl.playerNameExists(name))
            {
                System.out.println(name + " already exists");
                continue;
            }
            ctrl.createProfile(name);
            break;
        }
        System.out.println();
    }

    public static void login()
    {
        if(ctrl.getTotalPlayers() < 2)
        {
            System.out.println("\nNot enough users to play. Please add another user\n");
            return;
        }
        while(true)
        {
            System.out.println("\nLogin as a user from the list below");
            ctrl.printPlayers();
            System.out.print("Login as: ");
            String name = sc.nextLine();
            if(!ctrl.playerNameExists(name))
            {
                System.out.println(name + " does not exist");
                continue;
            }
            ctrl.loginCurrPlayer(name);
            System.out.println("\nLogged in as " + name + "\n");
            break;
        }
        selectGame();
    }

    public static void selectGame()
    {
        boolean loopGameSelection = true;
        while (loopGameSelection)
        {
            System.out.println("Select a game from the list below");
            System.out.println("1.) Tic-Tac-Toe");
            System.out.println("2.) Connect Four");
            System.out.print("Enter game: ");
            int game = sc.nextInt();
            sc.nextLine(); // flush scanner after int input

            switch(game)
            {
                case 1: 
                    selectTicTacToe();
                    loopGameSelection = false;
                    break;
                case 2:
                    selectConnectFour();
                    loopGameSelection = false;
                    break;
                default:
                    System.out.println("\nnot an option stoopid\n");
            }
        }
    }

    public static void selectConnectFour()
    {
        while(true)
        {
            System.out.println("\n" + ctrl.getCurrPlayer() + " playing as Player 1");
            System.out.println("Select Player 2 from the list below");
            ctrl.printPlayersExcept(ctrl.getCurrPlayer());
            System.out.print("Select player: ");
            String name = sc.nextLine();
            if(name.equals(ctrl.getCurrPlayer()))
            {
                System.out.println("\n" + name + " is already Player 1");
                continue;
            }
            if(!ctrl.playerNameExists(name))
            {
                System.out.println("\n" + name + " does not exist");
                continue;
            }
            ctrl.loginPlayer2(name);
            System.out.println("\n" + name + " playing as Player 2\n");
            break;
        }
        playConnectFour();
    }

    public static void playConnectFour()
    {
        System.out.println(ctrl.getCurrPlayer() + " is RED");
        System.out.println(ctrl.getPlayer2() + " is YELLOW");
        Connect4Board<String> c4 = new Connect4Board<>();
        while(!c4.checkWin())
        {
            if(c4.getTurn() % 2 == 1)
            {
                System.out.println("\n" + ctrl.getCurrPlayer() + "'s turn");
            }
            else
            {
                System.out.println("\n" + ctrl.getPlayer2() + "'s turn");
            }
            c4.printBoard();
            while(true)
            {
                System.out.print("Enter the column to drop: ");
                int column = sc.nextInt();
                if(!c4.isValidColumn(column - 1))
                {
                    System.out.println("Invalid column, please try again\n");
                    continue;
                }
                if(c4.getTurn() % 2 == 1)
                {
                    c4.drop("R", column);
                }
                else
                {
                    c4.drop("Y", column);
                }
                break;
            }
        }
        if(c4.getTurn() % 2 == 1)
        {
            System.out.println("\n" + ctrl.getCurrPlayer() + " wins!");
        }
        else
        {
            System.out.println("\n" + ctrl.getPlayer2() + " wins!");
        }
    }

    public static void selectTicTacToe()
    {
        System.out.println("\nttt");
    }
}
