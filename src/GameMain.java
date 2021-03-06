import chess.Board;
import control.ChessController;
import view.BoardView;

public class GameMain {
    private Board board;
    private ChessController controller;
    private BoardView view;

    public void set(){
        controller = new ChessController();
        board = controller.init();
        view = new BoardView(controller);
        board.boardView = view;
        view.init(board);

    }

    public void run(){
        while(controller.End(board)){

        }
    }

    public static void main(String[] args){
        GameMain main = new GameMain();
        main.set();
    }
}
