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
    private final int WIDTH = 1440;
    private final int HEIGHT = 760;
    private final int PIECE_WIDTH = 38;
    private final int PIECE_HEIGHT = 38;
    private ChessController controller;
    private Board board;
    private JLayeredPane pane;
    private Map<String, JLabel> pieceSets = new HashMap<String, JLabel>();
    private JFrame frame;
    private JLabel BackGround;
    private String selected;
    public BoardView(ChessController chessController){
        this.controller = chessController;
    }

    public void init(final Board board){
        this.board = board;
        BackGround = new JLabel(new ImageIcon("img/board.png"));
        frame= new JFrame("RPG Chess");
        pane = new JLayeredPane();
        Map<String, Piece> piece = board.pieceMap;

        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
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
            chess.setLocation(pos[0]*7,pos[1]*7);
            chess.setSize(PIECE_WIDTH,PIECE_HEIGHT);
            chess.addMouseListener(new PieceMouseListener(name));
            pieceSets.put(name,chess);
            pane.add(chess,0);

        }


        frame.setResizable(false);
        frame.setVisible(true);
    }

    class BackGroundMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            //super.mousePressed(e);
            //System.out.println(e.getX() + " " + e.getY());
            if(selected!=null){//something is selected
                //move on graph
                int[] selectPosition = board.pieceMap.get(selected).position;
                int[] pos = new int[]{e.getX(),e.getY()};
                //update position in board
                board.updatePosition(selected,pos);
                MoveChess(selected,pos);



            }
        }
    }

    public void MoveChess(String name, int[] pos){
        JLabel piece = pieceSets.get(name);
        piece.setLocation(pos[0],pos[1]);
        selected=null;
    }

    class PieceMouseListener extends MouseAdapter{
        private String name;
        PieceMouseListener(String name){ this.name = name; }

        @Override
        public void mousePressed(MouseEvent e) {
            //if selected and click other chess, kill
            if(selected!=null&&name.charAt(0)!=board.player){
                //need to -HP based on PW
            }
            else if(name.charAt(0)==board.player){
                //select
                selected = name;
                //need to have a square to indicate selected
            }


            System.out.println(name);
        }
    }


}
