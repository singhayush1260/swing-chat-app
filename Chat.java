import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;

public class Chat extends JFrame implements KeyListener {
    JFrame frame;
    JPanel chat_area;
    JTextField message_input;
    JButton send_button;
    int PORT;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter writer;
    Chat(String username, int PORT) {
        this.PORT=PORT;
        makeGUI();
        addEventListener();
        establishConnection();
        //startWriting();
        startReading();
    }

    private void establishConnection() {
        Client client=new Client(PORT);
        bufferedReader=client.getBufferedReader();
        socket=client.getSocket();
        writer=client.getWriter();
    }
    private void makeGUI() {
        frame = new JFrame();
        frame.setTitle("Chat App v1- Chat Area");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(400, 520);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding message area
        chat_area = new JPanel();
        chat_area.setLayout(new BoxLayout(chat_area,BoxLayout.Y_AXIS));
        chat_area.setPreferredSize(new Dimension(400, 400));
        JScrollPane jScrollPane=new JScrollPane(chat_area);
        frame.add(jScrollPane,BorderLayout.CENTER);

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
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            String message = message_input.getText();
            writer.println(message);
            writer.flush();
            addMessage(true,message);
            message_input.setText("");
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
                    System.out.println("Server: "+message);
                    //message_area.append("Server: "+msg+"\n");
                    addMessage(false,message);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        };
        new Thread(r1).start();
    }
    private void addMessage(boolean sent, String message) {
        JPanel message_panel = new JPanel();
        message_panel.setLayout(new BorderLayout());
        //JLabel senderLabel = new JLabel(sender);
        JTextArea messageArea = new JTextArea(message);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);

        if (sent) {
            message_panel.setBackground(Color.CYAN);
            //messagePanel.add(senderLabel, BorderLayout.PAGE_START);
            message_panel.add(messageArea, BorderLayout.CENTER);
            message_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        } else {
            message_panel.setBackground(Color.YELLOW);
            //messagePanel.add(senderLabel, BorderLayout.PAGE_START);
            message_panel.add(messageArea, BorderLayout.CENTER);
            message_panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        }

        chat_area.add(message_panel);
        frame.revalidate(); // Revalidate the frame to reflect the changes
    }

    public static void main(String[] args) {
        new Chat("", 3000);
    }
}
