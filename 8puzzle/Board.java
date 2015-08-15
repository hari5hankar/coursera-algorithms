
public class Board {
	private int[][] board;
	private int N;
	private int zr; // location of row of zero block
	private int zc; // location of col of zero block

	// what is the correct position in array[][] of number x?
	private int[] position(int num) {
		assert num > 0 && num < N * N;
		int x = num - 1;
		int[] p = new int[2];
		p[0] = x / N;
		p[1] = x % N;
		return p;
	}

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {

		if (blocks == null)
			throw new java.lang.NullPointerException();

		N = blocks[0].length;
		board = new int[N][N];
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {
				board[i][j] = blocks[i][j];
				if (board[i][j] == 0) {
					zr = i;
					zc = j;
				}
			}
		}
	}

	// board dimension N
	public int dimension() {
		return N;
	}

	// number of blocks out of place
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0)
					continue;
				int[] p = { i, j };
				int[] q = position(board[i][j]);
				if (p[0] != q[0] || p[1] != q[1]) // or use Arrays.equals()
					hamming++;
			}
		}
		return hamming;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int manhattan = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0)
					continue;
				int p[] = position(board[i][j]);
				manhattan += Math.abs(p[0] - i) + Math.abs(p[1] - j);
			}
		}
		return manhattan;
	}

	public boolean isGoal() {
		// is this board the goal board?
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0)
					continue;
				int[] p = { i, j };
				int[] q = position(board[i][j]);
				if (p[0] != q[0] || p[1] != q[1])
					return false;
			}
		}
		return true;
	}

	// a board that is obtained by exchanging two adjacent blocks in the same
	// row
	public Board twin() {
		int i = StdRandom.uniform(N);
		while (i == this.zr) {
			i = StdRandom.uniform(N);
		}
		Board twin = new Board(this.board);
		int temp = twin.board[i][0];
		twin.board[i][0] = twin.board[i][1];
		twin.board[i][1] = temp;
		return twin;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == null)
			return false;
		if (!(y instanceof Board))
			return false;

		final Board that = (Board) y;
		if (this.dimension() != that.dimension())
			return false;

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] != that.board[i][j])
					return false;
			}
		}
		return true;
	}

	private void swap(Board b, int i, int j, int k, int l) {
		int temp = b.board[i][j];
		b.board[i][j] = b.board[k][l];
		b.board[k][l] = temp;
		b.zr = i;
		b.zc = j;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		Queue<Board> q = new Queue<Board>();

		if (this.zc > 0) { // zero a left neighbour
			Board neighbor = new Board(this.board);
			swap(neighbor, this.zr, this.zc - 1, this.zr, this.zc);
			q.enqueue(neighbor);
		}
		if (this.zc < N - 1) { // zero has a right neighbour
			Board neighbor = new Board(this.board);
			swap(neighbor, this.zr, this.zc + 1, this.zr, this.zc);
			q.enqueue(neighbor);
		}
		if (this.zr > 0) { // zero has a top neighbour
			Board neighbor = new Board(this.board);
			swap(neighbor, this.zr - 1, this.zc, this.zr, this.zc);
			q.enqueue(neighbor);
		}
		if (this.zr < N - 1) { // zero has a bottom neighbour
			Board neighbor = new Board(this.board);
			swap(neighbor, this.zr + 1, this.zc, this.zr, this.zc);
			q.enqueue(neighbor);
		}
		return q;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", board[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		
	}
}
