package view;

import chess.Board;
import chess.Piece;
import control.ChessController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class BoardView {
    private final int WIDTH = 1400;
    private final int HEIGHT = 700;
    private ChessController controller;
    private Board board;


    public BoardView(ChessController chessController){
        controller = chessController;
    }

    public void init(Board board){
        JLabel BackGround = new JLabel(new ImageIcon("img/board.png"));
        JFrame frame= new JFrame("RPG Chess");
        JLayeredPane pane = new JLayeredPane();
        this.board = board;
        Map<String, Piece> piece = board.pieceMap;

        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);

        BackGround.setSize(WIDTH,HEIGHT);
        BackGround.setLocation(0,0);
        BackGround.addMouseListener(new BackGroundMouseListener());
        pane.add(BackGround,1);


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


}
