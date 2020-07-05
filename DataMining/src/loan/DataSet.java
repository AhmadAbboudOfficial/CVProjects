
package loan;

/**
 *
 * @author moh
 */
public class DataSet {
    public int age;
    public String job;
    public String marital;
    public String education;
    public String housing;
    public String label;
     
    public DataSet(int age, String job, String marital, String education, String housing, String label) {
        this.age = age;
        this.job = job;
        this.marital = marital;
        this.education = education;
        this.housing = housing;
        this.label = label;
    }

    public String getName(String name){
    if (name.equals("age")){
        return String.valueOf(this.age);
    } else if (name.equals("job")){
        return  this.job;
    } else if (name.equals("marital")){
        return  this.marital;
    } else if (name.equals("education")){
        return  this.education;
    }  else if (name.equals("housing")){
        return  this.housing;
    }
        return null;
            
    }
    
    
}
