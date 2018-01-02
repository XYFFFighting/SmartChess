package chess;

import java.util.Map;

public class Board {
    public int BOARD_WIDTH=200;//200
    public int BOARD_HEIGHT=100;//100
    public Map<String, Piece> pieceMap;
    private Piece[][] chessBoard = new Piece[BOARD_WIDTH][BOARD_HEIGHT];

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

    public void putPiece(Piece piece){
        int[] pos = piece.position;
        chessBoard[pos[0]][pos[1]]=piece;
    }

    public boolean Bleft(){//check if any black pieces left
        for(Map.Entry<String, Piece>stringPieceEntry : pieceMap.entrySet()){
            if(stringPieceEntry.getKey().charAt(0)=='B')
                return true;
        }
        return false;
    }

    public boolean Rleft(){//check if any red pieces left
        for(Map.Entry<String, Piece>stringPieceEntry : pieceMap.entrySet()){
            if(stringPieceEntry.getKey().charAt(0)=='R')
                return true;
        }
        return false;
    }


}
