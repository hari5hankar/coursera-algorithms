
public class Solver {

	private boolean initialIsSolvable;
	private SearchNode goal;

	private class SearchNode implements Comparable<SearchNode> {

		Board board;
		int moves, manhattan, priority;
		SearchNode prev;

		public SearchNode(Board board, int moves, SearchNode prev) {
			this.board = board;
			this.moves = moves;
			this.manhattan = this.board.manhattan();
			this.priority = this.manhattan + this.moves;
			this.prev = prev;
		}

		public int compareTo(SearchNode that) {
			if (this.priority < that.priority)
				return -1;
			if (this.priority > that.priority)
				return +1;
			return 0;
		}

	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {

		if (initial == null)
			throw new java.lang.NullPointerException();

		MinPQ<SearchNode> pq = new MinPQ<>();
		pq.insert(new SearchNode(initial, 0, null));
		SearchNode sn = pq.delMin();

		MinPQ<SearchNode> pq2 = new MinPQ<>();
		pq2.insert(new SearchNode(initial.twin(), 0, null));
		SearchNode sn2 = pq2.delMin();

		while (!(sn.board.isGoal() || sn2.board.isGoal())) {

			for (Board b : sn.board.neighbors()) {
				if (sn.prev != null && b.equals(sn.prev.board))
					continue;
				pq.insert(new SearchNode(b, sn.moves + 1, sn));
			}
			sn = pq.delMin();

			for (Board b : sn2.board.neighbors()) {
				if (sn2.prev != null && b.equals(sn2.prev.board))
					continue;
				pq2.insert(new SearchNode(b, sn2.moves + 1, sn2));
			}
			sn2 = pq2.delMin();
		}

		if (sn.board.isGoal()) {
			initialIsSolvable = true;
			this.goal = sn;
			return;
		}

		if (sn2.board.isGoal()) {
			initialIsSolvable = false;
			this.goal = null;
			return;
		}

	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return this.initialIsSolvable;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if (this.goal == null)
			return -1;
		return this.goal.moves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (this.goal == null)
			return null;
		Stack<Board> solution = new Stack<>();
		SearchNode temp = this.goal;
		while (temp != null) {
			solution.push(temp.board);
			temp = temp.prev;
		}
		return solution;
	}

	public static void main(String[] args) {

	}

}