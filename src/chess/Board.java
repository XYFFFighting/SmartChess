package chess;

import java.util.Map;

public class Board {
    public int BOARD_WIDTH;
    public int BOARD_HEIGHT;
    public Map<String, Piece> pieceMap;
    private Piece[][] chessBoard = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
    public Board(int BOARD_WIDTH, int BOARD_HEIGHT){
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.BOARD_HEIGHT = BOARD_HEIGHT;
    }

    public boolean isInside(int x, int y){
        if(x<0||x>=BOARD_HEIGHT||y<0||y>=BOARD_WIDTH)
            return false;
        return true;
    }

    public boolean isEmpty(int x, int y){
        if(chessBoard[x][y]==null)
            return true;
        return false;
    }

    public Piece getPiece(int x, int y){
        return chessBoard[x][y];
    }

}
