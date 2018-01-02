package control;

import chess.Board;
import chess.Piece;

import java.util.HashMap;
import java.util.Map;

public class ChessController {
    private Map<String, Piece> PiecesSets(){
        Map<String, Piece> pieceMap = new HashMap<String, Piece>();
        /*add Pieces here*/

        return pieceMap;

    }

    private Board init(int x, int y){
        //x y is the width and hight
        Board board = new Board(x,y);
        board.pieceMap = PiecesSets();

        return board;

    }

}
