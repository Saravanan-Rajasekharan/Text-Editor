import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.*;


class editor extends JFrame implements ActionListener{

    //Creating the test Area
    JTextArea text;

    //Creating the frame to accommodate the text area and the menu bar

    JFrame frame;

    editor(){

        //Initialising the frame
        frame = new JFrame("Text Editor");

        // Setting the overall theme of the application

        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetallicLookandFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e){

        }

        //Initialising the text Area

        text = new JTextArea();

        //Initialising the menubar

        JMenuBar menu = new JMenuBar();

        //Initialising the file menu

        JMenu f1 = new JMenu("File");
        // Create individual menu items

        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");


        // Adding the action Listener

        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        // Adding the menu items to the file menu


        f1.add(m1);
        f1.add(m2);
        f1.add(m3);
        f1.add(m4);

        // Similarly follow the steps for the Edit Menu

        JMenu f2 = new JMenu("Edit");

        JMenuItem m5 = new JMenuItem("Cut");
        JMenuItem m6 = new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");

        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);

        f2.add(m5);
        f2.add(m6);
        f2.add(m7);

        JMenuItem c = new JMenuItem("Exit");

        c.addActionListener(this);


        menu.add(f1);
        menu.add(f2);
        menu.add(c);

        frame.add(text);
        frame.setJMenuBar(menu);
        frame.setSize(500,500);
        frame.show();

    }
    public void actionPerformed(ActionEvent e){

        String s = e.getActionCommand();

        if(s.equals("New")){
            text.setText("");
        }
        else if(s.equals("Open")){
            JFileChooser j = new JFileChooser("C:");
            int r = j.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){

                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try{
                    String s1 ="", s2 ="";

                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);

                    s2 =  br.readLine();

                    while((s1 = br.readLine())!= null){

                        s2 = s2 + "\n" + s1;
                    }

                    text.setText(s2);
                }
                catch (Exception et){
                    JOptionPane.showMessageDialog(frame, et.getMessage());
                }
            }

            else {
                JOptionPane.showMessageDialog(frame, "Operation Cancelled");
            }

        }
        else if(s.equals("Save")){

            JFileChooser j = new JFileChooser("C:");
            int r = j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION) {

                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    FileWriter wr = new FileWriter(fi);

                    BufferedWriter bw = new BufferedWriter(wr);

                    bw.write(text.getText());

                    bw.flush();
                    bw.close();

                }
                catch (Exception et){
                    JOptionPane.showMessageDialog(frame, et.getMessage());
                }
            }

            else {
                JOptionPane.showMessageDialog(frame, "Operation Cancelled");
            }


        }
        else if(s.equals("Print")){

            try{
                text.print();
            }
            catch(Exception et){
                JOptionPane.showMessageDialog(frame, et.getMessage());
            }

        }
        else if(s.equals("Cut")){
            text.cut();
        }
        else if(s.equals("Copy")){
            text.copy();
        }
        else if(s.equals("Paste")){
            text.paste();
        }
        else if(s.equals("Exit")){
            frame.setVisible(false);
        }

    }
    public static void main(String args[]){
        editor e = new editor();
    }

}