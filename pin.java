
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class pin extends JFrame implements ActionListener{
    JComboBox c1,c2,c3; JTextField t1, t2; String post,spin; int pin; JButton b;
    Font font=new Font ("Candara",Font.BOLD,16);
    Font f=new Font("Segue UI",Font.CENTER_BASELINE, 16);
    pin(){
        setLayout(null);
        setVisible(true);
        setTitle("Pincodes by Abhi");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300,90);
        setIconImage(null);
        setLocationRelativeTo(getComponentAt(100,200));
        setAlwaysOnTop(true);

        t1=new JTextField();
        t1.setBounds(0,0,270,30);
        t1.setFont(f);
        add(t1);

        b=new JButton("lll");
        b.setBounds(270,0,30,30);
        b.addActionListener(this);
        add(b);

        c1=new JComboBox();
        c1.setBounds(0,30,295,30);
        c1.setBorder(null);
        add(c1);

        c1.addItem("Select from Search Result");

    }


    public void actionPerformed(ActionEvent ke){
        if(ke.getSource()==b){
            post=(String)t1.getText();
            String temp=post+"%";
            //String opt="";
            //c1.removeAllItems();


            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pin","root","pin");
                Statement stmt=con.createStatement();
                JOptionPane.showMessageDialog(this,"hi-");
                String s="select * from pincode where pin like '"+temp+"' OR post like '"+temp+" ' OR district like '"+temp+"' ";
                ResultSet rs=stmt.executeQuery(s);

                while(rs.next()){
                    JOptionPane.showMessageDialog(this,rs.getInt("id"));
                    //opt=rs.getInt("pin")+" - "+rs.getString("post")+", "+rs.getString("village")+", "+rs.getString("district");
                   // c1.addItem(rs.getInt("pin")+" - "+rs.getString("post")+", "+rs.getString("village")+", "+rs.getString("district"));
                }

            }catch (Exception e){}
        }
    }

    public static void main (String args[]){
        pin obj=new pin();
    }
}
