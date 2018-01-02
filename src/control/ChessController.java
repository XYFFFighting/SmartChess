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
        pieceMap.put("R3", new Piece("R3", new int[]{19,0}));
        pieceMap.put("R5", new Piece("R5", new int[]{39,0}));
        pieceMap.put("R7", new Piece("R7", new int[]{59,0}));
        pieceMap.put("R9", new Piece("R9", new int[]{79,0}));
        pieceMap.put("R11", new Piece("R11", new int[]{99,0}));
        pieceMap.put("B2", new Piece("B2", new int[]{0,199}));
        pieceMap.put("B4", new Piece("B4", new int[]{19,199}));
        pieceMap.put("B6", new Piece("B6", new int[]{39,199}));
        pieceMap.put("B8", new Piece("B8", new int[]{59,199}));
        pieceMap.put("R10", new Piece("R10", new int[]{79,199}));
        pieceMap.put("R12", new Piece("R12", new int[]{99,199}));
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

}
