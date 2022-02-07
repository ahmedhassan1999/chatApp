
package chat;

import chatapp.ChatApp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMulti {
ServerSocket external;

    public ServerMulti() throws IOException
    {

        external = new ServerSocket(5005);
            while(true)
            {
                Socket internal = external.accept();
                new ChatHandler(internal);
            }

    }
 public static void main(String[] args) throws IOException {
        ServerMulti s =  new ServerMulti();
    }
    
}
