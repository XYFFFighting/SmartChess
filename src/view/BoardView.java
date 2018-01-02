package view;

import chess.Board;
import chess.Piece;
import control.ChessController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class BoardView {
    private final int WIDTH = 1400;
    private final int HEIGHT = 700;
    private final int PIECE_WIDTH = 67;
    private final int PIECE_HEIGHT = 67;
    private ChessController controller;
    private Board board;
    private JLayeredPane pane;
    private Map<String, JLabel> pieceSets = new HashMap<String, JLabel>();
    private JFrame frame;

    public BoardView(ChessController chessController){
        controller = chessController;
    }

    public void init(Board board){
        JLabel BackGround = new JLabel(new ImageIcon("img/board.png"));
        frame= new JFrame("RPG Chess");
        pane = new JLayeredPane();
        this.board = board;
        Map<String, Piece> piece = board.pieceMap;

        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);

        BackGround.setSize(WIDTH,HEIGHT);
        BackGround.setLocation(0,0);
        BackGround.addMouseListener(new BackGroundMouseListener());
        pane.add(BackGround,1);

        for(Map.Entry<String, Piece>stringPieceEntry : piece.entrySet()){
            String name = stringPieceEntry.getKey();
            Piece piece1 = stringPieceEntry.getValue();
            int[] pos = piece1.position;
            JLabel chess = new JLabel(new ImageIcon("img/"+name.substring(0,1)+".png"));
            chess.setLocation(pos[0],pos[1]);
            chess.setSize(PIECE_WIDTH,PIECE_HEIGHT);
            chess.addMouseListener(new PieceMouseListener(name));
            pieceSets.put(name,chess);
            pane.add(chess,0);

        }


        frame.setResizable(false);
        frame.setVisible(true);
    }

    //not yet finish
    class BackGroundMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            //super.mousePressed(e);
            System.out.println(e.getX() + " " + e.getY());
        }
    }

    class PieceMouseListener extends MouseAdapter{
        private String name;
        PieceMouseListener(String name){ this.name = name; }

        @Override
        public void mousePressed(MouseEvent e) {
            //select


            //move
            //check valid
            //remove
            pane.remove(pieceSets.get(name));
            //show new


            //attack

            System.out.println(name);
        }
    }


}
