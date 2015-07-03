package percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    private double[] results;
    
    /*
     * Performs T independent experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();

        results = new double[T];
        
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            int numOpenSites = 0;
            
            while (!perc.percolates()) {
                int p = StdRandom.uniform(1, N + 1);
                int q = StdRandom.uniform(1, N + 1);
                if (!perc.isOpen(p,  q)) {
                    perc.open(p, q);
                    numOpenSites++;
                }
            }
            
            results[i] = (double) numOpenSites / (N * N);
        }
     
        mean = 0;
        for (int i = 0; i < results.length; i++) {
            mean += results[i];
        }
        mean = mean / T;
        
        stddev = 0;
        for (int i = 0; i < results.length; i++) {
            stddev += (results[i] - mean) * (results[i] - mean);
        }
        
        stddev = stddev / (T -1);
        stddev = Math.sqrt(stddev);
        
        confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(T));
        confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(T));
    }
    
    /*
     * sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }
    
    /*
     *  sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }
    
    /*
     *  low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return confidenceLo;
    }
    
    /*
     * high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return confidenceHi;
    }
    
    /*
     * test client
     */
    public static void main(String[] args) {
        int N =  Integer.parseInt(args[0]);
        int T =  Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " 
                   + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
    
}

