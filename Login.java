import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener {
    JFrame frame;
    JMenuBar menubar;
    JMenu options;
    JMenu about;
    JMenuItem connect,exit;
    Login(){
      makeGUI();
    }

    private void makeGUI() {
        frame=new JFrame();
        menubar=new JMenuBar();
        options=new JMenu("Options");
        about=new JMenu("About");
        connect=new JMenuItem("Connect");
        exit=new JMenuItem("Exit");
        options.add(connect);
        options.add(exit);
        menubar.add(options);
        menubar.add(about);
        frame.setJMenuBar(menubar);
        frame.setTitle("Chat App v1");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Login();
    }
}
