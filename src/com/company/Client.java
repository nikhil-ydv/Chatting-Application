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

/*First we complete java frame and then we move on to main method and action performed method to complete connection using
 *socket programming
 * Frame Coding is done in Construcor so that when we run the class ,frame pops up as it is called at the first line in main as shown*/
public class Client extends JFrame implements ActionListener {

    //works as a container, just like <div> in HTML
    JPanel p1;
    JTextField t1;
    JButton b1;
    //set Text area static because in main it throws error while trying to print msgInput string
    static JTextArea a1;
    /*Socket Programming is part of java networking. ServerSocket and Socket, both classes are present in java.net package*/
    //static ServerSocket skt;
    static Socket s;
    /*Data input and output stream object to track what msg is coming and going, this will also be static
    and It is present in java.io package*/
    static DataInputStream din;
    static DataOutputStream dout;


    Client(){
        //----------------------------------------------------
        //java.awt.Container; --Panel is a container, setLayout() is a function in Container Class
        p1= new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0,106,255));
        //for setting component layout(like position, width, height) setBounds(int x, int y, int width, int height);
        p1.setBounds(0,0,450,70);
        // If we use only add(), it will add component on the frame only because form is calling the add function
        add(p1);

        //----------------------------------------------------
            /* ClassLoader Class in java.lang having static method getSystemResource which takes
            address as string input and thus we can fetch images from that location */
        //ImageIcon class extends Object implements javax.swing.Icon, java.io.Serializable, javax.accessibility.Accessible
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/1.png"));
        // Abstract class Image of awt package contains getScaledInstance(width, height, hint);
        Image img1 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(img1); //convert to icon again to pass it in label
        // java.awt.Component;
            /*A component is an object having a graphical representation that can be displayed on the screen and that
            can interact with the user. Examples of components are the labels, buttons, scrollbars etc of a typical GUI.
             */
        JLabel l1 = new JLabel(i2);
        l1.setBounds(5,17,30,30);
            /* add(Component frame) will add the component on the frame but to add it over the panel, we use panel
            object (It means that now instead of form, panel will call add function)
            */
        p1.add(l1);
            /*Add MouseEvent to label1 to close frame when clicked..We have different mouse events methods like
              mouseClicked, mouseMoved etc. in MouseAdapter class present in java.awt.event.MouseAdapter
            *public abstract class MouseAdapter extends Object implements some interfaces
              An abstract adapter class for receiving mouse events.The methods in this class are empty,
              for e.g. mouseClicked(MouseEvent e) which we can override to handle clicks on a component like label, etc.
              This class exists as convenience for creating listener objects.
            *Implement ActionListener interface (present in java.awt.event.*;) to handle mouse actions as below
            */
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
            //socket class object that will accept data

                //Data is coming and going with the help of socket that's why we use s.getInputStream and OutputStream();
                // din will have data that is coming
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
