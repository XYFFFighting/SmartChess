package control;

import chess.Board;
import chess.Piece;

import java.util.HashMap;
import java.util.Map;

public class ChessController {
    private Map<String, Piece> PiecesSets(){
        Map<String, Piece> pieceMap = new HashMap<String, Piece>();
        /*add Pieces here*/
        pieceMap.put("R1", new Piece("R1", new int[]{0,0}));
        pieceMap.put("R3", new Piece("R3", new int[]{0,19}));
        pieceMap.put("R5", new Piece("R5", new int[]{0,39}));
        pieceMap.put("R7", new Piece("R7", new int[]{0,59}));
        pieceMap.put("R9", new Piece("R9", new int[]{0,79}));
        pieceMap.put("R11", new Piece("R11", new int[]{0,99}));
        pieceMap.put("B2", new Piece("B2", new int[]{199,0}));
        pieceMap.put("B4", new Piece("B4", new int[]{199,19}));
        pieceMap.put("B6", new Piece("B6", new int[]{199,39}));
        pieceMap.put("B8", new Piece("B8", new int[]{199,59}));
        pieceMap.put("B10", new Piece("B10", new int[]{199,79}));
        pieceMap.put("B12", new Piece("B12", new int[]{199,99}));
        return pieceMap;

    }

    public Board init(){
        //x y is the width and hight
        PiecesSets();
        Board board = new Board();
        board.pieceMap = PiecesSets();
        for(Map.Entry<String, Piece> stringPieceEntry:PiecesSets().entrySet()){
            Piece piece = stringPieceEntry.getValue();
            board.putPiece(piece);
        }
        return board;
    }



    public boolean End(Board board){
        if(board.Bleft()||board.Rleft())
            return false;
        return true;
    }

}
