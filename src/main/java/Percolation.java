

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
  private int nn;
  private int top;
  private int bot;
  private boolean[][] openTab;
  private WeightedQuickUnionUF fullTab;
  
  /** class ctor.
   * @param nn size.
   */
  public Percolation( int nn ) {
    
    if (nn <= 0) {
      throw new IllegalArgumentException();
    } else {
      
      int nxn = nn * nn;
      this.nn = nn;
      this.openTab = new boolean[nn][nn];
      this.fullTab = new WeightedQuickUnionUF(nxn + 2); //two for virtual nodes.
      this.top = nxn;
      this.bot = nxn + 1;
    }
  }
  
  private int rc2idx( int rr, int cc ) {
    return (nn * rr) + cc;
  }
  
  /** open.
   * @param ii one based row.
   * @param jj one based col.
   */
  public void open( int ii, int jj ) {
    int rr = ii - 1;
    int cc = jj - 1;
    
    if ( rr < 0 || nn < rr || cc < 0 || nn < cc) {
      throw new IndexOutOfBoundsException();
    } else if ( ! openTab[rr][cc] ) {
      
      openTab[rr][cc] = true;
      
      //check above
      if ( rr == 0 ) {
        fullTab.union(top, rc2idx(rr,cc));
      } else if ( openTab[rr - 1][cc]) {
        fullTab.union(rc2idx(rr,cc), rc2idx(rr - 1,cc));
      }
      
      //check below      
      if ( rr == (nn - 1)) {
        fullTab.union(bot, (nn * rr) + cc);
      } else if ( openTab[rr + 1][cc] ) {
        fullTab.union(rc2idx(rr,cc), rc2idx(rr + 1,cc));
      }
      
      //check left
      if ( cc != 0 && openTab[rr][cc - 1]) {
        fullTab.union(rc2idx(rr,cc), rc2idx(rr,cc - 1));
      }
      //check right
      if ( cc != (nn - 1)) {
        fullTab.union(rc2idx(rr,cc), rc2idx(rr,cc + 1));
      }      
    }
    
    
  }
  
  /** Check if location is open.
   * @param ii location.
   * @param jj location.
   * @return true or false.
   */
  public boolean isOpen( int ii, int jj ) {
    int rr = ii - 1;
    int cc = jj - 1;
    
    if ( rr < 0 || nn <= rr || cc < 0 || nn <= cc) {
      throw new IndexOutOfBoundsException();
    } else {
      return openTab[rr][cc];
    }
  }
  
  /** Check if location is open.
   * @param ii location.
   * @param jj location.
   * @return true or false.
   */
  public boolean isFull( int ii, int jj ) {
    int rr = ii - 1;
    int cc = jj - 1;
    
    if ( rr < 0 || nn <= rr || cc < 0 || nn <= cc) {
      throw new IndexOutOfBoundsException();
    } else {
      return isOpen(ii, jj) && fullTab.connected(top, rc2idx(rr,cc));
//      return isOpen(ii, jj) && ( fullTab.connected(top, rc2idx(rr,cc)) || fullTab.connected(bot, rc2idx(rr,cc)));
    }
  }
  
  public boolean percolates() {
    return fullTab.connected(top, bot);
    
  }

}
