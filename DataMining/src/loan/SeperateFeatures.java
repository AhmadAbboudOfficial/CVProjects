
package loan;

/**
 *
 * @author moh
 */
public class SeperateFeatures { //seperate feature with its label
    public String feature ; //column(feature)'s value i.e job.
    public String label ; //and its label (yes or no).
    
    public SeperateFeatures(String feature, String label) {
        this.feature = feature;
        this.label = label;
    }
    
}
