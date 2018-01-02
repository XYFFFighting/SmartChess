package alogrithm;

//chess[12][6] = {NUM, HP, SD, MV, RA, PO}; NUM: 1 - 12

public class rulls {
    int a[];
    for(int i = 0; i < 12; i++){
        for(int j = 0; j < 11; j++){
            if(chess[j][2] < chess[j+1][2]){//compare SD
                a[] = chess[j][];
                chess[j][] = chess[j+1][];
                chess[j+1][] = a[];
            }
            else if(chess[j][2] == chess[j+1][2]){// same SD compare NUM
                if(chess[j][0] > chess[j+1][0]){
                    a[] = chess[j][];
                    chess[j][] = chess[j+1][];
                    chess[j+1][] = a[];
                }
            }
        }
    }
    for(int i = 0; i < 12; i++){
        if(chess[i][1] > 0){ //no dead chess
            move(i);
            attack(i);
        }
    }
}

//no chess: board[x][y] = 0; you chess: board[x][y] = chess[i][0](NUM of chess);
//click[2] = {click_x , click_y}: user input;

void move(int i){
    int w, h;
    find_chess_i(i, w, h, board);
    do {
        click[] = get_user_input();// NUM 1,3,5,7,9,11=user1, NUM 2,4,6,8,10,12=user2;
        if (abs(w - click_x) < chess[i][3] * 10 && abs(h - click_y) < chess[i][3] * 10) {
            board[click_x][click_y] = i;
            board[w][h] = 0;
        }
        else {//out of MV range, click again
            false;
        }
    }while(false);
}

void attack(int i){
    double distance;
    int w, h, j;
    find_chess_i(i, w, h, board);
    do{
        click[] = get_user_input();
        if(board[click_x][click_y] != 0){
            j = board[click_x][click_y];//NUM of selected chess
            distance = sqrt(pow((click_x - w), 2) + pow((click_y - h), 2));
            if(distance <= chess[i][4]){
                chess[j][1] = chess[j][1] - chess[i][5];
            }
            else{//out of RA
                false;
            }
        }
        else{//no chess selected
            false;
        }
    }while(false);
}

void find_chess_i(int i, int &w, int &h, int *board[]){
    for(int x = 0; x < 200; x++){
        for(int y = 0; y < 100; y++){
            if(board[x][y] == i){
                w = x;
                h = y;
            }
        }
    }
}