/*
 * Database will be handled by this class
 * Name: DS , Tables:clients,products & purchases , username: root , pwd: root
 * using localhost as my server
 */
package ds;
import java.sql.*;



/**
 *
 * @author chaosprince
 */
public class DBHandler {
    //database connection details
    private final String USERNAME="root";
    private final String PASSWORD="root";
    private final String DB_NAME = "DS";
    private final String ServerName = "localhost";
    private final String DB_URL = "jdbc:mysql://"+ServerName+"/"+DB_NAME;
    //jdbc driver variables
    private String Query;
    private Connection connection;
    private Statement stm;
    
    public DBHandler(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            this.stm = this.connection.createStatement();
            
            //System.out.println("Databse connected successfully....");
        }catch(ClassNotFoundException e){
            System.out.println("JDBC Driver were not found");
        }catch(SQLException s){
            System.out.println("Failure:"+s.getMessage());
        }
    }
    
    public DBHandler(String DB_URL,String username,String pwd){
        //maybe later will be used 
        //after testing
    }
    
    public void setQuery(String q){
        this.Query = q;
    }
    
    /**
     * this method used to end database connection
     */
    public void endDBConnection(){
        try {
            this.connection.close();
            //System.out.println("Databse disconnected successfully....");
        } catch (SQLException ex) {
            System.out.println("connection:"+ex.getMessage());
        }
    }
    
    /**
     * this method used to execute query when ever needed
     * execute method works with all types of query needed
     * @return 
     */
    public ResultSet executeQuery(){
        ResultSet result = null;
        try {
            if(this.Query.contains("INSERT")){
                this.stm.executeUpdate(this.Query);
            }else if (this.Query.contains("DELETE") || this.Query.contains("UPDATE")){
                this.stm.execute(this.Query);
            }
            else{
                result = this.stm.executeQuery(this.Query);
            }
            //System.out.println(""+result);
        } catch (SQLException ex) {
            System.out.println("query execution faild:"+ex.getMessage());
        }
        return result;
    }
    
}
