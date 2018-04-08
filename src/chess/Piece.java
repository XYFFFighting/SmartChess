package chess;

public class Piece {
    public char color;
    public int[] position = new int[2];
    public int HP=100;//health
    public int SD=10;//speed
    public int RG=4;//range
    public int PW=5;//power
    public int MV=5;//Movement
    public char index;
    public Piece(String name, int[] position){
        /*
        color: R for player 1; B for player 2;
         */
        this.color = name.charAt(0);
        this.index = name.charAt(1);
        this.position=position;


    }

    public void addHP(int HP){
        this.HP += HP;
    }

    public void addSD(int SD){
        this.SD += SD;
    }

    public void addRG(int RG){
        this.RG += RG;
    }

    public void addPW(int PW){
        this.PW += PW;
    }

    public void addMV(int MV)
    {
        this.MV += MV;
    }

}
