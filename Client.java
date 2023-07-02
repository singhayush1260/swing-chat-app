
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
public class Client {
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter writer;
    Client(int PORT){
        System.out.println(PORT);
        try{
            System.out.println("Connecting to server at PORT "+PORT);
            socket=new Socket("127.0.0.1",PORT);
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new PrintWriter(socket.getOutputStream());
            System.out.println("Successfully connected.");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    Client(ServerSocket server){

    }
    public BufferedReader getBufferedReader(){
        return bufferedReader;
    }
    public Socket getSocket(){
        return socket;
    }
    public PrintWriter getWriter(){
        return writer;
    }
    public static void main(String[] args) {
        new Client(3000);
    }
}
