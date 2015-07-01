package percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
   
	private Percolation perc;
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	private double[] results;
	
	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T){	   
		results = new double[T];		
		
		for( int i = 0; i < T; i++){

			perc = new Percolation(N);			
			int numOpenSites = 0;
			while(!perc.percolates()){
				int p = StdRandom.uniform(1, N + 1);
				int q = StdRandom.uniform(1, N + 1);
				if(! perc.isOpen(p, q)){
					perc.open(p, q);
					numOpenSites ++;
				}
			}
			
			results[i] = (double)numOpenSites / (N * N);
		}
		
		mean = 0;
		for(int i = 0; i < results.length; i++){
			mean += results[i];
		}
		mean = mean / T;
		
		stddev = 0;
		for(int i = 0; i < results.length; i++){
			stddev += (results[i] - mean) * (results[i] - mean);
		}
		
		stddev = stddev / (T -1 );
		stddev = Math.sqrt(stddev);
		
		confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(T));
		confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(T));
	
   }
	
	
   public double mean(){
	   // sample mean of percolation threshold
	   return mean;
   }
   public double stddev(){
	   // sample standard deviation of percolation threshold
	   return stddev;
   }
   public double confidenceLo() {
	   // low  endpoint of 95% confidence interval
	   return confidenceLo;
   }
   public double confidenceHi(){
	   // high endpoint of 95% confidence interval
	   return confidenceHi;
   }
   
   public static void main(String[] args){
	   // test client (described below)
	   int N =  Integer.parseInt(args[0]);
	   int T =  Integer.parseInt(args[1]);
	   PercolationStats ps = new PercolationStats(N, T);
	   StdOut.println("mean                    = " + ps.mean());
	   StdOut.println("stddev                  = " + ps.stddev());
	   StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
   }
}
