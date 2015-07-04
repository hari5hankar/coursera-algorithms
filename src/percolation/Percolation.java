package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private WeightedQuickUnionUF wuf;
    private boolean[] isSiteOpen;
    private boolean[] isSiteConnectedToBottom;
    private int N;
    
    /*
     * Creates an N-by-N (+ 2 virtual sites) grid, with all sites blocked.
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        wuf = new WeightedQuickUnionUF(N * N + 1);
        isSiteOpen = new boolean[N * N + 1];
        isSiteConnectedToBottom = new boolean[N * N + 1];
        
        for (int i = N * (N - 1); i < N * N; i++) {
            isSiteConnectedToBottom[i] = true;
        }

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
        
        if (indicesAreInvalid(i, j)) throw new IndexOutOfBoundsException();
        
        int p = indexOf(i, j);
        
        isSiteOpen[p] = true;
        
        if (i == 1) {
            wuf.union(p, N * N);
        }
        
        if (i > 1 && isOpen(i - 1, j)) {
            int q = indexOf(i - 1, j);
            boolean unionIsConnectedToBottom = isSiteConnectedToBottom[wuf.find(q)] | isSiteConnectedToBottom[wuf.find(p)];
            wuf.union(p , q);
            isSiteConnectedToBottom[wuf.find(q)] = unionIsConnectedToBottom;
        }
        if (j > 1 && isOpen(i, j - 1)) {
            int q = indexOf(i, j - 1);
            boolean unionIsConnectedToBottom = isSiteConnectedToBottom[wuf.find(q)] | isSiteConnectedToBottom[wuf.find(p)];
            wuf.union(p , q);
            isSiteConnectedToBottom[wuf.find(q)] = unionIsConnectedToBottom;
        }
        if (i < N && isOpen(i + 1, j)) {
            int q = indexOf(i + 1, j);
            boolean unionIsConnectedToBottom = isSiteConnectedToBottom[wuf.find(q)] | isSiteConnectedToBottom[wuf.find(p)];
            wuf.union(p , q);
            isSiteConnectedToBottom[wuf.find(q)] = unionIsConnectedToBottom;
         }
        if (j < N && isOpen(i, j + 1)) {
            int q = indexOf(i, j + 1);
            boolean unionIsConnectedToBottom = isSiteConnectedToBottom[wuf.find(q)] | isSiteConnectedToBottom[wuf.find(p)];
            wuf.union(p , q);
            isSiteConnectedToBottom[wuf.find(q)] = unionIsConnectedToBottom;
        }
     
    }
    
    /*
     * Is site (row i, column j) open? 
     */
    public boolean isOpen(int i, int j) {
        if (indicesAreInvalid(i, j)) throw new IndexOutOfBoundsException();
        return isSiteOpen[indexOf(i, j)];
    }
    
    /*
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (indicesAreInvalid(i, j)) throw new IndexOutOfBoundsException();
        return wuf.connected(indexOf(i, j), N * N);
    }
    
    /*
     * Does the system percolate?
     */
    public boolean percolates() {
        return isSiteConnectedToBottom[wuf.find(N * N)];
    }
    
    /*
     * optional test client
     */
    public static void main(String[] args) {
        
    }
    
}
