import model.GameStatus;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===>>> TicTacToe Game\n");
        TicToeGame ticToeGame = new TicToeGame();
        ticToeGame.initializeGame();
        GameStatus status = ticToeGame.startGame();
        System.out.print("\n===>>> GAME OVER: ");
        switch (status) {
            case WIN:
                System.out.print(ticToeGame.winner.getName() + " won the game");
                break;
            case DRAW:
                System.out.print(" Its a Draw!");
                break;
            default:
                System.out.print(" Game Ends");
                break;
        }
    }
}
