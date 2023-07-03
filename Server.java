import java.net.*;
import java.io.*;
class Server
{
    ServerSocket server;
    Socket socket; //client
    BufferedReader buffered_reader;
    PrintWriter writer;
    int PORT=3000;
    Server(int PORT)
    {
        try
        {
            server=new ServerSocket(PORT);
            System.out.println("Server is ready to accept request at PORT "+PORT);
            socket=server.accept();
            buffered_reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new PrintWriter(socket.getOutputStream());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Socket getSocket(){
        return socket;
    }
    public BufferedReader getBufferedReader(){
        return buffered_reader;
    }
    public PrintWriter getWriter(){
        return writer;
    }
    public static void main(String args[])
    {
        new Server(3000);
    }
}