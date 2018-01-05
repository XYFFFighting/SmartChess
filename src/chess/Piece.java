package chess;

public class Piece {
    public char color;
    public int[] position = new int[2];
    public int HP=100;//health
    public int SD=10;//speed
    public int RG=1;//range
    public int PW=1;//power
    public int MV=10;//Movement
    public char index;
    public Piece(String name, int[] position){
        /*
        color: R for player 1; B for player 2;
         */
        this.color = name.charAt(0);
        this.index = name.charAt(1);
        this.position=position;

    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public void setSD(int SD){
        this.SD= SD;
    }

    public void setRG(int RG){
        this.RG = RG;
    }

    public void setPW(int PW){
        this.PW = PW;
    }

    public void setMV(int MV)
    {
        this.MV = MV;
    }

}
