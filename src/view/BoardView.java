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

    public BoardView(ChessController chessController) {
        controller = chessController;
    }

    private JButton Move;
    private JButton Attack;
    private JButton Property;
    private JPanel bottom;
    private JFrame property;
    private JPanel PROPERTY;
    private JButton Skip;
    private JLabel kuang;
    private JLabel MoveKuang;
    private Map<String, Piece> piece;
    private int mode = 0;//0 for move mode, 1 for attack mode

    public void init(final Board board) {
        this.board = board;
        BackGround = new JLabel(new ImageIcon("img/board.png"));
        kuang = new JLabel(new ImageIcon("img/kuang.png"));
        BackGround.setLocation(0, 0);
        frame = new JFrame("RPG Chess");
        pane = new JLayeredPane();
        piece = board.pieceMap;

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(pane);

        bottom = new JPanel(new GridBagLayout());
        bottom.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        bottom.setSize(new Dimension(WIDTH, 40));

        Move = new JButton("Move");
        Attack = new JButton("Attack");
        Property = new JButton("Property");
        Skip = new JButton("Skip");
        Property.addMouseListener(new ShowPropertyWindow());
        Attack.addMouseListener(new AttackMode());
        Move.addMouseListener(new MoveMode());
        Skip.addMouseListener(new Skip());

        bottom.add(Move);
        bottom.add(Attack);
        bottom.add(Property);
        bottom.add(Skip);
        bottom.setLocation(0, 730);
        pane.add(bottom);

        BackGround.setSize(WIDTH, HEIGHT);
        BackGround.setLocation(0, 0);
        BackGround.addMouseListener(new BackGroundMouseListener());
        pane.add(BackGround, 2);
        int i = 0;
        for (Map.Entry<String, Piece> stringPieceEntry : piece.entrySet()) {
            String name = stringPieceEntry.getKey();
            Piece piece1 = stringPieceEntry.getValue();
            int[] pos = piece1.position;
            JLabel chess = new JLabel(new ImageIcon("img/" + name.substring(0, 1) + ".png"));
            chess.setLocation(pos[0] - 19, pos[1] - 19);
            chess.setSize(PIECE_WIDTH, PIECE_HEIGHT);
            chess.addMouseListener(new PieceMouseListener(name));
            pieceSets.put(name, chess);
            pane.add(chess, 0);


        }

        Sequence = new String[board.pieceMap.size()];
        Sequence = rules.sequence(board);
        nextMove = Sequence[order];
        board.player = Sequence[order].charAt(0);


        int pos[] = piece.get(nextMove).position;
        kuang.setLocation(pos[0] - 19, pos[1] - 19);
        kuang.setSize(40, 40);
        pane.add(kuang, 0);
        MoveKuang = new JLabel(new ImageIcon("img/MV" + piece.get(nextMove).MV + ".png"));
        MoveKuang.setSize(piece.get(nextMove).MV * 100, piece.get(nextMove).MV * 100);
        MoveKuang.setLocation(pos[0] - 50, pos[1] - 50);
        pane.add(MoveKuang, 1);


        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void MoveChess(String name, int[] pos) {
        JLabel piece = pieceSets.get(name);
        piece.setLocation(pos[0] - 19, pos[1] - 19);
        selected = null;
    }


    class ShowPropertyWindow extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (selected != null) {
                //frame.setEnabled(false);
                property = new JFrame("Property");
                PROPERTY = new JPanel(new GridLayout(0, 1));
                property.setSize(350, 550);
                PROPERTY.setSize(300, 500);
                property.add(PROPERTY);
                JLabel HP = new JLabel("Health Power: " + board.pieceMap.get(selected).HP);
                JLabel SD = new JLabel("Speed: " + board.pieceMap.get(selected).SD);
                JLabel RG = new JLabel("Range: " + board.pieceMap.get(selected).RG);
                JLabel PW = new JLabel("Power: " + board.pieceMap.get(selected).PW);
                JLabel MV = new JLabel("Movement: " + board.pieceMap.get(selected).MV);
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


    class BackGroundMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

            if (selected == nextMove && mode == 0) {//something is selected
                //move on graph
                int[] selectPosition = board.pieceMap.get(nextMove).position;
                int[] pos1 = new int[]{e.getX(), e.getY()};
                //update position in board
                if (pos1[0] - selectPosition[0] <= board.pieceMap.get(nextMove).MV * 100 && pos1[1] - selectPosition[1] <= board.pieceMap.get(nextMove).MV * 100) {
                    board.updatePosition(nextMove, pos1, order);
                    MoveChess(nextMove, pos1);
                    nextMove = Sequence[order];

                    int kuangPos[] = piece.get(nextMove).position;
                    kuang.setLocation(kuangPos[0] - 19, kuangPos[1] - 19);

                    MoveKuang.setIcon(new ImageIcon("img/MV" + piece.get(nextMove).MV + ".png"));
                    MoveKuang.setSize(piece.get(nextMove).MV * 100, piece.get(nextMove).MV * 100);
                    MoveKuang.setLocation(kuangPos[0] - piece.get(nextMove).MV * 50, kuangPos[1] - piece.get(nextMove).MV * 50);

                }

            }

        }
    }


    class MoveMode extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (mode == 0) {
                //do nothing
            } else if (mode == 1) {
                //change to move mode
                mode = 0;
                //hide attack range
                //show move range
                int kuangPos[] = piece.get(nextMove).position;
                kuang.setLocation(kuangPos[0] - 19, kuangPos[1] - 19);

                MoveKuang.setIcon(new ImageIcon("img/MV" + piece.get(nextMove).MV + ".png"));
                MoveKuang.setSize(piece.get(nextMove).MV * 100, piece.get(nextMove).MV * 100);
                MoveKuang.setLocation(kuangPos[0] - piece.get(nextMove).MV * 50, kuangPos[1] - piece.get(nextMove).MV * 50);
            }
        }
    }

    class AttackMode extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (mode == 0) {
                //change to attack mode
                mode = 1;
                //hide move range
                //show attack range
                MoveKuang.setIcon(new ImageIcon("img/RG" + piece.get(nextMove).RG + ".png"));
                MoveKuang.setSize(piece.get(nextMove).RG * 100, piece.get(nextMove).RG * 100);
                int kuangPos[] = piece.get(nextMove).position;
                kuang.setLocation(kuangPos[0] - 19, kuangPos[1] - 19);
                MoveKuang.setLocation(kuangPos[0] - piece.get(nextMove).RG * 50, kuangPos[1] - piece.get(nextMove).RG * 50);
            }

        }
    }

    class Skip extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            board.updatePosition(nextMove, board.pieceMap.get(nextMove).position, order);
            MoveChess(nextMove, board.pieceMap.get(nextMove).position);
            nextMove = Sequence[order];
            int kuangPos[] = piece.get(nextMove).position;
            kuang.setLocation(kuangPos[0] - 19, kuangPos[1] - 19);

            MoveKuang.setIcon(new ImageIcon("img/MV" + piece.get(nextMove).MV + ".png"));
            MoveKuang.setSize(piece.get(nextMove).MV * 100, piece.get(nextMove).MV * 100);
            MoveKuang.setLocation(kuangPos[0] - piece.get(nextMove).MV * 50, kuangPos[1] - piece.get(nextMove).MV * 50);
        }
    }

    class PieceMouseListener extends MouseAdapter {
        private String name;

        PieceMouseListener(String name) {
            this.name = name;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //if selected and click other chess, kill
            if (name.charAt(0) == board.player) {
                //select
                selected = name;
                //need to have a square to indicate selected
            }

            System.out.println(name);
            if (selected == nextMove && mode == 1) {
                //attack mode
                //check range if out of range do nothing
                System.out.println("in range");
                int SqureModelRange = (board.pieceMap.get(nextMove).RG * 100) ^ 2;
                int[] selectPosition = board.pieceMap.get(nextMove).position;
                int[] pos1 = board.pieceMap.get(name).position;
                Piece tpiece = board.pieceMap.get(name);
                if (((Math.abs(selectPosition[0] - pos1[0]) ^ 2 + Math.abs(selectPosition[1] - pos1[1]) ^ 2) <= SqureModelRange)) {
                    //attack something
                    System.out.println("Attack");
                    //if(!name.equals(selected)) {

                    tpiece.HP -= board.pieceMap.get(nextMove).PW * 10;
                    //skip
                    board.updatePosition(nextMove, board.pieceMap.get(nextMove).position, order);
                    MoveChess(nextMove, board.pieceMap.get(nextMove).position);
                    nextMove = Sequence[order];
                    int kuangPos[] = piece.get(nextMove).position;
                    kuang.setLocation(kuangPos[0] - 19, kuangPos[1] - 19);

                    MoveKuang.setIcon(new ImageIcon("img/MV" + piece.get(nextMove).MV + ".png"));
                    MoveKuang.setSize(piece.get(nextMove).MV * 100, piece.get(nextMove).MV * 100);
                    MoveKuang.setLocation(kuangPos[0] - piece.get(nextMove).MV * 50, kuangPos[1] - piece.get(nextMove).MV * 50);
                    mode = 0;
                    //}


                }


            }


        }
    }
}
