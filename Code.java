//Question
/*
Input 
The input will be an 3x3 matrix consisting only of 0, 1 or 2. Then another line will follow which will contain a number 1 or 2, which is your player id. 

In the given matrix, top-left is [0,0] and bottom-right is [2,2]. The x-coordinate increases from left to right, and y-coordinate increases from top to bottom. 

The cell marked 0 means it doesn't contain any marks. The cell marked 1 means it contains player 1's mark which is gray in color. The cell marked 2 means it contains player 2's mark which is black in color. 

Output 
Print the coordinates of the cell (x, y) where you want to play your move. 

Starting state 
0 0 0
0 0 0
0 0 0
*/



/* IMPORTANT: Multiple classes and nested static classes are supported */
 
/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
//import for Scanner and other utility classes
import java.util.*;
*/
import java.util.*;
 
class TestClass {
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
         * Use either of these methods for input
 
        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
 
        //Scanner
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
 
        for (int i = 0; i < N; i++) {
            System.out.println("hello world");
        }
        */
		Scanner s=new Scanner(System.in);
		int i,j;
		int inp[][]=new int[3][3];
		for(i=0;i<3;i++)
			for(j=0;j<3;j++)
				inp[i][j]=s.nextInt();
		int symbol = s.nextInt();
		int opponent=(symbol==1)?2:1;
		int numCounter=0;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				if(inp[i][j]!=0){
					numCounter++;
				}
			}
		}
		
		int v=checkWin(inp,symbol,opponent);
		v=(v==-1)?checkPinch(inp,symbol,opponent,numCounter):v;
		if(v==-1){
			ArrayList<Integer> al = new ArrayList<>();
			for(i=0; i<3 ; i++) {
				for(j=0;j<3;j++){
					if(inp[i][j]==0){
						al.add(3*i+j);
					}
				}
			}
			int random = (int) (Math.random()*al.size());
			v = al.get(random);
		}
		if(numCounter==0) {
			int random = (int) (Math.random()*9);
			while(random%2!=0) {
				random = (int) (Math.random()*9);
			}
			v=random;
		}
		int outi=v/3,outj=v%3;
		System.out.println(outj+" "+outi);
		
		
		s.close();
	}
	
	public static int checkWin(int[][] inp, int symbol, int opponent) {
		int i,j,counter=0,lose_index=-1,win_index=-1,winCount=0;
		for(i=0;i<3;i++){		//horizontal check
			counter=0;
			for(j=0;j<3;j++){
				if(inp[i][j]==symbol){
					counter++;
				}
				if(inp[i][j]==opponent){
					counter--;
				}
			}
			if(counter==2){
				for(j=0;j<3;j++){
					if(inp[i][j]==0){
						winCount++;
						win_index=3*i+j;
					}
				}
			}
			if(counter==-2 && opponent!=-1){
				for(j=0;j<3;j++){
					if(inp[i][j]==0){
						lose_index=3*i+j;
					}
				}
			}
		}
		for(j=0;j<3;j++){	//vertical check
			counter=0;
			for(i=0;i<3;i++){
				if(inp[i][j]==symbol){
					counter++;
				}
				if(inp[i][j]==opponent){
					counter--;
				}
			}
			if(counter==2){
				for(i=0;i<3;i++){
					if(inp[i][j]==0){
						winCount++;
						win_index = 3*i+j;
					}
				}
			}
			if(counter==-2 && opponent!=-1){
				for(i=0;i<3;i++){
					if(inp[i][j]==0){
						lose_index=3*i+j;
					}
				}
			}
		}
		counter=0;
		for(i=0;i<3;i++){	//left to right diagonal
			if(inp[i][i]==symbol){
				counter++;
			}
			if(inp[i][i]==opponent){
				counter--;
			}
		}
		if(counter==2){
			for(i=0;i<3;i++){
				if(inp[i][i]==0){
					winCount++;
					win_index = 3*i+i;
				}
			}
		}
		if(counter==-2 && opponent!=-1){
			for(i=0;i<3;i++){
				if(inp[i][i]==0){
					lose_index=3*i+i;
				}
			}
		}
		counter=0;
		for(i=0;i<3;i++){	//right to left diagonal
			if(inp[i][2-i]==symbol){
				counter++;
			}
			if(inp[i][2-i]==opponent){
				counter--;
			}
		}
		if(counter==2){
			for(i=0;i<3;i++){
				if(inp[i][2-i]==0){
					winCount++;
					win_index = 2*i+2;
				}
			}
		}
		if(counter==-2 && opponent!=-1){
			for(i=0;i<3;i++){
				if(inp[i][2-i]==0){
					lose_index=2*i+2;
				}
			}
		}
		if(opponent == -1){
			if(winCount>1){
				return 1;
			}else{
				return 0;
			}
		}
		if(win_index!=-1){
			return win_index;
		}
		return lose_index;
	}
	
	public static int checkPinch(int[][] inp, int symbol, int opponent,int numCounter){
		int i,j,k,l;
		if(numCounter==1){
			if(inp[1][1]==opponent){
				int randArr[]={0,2,6,8};
				int random = (int) (Math.random()*4);
				return randArr[random];
			}
			int check=checkCorner(inp,opponent);
			if(check!=-1){
				int test=check/3;
				int arr[]={(check+3)%9,(check+6)%9,test*3 + ((check+1)%3),test*3 + ((check+2)%3)};
				int random = (int) (Math.random()*4);
				return arr[random];
			}
		}
		if(numCounter==2){
			if(inp[1][1]==symbol){
				if(inp[0][1] ==opponent || inp[1][0] == opponent || inp[1][2] ==opponent || inp[2][1]==opponent){
					int randArr[]={0,2,6,8};
					int random = (int) (Math.random()*4);
					return randArr[random];
				}
			}
			if(inp[0][0]!=0 && inp[0][2]!=0){
				if(inp[0][0]==symbol){
					return 8;
				}else{
					return 6;
				}
			}
			if(inp[0][0]!=0 && inp[2][0]!=0){
				if(inp[0][0]==symbol){
					return 8;
				}else{
					return 2;
				}
			}
			if(inp[2][0]!=0 && inp[2][2]!=0){
				if(inp[2][0]==symbol){
					return 2;
				}else{
					return 0;
				}
			}
			if(inp[0][2]!=0 && inp[2][2]!=0){
				if(inp[0][2]==symbol){
					return 6;
				}else{
					return 0;
				}
			}
		}
		if(numCounter==3){
			int arr[]=new int[9];
			int iCounter=0;
			for(i=0;i<3;i++){
				for(j=0;j<3;j++){
					if(inp[i][j]==0){
						inp[i][j]=symbol;
						for(k=0;k<3;k++){
							for(l=0;l<3;l++){
								if(inp[k][l]==0){
									inp[k][l]=opponent;
									if(checkWin(inp,opponent,-1)==1){
										inp[k][l]=0;
										if(3*k+l==checkWin(inp,symbol,opponent)){
											arr[3*i+j]=1;
											iCounter=1;
										}
										inp[k][l]=opponent;
									}
									inp[k][l]=0;
								}
							}
						}
						inp[i][j]=0;
					}else{
						arr[3*i+j]=1;
						
					}
				}
			}
			if(iCounter==1){
				ArrayList<Integer> al = new ArrayList<>();
				for(i=0; i<9; i++) {
					if(arr[i] == 0) {
						al.add(i);
					}
				}
				int random = (int) (Math.random()*al.size());
				return al.get(random);
			}
		}
		if(numCounter==4){
			for(i=0;i<3;i++){
				for(j=0;j<3;j++){
					if(inp[i][j]==0){
						inp[i][j]=symbol;
						if(checkWin(inp,symbol,-1)==1){
							return 3*i+j;
						}
						inp[i][j]=0;
					}
				}
			}
		}
		
		return -1;
	}
	
	
	public static int checkCorner(int[][] inp, int number) {
		for(int i=0; i<inp.length; i++) {
			for(int j=0; j<inp[i].length; j++) {
				if(inp[i][j]==number) {
					if(i==j && i!=1)
						return 3*i+j;
					else if(i==0 && j==2)
						return 2;
					else if(i==2 && j==0)
						return 6;
				}
			}
		}
		return -1;
	}
	
}