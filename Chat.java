import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;

public class Chat extends JFrame implements KeyListener, ActionListener {
    JFrame frame;
    JPanel chat_area;
    JTextField message_input;
    JButton send_button;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter writer;

    JMenuBar menubar;
    JMenu options;
    JMenuItem exit;
    String username;
    Chat(boolean isServer,String username, int PORT) {
        this.username=username;
        makeGUI();
        addEventListener();
        if(isServer){
            startServer(PORT);
        }
        else{
            startClient(PORT);
            startReading();
        }
    }
    private void startClient(int PORT) {
        Client client=new Client(PORT);
        bufferedReader=client.getBufferedReader();
        socket=client.getSocket();
        writer=client.getWriter();
    }
    private void startServer(int PORT){
        Server server=new Server(PORT);
        writer=server.getWriter();
        bufferedReader=server.getBufferedReader();
        socket=server.getSocket();
        startReading();
    }
    private void makeGUI() {
        frame = new JFrame();
        frame.setTitle("Chat App v1- Chat Area");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(400, 520);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adding menubar
        menubar=new JMenuBar();
        options=new JMenu("Options");
        exit=new JMenuItem("Exit");
        options.add(exit);
        menubar.add(options);
        frame.setJMenuBar(menubar);

        // Adding message area
        chat_area = new JPanel();
        chat_area.setLayout(new BoxLayout(chat_area,BoxLayout.Y_AXIS));
        chat_area.setPreferredSize(new Dimension(400, 400));
        JScrollPane jScrollPane=new JScrollPane(chat_area);
        frame.add(jScrollPane);

        // Adding message input box
        message_input = new JTextField();
        message_input.setPreferredSize(new Dimension(300, 80));
        message_input.setFont(new Font("Roboto", Font.PLAIN, 22));
        message_input.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
        frame.add(message_input, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void addEventListener() {
        message_input.addKeyListener(this);
        exit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
      addMessage("Exit Message","Session has been terminated.");
        System.exit(0);
    }
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            if(writer==null){
                JOptionPane.showMessageDialog(null ,"Waiting for the client to send the request.");
            }
            else{
                String message = message_input.getText();
                System.out.println("writer"+writer);
                writer.println(message);
                writer.flush();
                addMessage(username,message);
                message_input.setText("");
            }
        }
    }

    private void startReading()
    {
        Runnable r1=()->{
            System.out.println("reading started...");
            try
            {
                while(true)
                {
                    String message=bufferedReader.readLine();
                    if(message.equals("exit") )
                    {
                        System.out.println("Client has stopped.");
                        JOptionPane.showMessageDialog(this,"Server terminated the chat.");
                        socket.close();
                        break;
                    }
                    System.out.println(username+":"+message);
                    //message_area.append("Server: "+msg+"\n");
                    addMessage(username,message);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        };
        new Thread(r1).start();
    }
    private void addMessage(String sender, String message) {
        JPanel message_panel = new JPanel();
        message_panel.setLayout(new BoxLayout(message_panel,BoxLayout.Y_AXIS));
        JLabel senderLabel = new JLabel(sender);
        //sent?senderLabel=new JLabel("You"):new JLabel("Server");
        JTextArea messageArea = new JTextArea(message);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        message_panel.add(senderLabel);
        message_panel.add(messageArea);
        chat_area.add(message_panel);
        frame.revalidate(); // Revalidate the frame to reflect the changes
    }

    public static void main(String[] args) {
       new Chat(true,"", 3000);
    }

}
