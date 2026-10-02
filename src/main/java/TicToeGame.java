import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import model.Board;
import model.GameStatus;
import model.PlayingPieceO;
import model.PlayingPieceX;
import model.PlayerName;
import model.PlayingPiece;

public class TicToeGame {

    Deque<PlayerName> players;
    Board gameBoard;
    PlayerName winner;

    public void initializeGame() {
        players = new LinkedList<>();
        players.add(new PlayerName("Player 1", new PlayingPieceX()));
        players.add(new PlayerName("Player 2", new PlayingPieceO()));

        gameBoard = new Board(3);
    }

    public GameStatus startGame() {
        boolean gameEnded = false;
        while(!gameEnded){
            PlayerName currentPlayer = players.poll();
            gameBoard.printBoard();

            List<Pair<Integer,Integer>> freeCells = gameBoard.getFreeCells();
            if(freeCells.isEmpty()){
                gameEnded = true;
                continue;
            }
            System.out.println(currentPlayer.getName() + "'s turn. Please enter row and column");
            // Get user input for row and column
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);

            boolean isPieceAdded = gameBoard.addPiece(inputRow, inputColumn, currentPlayer.getPlayingPiece());
            if(!isPieceAdded){
                System.out.println("Invalid move. Cell is already occupied. Please try again.");
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);
            boolean isWinner = checkWinner(inputRow, inputColumn, gameBoard.size, currentPlayer.getPlayingPiece());
            if(isWinner){
                winner = currentPlayer;
                gameBoard.printBoard();
                return GameStatus.WIN;
            }

        }
        return GameStatus.DRAW;
    }

    public boolean checkWinner(int row, int col, int size, PlayingPiece pieceType){
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = false;
        boolean antiDiagMatch = false;

        for(int i=0; i<size; i++){
            if(gameBoard.board[row][i] == null || gameBoard.board[row][i] != pieceType){
                rowMatch = false;
                break;
            }
        }
        for(int i=0; i<size; i++){
            if(gameBoard.board[i][col] == null || gameBoard.board[i][col] != pieceType){
                columnMatch = false;
                break;
            }
        }
        
        if(row == col){
            diagonalMatch = true;
            for(int i =0, j= 0; i<size; i++,j++){
                if(gameBoard.board[i][j] == null || gameBoard.board[i][j] != pieceType){
                    diagonalMatch = false;
                    break;
                }
            }
        }
        if(row+col == size-1)
        {
            antiDiagMatch=true;
            for(int i =0, j= size-1; i<size; i++,j--){
                if(gameBoard.board[i][j] == null || gameBoard.board[i][j] != pieceType){
                    antiDiagMatch = false;
                    break;
                }
            }
        }

        return rowMatch||columnMatch||diagonalMatch||antiDiagMatch;
    }
}
