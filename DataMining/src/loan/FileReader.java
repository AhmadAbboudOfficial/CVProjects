
package loan;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moh
 */
public class FileReader {
    
    private String fileName=""; //the file directory to read.
 
    public  double  counter =1 ; // counter for counting records
    
    public FileReader (String fileName){
        this.fileName = fileName;
    }
    public  List<DataSet> readCsvFile(){
        
        String COMMA_DELIMITER = ","; //Delimiters used in the CSV file
        //int count = 0 ;
        List<DataSet> data = new ArrayList<DataSet>();
        BufferedReader br = null;
        try
        {
            //Reading the csv file
            br = new BufferedReader(new java.io.FileReader(this.fileName));
            
       
            
            String line = "";
            //Read to skip the header
           br.readLine();
            //Reading from the second line
                   
            while ((line = br.readLine()) != null)
            {
                String[] dataSet = line.split(COMMA_DELIMITER);
                
                if(dataSet.length > 0 )
                {
                    //Save the  dataSet  in  object
                    DataSet emp = new DataSet(Integer.parseInt(dataSet[0]),
                            dataSet[1],dataSet[2],
                            dataSet[3],dataSet[4],dataSet[5]);
                    data.add(emp);
                }
                counter ++ ;
            }
            
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        return data;
    }
    
}
