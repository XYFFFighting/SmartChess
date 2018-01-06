package chess;

import view.BoardView;

import java.util.Map;

public class Board {
    public int BOARD_WIDTH=201;//200
    public int BOARD_HEIGHT=101;//100
    public Map<String, Piece> pieceMap;
    public char player='R';
    public BoardView boardView;
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

    public void updatePosition(String name, int[] pos, int order){
        Piece oldPiece = pieceMap.get(name);
        oldPiece.position = pos;
        pieceMap.remove(name);
        pieceMap.put(name, oldPiece);
        //need to check if kill some chess here
        //change next move
        if(order!=11)
            boardView.order = order+1;
        else
            boardView.order = 0;

        player=boardView.Sequence[boardView.order].charAt(0);

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
