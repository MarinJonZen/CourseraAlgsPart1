

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  
  private double[] results;

  /** Ctor.
   * @param nn nn.
   * @param trials no. of trials.
   */
  public PercolationStats( int nn, int trials ) {
    if ( nn <= 0 || trials <= 0 ) {
      throw new IllegalArgumentException();
    } else {
      this.results = new double[trials];
      
      
      for (int t = 0 ; t < trials ; ++t ) {
        int count = 0;
        Percolation perc = new Percolation(nn);

        while ( ! perc.percolates() ) {
          int ii = StdRandom.uniform(1,nn + 1);
          int jj = StdRandom.uniform(1, nn + 1);
          
          if ( ! perc.isOpen(ii, jj)) {
            perc.open(ii, jj);
            count++;
          }
        }
        this.results[t] = 1.0 * count / (nn * nn);
      }
    }
    
  }
  
  public double mean() {
    return StdStats.mean(results);
  }
  
  public double stddev() {
    return StdStats.stddev(results);
  }
  
  /** Compute confidence lo.
   * @return confidence.
   */
  public double confidenceLo() {
    double mean = StdStats.mean(results);
    double stddev = StdStats.stddev(results);
    double sqrtT = Math.sqrt(results.length);
    return (mean - ((1.96 * stddev) / sqrtT ));
  }
  
  /** Compute confidence hi.
   * @return confidence.
   */
  public double confidenceHi() {
    double mean = StdStats.mean(results);
    double stddev = StdStats.stddev(results);
    double sqrtT = Math.sqrt(results.length);
    return (mean + ((1.96 * stddev) / sqrtT ));
  }
  
  /** main driver.
   * @param args what, the args!
   */
  public static void main(String[] args) {
    if ( args.length < 2 ) {
      throw new IllegalArgumentException();
    } else {
      int nn = Integer.parseInt(args[0]);
      int trials = Integer.parseInt(args[1]);
      
      PercolationStats stats = new PercolationStats(nn, trials);
      StdOut.printf("%-24s= ", "mean" );
      StdOut.print(stats.mean());
      StdOut.printf("\n%-24s= ", "stddev" );
      StdOut.print(stats.stddev());
      StdOut.printf("\n%-24s= ", "95% confidence interval" );
      StdOut.print(stats.confidenceLo());
      StdOut.print(", ");
      StdOut.print(stats.confidenceHi());
    }
  }
}
