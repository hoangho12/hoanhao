/*
 * Created by JFormDesigner on Thu Jan 20 16:35:29 ICT 2022
 */

package MN2.Login;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.table.DefaultTableModel;


/**
 * @author 21IT348 Hồ Việt Hoàng
 */
public class MenuForm extends JFrame {
    Connection connection;
    Statement statement;
    ResultSet rst;

    Vector vData = new Vector();
    Vector vTitle= new Vector();
    public MenuForm() {
        initComponents();

        
        reload();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlhs","hoang","1234");
            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void reload(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlhs","hoang","1234");
            statement = connection.createStatement();
            rst = statement.executeQuery("Select * from sinhvien");
            vTitle.clear();
            vData.clear();
            ResultSetMetaData resultSetMetaData = rst.getMetaData();
            int num_col= resultSetMetaData.getColumnCount();
            for(int i=1;i<=num_col;i++){
                vTitle.add(resultSetMetaData.getColumnLabel(i));
            }

            while(rst.next()){
                Vector row = new Vector(num_col);
                for(int i=1;i<=num_col;i++){
                    row.add(rst.getString(i));
                }
                vData.add(row);
            }
            rst.close();
        }catch (Exception e){
            System.out.println(e);
        }

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table1.setModel(new DefaultTableModel(vData, vTitle));
        scrollPane1.setViewportView(table1);
        table1.setRowHeight(50);
    }

    public static void main(String[] args) {
        new MenuForm();
    }

    private void button1(ActionEvent e) {
        this.dispose();
    }

    private void logout(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - 21IT348 Hồ Việt Hoàng
        panel7 = new JPanel();
        panel5 = new JPanel();
        tabbedPane1 = new JTabbedPane();
        HOME = new JPanel();
        panel4 = new JPanel();
        panel6 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        panel8 = new JPanel();
        label1 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        panel9 = new JPanel();
        panel10 = new JPanel();
        label6 = new JLabel();
        label7 = new JLabel();
        panel11 = new JPanel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        panel12 = new JPanel();
        panel13 = new JPanel();
        label11 = new JLabel();
        label12 = new JLabel();
        panel14 = new JPanel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        panel15 = new JPanel();
        label16 = new JLabel();
        panel3 = new JPanel();
        PROFILE = new JPanel();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        panel16 = new JPanel();
        button1 = new JButton();
        panel17 = new JPanel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/MN2/img/2037099 (1).png")).getImage());
        setTitle("INTERNET MANAGER");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel7 ========
        {
            panel7.setBackground(new Color(0, 0, 102));
            panel7.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,panel7. getBorder( )) ); panel7. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            panel7.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel7.getComponentCount(); i++) {
                    Rectangle bounds = panel7.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel7.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel7.setMinimumSize(preferredSize);
                panel7.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel7);
        panel7.setBounds(0, 0, 1180, 75);

        //======== panel5 ========
        {
            panel5.setBackground(new Color(0, 153, 255));
            panel5.setLayout(null);

            //======== tabbedPane1 ========
            {
                tabbedPane1.setFont(new Font("Segoe UI", Font.BOLD, 16));
                tabbedPane1.setBackground(new Color(0, 153, 255));
                tabbedPane1.setTabPlacement(SwingConstants.LEFT);
                tabbedPane1.setForeground(new Color(238, 238, 238));

                //======== HOME ========
                {
                    HOME.setBackground(Color.white);
                    HOME.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < HOME.getComponentCount(); i++) {
                            Rectangle bounds = HOME.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = HOME.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        HOME.setMinimumSize(preferredSize);
                        HOME.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("HOME", new ImageIcon(getClass().getResource("/MN2/img/img_169167 (1).png")), HOME);
                tabbedPane1.setDisabledIconAt(0, new ImageIcon(getClass().getResource("/MN2/img/R (1).png")));

                //======== panel4 ========
                {
                    panel4.setBackground(new Color(238, 238, 238));
                    panel4.setLayout(null);

                    //======== panel6 ========
                    {
                        panel6.setBackground(new Color(153, 255, 255));
                        panel6.setLayout(null);

                        //---- label2 ----
                        label2.setText("INTER");
                        label2.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                        label2.setForeground(Color.orange);
                        panel6.add(label2);
                        label2.setBounds(45, 20, 80, 30);

                        //---- label3 ----
                        label3.setText("30");
                        label3.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                        label3.setForeground(Color.black);
                        panel6.add(label3);
                        label3.setBounds(125, 20, 40, 30);

                        //======== panel8 ========
                        {
                            panel8.setBackground(new Color(102, 255, 102));
                            panel8.setLayout(null);

                            //---- label1 ----
                            label1.setText("190.000");
                            label1.setFont(new Font("Segoe UI", Font.BOLD, 30));
                            label1.setForeground(new Color(238, 238, 238));
                            panel8.add(label1);
                            label1.setBounds(50, 20, 110, 35);

                            //---- label4 ----
                            label4.setText("VND/MONTH");
                            label4.setFont(new Font("Segoe UI", Font.BOLD, 26));
                            label4.setForeground(Color.white);
                            panel8.add(label4);
                            label4.setBounds(30, 45, 180, 60);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel8.getComponentCount(); i++) {
                                    Rectangle bounds = panel8.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel8.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel8.setMinimumSize(preferredSize);
                                panel8.setPreferredSize(preferredSize);
                            }
                        }
                        panel6.add(panel8);
                        panel8.setBounds(0, 75, 210, 115);

                        //---- label5 ----
                        label5.setText("Suitable for individuals and households");
                        label5.setIcon(new ImageIcon(getClass().getResource("/MN2/img/538642 (1).png")));
                        panel6.add(label5);
                        label5.setBounds(0, 205, 210, 50);

                        //======== panel9 ========
                        {
                            panel9.setBackground(Color.yellow);
                            panel9.setLayout(null);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel9.getComponentCount(); i++) {
                                    Rectangle bounds = panel9.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel9.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel9.setMinimumSize(preferredSize);
                                panel9.setPreferredSize(preferredSize);
                            }
                        }
                        panel6.add(panel9);
                        panel9.setBounds(30, 180, 160, 15);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel6.getComponentCount(); i++) {
                                Rectangle bounds = panel6.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel6.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel6.setMinimumSize(preferredSize);
                            panel6.setPreferredSize(preferredSize);
                        }
                    }
                    panel4.add(panel6);
                    panel6.setBounds(55, 105, 210, 410);

                    //======== panel10 ========
                    {
                        panel10.setBackground(new Color(153, 255, 255));
                        panel10.setLayout(null);

                        //---- label6 ----
                        label6.setText("INTER");
                        label6.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                        label6.setForeground(Color.orange);
                        panel10.add(label6);
                        label6.setBounds(45, 20, 80, 30);

                        //---- label7 ----
                        label7.setText("30");
                        label7.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                        label7.setForeground(Color.black);
                        panel10.add(label7);
                        label7.setBounds(125, 20, 40, 30);

                        //======== panel11 ========
                        {
                            panel11.setBackground(new Color(102, 255, 102));
                            panel11.setLayout(null);

                            //---- label8 ----
                            label8.setText("190.000");
                            label8.setFont(new Font("Segoe UI", Font.BOLD, 30));
                            label8.setForeground(new Color(238, 238, 238));
                            panel11.add(label8);
                            label8.setBounds(50, 20, 110, 35);

                            //---- label9 ----
                            label9.setText("VND/MONTH");
                            label9.setFont(new Font("Segoe UI", Font.BOLD, 26));
                            label9.setForeground(Color.white);
                            panel11.add(label9);
                            label9.setBounds(30, 45, 180, 60);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel11.getComponentCount(); i++) {
                                    Rectangle bounds = panel11.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel11.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel11.setMinimumSize(preferredSize);
                                panel11.setPreferredSize(preferredSize);
                            }
                        }
                        panel10.add(panel11);
                        panel11.setBounds(0, 75, 210, 115);

                        //---- label10 ----
                        label10.setText("Suitable for individuals and households");
                        label10.setIcon(new ImageIcon(getClass().getResource("/MN2/img/538642 (1).png")));
                        panel10.add(label10);
                        label10.setBounds(0, 205, 210, 50);

                        //======== panel12 ========
                        {
                            panel12.setBackground(Color.yellow);
                            panel12.setLayout(null);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel12.getComponentCount(); i++) {
                                    Rectangle bounds = panel12.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel12.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel12.setMinimumSize(preferredSize);
                                panel12.setPreferredSize(preferredSize);
                            }
                        }
                        panel10.add(panel12);
                        panel12.setBounds(30, 180, 160, 15);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel10.getComponentCount(); i++) {
                                Rectangle bounds = panel10.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel10.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel10.setMinimumSize(preferredSize);
                            panel10.setPreferredSize(preferredSize);
                        }
                    }
                    panel4.add(panel10);
                    panel10.setBounds(345, 105, 210, 410);

                    //======== panel13 ========
                    {
                        panel13.setBackground(new Color(153, 255, 255));
                        panel13.setLayout(null);

                        //---- label11 ----
                        label11.setText("INTER");
                        label11.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                        label11.setForeground(Color.orange);
                        panel13.add(label11);
                        label11.setBounds(45, 20, 80, 30);

                        //---- label12 ----
                        label12.setText("30");
                        label12.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                        label12.setForeground(Color.black);
                        panel13.add(label12);
                        label12.setBounds(125, 20, 40, 30);

                        //======== panel14 ========
                        {
                            panel14.setBackground(new Color(102, 255, 102));
                            panel14.setLayout(null);

                            //---- label13 ----
                            label13.setText("190.000");
                            label13.setFont(new Font("Segoe UI", Font.BOLD, 30));
                            label13.setForeground(new Color(238, 238, 238));
                            panel14.add(label13);
                            label13.setBounds(50, 20, 110, 35);

                            //---- label14 ----
                            label14.setText("VND/MONTH");
                            label14.setFont(new Font("Segoe UI", Font.BOLD, 26));
                            label14.setForeground(Color.white);
                            panel14.add(label14);
                            label14.setBounds(30, 45, 180, 60);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel14.getComponentCount(); i++) {
                                    Rectangle bounds = panel14.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel14.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel14.setMinimumSize(preferredSize);
                                panel14.setPreferredSize(preferredSize);
                            }
                        }
                        panel13.add(panel14);
                        panel14.setBounds(0, 75, 210, 115);

                        //---- label15 ----
                        label15.setText("Suitable for individuals and households");
                        label15.setIcon(new ImageIcon(getClass().getResource("/MN2/img/538642 (1).png")));
                        panel13.add(label15);
                        label15.setBounds(0, 205, 210, 50);

                        //======== panel15 ========
                        {
                            panel15.setBackground(Color.yellow);
                            panel15.setLayout(null);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel15.getComponentCount(); i++) {
                                    Rectangle bounds = panel15.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel15.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel15.setMinimumSize(preferredSize);
                                panel15.setPreferredSize(preferredSize);
                            }
                        }
                        panel13.add(panel15);
                        panel15.setBounds(30, 180, 160, 15);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel13.getComponentCount(); i++) {
                                Rectangle bounds = panel13.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel13.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel13.setMinimumSize(preferredSize);
                            panel13.setPreferredSize(preferredSize);
                        }
                    }
                    panel4.add(panel13);
                    panel13.setBounds(620, 100, 210, 410);

                    //---- label16 ----
                    label16.setText("Personal Packages");
                    label16.setForeground(Color.black);
                    label16.setFont(new Font("Segoe UI", Font.PLAIN, 36));
                    label16.setIcon(new ImageIcon(getClass().getResource("/MN2/img/R (1) (1).png")));
                    panel4.add(label16);
                    label16.setBounds(260, 20, 360, 55);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel4.getComponentCount(); i++) {
                            Rectangle bounds = panel4.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel4.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel4.setMinimumSize(preferredSize);
                        panel4.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("Products & Services", new ImageIcon(getClass().getResource("/MN2/img/581-5817117_service-maintenance-png-clipart-maintenance-icon-transparent (1).png")), panel4);

                //======== panel3 ========
                {
                    panel3.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel3.getComponentCount(); i++) {
                            Rectangle bounds = panel3.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel3.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel3.setMinimumSize(preferredSize);
                        panel3.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("USER", panel3);

                //======== PROFILE ========
                {
                    PROFILE.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < PROFILE.getComponentCount(); i++) {
                            Rectangle bounds = PROFILE.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = PROFILE.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        PROFILE.setMinimumSize(preferredSize);
                        PROFILE.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("PROFILE", PROFILE);

                //======== panel1 ========
                {
                    panel1.setBackground(new Color(238, 238, 238));
                    panel1.setLayout(null);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(table1);
                    }
                    panel1.add(scrollPane1);
                    scrollPane1.setBounds(10, 170, 850, 375);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel1.getComponentCount(); i++) {
                            Rectangle bounds = panel1.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel1.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel1.setMinimumSize(preferredSize);
                        panel1.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("INFORMATION", panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel2.getComponentCount(); i++) {
                            Rectangle bounds = panel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel2.setMinimumSize(preferredSize);
                        panel2.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("EXIT", panel2);

                tabbedPane1.setSelectedIndex(0);
            }
            panel5.add(tabbedPane1);
            tabbedPane1.setBounds(250, -30, 1070, 550);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel5.getComponentCount(); i++) {
                    Rectangle bounds = panel5.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel5.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel5.setMinimumSize(preferredSize);
                panel5.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel5);
        panel5.setBounds(415, 75, 940, 550);

        //======== panel16 ========
        {
            panel16.setBackground(new Color(0, 0, 102));
            panel16.setLayout(null);

            //---- button1 ----
            button1.setText("LOGOUT");
            button1.setBackground(new Color(238, 238, 238));
            button1.setIcon(new ImageIcon(getClass().getResource("/MN2/img/3094700.png")));
            button1.addActionListener(e -> {
			logout(e);
			button1(e);
		});
            panel16.add(button1);
            button1.setBounds(5, 500, 95, 40);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel16.getComponentCount(); i++) {
                    Rectangle bounds = panel16.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel16.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel16.setMinimumSize(preferredSize);
                panel16.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel16);
        panel16.setBounds(0, 75, 115, 550);

        //======== panel17 ========
        {
            panel17.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel17.getComponentCount(); i++) {
                    Rectangle bounds = panel17.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel17.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel17.setMinimumSize(preferredSize);
                panel17.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel17);
        panel17.setBounds(115, 75, 255, 550);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - 21IT348 Hồ Việt Hoàng
    private JPanel panel7;
    private JPanel panel5;
    private JTabbedPane tabbedPane1;
    private JPanel HOME;
    private JPanel panel4;
    private JPanel panel6;
    private JLabel label2;
    private JLabel label3;
    private JPanel panel8;
    private JLabel label1;
    private JLabel label4;
    private JLabel label5;
    private JPanel panel9;
    private JPanel panel10;
    private JLabel label6;
    private JLabel label7;
    private JPanel panel11;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JPanel panel12;
    private JPanel panel13;
    private JLabel label11;
    private JLabel label12;
    private JPanel panel14;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JPanel panel15;
    private JLabel label16;
    private JPanel panel3;
    private JPanel PROFILE;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel2;
    private JPanel panel16;
    private JButton button1;
    private JPanel panel17;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
