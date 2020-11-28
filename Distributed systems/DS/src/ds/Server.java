package ds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//import java.rmi.Naming;
import javax.swing.JOptionPane;

/**
 *
 * @author chaosprince
 */
public class Server {

    private ServerSocket server_socket;
    private Socket client_socket;
    private InputStream input;
    private OutputStream output;
    private final int PORT_NUMBER = 9000;

    public Server() {
        
    }

    private void start_server() {
        while (true) {
            try {
                server_socket = new ServerSocket(this.PORT_NUMBER);
                //first thing is to accpet client
                //System.out.println("Server is w8ing...");
                client_socket = server_socket.accept();
                //System.out.println("Client was accpeted.");
                input = client_socket.getInputStream();
                output = client_socket.getOutputStream();
                /**
                 * handling of server to client
                 */
                int status = input.read();
                if (status == 1) {
                    //try {
                    //    ServerC obj = new ServerC();
                        //System.setProperty("java.rmi.server.hostname","192.168.1.6");
                    //    Naming.rebind("login", obj);
                        output.write(1);//this means go ahead
                    //} catch (Exception ex) {
                    //    System.err.println(ex.getMessage());
                    //    JOptionPane.showMessageDialog(null, "faild: " + ex.getMessage() + "S");
                    //}
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                break;
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start_server();
    }

}
