package view;

import alogrithm.rules;
import chess.Board;
import chess.Piece;
import control.ChessController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class BoardView {
    private final int WIDTH = 1460;
    private final int HEIGHT = 800;
    private final int PIECE_WIDTH = 38;
    private final int PIECE_HEIGHT = 38;
    private ChessController controller;
    private rules rules = new rules();
    private Board board;
    private JLayeredPane pane;
    private Map<String, JLabel> pieceSets = new HashMap<String, JLabel>();
    private JFrame frame;
    private JLabel BackGround;
    private String selected;
    public String nextMove;
    public String Sequence[];
    public int order = 0;
    public BoardView(ChessController chessController){
        this.controller = chessController;
    }
    private JButton Move;
    private JButton Attack;
    private JButton Property;
    private JPanel bottom;
    private JFrame property;
    private JPanel PROPERTY;
    private JButton Skip;
    private JLabel kuang;
    private Map<String, Piece> piece;
    public void init(final Board board){
        this.board = board;
        BackGround = new JLabel(new ImageIcon("img/board.png"));
        kuang = new JLabel(new ImageIcon("img/kuang.png"));
        BackGround.setLocation(0,0);
        frame= new JFrame("RPG Chess");
        pane = new JLayeredPane();
        piece = board.pieceMap;

        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);

        bottom = new JPanel(new GridBagLayout());
        bottom.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        bottom.setSize(new Dimension(WIDTH,40));

        Move = new JButton("Move");
        Attack = new JButton("Attack");
        Property = new JButton("Property");
        Skip  =new JButton("Skip");
        Property.addMouseListener(new ShowPropertyWindow());

        bottom.add(Move);
        bottom.add(Attack);
        bottom.add(Property);
        bottom.add(Skip);
        bottom.setLocation(0,730);
        pane.add(bottom);

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
        Sequence = new String[board.pieceMap.size()];
        Sequence  = rules.sequence(board);
        nextMove = Sequence[order];
        board.player = Sequence[order].charAt(0);


        int pos[] = piece.get(nextMove).position;
        kuang.setLocation(pos[0]*7,pos[1]*7);
        kuang.setSize(40,40);
        pane.add(kuang,0);

        frame.setResizable(false);
        frame.setVisible(true);
    }



    class ShowPropertyWindow extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            if(selected!=null){
                //frame.setEnabled(false);
                property = new JFrame("Property");
                PROPERTY = new JPanel(new GridLayout(0,1));
                property.setSize(350,550);
                PROPERTY.setSize(300,500);
                property.add(PROPERTY);
                JLabel HP = new JLabel("Health Power: "+board.pieceMap.get(selected).HP);
                JLabel SD = new JLabel("Speed: "+board.pieceMap.get(selected).SD);
                JLabel RG = new JLabel("Range: "+board.pieceMap.get(selected).RG);
                JLabel PW = new JLabel("Power: "+board.pieceMap.get(selected).PW);
                JLabel MV = new JLabel("Movement: "+board.pieceMap.get(selected).MV);
                PROPERTY.add(HP);
                PROPERTY.add(SD);
                PROPERTY.add(RG);
                PROPERTY.add(PW);
                PROPERTY.add(MV);
                property.setVisible(true);
                //property.setAlwaysOnTop(true);
                property.setLocationRelativeTo(null);
            }
        }
    }


    class BackGroundMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            //super.mousePressed(e);
            //System.out.println(e.getX() + " " + e.getY());
            if(selected==nextMove){//something is selected
                //move on graph
                int[] selectPosition = board.pieceMap.get(nextMove).position;
                int[] pos1 = new int[]{e.getX()/7,e.getY()/7};
                int[] pos2 = new int[]{e.getX(),e.getY()};
                //update position in board
                board.updatePosition(nextMove,pos1, order);
                MoveChess(nextMove,pos2);
                nextMove = Sequence[order];

                int kuangPos[] = piece.get(nextMove).position;
                kuang.setLocation(kuangPos[0]*7,kuangPos[1]*7);


            }
        }
    }

    public void MoveChess(String name, int[] pos){
        JLabel piece = pieceSets.get(name);
        piece.setLocation(pos[0]-19,pos[1]-19);
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
