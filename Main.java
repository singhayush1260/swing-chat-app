import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    JFrame frame;
    JMenuBar menubar;
    JMenu options;
    JMenuItem connect,exit,about;
    Main() {
        System.out.println("main");
        makeGUI();
        addEventListener();
    }
    private void makeGUI() {
        frame = new JFrame();
        menubar = new JMenuBar();
        options = new JMenu("Options");
        about = new JMenuItem("About"); // Initialize the 'about' JMenu
        connect = new JMenuItem("Connect");
        exit = new JMenuItem("Exit");
        options.add(connect);
        options.add(about);
        options.add(exit);
        menubar.add(options);
        frame.setJMenuBar(menubar);
       // exit.addActionListener(this);
        frame.setTitle("Chat App v1");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(400, 520);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void addEventListener() {
        about.addActionListener(this);
        connect.addActionListener(this);
        exit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == connect) {
            frame.setVisible(false);
            new Connect().setVisible(true);
        }
        else if(event.getSource()==about){
            String message="Chat Appv1 Developed By: Ayush Singh";
            JOptionPane.showMessageDialog(null,message);
        }
        else{
            System.out.println("Exit");
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}
