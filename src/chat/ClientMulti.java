
package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ClientMulti extends Application {
    TextArea txt = new TextArea();
    Label lb = new Label("Your Message");
    Button SendButton = new Button("Send");
    TextField tf = new TextField();

    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    Thread th ;
    public void init(){
        try {
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());

        } catch (IOException e) {
            System.out.print("error connecting server socket! ");
        }

        th = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    String replyMsg;
                    try {
                        replyMsg = dis.readLine();
                        txt.appendText("\n"+replyMsg);
                    } catch (IOException e) {
                        System.out.println("client stopped");
                    }
                }

            }
        });
        th.setDaemon(true);
        th.start();
    }
    @Override
    public void stop() throws Exception{
       super.stop();
       th.stop();
       dis.close();
       ps.close();
       mySocket.close();
    }
    @Override
    public void start(Stage Stage) {

        SendButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ps.println(tf.getText());
                tf.clear();

            }
        });
        SendButton.setDefaultButton(true);
        FlowPane f = new FlowPane();
        BorderPane root = new BorderPane();

        txt.setEditable(false);
        f.getChildren().addAll(lb,tf,SendButton);
        root.setCenter(txt);
        root.setBottom(f);

        Scene scene = new Scene(root, 270, 250);

        Stage.setTitle("Chat..");
        Stage.setScene(scene);
        Stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}