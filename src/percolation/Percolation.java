package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private WeightedQuickUnionUF wuf;
    private boolean[] open;
    private int N;
    
    /*
     * Creates an N-by-N (+ 2 virtual sites) grid, with all sites blocked
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        wuf = new WeightedQuickUnionUF(N * N + 2);
        open = new boolean[N * N + 2];
    }

    private boolean areIndicesValid(int i , int j) {
        return !(i < 1 || j < 1 || i > N || j > N);
    }
    
    /*
     * returns index in a 1-d array addressed by 2-d indices.
     */
    private int indexOf(int i, int j) {
        if (!areIndicesValid(i, j)) throw new IndexOutOfBoundsException();
        return (i - 1) * N + (j - 1);
    }
    
    /*
     * Opens site (row i, column j) if it is not open already
     */
    public void open(int i, int j) {
        
        if (!areIndicesValid(i, j)) throw new IndexOutOfBoundsException();
        
        int p = indexOf(i, j);
        open[p] = true;
        
        if (i == 1) {
            wuf.union(indexOf(i, j), N * N);
        }
        if (i == N) {
            wuf.union(indexOf(i, j), N * N + 1);
        }
        
        if (i > 1) {
            if (isOpen(i - 1, j)) {
                wuf.union(indexOf(i, j) , indexOf(i - 1 , j));
            }
        }
        if (j > 1) {
            if (isOpen(i, j - 1)) {
                wuf.union(indexOf(i, j) , indexOf(i, j - 1));
            }
        }
        if (i < N) {
            if (isOpen(i + 1, j)) {
                wuf.union(indexOf(i, j) , indexOf(i + 1 , j));
            }
        }
        if (j < N) {
            if (isOpen(i, j + 1)) {
                wuf.union(indexOf(i, j) , indexOf(i , j + 1));
            }
        }
       
    }
    
    /*
     * Is site (row i, column j) open? 
     */
    public boolean isOpen(int i, int j) {
        if (!areIndicesValid(i, j)) throw new IndexOutOfBoundsException();
        return open[indexOf(i, j)];
    }
    
    /*
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (!areIndicesValid(i, j)) throw new IndexOutOfBoundsException();
        return wuf.connected(indexOf(i, j), N * N);
    }
    
    /*
     * Does the system percolate?
     */
    public boolean percolates() {
        return wuf.connected(N * N , N * N + 1);
    }
    
    public static void main(String[] args) {
        
    }
    
}
