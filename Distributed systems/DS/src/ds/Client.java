
package ds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.net.MalformedURLException;
import java.net.Socket;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
import javax.swing.JOptionPane;


/**
 *
 * @author chaosprince
 */
public class Client implements Runnable{
    
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private final int PORT_NUMBER = 9000;
    
    
    private void start_client(){
        
        try {
            socket = new Socket("localhost",PORT_NUMBER);
            input = socket.getInputStream();
            output = socket.getOutputStream();
            /**
             * handling of client and server here later on 
             */
            
            output.write(1);//status 1 means to request login
            int response = input.read();
            if(response == 1){//permission to login
                //try{
                //ServerI obj = (ServerI) Naming.lookup("login");
                LoginSignupForm l = new LoginSignupForm();
                l.setVisible(true);
                //obj.login(l.getUsername());
                //}catch(MalformedURLException | NotBoundException | RemoteException e){
                //    JOptionPane.showMessageDialog(null, e.getMessage());
                //}
            }
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void run() {
        this.start_client();
    }
}
