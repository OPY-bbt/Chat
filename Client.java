import java.io.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextArea.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class Client {
    JTextField textfield;
    Socket socket;
    PrintWriter writer;

    public static void main(String[] args) {
        Client client = new Client();
        client.go();
    }

    private void go() {
        ui();
        setupNetworking();
    }

    private void setupNetworking() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(socket.getOutputStream());

            // sendText("client");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void ui() {
        JFrame frame = new JFrame("Chat");

        JPanel mainPanel = new JPanel();

        textfield = new JTextField(20);

        JButton button = new JButton("send");
        button.addActionListener(new SendBtnListener());
        
        mainPanel.add(textfield);
        mainPanel.add(button);

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    private class SendBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String text = textfield.getText();
            sendText(text);
        }
    }

    private void sendText(String text) {
        try {
            writer.println(text);
            writer.flush();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}