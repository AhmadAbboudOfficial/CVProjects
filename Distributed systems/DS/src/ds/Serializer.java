/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author chaosprince
 */
public class Serializer implements Serializable{
    
    private String filename;
    private String location;
    
    
    public Serializer(){
        this.filename = "";
        this.location = "";
    }
    
    public void setFilename(String FileNameWithoutExtention){
        String extension = ".ser";
        this.filename = FileNameWithoutExtention+extension;
    }

    public String getFilename() {
        return filename;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
    
    /**
     * 
     * @param obj 
     */
    public void serialize(Object obj){
        
        try{
            FileOutputStream fout = new FileOutputStream(new File(this.location+File.separator+this.getFilename()+".ser"));
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(obj);
            oout.close();
            fout.close();
            System.out.println("Done serializing object\nSaved in "+this.getLocation());
        }catch(IOException e){
            System.out.println(""+e.getMessage());
        }
    }
    
    public Object deserialize(){
        Object obj = null;
        
        try {
            
            FileInputStream fin = new FileInputStream(new File(this.getLocation()+"/"+this.getFilename()+".ser"));
            ObjectInputStream fout = new ObjectInputStream(fin);
            obj = (Object) fout.readObject();
            fin.close();
            fout.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println(""+ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(""+ex.getMessage());
        }
        if( !(obj == null || obj.equals("")) ){
            return obj;
        }
        return null;
    }
    
}
