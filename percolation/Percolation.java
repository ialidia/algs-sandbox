import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * This class implements the percolation experiment.
 * Executed separately it starts the tests.
 * Known bugs: after the system percolates it backwashes: the isFull() method
 * returns true for sites that are connected to BOTTOM, but are not connected
 * to the TOP. Can be solved with a second UF structure or by making openSites
 * array take care of the FULL state as well (by turning openSites into
 * a byte array).
 * @author Lidia Khmylko
 * created on 2017/05/08
 */
public class Percolation{
    // size of grid
    private int n;

    // index of the top component
    private int TOP = 0;

    // index of the bottom component
    private int BOTTOM;

    // saving open sites
    private boolean[] openSites;

    // connected sites with size n+1
    private WeightedQuickUnionUF uf;

    /**
     * Constructor to create an n-by-n grid, with all sites blocked.
     * @param n the size of the grid
     */
    public Percolation(int n)  {
        if (n < 1){
            throw new java.lang.IllegalArgumentException(
                    "n should be greater than 0");
        }
        this.n = n;
        this.BOTTOM = n*n + 1;

        openSites = new boolean[BOTTOM + 1];
        for (int i = 0; i < openSites.length; i++){
            openSites[i] = false;
        }
        openSites[TOP] = true; // TOP is always open
        openSites[BOTTOM] = true;

        this.uf = new WeightedQuickUnionUF(BOTTOM+1);
    }

    /** Open site (row, col) if it is not open already.
     *
     * @param row grid row
     * @param col grid column
     */
    public void open(int row, int col){
        if (! isValidIndex(row, col)){
            throw new java.lang.IndexOutOfBoundsException(
                    "row should be between 1 and " + n);
        }

        int currentSite = xyTo1D(row, col);
        openSites[currentSite] = true;

        int[] neighbors = getNeighbors(row, col);
        for (int i = 0; i < neighbors.length; i++){
            int currentNeighbor = neighbors[i];
            if (openSites[currentNeighbor]){
                uf.union(currentSite, currentNeighbor);
            }
        }

    }

    /** Is site (row, col) open?
     *
     * @param row grid row
     * @param col grid column
     * @return true if the site is open
     */
    public boolean isOpen(int row, int col){
        if (! isValidIndex(row, col)){
            throw new java.lang.IndexOutOfBoundsException(
                    "row should be between 1 and " + n);
        }

        return openSites[xyTo1D(row, col)];
    }

    /** Is site (row, col) full?
     *
     * @param row grid row
     * @param col grid column
     * @return true if the site is full and false otherwise
     */
    public boolean isFull(int row, int col){
        if (! isValidIndex(row, col)){
            throw new java.lang.IndexOutOfBoundsException(
                    "row should be between 1 and " + n);
        }

        boolean result = false;

        int currentSite = xyTo1D(row, col);
        return uf.connected(currentSite, TOP);
    }

    /** Number of open sites
     *
     * @return the number of open sites
     */
    public int numberOfOpenSites(){
        int count = 0;

        for (int i = 1; i < (openSites.length - 1); i++){
            if (openSites[i])
                count++;
        }

        return count;
    }

    /** Does the system percolate?
     *
     * @return true if the system percolates and false otherwise
     */
    public boolean percolates(){
        return uf.connected(TOP, BOTTOM);
    }


    ///////////////////////////////////////////////////////
    // PRIVATE FUNCTIONS
    ///////////////////////////////////////////////////////
    /*
     * Translate the (x,y)-coordinates to 1D coordinates
     */
    private  int xyTo1D(int row, int col){
        if (! isValidIndex(row, col)){
            throw new java.lang.IndexOutOfBoundsException(
                    "row should be between 1 and " + n);
        }
        return n*(row - 1 ) + col;
    }

    /*
     * Validates indices. Return true if the indices are in the correct range
     * and false otherwise.
     */
    private boolean isValidIndex(int row, int col){
        if ( row < 1 || row > n ){
            return false;
        }
        if ( col < 1 || col > n ) {
            return false;
        }
        return true;
    }

    /*
     * Get the neighbors of a cell in a grid.
     */
    private int[] getNeighbors(int row, int col){
        if (! isValidIndex(row, col))
            throw new java.lang.IndexOutOfBoundsException(
                    "row should be between 1 and " + n);
        int neighborCount = 4;
        int siteIndex = xyTo1D(row, col);
        int above = siteIndex - n;
        if (above < 1) above = TOP;
        int below = siteIndex + n;
        if (below > (n*n)) below = BOTTOM;

        int left = siteIndex - 1;
        if (col == 1) {
            left = -1;
            neighborCount--;
        }
        int right = siteIndex + 1;
        if (col == n){
            right = -1;
            neighborCount --;
        }
        int [] neighbors = new int[neighborCount];
        neighbors[0] = above;
        neighbors[1] = below;
        // put side neighbor into the correct slot
        if (left > 0){
            neighbors[2] = left;
        } else if ((left < 0) && (right > 0)){
            neighbors[2] = right;
        }
        if ((left > 0) && (right > 0)){
            neighbors[3] = right;
        }
        return neighbors;
    }

    /** Test client (optional)
     *
     * @param args
     */
    public static void main(String[] args){
        System.out.println("Testing...");

        Percolation p = new Percolation(5);
        p.open(4,4);
        p.isOpen(4,4);
        p.isFull(4,5);

        /////////////////////
        // Testing xyTo1D()
        System.out.println();
        Percolation pXY = new Percolation( 3);
        System.out.println("? 1 = " + pXY.xyTo1D(1,1));
        System.out.println("? 2 = " + pXY.xyTo1D(1,2));
        System.out.println("? 3 = " + pXY.xyTo1D(1,3));
        System.out.println("? 4 = " + pXY.xyTo1D(2,1));
        System.out.println("? 5 = " + pXY.xyTo1D(2,2));
        System.out.println("? 6 = " + pXY.xyTo1D(2,3));
        System.out.println("? 7 = " + pXY.xyTo1D(3,1));
        System.out.println("? 8 = " + pXY.xyTo1D(3,2));
        System.out.println("? 9 = " + pXY.xyTo1D(3,3));
        //System.out.println("(0,0) = " + pXY.xyTo1D(0,0));
        //System.out.println("(4,4) = " + pXY.xyTo1D(4,4));

        System.out.println(pXY.BOTTOM);
        System.out.println();
        int [] neighbors = pXY.getNeighbors(3,1);
        System.out.println("Neighbors of (3,2):");
        for (int i = 0; i < neighbors.length; i++){
            System.out.println("   " + neighbors[i]);
        }

        ////////////////////////
        // Testing open
        System.out.println();
        Percolation op = new Percolation(3);

        op.open(1,1);
        System.out.println("connected (1,1) and (1,2) ? " +
                op.uf.connected(op.xyTo1D(1,1), op.xyTo1D(1,2)));
        System.out.println("percolates ? " + op.percolates());
        op.open(1,2);
        System.out.println("connected (1,1) and (1,2) ? " +
                op.uf.connected(op.xyTo1D(1,1), op.xyTo1D(1,2)));
        System.out.println("percolates ? " + op.percolates());
        op.open(2,2);
        System.out.println("percolates ? " + op.percolates());
        op.open(3,2);
        System.out.println("percolates ? " + op.percolates());

    }

}