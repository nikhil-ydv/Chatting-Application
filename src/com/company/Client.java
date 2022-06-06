package com.company;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Client extends JFrame implements ActionListener {


    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;


    Client(){
        p1= new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0,106,255));
        p1.setBounds(0,0,450,70);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/1.png"));
        Image img1 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(img1); //convert to icon again to pass it in label
        JLabel l1 = new JLabel(i2);
        l1.setBounds(5,17,30,30);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                System.exit(0);
            }
        });

        //----------------------------------------------------
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/4.png"));
        Image img2 = i3.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i4 = new ImageIcon(img2);
        JLabel l2 = new JLabel(i4);
        l2.setBounds(40,5,60,60);
        p1.add(l2);

        //----------------------------------------------------
        //We write on the frame with the help of Labels
        JLabel l3 = new JLabel("Pratikshit");
        l3.setFont(new Font("San_Serif", Font.BOLD, 18));
        // setForeGround Method to change color, etc. of what you write in label
        l3.setForeground(Color.white);
        l3.setBounds(110,15,100,18);
        p1.add(l3);

        //----------------------------------------------------
        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("San_Serif", Font.PLAIN, 14));
        l4.setForeground(Color.white);
        l4.setBounds(110,35,100,14);
        p1.add(l4);

        //----------------------------------------------------
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/video.png"));
        Image img3 = i5.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(img3);
        JLabel l5 = new JLabel(i6);
        l5.setBounds(290,20,30,30);
        p1.add(l5);

        //----------------------------------------------------
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/phone.png"));
        Image img4 = i7.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i8 = new ImageIcon(img4);
        JLabel l6 = new JLabel(i8);
        l6.setBounds(350,20,35,30);
        p1.add(l6);

        //----------------------------------------------------
        ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/3icon.png"));
        Image img5 = i9.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
        ImageIcon i10 = new ImageIcon(img5);
        JLabel l7 = new JLabel(i10);
        l7.setBounds(410,20,13,25);
        p1.add(l7);

        //---------------------------TextArea TextField Button -----------------
        a1 = new JTextArea();
        a1.setBounds(5,73,440,480);
        a1.setBackground(Color.white);
        a1.setFont(new Font("San_Serif",Font.PLAIN,16));
        //make text area non-editable component
        a1.setEditable(false);
        //so that text does not go out of text area
        a1.setLineWrap(true);
        a1.setWrapStyleWord(false);
        add(a1);


        t1 = new JTextField();
        t1.setBounds(5,560,350,30);
        t1.setFont(new Font("San_Serif", Font.PLAIN,16));
        add(t1);


        b1 = new JButton("Send");
        b1.setBounds(360,560,85,30);
        b1.setBackground(new Color(0,106,255));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("San_Serif",Font.PLAIN,16));

        /*this is used to refer to action performed method and do what's asked in that method*/
        b1.addActionListener(this);
        add(b1);


        //-------------------Start here and then add panel, imageIcon, labels etc.-------------------------
        //getContentPane picks whole form
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(450, 600);
        //setLocation(x coordinate, y coordinate); to set the place where form will show up.
        setLocation(950, 150);
        //by default setVisible for frames is false, so it can't be seen without setting setVisible true
        setUndecorated(true); //to remove top bar of minimize, maximize, close
             /* If we setVisible(true) before any component function like setUndecorated()
               then the changes of the function won't show
             */



        setVisible(true);


    }

    /* Since server class is not abstract and it is implementing ActionListener interface, It has to give definition
       for actionPerformed method present in the interface else error occurs*/
    public void actionPerformed(ActionEvent ae)
    {
        try {
            //get text from text field at the bottom
            String text = t1.getText();
            /*get what's written in text area and then append new line and the text of text field*/
            a1.setText(a1.getText() + "\n\t\t\t" + text);
            dout.writeUTF(text);
            t1.setText("");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
            Statement st = con.createStatement();
            int result = st.executeUpdate("Insert into chathistory values(concat('ClientMsg: ','"+text+"'))");


        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args)
    {
        new Client().setVisible(true);
        String msgInput ="";
        try{
            s = new Socket("127.0.0.1",6003);
                din = new DataInputStream(s.getInputStream());
                // dout will have data that we are sending
                dout = new DataOutputStream(s.getOutputStream());
                //readUTF() function to read inputStream of DataInputStream
                while(true) {
                    msgInput = din.readUTF();
                    a1.setText(a1.getText() + "\n" + msgInput);

                }


        }catch (Exception e)
        {
        }

    }

}
