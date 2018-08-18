import java.util. Scanner;
public class Lab4 {
	static Scanner sc = new Scanner(System.in);
	static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
	public static enum Player{X, O};
	public static void main(String[] args) {
		Player turn = Player.X;
		boolean isBoardFull = false;
		while (isBoardFull == false && checkPlayerWin()==false) {
			getMove(turn);
			printBoard();
			isBoardFull = isBoardFull();
			if (checkPlayerWin() == true) {
				System.out.println("\n Player " + turn + " Has Won The Game");
			}
			turn=changeTurn(turn);
		}
		if (checkPlayerWin() == false) {
			System.out.println("Stalemate!");
		}
	}

	public static void getMove(Player player) {
		int a,b;
		boolean cellFull= false;
		do {
			do {
				System.out.println("Player " + player + " is Playing: Choose a row");
				while (!sc.hasNextInt()) {
					String badInt = sc.nextLine();
					System.out.println(badInt + "is invalid");
				} 
				a = sc.nextInt();
				sc.nextLine();
			} while(a < 0 || a > 2);
			do {
				System.out.println("Player " + player + " is Playing: Choose a column");
				while (!sc.hasNextInt()) {
					String badInt = sc.nextLine();
					System.out.println(badInt + "is invalid");
				}
				b = sc.nextInt();
				sc.nextLine();
			} while (b < 0 || b > 2);
			cellFull = isCellFull(a,b);
			if (cellFull == true) {
				System.out.println("Cell is occupied. Choose again");
			}
		} while (cellFull == true);
		if (player == Player.X) {
			board[a][b] = 'X';
		} else {
			board[a][b] = 'O';
		}
	}

	public static boolean isCellFull(int x, int y) {
		boolean isFull = true;
		if (board[x][y] == ' ') {
			isFull = false;
		}
		return isFull;
	}

	public static boolean isBoardFull() {
		boolean boardFull = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					boardFull = false;
				}
			}
		}
		return boardFull;
	}

	public static boolean winnerRow() {
		boolean checkWin = false;
		char[] check = new char[board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				check[j] = board[i][j];
			}
			if((check[0] == check[1]) && (check[1] == check[2]) && (check[0] != ' ')) {
				checkWin = true;
			} 
		}
		return checkWin;
	}

	public static boolean winnerColumn() {
		boolean checkWin = false;
		char[] check = new char[board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				check[j] = board[j][i];
			}
			if((check[0] == check[1]) && (check[1] == check[2]) && (check[0] != ' ')) {
				checkWin =  true;
			}
		}
		return checkWin;
	}
	public static boolean winnerDiag(){
		boolean checkWin = false;
		char[] check = new char[board.length];

		for (int i =0; i < board.length; i++) {
			check[i] = board [i][i];
		}
		if((check[0] == check[1]) && (check[1] == check[2]) && (check[0] != ' ')) {
			checkWin = true;
		}
		return checkWin;
	}
	public static boolean winnerDiagAnte() {
		boolean checkWin = false;
		char[] check = new char[board.length];
		int x = 2;
		for (int i = 0; i < board.length; i++ ) {
			for (int j = x; j <=x; j++ ) {
				check[i] = board[i][j];
			}
			x--;
		}

		if((check[0] == check[1]) && (check[1] == check[2]) && (check[0] != ' ')) {
			checkWin = true;
		}
		return checkWin;
	}

	public static boolean checkPlayerWin() {
		boolean win = false;
		if ((winnerRow() == true) || (winnerColumn()==true) || (winnerDiag() == true) || (winnerDiagAnte() == true)) {
			win = true;
		}
		return win;
	}
	public static Player changeTurn(Player turn) {
		if (turn == Player.X) {
			turn = Player.O;
		} else {
			turn = Player.X;
		}
		return turn;
	}
	public static void printBoard() {
		for (int i = 0; i < 3; i++) {
			System.out.println();
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					System.out.print("- ");
				} else {
					System.out.print(board[i][j] + " ");
				}
			}
		}
	}
}
