package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF wuf1;
    private WeightedQuickUnionUF wuf2;
    private boolean[] open;
    private int N;
    
    /*
     * Creates an N-by-N (+ 2 virtual sites) grid, with all sites blocked
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        wuf1 = new WeightedQuickUnionUF(N * N + 2);
        wuf2 = new WeightedQuickUnionUF(N * N + 1);
        open = new boolean[N * N + 2];
    }

    private boolean indicesAreInvalid(int i , int j) {
        return (i < 1 || j < 1 || i > N || j > N);
    }
    
    /*
     * returns index in a 1-d array addressed by 2-d indices (i * N + j)
     */
    private int indexOf(int i, int j) {
        if (indicesAreInvalid(i, j)) throw new IndexOutOfBoundsException();
        return (i - 1) * N + (j - 1);
    }
    
    /*
     * Opens site (row i, column j) if it is not open already
     */
    public void open(int i, int j) {
       // StdOut.println();
        
        if (indicesAreInvalid(i, j)) throw new IndexOutOfBoundsException();
        
        int p = indexOf(i, j);
        
        open[p] = true;
        
        if (i == 1) {
            wuf1.union(p, N * N);
            wuf2.union(p, N * N);
        }
        if (i == N) {
            wuf1.union(p, N * N + 1);
        }
        
        if (i > 1) {
            if (isOpen(i - 1, j)) {
                wuf1.union(p , indexOf(i - 1 , j));
                wuf2.union(p , indexOf(i - 1 , j));
            }
        }
        if (j > 1) {
            if (isOpen(i, j - 1)) {
                wuf1.union(p , indexOf(i, j - 1));
                wuf2.union(p , indexOf(i, j - 1));
            }
        }
        if (i < N) {
            if (isOpen(i + 1, j)) {
                wuf1.union(p , indexOf(i + 1 , j));
                wuf2.union(p , indexOf(i + 1 , j));
            }
        }
        if (j < N) {
            if (isOpen(i, j + 1)) {
                wuf1.union(p , indexOf(i , j + 1));
                wuf2.union(p , indexOf(i , j + 1));
            }
        }
       
    }
    
    /*
     * Is site (row i, column j) open? 
     */
    public boolean isOpen(int i, int j) {
        if (indicesAreInvalid(i, j)) throw new IndexOutOfBoundsException();
        return open[indexOf(i, j)];
    }
    
    /*
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (indicesAreInvalid(i, j)) throw new IndexOutOfBoundsException();
        //StdOut.print(wuf1.find(indexOf(i, j)) + " ");
        return wuf2.connected(indexOf(i, j), N * N);
    }
    
    /*
     * Does the system percolate?
     */
    public boolean percolates() {
        return wuf1.connected(N * N , N * N + 1);
    }
    
    /*
     * optional test client
     */
    public static void main(String[] args) {
        
    }
    
}