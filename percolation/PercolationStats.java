import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * PercolationStats starts the percolation
 * experiment and counts the stats.
 * @author Lidia Khmylko
 * created on 2017/05/08
 */
public class PercolationStats{
    // size of the grid
    private int n;

    // number of trials
    private int trials;

    private double[] thresholds;

    /** Perform trials independent experiments on an n-by-n grid
     *
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0){
            throw new java.lang.IllegalArgumentException(
                    "n should be positive");
        }
        if (trials <= 0){
            throw new java.lang.IllegalArgumentException(
                    "Number of trials should be positive");
        }

        this.n = n;
        this.trials = trials;

        this.thresholds = new double[trials];

        for (int i = 0; i < trials; i++){
            // experiment
            // System.out.println("EXPERIMENT " + i);

            Percolation p = new Percolation(n);

            boolean percolates = false;

            while(! percolates) {

                boolean open = true;
                int row = 0;
                int col = 0;

                while (open) {
                    row = StdRandom.uniform(1, n+1);
                    col = StdRandom.uniform(1, n+1);

                    open = p.isOpen(row, col);
                }

                p.open(row, col);
                percolates = p.percolates();
            }
            // It percolated, add the result to
            thresholds[i] = 1.0 * p.numberOfOpenSites() / (n*n);
        }
    }

    /** Sample mean of percolation threshold
     *
     * @return mean value for the trials thresholds
     */
    public double mean(){
        return StdStats.mean(thresholds);
    }

    /** Sample standard deviation of percolation threshold
     *
     * @return standard deviation for the trials thresholds
     */
    public double stddev(){
        if (trials == 1){
            return Double.NaN;
        } else {
            return StdStats.stddev(thresholds);
        }
    }

    /** Low  endpoint of 95% confidence interval
     *
     * @return the low endpoint of 95% confindence interval
     */
    public double confidenceLo(){
        return mean() - 1.96*stddev() / (Math.sqrt(trials));
    }

    /** High endpoint of 95% confidence interval
     *
     * @return the high endpoint of 95% confidence interval
     */
    public double confidenceHi(){
        return mean() + 1.96*stddev() / (Math.sqrt(trials));
    }

    /** Test client (described below)
     *
     * @param args size of the grid and the number of trials
     */
    public static void main(String[] args){
        int n = Integer.parseInt( args[0] );
        int t = Integer.parseInt( args[1] );

        //System.out.println("n= " + n);
        //System.out.println("trials= " + trials);

        PercolationStats ps = new PercolationStats(n, t);



        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = [" +
                ps.confidenceLo() + ", " + ps.confidenceHi() + "]");

        //StdRandom stdRandom = new StdRandom();
    }

}