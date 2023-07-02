import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter writer;
    Server(int PORT){
        try{
            server=new ServerSocket(PORT);
            socket=server.accept();
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new PrintWriter(socket.getOutputStream());
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        new Server(3000);
    }
}
