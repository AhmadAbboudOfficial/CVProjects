package ds;

/**
 *
 * @author chaosprince
 */
public class DS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //building client server
        Client client = new Client();
        //tested and working
        Thread client_ = new Thread(client);

        client_.start();

    }

}
