import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Connect extends JFrame implements ActionListener{
    JFrame frame;
    JMenuBar menubar;
    JMenu menu;
    JMenuItem back,exit;
    JLabel username_label,port_label;
    JTextField username_tf,port_tf;
    JButton client_connect_button,server_connect_button;
    Connect(){
        makeGUI();
        addEventListener();
    }

    private void makeGUI() {
        frame=new JFrame();

        // creating menu
        menubar=new JMenuBar();
        menu=new JMenu("Options");
        //back=new JMenuItem("Back");
        exit=new JMenuItem("Exit");
        //menu.add(back);
        menu.add(exit);
        menubar.add(menu);
        frame.setJMenuBar(menubar);

        //adding icon
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon.png"));
        Image i2=i1.getImage().getScaledInstance(180,120,Image.SCALE_DEFAULT );
        JLabel label=new JLabel(new ImageIcon(i2));
        label.setBounds(100,10,180,130);
        frame.add(label);

         // adding input fields and their respective labels
          username_label=new JLabel("Username");
          username_label.setFont(new Font("Raleway",Font.PLAIN,18));
          username_label.setBounds(95,200,100,30);
          username_tf=new JTextField();
          username_tf.setBounds(94,235,200,25);
          port_label=new JLabel("PORT");
          port_label.setFont(new Font("Raleway",Font.PLAIN,18));
          port_label.setBounds(95,270,100,30);
          port_tf=new JTextField();
          port_tf.setBounds(94,305,200,25);
          frame.add(username_label);
          frame.add(username_tf);
          frame.add(port_label);
          frame.add(port_tf);

          // adding buttons
        client_connect_button=new JButton("Connect As Client");
        client_connect_button.setBounds(94,350,200,30);
        client_connect_button.setBackground(Color.black);
        client_connect_button.setForeground(Color.white);
        frame.add(client_connect_button);

        server_connect_button=new JButton("Connect As Server");
        server_connect_button.setBounds(94,390,200,30);
        server_connect_button.setBackground(Color.black);
        server_connect_button.setForeground(Color.white);
        frame.add(server_connect_button);

        frame.setTitle("Chat App v1- Connect");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(400, 520);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void addEventListener() {
        //back.addActionListener(this);
        exit.addActionListener(this);
        client_connect_button.addActionListener(this);
        server_connect_button.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
//       if(event.getSource()==back){
//           frame.setVisible(false);
//           new Main().setVisible(true);
//       }
       if(event.getSource()==exit){
           System.exit(0);
       }
       else{
           String username=username_tf.getText();
           String port_number=port_tf.getText();
           int port=0;
           String warnings="";
           try{
               port=Integer.parseInt(port_number);
               if(port<1||port>65535){
                   warnings="Enter a valid PORT number.\n";
               }
               if(username.equals("")){
                   warnings+="Username cannot be empty.";
               }
           }catch(Exception e){
               warnings+="Enter a valid PORT number.\n";
           }
           finally {
               if(!warnings.equals("")){
                   JOptionPane.showMessageDialog(null,warnings);
                   //System.exit(0);
               }
               else{
                   frame.setVisible(false);
                   if(event.getSource()==server_connect_button){
                       JOptionPane.showMessageDialog(null,"Waiting for the client to connect.");
                       new Chat(true,username,port).setVisible(true);
                   }
                   else{
                       new Chat(false,username,port).setVisible(true);
                   }
               }
           }
       }
    }
    public static void main(String[] args) {
        new Connect();
    }
}
