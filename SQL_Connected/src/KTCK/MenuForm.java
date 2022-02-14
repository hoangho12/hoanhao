/*
 * Created by JFormDesigner on Sat Jan 22 20:29:36 ICT 2022
 */

package KTCK;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 21IT348 Hồ Việt Hoàng
 */
public class MenuForm extends JFrame {
    Connection connection;
    Statement statement;
    ResultSet rst;
    String quyentc, codeuser;
    String usercodeSD;

    public MenuForm(String access, String code) {
        initComponents();
        showLineChart();
        showline();
        showchart();
        quyentc = access;
        codeuser =  code;
        
        if(quyentc.equals("admin")){
            homebt4.setVisible(true);
            showacc.setText("Admin");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager","hoang","1234");
                statement = connection.createStatement();
                rst = statement.executeQuery("SELECT * from login where username = \""+codeuser +"\"");
                while(rst.next()){
                    usercodeSD = rst.getString(3);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }else {
            homebt4.setVisible(false);
            s1.setVisible(false);
            s2.setVisible(false);
            s3.setVisible(false);
            s4.setVisible(false);
            button18.setVisible(false);
            button19.setVisible(false);
            button20.setVisible(false);
            showacc.setText("User");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager","hoang","1234");
                statement = connection.createStatement();
                rst = statement.executeQuery("SELECT * from login where username = \""+codeuser +"\"");
                while(rst.next()){
                    usercodeSD = rst.getString(3);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        reload();
        tranLoad();
        userload();
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager","hoang","1234");
            statement = connection.createStatement();
            rst = statement.executeQuery("SELECT * from services");
            while(rst.next()){
                comboBox1.addItem(rst.getString(1));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void logout(ActionEvent e) {
        new loadingScreen();
        setVisible(false);
    }

    public void deleterow(){
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE from usermanager where UserCode =?");
            pst.setString(1,usercode.getText());
            pst.execute();
            userload();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    Vector vData = new Vector();
    Vector vTitle= new Vector();
    public void reload() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager", "hoang", "1234");
            statement = connection.createStatement();
            rst = statement.executeQuery("Select * from services");
            vTitle.clear();
            vData.clear();
            ResultSetMetaData resultSetMetaData = rst.getMetaData();
            int num_col = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= num_col; i++) {
                vTitle.add(resultSetMetaData.getColumnLabel(i));
            }

            while (rst.next()) {
                Vector row = new Vector(num_col);
                for (int i = 1; i <= num_col; i++) {
                    row.add(rst.getString(i));
                }
                vData.add(row);
            }
            rst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        servicestb.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        servicestb.setModel(new DefaultTableModel(vData, vTitle));
        scrollPane1.setViewportView(servicestb);
        servicestb.setRowHeight(50);
    }

    Vector user= new Vector();
    Vector stringUser = new Vector();
    public void userload() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager", "hoang", "1234");
            statement = connection.createStatement();
            rst = statement.executeQuery("Select * from usermanager");
            user.clear();
            stringUser.clear();
            ResultSetMetaData resultSetMetaData = rst.getMetaData();
            int num_col = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= num_col; i++) {
                user.add(resultSetMetaData.getColumnLabel(i));
            }

            while (rst.next()) {
                Vector row = new Vector(num_col);
                for (int i = 1; i <= num_col; i++) {
                    row.add(rst.getString(i));
                }
                stringUser.add(row);
            }
            rst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        usermanagertb.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        usermanagertb.setModel(new DefaultTableModel(stringUser, user));
        scrollPane2.setViewportView(usermanagertb);
        usermanagertb.setRowHeight(50);
    }

    Vector vtrant = new Vector();
    Vector vtransaction = new Vector();
    public void tranLoad(){
        try {
            vtrant.clear();
            vtransaction.clear();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager", "hoang", "1234");
            statement = connection.createStatement();
            if(quyentc.equals("user"))
            rst = statement.executeQuery("Select * from transaction where UserCode = \""+usercodeSD+"\"");
            else rst = statement.executeQuery("Select * from transaction");
            ResultSetMetaData resultSetMetaData = rst.getMetaData();
            int num_col = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= num_col; i++) {
                vtrant.add(resultSetMetaData.getColumnLabel(i));
            }

            while (rst.next()) {
                Vector row = new Vector(num_col);
                for (int i = 1; i <= num_col; i++) {
                    row.add(rst.getString(i));
                }
                balancetext.setText(rst.getString(6));
                IDTEXT.setText(rst.getString(2));
                NAMETEXT.setText(rst.getString(3));
                SERVICETEXT.setText(rst.getString(4));
                vaxtext.setText(rst.getString(7));
                PRICETEXT1.setText(rst.getString(7));
                VAXTEXT.setText(rst.getString(8));
                TOTALTEXT.setText(rst.getString(9));
                usernameshow.setText(rst.getString(3));
                vtransaction.add(row);
                if(rst.getString(10).equals("paid")){
                    button3.setText("PAID");
                    button3.setEnabled(false);
                    TOTALTEXT.setText("");
                }
            }
            rst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        trantb.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        trantb.setModel(new DefaultTableModel(vtransaction, vtrant));
        scrollPane3.setViewportView(trantb);
        trantb.setRowHeight(50);
    }



    public void searchService(){
            try {
                vData.clear();
                vTitle.clear();
                String sql = "SELECT * from services where servicecode =\"" + serviceText.getText() + "\"";
                rst = statement.executeQuery(sql);
                ResultSetMetaData resultSetMetaData = rst.getMetaData();
                int num_col = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= num_col; i++) {
                    vTitle.add(resultSetMetaData.getColumnLabel(i));
                }

                while (rst.next()) {
                    Vector row = new Vector(num_col);
                    for (int i = 1; i <= num_col; i++) {
                        row.add(rst.getString(i));
                    }
                    vData.add(row);
                }
                rst.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            servicestb.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            servicestb.setModel(new DefaultTableModel(vData, vTitle));
            scrollPane1.setViewportView(servicestb);
            servicestb.setRowHeight(50);
    }


    private void adduser(){
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO usermanager values (?,?,?,?,?)");
            pst.setString(1,usercode.getText());
            pst.setString(2,name.getText());
            pst.setString(3,address.getText());
            pst.setString(4,phone.getText());
            pst.setString(5, (String) comboBox1.getSelectedItem());
            pst.execute();
            userload();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void edituser(){
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE usermanager set Username = ?, Address=?,NumberPhone=?,Servicecode =? where Usercode = ?");
            pst.setString(1, name.getText());
            pst.setString(2, address.getText());
            pst.setString(3, phone.getText());
            pst.setString(4, (String) comboBox1.getSelectedItem());
            pst.setString(5, usercode.getText());
            pst.execute();
            userload();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void showchart(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager", "hoang", "1234");
            statement = connection.createStatement();
            rst = statement.executeQuery("Select * from dashboard");
            while (rst.next()) {
                dataset.setValue(Double.parseDouble(rst.getString(2)),"DATA",rst.getString(3));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        JFreeChart chart = ChartFactory.createLineChart("Data statistical","monthly","Data",dataset,PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);

        ChartPanel panel = new ChartPanel(chart);
        linebar.removeAll();
        linebar.add(panel,BorderLayout.CENTER);
        linebar.validate();
    }
    public void showline(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager", "hoang", "1234");
            statement = connection.createStatement();
            rst = statement.executeQuery("Select * from dashboard");
            while (rst.next()) {
                dataset.setValue(Double.parseDouble(rst.getString(2)),"DATA",rst.getString(3));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        JFreeChart chart = ChartFactory.createBarChart("Data statistical","date","Data",dataset,PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);

        ChartPanel panel = new ChartPanel(chart);
        chartbar.removeAll();
        chartbar.add(panel,BorderLayout.CENTER);
        chartbar.validate();
    }
    public void showLineChart(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager", "hoang", "1234");
            statement = connection.createStatement();
            rst = statement.executeQuery("Select * from services");
            while (rst.next()) {
                dataset.setValue(rst.getString(1), Double.parseDouble(rst.getString(5)));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        JFreeChart piechart = ChartFactory.createPieChart("STATISTICAL USER",dataset,false,true,false);
        PiePlot plot = (PiePlot) piechart.getPlot();
        plot.setBackgroundPaint(Color.white);

        ChartPanel panel = new ChartPanel(piechart);
        charlinepn.removeAll();
        charlinepn.add(panel,BorderLayout.CENTER);
        charlinepn.validate();
    }
    private void homebt(ActionEvent e) {
        tabbedPane1.setSelectedIndex(0);
    }

    private void Servicesbt(ActionEvent e) {
        tabbedPane1.setSelectedIndex(1);
    }

    private void homebt2(ActionEvent e) {
        tabbedPane1.setSelectedIndex(2);
    }

    private void servicebt(ActionEvent e) {
        tabbedPane1.setSelectedIndex(1);
    }

    private void homebt4(ActionEvent e) {
        tabbedPane1.setSelectedIndex(4);
    }

    private void transaction(ActionEvent e) {
        tabbedPane1.setSelectedIndex(5);
    }

    private void transactionhome(ActionEvent e) {
        tabbedPane1.setSelectedIndex(5);
    }

    private void button14(ActionEvent e) {
        tabbedPane1.setSelectedIndex(0);
    }

    private void serbt(ActionEvent e) {
        tabbedPane1.setSelectedIndex(2);
    }

    private void searchbt(ActionEvent e) {
        searchService();
    }

    private void reloadbt(ActionEvent e) {
        reload();
    }

    private void deletebt(ActionEvent e) {
        deleterow();
    }

    private void addbt(ActionEvent e) {
        adduser();
    }

    private void editbt(ActionEvent e) {
        edituser();
    }

    private void usermanagertbMouseClicked(MouseEvent e) {
        int  selectedrow = usermanagertb.getSelectedRow();
        if(selectedrow>=0){
            usercode.setText(usermanagertb.getModel().getValueAt(selectedrow, 0).toString());
            name.setText(usermanagertb.getModel().getValueAt(selectedrow, 1).toString());
            address.setText(usermanagertb.getModel().getValueAt(selectedrow, 2).toString());
            phone.setText(usermanagertb.getModel().getValueAt(selectedrow, 3).toString());
            comboBox1.setSelectedItem(usermanagertb.getModel().getValueAt(selectedrow,4).toString());
        }
    }


    private void CONTACTbt(ActionEvent e) {
        tabbedPane1.setSelectedIndex(6);
    }

    private void dashbt(ActionEvent e) {
        tabbedPane1.setSelectedIndex(3);
    }

    private void dashhomebt(ActionEvent e) {
        tabbedPane1.setSelectedIndex(3);
    }

    private void paybt(ActionEvent e) {
        new QRCODE();
        edittran();
    }

    public void edittran(){
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE transaction set balance=balance-total, status=\"paid\"  where Usercode = ?");
            pst.setString(1, IDTEXT.getText());
            pst.execute();
            button3.setText("PAID");
            button3.setEnabled(false);
            TOTALTEXT.setText("");
            tranLoad();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }


    private void addservicebt(ActionEvent e) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO services values (?,?,?,?,1)");
            pst.setString(1,s1.getText());
            pst.setString(2,s2.getText());
            pst.setString(3,s3.getText());
            pst.setString(4,s4.getText());
            pst.execute();
            reload();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void deleteservicebt(ActionEvent e) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE from services where servicecode =?");
            pst.setString(1,s1.getText());
            pst.execute();
            reload();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }


    private void servicestbMouseClicked(MouseEvent e) {
        int  selectedrow = servicestb.getSelectedRow();
        if(selectedrow>=0){
            s1.setText(servicestb.getModel().getValueAt(selectedrow, 0).toString());
            s2.setText(servicestb.getModel().getValueAt(selectedrow, 1).toString());
            s3.setText(servicestb.getModel().getValueAt(selectedrow, 2).toString());
            s4.setText(servicestb.getModel().getValueAt(selectedrow, 3).toString());
        }
    }

    private void editservicebt(ActionEvent e) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE services set servicename = ?, price=?,information=? where servicecode = ?");
            pst.setString(1, s2.getText());
            pst.setString(2, s3.getText());
            pst.setString(3, s4.getText());
            pst.setString(4, s1.getText());
            pst.execute();
            reload();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void CLEARBT(ActionEvent e) {
        usercode.setText("");
        address.setText("");
        name.setText("");
        phone.setText("");
    }
    
    private void homebtMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void serviceclick(MouseEvent e) {
        // TODO add your code here
    }

    private void selected(MouseEvent e) {
        // TODO add your code here
    }

    private void selectedTB(MouseEvent e) {
        // TODO add your code here
    }

    private void button9MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hoang
        panel1 = new JPanel();
        panel15 = new JPanel();
        label15 = new JLabel();
        panel25 = new JPanel();
        usernameshow = new JLabel();
        showacc = new JLabel();
        button21 = new JButton();
        homebt = new JButton();
        Servicesbt = new JButton();
        homebt2 = new JButton();
        logout = new JButton();
        homebt4 = new JButton();
        homebt5 = new JButton();
        homebt6 = new JButton();
        label1 = new JLabel();
        label73 = new JLabel();
        label2 = new JLabel();
        tabbedPane1 = new JTabbedPane();
        home = new JPanel();
        panel2 = new JPanel();
        label50 = new JLabel();
        label51 = new JLabel();
        button14 = new JButton();
        label52 = new JLabel();
        panel3 = new JPanel();
        label20 = new JLabel();
        label26 = new JLabel();
        label49 = new JLabel();
        button13 = new JButton();
        panel16 = new JPanel();
        label18 = new JLabel();
        label27 = new JLabel();
        button4 = new JButton();
        label58 = new JLabel();
        panel17 = new JPanel();
        label22 = new JLabel();
        label23 = new JLabel();
        label30 = new JLabel();
        button2 = new JButton();
        panel18 = new JPanel();
        label19 = new JLabel();
        label25 = new JLabel();
        button15 = new JButton();
        label63 = new JLabel();
        panel19 = new JPanel();
        label21 = new JLabel();
        label24 = new JLabel();
        label28 = new JLabel();
        label29 = new JLabel();
        button1 = new JButton();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel8 = new JPanel();
        label6 = new JLabel();
        label7 = new JLabel();
        panel14 = new JPanel();
        label3 = new JLabel();
        panel9 = new JPanel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        button5 = new JButton();
        panel6 = new JPanel();
        panel10 = new JPanel();
        label13 = new JLabel();
        label14 = new JLabel();
        label4 = new JLabel();
        panel11 = new JPanel();
        label39 = new JLabel();
        label40 = new JLabel();
        label41 = new JLabel();
        label42 = new JLabel();
        label43 = new JLabel();
        button6 = new JButton();
        panel7 = new JPanel();
        panel12 = new JPanel();
        label16 = new JLabel();
        label17 = new JLabel();
        label5 = new JLabel();
        panel13 = new JPanel();
        label44 = new JLabel();
        label45 = new JLabel();
        label46 = new JLabel();
        label47 = new JLabel();
        label48 = new JLabel();
        button7 = new JButton();
        pro = new JPanel();
        charlinepn = new JPanel();
        scrollPane1 = new JScrollPane();
        servicestb = new JTable();
        serviceText = new JTextField();
        button11 = new JButton();
        button12 = new JButton();
        s1 = new JTextField();
        s2 = new JTextField();
        s3 = new JTextField();
        s4 = new JTextField();
        button18 = new JButton();
        button19 = new JButton();
        button20 = new JButton();
        payment = new JPanel();
        chartbar = new JPanel();
        linebar = new JPanel();
        panel28 = new JPanel();
        label72 = new JLabel();
        label33 = new JLabel();
        label67 = new JLabel();
        label70 = new JLabel();
        label71 = new JLabel();
        label74 = new JLabel();
        label75 = new JLabel();
        usermanager = new JPanel();
        scrollPane2 = new JScrollPane();
        usermanagertb = new JTable();
        button8 = new JButton();
        button9 = new JButton();
        button10 = new JButton();
        usercode = new JTextField();
        address = new JTextField();
        name = new JTextField();
        phone = new JTextField();
        label53 = new JLabel();
        label54 = new JLabel();
        label55 = new JLabel();
        label56 = new JLabel();
        comboBox1 = new JComboBox();
        label57 = new JLabel();
        CLEARBT = new JButton();
        transaction = new JPanel();
        button3 = new JButton();
        scrollPane3 = new JScrollPane();
        trantb = new JTable();
        panel22 = new JPanel();
        label32 = new JLabel();
        label34 = new JLabel();
        label35 = new JLabel();
        label36 = new JLabel();
        label37 = new JLabel();
        label38 = new JLabel();
        IDTEXT = new JLabel();
        NAMETEXT = new JLabel();
        PHONETEXT = new JLabel();
        SERVICETEXT = new JLabel();
        VAXTEXT = new JLabel();
        TOTALTEXT = new JLabel();
        PRICETEXT1 = new JLabel();
        PRICETEXT = new JLabel();
        panel23 = new JPanel();
        panel26 = new JPanel();
        label66 = new JLabel();
        label64 = new JLabel();
        balancetext = new JLabel();
        button16 = new JButton();
        button17 = new JButton();
        balancetext2 = new JLabel();
        panel24 = new JPanel();
        panel27 = new JPanel();
        label31 = new JLabel();
        vaxtext = new JLabel();
        balancetext3 = new JLabel();
        panel21 = new JPanel();
        label59 = new JLabel();
        label60 = new JLabel();
        label61 = new JLabel();
        label62 = new JLabel();
        label68 = new JLabel();
        label69 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/MN2/img/2037099 (1).png")).getImage());
        setTitle("INTERNET MANAGEMENT");
        setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );
            panel1.setLayout(null);

            //======== panel15 ========
            {
                panel15.setBackground(new Color(255, 51, 255));
                panel15.setLayout(null);

                //---- label15 ----
                label15.setText("INTERNET");
                label15.setForeground(new Color(0, 51, 255));
                label15.setFont(new Font("Rockwell Condensed", Font.BOLD, 27));
                panel15.add(label15);
                label15.setBounds(25, 5, 135, 40);

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
            panel1.add(panel15);
            panel15.setBounds(0, 0, 175, 50);

            //======== panel25 ========
            {
                panel25.setBackground(Color.white);
                panel25.setOpaque(false);
                panel25.setLayout(null);

                //---- usernameshow ----
                usernameshow.setText("Username");
                usernameshow.setFont(new Font("SimSun", Font.PLAIN, 16));
                usernameshow.setForeground(Color.black);
                usernameshow.setHorizontalAlignment(SwingConstants.CENTER);
                panel25.add(usernameshow);
                usernameshow.setBounds(0, 50, 175, 25);

                //---- showacc ----
                showacc.setText("admin");
                showacc.setFont(new Font("SimSun", Font.PLAIN, 16));
                showacc.setForeground(Color.black);
                showacc.setHorizontalAlignment(SwingConstants.CENTER);
                panel25.add(showacc);
                showacc.setBounds(0, 70, 175, 25);

                //---- button21 ----
                button21.setIcon(new ImageIcon(getClass().getResource("/MN2/img/R (1) (1).png")));
                button21.setContentAreaFilled(false);
                button21.setBorderPainted(false);
                panel25.add(button21);
                button21.setBounds(0, 0, 175, 50);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel25.getComponentCount(); i++) {
                        Rectangle bounds = panel25.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel25.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel25.setMinimumSize(preferredSize);
                    panel25.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel25);
            panel25.setBounds(0, 60, 175, 95);

            //---- homebt ----
            homebt.setText("Home");
            homebt.setBackground(new Color(160, 64, 149));
            homebt.setForeground(new Color(238, 238, 238));
            homebt.setFont(new Font("Arial Black", Font.PLAIN, 18));
            homebt.setContentAreaFilled(false);
            homebt.setIcon(new ImageIcon(getClass().getResource("/MN2/img/img_169167 (1).png")));
            homebt.setBorderPainted(false);
            homebt.setHorizontalAlignment(SwingConstants.LEFT);
            homebt.addActionListener(e -> homebt(e));
            homebt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    homebtMouseClicked(e);
                }
            });
            panel1.add(homebt);
            homebt.setBounds(0, 160, 175, 45);

            //---- Servicesbt ----
            Servicesbt.setText("Packages");
            Servicesbt.setBackground(new Color(160, 64, 149));
            Servicesbt.setForeground(new Color(238, 238, 238));
            Servicesbt.setFont(new Font("Arial Black", Font.PLAIN, 18));
            Servicesbt.setContentAreaFilled(false);
            Servicesbt.setIcon(new ImageIcon(getClass().getResource("/MN2/img/581-5817117_service-maintenance-png-clipart-maintenance-icon-transparent (1).png")));
            Servicesbt.setBorderPainted(false);
            Servicesbt.setHorizontalAlignment(SwingConstants.LEFT);
            Servicesbt.addActionListener(e -> {
			homebt(e);
			Servicesbt(e);
		});
            panel1.add(Servicesbt);
            Servicesbt.setBounds(0, 200, 175, 45);

            //---- homebt2 ----
            homebt2.setText("Services");
            homebt2.setBackground(new Color(160, 64, 149));
            homebt2.setForeground(new Color(238, 238, 238));
            homebt2.setFont(new Font("Arial Black", Font.PLAIN, 18));
            homebt2.setContentAreaFilled(false);
            homebt2.setIcon(new ImageIcon(getClass().getResource("/MN2/img/R (2) (1).png")));
            homebt2.setBorderPainted(false);
            homebt2.setHorizontalAlignment(SwingConstants.LEFT);
            homebt2.addActionListener(e -> {
			homebt(e);
			homebt2(e);
		});
            panel1.add(homebt2);
            homebt2.setBounds(0, 240, 175, 45);

            //---- logout ----
            logout.setIcon(new ImageIcon(getClass().getResource("/MN2/img/logout.png")));
            logout.setContentAreaFilled(false);
            logout.setBorderPainted(false);
            logout.setText("LOGOUT");
            logout.setForeground(Color.red);
            logout.setFont(logout.getFont().deriveFont(logout.getFont().getStyle() | Font.BOLD, logout.getFont().getSize() + 5f));
            logout.addActionListener(e -> logout(e));
            panel1.add(logout);
            logout.setBounds(new Rectangle(new Point(10, 615), logout.getPreferredSize()));

            //---- homebt4 ----
            homebt4.setText("UserManager");
            homebt4.setBackground(new Color(160, 64, 149));
            homebt4.setForeground(new Color(238, 238, 238));
            homebt4.setFont(new Font("Arial Black", Font.PLAIN, 16));
            homebt4.setContentAreaFilled(false);
            homebt4.setIcon(new ImageIcon(getClass().getResource("/MN2/img/user.png")));
            homebt4.setBorderPainted(false);
            homebt4.setHorizontalAlignment(SwingConstants.LEFT);
            homebt4.addActionListener(e -> {
			homebt(e);
			homebt2(e);
			homebt4(e);
		});
            panel1.add(homebt4);
            homebt4.setBounds(0, 365, 175, 45);

            //---- homebt5 ----
            homebt5.setText("Transaction");
            homebt5.setBackground(new Color(160, 64, 149));
            homebt5.setForeground(new Color(238, 238, 238));
            homebt5.setFont(new Font("Arial Black", Font.PLAIN, 17));
            homebt5.setContentAreaFilled(false);
            homebt5.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/R (10) (1).png")));
            homebt5.setBorderPainted(false);
            homebt5.setHorizontalAlignment(SwingConstants.LEFT);
            homebt5.addActionListener(e -> {
			homebt(e);
			transaction(e);
		});
            panel1.add(homebt5);
            homebt5.setBounds(0, 320, 175, 45);

            //---- homebt6 ----
            homebt6.setText("Dashboard");
            homebt6.setBackground(new Color(160, 64, 149));
            homebt6.setForeground(new Color(238, 238, 238));
            homebt6.setFont(new Font("Arial Black", Font.PLAIN, 15));
            homebt6.setContentAreaFilled(false);
            homebt6.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/R (13) (1).png")));
            homebt6.setBorderPainted(false);
            homebt6.setHorizontalAlignment(SwingConstants.LEFT);
            homebt6.addActionListener(e -> {
			homebt(e);
			transaction(e);
			dashhomebt(e);
		});
            panel1.add(homebt6);
            homebt6.setBounds(0, 280, 175, 45);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/MN2/img/479190-cool-color-backgrounds-1920x1200-hd-for-mobile.jpg")));
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label1);
            label1.setBounds(0, 50, 175, 625);

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
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 175, 670);

        //---- label73 ----
        label73.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/R (15) (1).png")));
        contentPane.add(label73);
        label73.setBounds(1300, -5, 45, 60);

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/MN2/img/479190-cool-color-backgrounds-1920x1200-hd-for-mobile.jpg")));
        contentPane.add(label2);
        label2.setBounds(165, 0, 1190, 50);

        //======== tabbedPane1 ========
        {

            //======== home ========
            {
                home.setBackground(new Color(204, 204, 204));
                home.setForeground(new Color(238, 238, 238));
                home.setLayout(null);

                //======== panel2 ========
                {
                    panel2.setBackground(Color.white);
                    panel2.setLayout(null);

                    //---- label50 ----
                    label50.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/OIP (1).png")));
                    label50.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label50);
                    label50.setBounds(0, 0, 275, 97);

                    //---- label51 ----
                    label51.setText("Home");
                    label51.setForeground(new Color(102, 51, 255));
                    label51.setFont(new Font("Fira Code Medium", Font.PLAIN, 28));
                    label51.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label51);
                    label51.setBounds(0, 110, 275, 35);

                    //---- button14 ----
                    button14.setText("Home");
                    button14.setContentAreaFilled(false);
                    button14.setForeground(Color.black);
                    button14.setBackground(Color.blue);
                    button14.addActionListener(e -> {
			servicebt(e);
			transactionhome(e);
			button14(e);
		});
                    panel2.add(button14);
                    button14.setBounds(80, 195, 110, 35);

                    //---- label52 ----
                    label52.setText("Home");
                    label52.setForeground(Color.black);
                    label52.setHorizontalAlignment(SwingConstants.CENTER);
                    label52.setFont(label52.getFont().deriveFont(label52.getFont().getSize() + 6f));
                    panel2.add(label52);
                    label52.setBounds(0, 140, 275, 45);

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
                home.add(panel2);
                panel2.setBounds(65, 55, 275, 240);

                //======== panel3 ========
                {
                    panel3.setBackground(Color.white);
                    panel3.setLayout(null);

                    //---- label20 ----
                    label20.setIcon(new ImageIcon(getClass().getResource("/MN2/img/ser.png")));
                    label20.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(label20);
                    label20.setBounds(0, 0, 275, 95);

                    //---- label26 ----
                    label26.setText("Service");
                    label26.setForeground(new Color(102, 51, 255));
                    label26.setFont(new Font("Fira Code Medium", Font.PLAIN, 28));
                    label26.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(label26);
                    label26.setBounds(0, 95, 275, label26.getPreferredSize().height);

                    //---- label49 ----
                    label49.setText("Internet Services");
                    label49.setForeground(Color.black);
                    label49.setHorizontalAlignment(SwingConstants.CENTER);
                    label49.setFont(label49.getFont().deriveFont(label49.getFont().getSize() + 6f));
                    panel3.add(label49);
                    label49.setBounds(0, 135, 275, 45);

                    //---- button13 ----
                    button13.setText("Service");
                    button13.setContentAreaFilled(false);
                    button13.setForeground(Color.black);
                    button13.setBackground(Color.blue);
                    button13.addActionListener(e -> {
			servicebt(e);
			serbt(e);
		});
                    panel3.add(button13);
                    button13.setBounds(85, 195, 110, 35);

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
                home.add(panel3);
                panel3.setBounds(70, 355, 275, 240);

                //======== panel16 ========
                {
                    panel16.setBackground(Color.white);
                    panel16.setLayout(null);

                    //---- label18 ----
                    label18.setIcon(new ImageIcon(getClass().getResource("/MN2/img/tranv.png")));
                    label18.setHorizontalAlignment(SwingConstants.CENTER);
                    panel16.add(label18);
                    label18.setBounds(0, 0, 275, 97);

                    //---- label27 ----
                    label27.setText("Transaction");
                    label27.setForeground(new Color(102, 51, 255));
                    label27.setFont(new Font("Fira Code Medium", Font.PLAIN, 28));
                    label27.setHorizontalAlignment(SwingConstants.CENTER);
                    panel16.add(label27);
                    label27.setBounds(0, 110, 275, label27.getPreferredSize().height);

                    //---- button4 ----
                    button4.setText("Transaction");
                    button4.setContentAreaFilled(false);
                    button4.setForeground(Color.black);
                    button4.setBackground(Color.blue);
                    button4.addActionListener(e -> {
			servicebt(e);
			transactionhome(e);
		});
                    panel16.add(button4);
                    button4.setBounds(80, 195, 110, 35);

                    //---- label58 ----
                    label58.setText("Transaction");
                    label58.setForeground(Color.black);
                    label58.setHorizontalAlignment(SwingConstants.CENTER);
                    label58.setFont(label58.getFont().deriveFont(label58.getFont().getSize() + 6f));
                    panel16.add(label58);
                    label58.setBounds(0, 145, 275, 45);

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
                home.add(panel16);
                panel16.setBounds(450, 55, 275, 240);

                //======== panel17 ========
                {
                    panel17.setBackground(Color.white);
                    panel17.setLayout(null);

                    //---- label22 ----
                    label22.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/pack.png")));
                    label22.setHorizontalAlignment(SwingConstants.CENTER);
                    panel17.add(label22);
                    label22.setBounds(0, 0, 275, 90);

                    //---- label23 ----
                    label23.setText("Packages");
                    label23.setForeground(new Color(102, 51, 255));
                    label23.setFont(new Font("Fira Code Medium", Font.PLAIN, 28));
                    label23.setHorizontalAlignment(SwingConstants.CENTER);
                    panel17.add(label23);
                    label23.setBounds(0, 105, 275, label23.getPreferredSize().height);

                    //---- label30 ----
                    label30.setText("Internet Services");
                    label30.setForeground(Color.black);
                    label30.setHorizontalAlignment(SwingConstants.CENTER);
                    label30.setFont(label30.getFont().deriveFont(label30.getFont().getSize() + 6f));
                    panel17.add(label30);
                    label30.setBounds(0, 135, 275, 45);

                    //---- button2 ----
                    button2.setText("Packages");
                    button2.setContentAreaFilled(false);
                    button2.setForeground(Color.black);
                    button2.setBackground(Color.blue);
                    button2.addActionListener(e -> servicebt(e));
                    panel17.add(button2);
                    button2.setBounds(80, 195, 110, 35);

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
                home.add(panel17);
                panel17.setBounds(830, 55, 275, 240);

                //======== panel18 ========
                {
                    panel18.setBackground(Color.white);
                    panel18.setLayout(null);

                    //---- label19 ----
                    label19.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/dash.png")));
                    label19.setHorizontalAlignment(SwingConstants.CENTER);
                    panel18.add(label19);
                    label19.setBounds(0, 0, 275, 90);

                    //---- label25 ----
                    label25.setText("Dashboard");
                    label25.setForeground(new Color(102, 51, 255));
                    label25.setFont(new Font("Fira Code Medium", Font.PLAIN, 28));
                    label25.setHorizontalAlignment(SwingConstants.CENTER);
                    panel18.add(label25);
                    label25.setBounds(0, 95, 275, 40);

                    //---- button15 ----
                    button15.setText("Dashboard");
                    button15.setContentAreaFilled(false);
                    button15.setForeground(Color.black);
                    button15.setBackground(Color.blue);
                    button15.addActionListener(e -> {
			CONTACTbt(e);
			dashbt(e);
		});
                    panel18.add(button15);
                    button15.setBounds(80, 195, 110, 35);

                    //---- label63 ----
                    label63.setText("Dashboard");
                    label63.setForeground(Color.black);
                    label63.setHorizontalAlignment(SwingConstants.CENTER);
                    label63.setFont(label63.getFont().deriveFont(label63.getFont().getSize() + 6f));
                    panel18.add(label63);
                    label63.setBounds(0, 135, 275, 45);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel18.getComponentCount(); i++) {
                            Rectangle bounds = panel18.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel18.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel18.setMinimumSize(preferredSize);
                        panel18.setPreferredSize(preferredSize);
                    }
                }
                home.add(panel18);
                panel18.setBounds(455, 350, 275, 240);

                //======== panel19 ========
                {
                    panel19.setBackground(Color.white);
                    panel19.setLayout(null);

                    //---- label21 ----
                    label21.setIcon(new ImageIcon(getClass().getResource("/MN2/img/contact.png")));
                    label21.setHorizontalAlignment(SwingConstants.CENTER);
                    panel19.add(label21);
                    label21.setBounds(0, 0, 275, 90);

                    //---- label24 ----
                    label24.setText("Contact");
                    label24.setForeground(new Color(102, 51, 255));
                    label24.setFont(new Font("Fira Code Medium", Font.PLAIN, 28));
                    label24.setHorizontalAlignment(SwingConstants.CENTER);
                    panel19.add(label24);
                    label24.setBounds(0, 100, 275, label24.getPreferredSize().height);

                    //---- label28 ----
                    label28.setText("You can ask this program ");
                    label28.setForeground(Color.black);
                    label28.setFont(label28.getFont().deriveFont(label28.getFont().getSize() + 6f));
                    label28.setHorizontalAlignment(SwingConstants.CENTER);
                    panel19.add(label28);
                    label28.setBounds(0, 135, 275, 30);

                    //---- label29 ----
                    label29.setText("with email or telephone");
                    label29.setForeground(Color.black);
                    label29.setHorizontalAlignment(SwingConstants.CENTER);
                    label29.setFont(label29.getFont().deriveFont(label29.getFont().getSize() + 6f));
                    panel19.add(label29);
                    label29.setBounds(0, 165, 275, 25);

                    //---- button1 ----
                    button1.setText("Contact");
                    button1.setContentAreaFilled(false);
                    button1.setForeground(Color.black);
                    button1.setBackground(Color.blue);
                    button1.addActionListener(e -> CONTACTbt(e));
                    panel19.add(button1);
                    button1.setBounds(85, 200, 110, 35);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel19.getComponentCount(); i++) {
                            Rectangle bounds = panel19.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel19.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel19.setMinimumSize(preferredSize);
                        panel19.setPreferredSize(preferredSize);
                    }
                }
                home.add(panel19);
                panel19.setBounds(835, 350, 275, 240);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < home.getComponentCount(); i++) {
                        Rectangle bounds = home.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = home.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    home.setMinimumSize(preferredSize);
                    home.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("home", home);

            //======== panel4 ========
            {
                panel4.setBackground(Color.white);
                panel4.setLayout(null);

                //======== panel5 ========
                {
                    panel5.setLayout(null);

                    //======== panel8 ========
                    {
                        panel8.setBackground(new Color(153, 255, 153));
                        panel8.setLayout(null);

                        //---- label6 ----
                        label6.setText("9.99 $/MONTH");
                        label6.setForeground(Color.black);
                        label6.setFont(new Font("Roboto Thin", Font.PLAIN, 36));
                        panel8.add(label6);
                        label6.setBounds(25, 10, 260, 70);

                        //---- label7 ----
                        label7.setText("30MBPS");
                        label7.setFont(new Font("Roboto Thin", Font.PLAIN, 35));
                        label7.setForeground(Color.black);
                        panel8.add(label7);
                        label7.setBounds(85, 90, 148, 47);

                        //======== panel14 ========
                        {
                            panel14.setBackground(new Color(0, 204, 51));
                            panel14.setLayout(null);

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
                        panel8.add(panel14);
                        panel14.setBounds(50, 150, 210, 5);

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
                    panel5.add(panel8);
                    panel8.setBounds(0, 86, 303, 155);

                    //---- label3 ----
                    label3.setText("INTERNET15");
                    label3.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 28));
                    panel5.add(label3);
                    label3.setBounds(45, 6, 211, 74);

                    //======== panel9 ========
                    {
                        panel9.setBackground(new Color(204, 204, 204));
                        panel9.setLayout(null);

                        //---- label8 ----
                        label8.setText("Suitable for individuals and households");
                        label8.setIcon(new ImageIcon(getClass().getResource("/MN2/img/user.png")));
                        label8.setForeground(Color.black);
                        label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 4f));
                        panel9.add(label8);
                        label8.setBounds(0, 15, 305, 40);

                        //---- label9 ----
                        label9.setText("Download/Upload Speed: 30Mbps");
                        label9.setIcon(new ImageIcon(getClass().getResource("/MN2/img/icon-backup (1).png")));
                        label9.setForeground(Color.black);
                        label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 4f));
                        panel9.add(label9);
                        label9.setBounds(0, 60, 304, 40);

                        //---- label10 ----
                        label10.setText("Quick installion within 48 hours");
                        label10.setForeground(Color.black);
                        label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 4f));
                        panel9.add(label10);
                        label10.setBounds(35, 105, 265, 35);

                        //---- label11 ----
                        label11.setText("24/7 technical support");
                        label11.setForeground(Color.black);
                        label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 4f));
                        panel9.add(label11);
                        label11.setBounds(35, 135, 270, 31);

                        //---- label12 ----
                        label12.setIcon(new ImageIcon(getClass().getResource("/MN2/img/R (3) (1).png")));
                        panel9.add(label12);
                        label12.setBounds(0, 105, 40, 60);

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
                    panel5.add(panel9);
                    panel9.setBounds(0, 240, 303, 240);

                    //---- button5 ----
                    button5.setText("Register Now");
                    button5.setBackground(new Color(255, 153, 51));
                    panel5.add(button5);
                    button5.setBounds(90, 490, 135, 45);

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
                panel4.add(panel5);
                panel5.setBounds(55, 35, 303, 545);

                //======== panel6 ========
                {
                    panel6.setLayout(null);

                    //======== panel10 ========
                    {
                        panel10.setBackground(new Color(153, 153, 255));
                        panel10.setLayout(null);

                        //---- label13 ----
                        label13.setText("19.99 $/MONTH");
                        label13.setForeground(Color.black);
                        label13.setFont(new Font("Roboto Thin", Font.PLAIN, 36));
                        panel10.add(label13);
                        label13.setBounds(20, 5, 260, 70);

                        //---- label14 ----
                        label14.setText("80MBPS");
                        label14.setFont(new Font("Roboto Thin", Font.PLAIN, 35));
                        label14.setForeground(Color.black);
                        panel10.add(label14);
                        label14.setBounds(75, 80, 148, 47);

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
                    panel6.add(panel10);
                    panel10.setBounds(0, 86, 303, 155);

                    //---- label4 ----
                    label4.setText("INTERNET30");
                    label4.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 28));
                    panel6.add(label4);
                    label4.setBounds(45, 6, 211, 74);

                    //======== panel11 ========
                    {
                        panel11.setBackground(new Color(204, 204, 204));
                        panel11.setLayout(null);

                        //---- label39 ----
                        label39.setText("Suitable for individuals and households");
                        label39.setIcon(new ImageIcon(getClass().getResource("/MN2/img/user.png")));
                        label39.setForeground(Color.black);
                        label39.setFont(label39.getFont().deriveFont(label39.getFont().getSize() + 4f));
                        panel11.add(label39);
                        label39.setBounds(0, 15, 305, 40);

                        //---- label40 ----
                        label40.setText("Download/Upload Speed: 80Mbps");
                        label40.setIcon(new ImageIcon(getClass().getResource("/MN2/img/icon-backup (1).png")));
                        label40.setForeground(Color.black);
                        label40.setFont(label40.getFont().deriveFont(label40.getFont().getSize() + 4f));
                        panel11.add(label40);
                        label40.setBounds(0, 60, 304, 40);

                        //---- label41 ----
                        label41.setText("Quick installion within 48 hours");
                        label41.setForeground(Color.black);
                        label41.setFont(label41.getFont().deriveFont(label41.getFont().getSize() + 4f));
                        panel11.add(label41);
                        label41.setBounds(40, 105, 265, 35);

                        //---- label42 ----
                        label42.setText("24/7 technical support");
                        label42.setForeground(Color.black);
                        label42.setFont(label42.getFont().deriveFont(label42.getFont().getSize() + 4f));
                        panel11.add(label42);
                        label42.setBounds(35, 135, 270, 31);

                        //---- label43 ----
                        label43.setIcon(new ImageIcon(getClass().getResource("/MN2/img/R (3) (1).png")));
                        panel11.add(label43);
                        label43.setBounds(0, 105, 40, 60);

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
                    panel6.add(panel11);
                    panel11.setBounds(0, 240, 303, 240);

                    //---- button6 ----
                    button6.setText("Register Now");
                    button6.setBackground(new Color(255, 153, 51));
                    panel6.add(button6);
                    button6.setBounds(85, 490, 135, 45);

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
                panel6.setBounds(455, 35, 303, 545);

                //======== panel7 ========
                {
                    panel7.setLayout(null);

                    //======== panel12 ========
                    {
                        panel12.setBackground(new Color(255, 153, 153));
                        panel12.setLayout(null);

                        //---- label16 ----
                        label16.setText("49.99 $/MONTH");
                        label16.setForeground(Color.black);
                        label16.setFont(new Font("Roboto Thin", Font.PLAIN, 36));
                        panel12.add(label16);
                        label16.setBounds(20, 5, 260, 70);

                        //---- label17 ----
                        label17.setText("150MBPS");
                        label17.setFont(new Font("Roboto Thin", Font.PLAIN, 35));
                        label17.setForeground(Color.black);
                        panel12.add(label17);
                        label17.setBounds(80, 80, 165, 47);

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
                    panel7.add(panel12);
                    panel12.setBounds(0, 86, 303, 155);

                    //---- label5 ----
                    label5.setText("INTERNET45");
                    label5.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 28));
                    panel7.add(label5);
                    label5.setBounds(45, 6, 211, 74);

                    //======== panel13 ========
                    {
                        panel13.setBackground(new Color(204, 204, 204));
                        panel13.setLayout(null);

                        //---- label44 ----
                        label44.setText("Suitable for individuals and households");
                        label44.setIcon(new ImageIcon(getClass().getResource("/MN2/img/user.png")));
                        label44.setForeground(Color.black);
                        label44.setFont(label44.getFont().deriveFont(label44.getFont().getSize() + 4f));
                        panel13.add(label44);
                        label44.setBounds(0, 15, 305, 40);

                        //---- label45 ----
                        label45.setText("Download/Upload Speed: 150Mbps");
                        label45.setIcon(new ImageIcon(getClass().getResource("/MN2/img/icon-backup (1).png")));
                        label45.setForeground(Color.black);
                        label45.setFont(label45.getFont().deriveFont(label45.getFont().getSize() + 4f));
                        panel13.add(label45);
                        label45.setBounds(0, 60, 304, 40);

                        //---- label46 ----
                        label46.setText("Quick installion within 48 hours");
                        label46.setForeground(Color.black);
                        label46.setFont(label46.getFont().deriveFont(label46.getFont().getSize() + 4f));
                        panel13.add(label46);
                        label46.setBounds(35, 105, 265, 35);

                        //---- label47 ----
                        label47.setText("24/7 technical support");
                        label47.setForeground(Color.black);
                        label47.setFont(label47.getFont().deriveFont(label47.getFont().getSize() + 4f));
                        panel13.add(label47);
                        label47.setBounds(35, 135, 270, 31);

                        //---- label48 ----
                        label48.setIcon(new ImageIcon(getClass().getResource("/MN2/img/R (3) (1).png")));
                        panel13.add(label48);
                        label48.setBounds(0, 105, 40, 60);

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
                    panel7.add(panel13);
                    panel13.setBounds(0, 240, 303, 240);

                    //---- button7 ----
                    button7.setText("Register Now");
                    button7.setBackground(new Color(255, 153, 51));
                    panel7.add(button7);
                    button7.setBounds(95, 490, 135, 45);

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
                panel4.add(panel7);
                panel7.setBounds(845, 30, 303, 545);

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
            tabbedPane1.addTab(" Packages", panel4);

            //======== pro ========
            {
                pro.setBackground(new Color(238, 238, 238));
                pro.setLayout(null);

                //======== charlinepn ========
                {
                    charlinepn.setBackground(Color.lightGray);
                    charlinepn.setLayout(new BorderLayout());
                }
                pro.add(charlinepn);
                charlinepn.setBounds(35, 15, 555, 240);

                //======== scrollPane1 ========
                {
                    scrollPane1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            serviceclick(e);
                        }
                    });

                    //---- servicestb ----
                    servicestb.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            servicestbMouseClicked(e);
                            servicestbMouseClicked(e);
                        }
                    });
                    scrollPane1.setViewportView(servicestb);
                }
                pro.add(scrollPane1);
                scrollPane1.setBounds(10, 260, 1155, 352);

                //---- serviceText ----
                serviceText.setForeground(Color.black);
                serviceText.setBackground(Color.white);
                pro.add(serviceText);
                serviceText.setBounds(725, 205, 275, 40);

                //---- button11 ----
                button11.setText("Search");
                button11.setForeground(Color.black);
                button11.setFont(button11.getFont().deriveFont(button11.getFont().getSize() + 2f));
                button11.addActionListener(e -> searchbt(e));
                pro.add(button11);
                button11.setBounds(1005, 210, 95, 30);

                //---- button12 ----
                button12.setDefaultCapable(false);
                button12.setContentAreaFilled(false);
                button12.setBorderPainted(false);
                button12.setSelectedIcon(new ImageIcon(getClass().getResource("/KTCK/img/reload.png")));
                button12.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/R (11) (1).png")));
                button12.addActionListener(e -> reloadbt(e));
                pro.add(button12);
                button12.setBounds(1105, 195, 60, 55);

                //---- s1 ----
                s1.setBackground(Color.white);
                s1.setForeground(Color.black);
                s1.setFont(s1.getFont().deriveFont(s1.getFont().getSize() + 2f));
                s1.setText("SERVICECODE");
                pro.add(s1);
                s1.setBounds(655, 15, 200, 40);

                //---- s2 ----
                s2.setBackground(Color.white);
                s2.setForeground(Color.black);
                s2.setFont(s2.getFont().deriveFont(s2.getFont().getSize() + 2f));
                s2.setText("SERVICENAME");
                pro.add(s2);
                s2.setBounds(935, 15, 200, 40);

                //---- s3 ----
                s3.setBackground(Color.white);
                s3.setForeground(Color.black);
                s3.setFont(s3.getFont().deriveFont(s3.getFont().getSize() + 2f));
                s3.setText("PRICE");
                pro.add(s3);
                s3.setBounds(655, 80, 200, 40);

                //---- s4 ----
                s4.setBackground(Color.white);
                s4.setForeground(Color.black);
                s4.setFont(s4.getFont().deriveFont(s4.getFont().getSize() + 2f));
                s4.setText("INFORMATION");
                pro.add(s4);
                s4.setBounds(935, 80, 200, 40);

                //---- button18 ----
                button18.setText("ADD");
                button18.setBackground(new Color(255, 204, 204));
                button18.addActionListener(e -> addservicebt(e));
                pro.add(button18);
                button18.setBounds(675, 150, 110, 35);

                //---- button19 ----
                button19.setText("DELETE");
                button19.setBackground(new Color(255, 204, 204));
                button19.addActionListener(e -> deleteservicebt(e));
                pro.add(button19);
                button19.setBounds(845, 150, 110, 35);

                //---- button20 ----
                button20.setText("EDIT");
                button20.setBackground(new Color(255, 204, 204));
                button20.addActionListener(e -> editservicebt(e));
                pro.add(button20);
                button20.setBounds(1010, 145, 110, 35);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < pro.getComponentCount(); i++) {
                        Rectangle bounds = pro.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = pro.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    pro.setMinimumSize(preferredSize);
                    pro.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("text", pro);

            //======== payment ========
            {
                payment.setBackground(new Color(255, 204, 204));
                payment.setLayout(null);

                //======== chartbar ========
                {
                    chartbar.setLayout(new BorderLayout());
                }
                payment.add(chartbar);
                chartbar.setBounds(30, 20, 700, 275);

                //======== linebar ========
                {
                    linebar.setLayout(new BorderLayout());
                }
                payment.add(linebar);
                linebar.setBounds(30, 305, 1140, 310);

                //======== panel28 ========
                {
                    panel28.setBackground(Color.white);
                    panel28.setLayout(null);

                    //---- label72 ----
                    label72.setText("1.5GB");
                    label72.setForeground(Color.black);
                    label72.setFont(new Font("SimSun-ExtB", Font.PLAIN, 25));
                    panel28.add(label72);
                    label72.setBounds(275, 120, 70, 30);

                    //---- label33 ----
                    label33.setText("Data traffic");
                    label33.setFont(label33.getFont().deriveFont(label33.getFont().getSize() + 5f));
                    label33.setForeground(Color.black);
                    panel28.add(label33);
                    label33.setBounds(15, 10, 140, label33.getPreferredSize().height);

                    //---- label67 ----
                    label67.setText("Upload ");
                    label67.setForeground(Color.red);
                    label67.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                    label67.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/R (16) (1).png")));
                    panel28.add(label67);
                    label67.setBounds(15, 100, 140, 36);

                    //---- label70 ----
                    label70.setText("Dowload");
                    label70.setForeground(Color.blue);
                    label70.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                    label70.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/R (16)cc.png")));
                    panel28.add(label70);
                    label70.setBounds(15, 140, 145, 31);

                    //---- label71 ----
                    label71.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/384-3847306_gradient-circle-png-blue-gradient-circle-transparent-clipart (1).png")));
                    panel28.add(label71);
                    label71.setBounds(new Rectangle(new Point(250, 75), label71.getPreferredSize()));

                    //---- label74 ----
                    label74.setText("800MB");
                    label74.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
                    label74.setForeground(Color.black);
                    panel28.add(label74);
                    label74.setBounds(150, 105, 90, label74.getPreferredSize().height);

                    //---- label75 ----
                    label75.setText("700MB");
                    label75.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
                    label75.setForeground(Color.black);
                    panel28.add(label75);
                    label75.setBounds(150, 140, 90, 29);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel28.getComponentCount(); i++) {
                            Rectangle bounds = panel28.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel28.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel28.setMinimumSize(preferredSize);
                        panel28.setPreferredSize(preferredSize);
                    }
                }
                payment.add(panel28);
                panel28.setBounds(755, 20, 415, 275);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < payment.getComponentCount(); i++) {
                        Rectangle bounds = payment.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = payment.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    payment.setMinimumSize(preferredSize);
                    payment.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("text", payment);

            //======== usermanager ========
            {
                usermanager.setBackground(Color.white);
                usermanager.setLayout(null);

                //======== scrollPane2 ========
                {
                    scrollPane2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            selected(e);
                            selectedTB(e);
                        }
                    });

                    //---- usermanagertb ----
                    usermanagertb.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            usermanagertbMouseClicked(e);
                        }
                    });
                    scrollPane2.setViewportView(usermanagertb);
                }
                usermanager.add(scrollPane2);
                scrollPane2.setBounds(25, 290, 1145, 320);

                //---- button8 ----
                button8.setText("ADD");
                button8.setForeground(Color.black);
                button8.setFont(button8.getFont().deriveFont(button8.getFont().getSize() + 2f));
                button8.setContentAreaFilled(false);
                button8.addActionListener(e -> addbt(e));
                usermanager.add(button8);
                button8.setBounds(225, 215, 155, 50);

                //---- button9 ----
                button9.setText("DELETE");
                button9.setFont(button9.getFont().deriveFont(button9.getFont().getSize() + 2f));
                button9.setForeground(Color.black);
                button9.setContentAreaFilled(false);
                button9.addActionListener(e -> deletebt(e));
                button9.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button9MouseClicked(e);
                    }
                });
                usermanager.add(button9);
                button9.setBounds(440, 215, 155, 55);

                //---- button10 ----
                button10.setText("EDIT");
                button10.setFont(button10.getFont().deriveFont(button10.getFont().getSize() + 2f));
                button10.setForeground(Color.black);
                button10.setContentAreaFilled(false);
                button10.addActionListener(e -> editbt(e));
                usermanager.add(button10);
                button10.setBounds(655, 215, 155, 55);

                //---- usercode ----
                usercode.setForeground(Color.black);
                usercode.setFont(usercode.getFont().deriveFont(usercode.getFont().getSize() + 3f));
                usercode.setBackground(Color.white);
                usermanager.add(usercode);
                usercode.setBounds(225, 25, 295, 50);

                //---- address ----
                address.setForeground(Color.black);
                address.setFont(address.getFont().deriveFont(address.getFont().getSize() + 3f));
                address.setBackground(Color.white);
                usermanager.add(address);
                address.setBounds(670, 25, 295, 50);

                //---- name ----
                name.setForeground(Color.black);
                name.setFont(name.getFont().deriveFont(name.getFont().getSize() + 3f));
                name.setBackground(Color.white);
                usermanager.add(name);
                name.setBounds(225, 100, 295, 50);

                //---- phone ----
                phone.setForeground(Color.black);
                phone.setFont(phone.getFont().deriveFont(phone.getFont().getSize() + 3f));
                phone.setBackground(Color.white);
                usermanager.add(phone);
                phone.setBounds(670, 95, 295, 50);

                //---- label53 ----
                label53.setText("UserCode");
                label53.setForeground(Color.black);
                label53.setFont(label53.getFont().deriveFont(label53.getFont().getSize() + 6f));
                label53.setHorizontalAlignment(SwingConstants.CENTER);
                usermanager.add(label53);
                label53.setBounds(125, 30, 95, 40);

                //---- label54 ----
                label54.setText("Name");
                label54.setForeground(Color.black);
                label54.setFont(label54.getFont().deriveFont(label54.getFont().getSize() + 6f));
                label54.setHorizontalAlignment(SwingConstants.CENTER);
                usermanager.add(label54);
                label54.setBounds(125, 105, 95, 40);

                //---- label55 ----
                label55.setText("Address");
                label55.setForeground(Color.black);
                label55.setFont(label55.getFont().deriveFont(label55.getFont().getSize() + 6f));
                label55.setHorizontalAlignment(SwingConstants.CENTER);
                usermanager.add(label55);
                label55.setBounds(570, 30, 95, 40);

                //---- label56 ----
                label56.setText("Phone");
                label56.setForeground(Color.black);
                label56.setFont(label56.getFont().deriveFont(label56.getFont().getSize() + 6f));
                label56.setHorizontalAlignment(SwingConstants.CENTER);
                usermanager.add(label56);
                label56.setBounds(570, 100, 95, 40);

                //---- comboBox1 ----
                comboBox1.setBackground(Color.white);
                comboBox1.setForeground(Color.black);
                comboBox1.setFont(comboBox1.getFont().deriveFont(comboBox1.getFont().getSize() + 4f));
                usermanager.add(comboBox1);
                comboBox1.setBounds(675, 155, 290, 40);

                //---- label57 ----
                label57.setText("Service");
                label57.setForeground(Color.black);
                label57.setFont(label57.getFont().deriveFont(label57.getFont().getSize() + 6f));
                label57.setHorizontalAlignment(SwingConstants.CENTER);
                usermanager.add(label57);
                label57.setBounds(575, 155, 95, 40);

                //---- CLEARBT ----
                CLEARBT.setText("CLEAR");
                CLEARBT.setForeground(Color.black);
                CLEARBT.setFont(CLEARBT.getFont().deriveFont(CLEARBT.getFont().getSize() + 2f));
                CLEARBT.setContentAreaFilled(false);
                CLEARBT.addActionListener(e -> {
			addbt(e);
			CLEARBT(e);
		});
                usermanager.add(CLEARBT);
                CLEARBT.setBounds(850, 215, 155, 50);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < usermanager.getComponentCount(); i++) {
                        Rectangle bounds = usermanager.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = usermanager.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    usermanager.setMinimumSize(preferredSize);
                    usermanager.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("text", usermanager);

            //======== transaction ========
            {
                transaction.setBackground(Color.white);
                transaction.setLayout(null);

                //---- button3 ----
                button3.setText("Pay");
                button3.setForeground(Color.black);
                button3.setContentAreaFilled(false);
                button3.setFont(button3.getFont().deriveFont(button3.getFont().getSize() + 10f));
                button3.addActionListener(e -> paybt(e));
                transaction.add(button3);
                button3.setBounds(940, 535, 155, 45);

                //======== scrollPane3 ========
                {

                    //---- trantb ----
                    trantb.setEnabled(false);
                    scrollPane3.setViewportView(trantb);
                }
                transaction.add(scrollPane3);
                scrollPane3.setBounds(30, 315, 820, 270);

                //======== panel22 ========
                {
                    panel22.setBackground(new Color(204, 204, 255));
                    panel22.setLayout(null);

                    //---- label32 ----
                    label32.setText("TOTAL");
                    label32.setForeground(Color.white);
                    label32.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(label32);
                    label32.setBounds(20, 365, 70, 30);

                    //---- label34 ----
                    label34.setText("ID");
                    label34.setForeground(Color.white);
                    label34.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(label34);
                    label34.setBounds(10, 20, 70, label34.getPreferredSize().height);

                    //---- label35 ----
                    label35.setText("SERVICES");
                    label35.setForeground(Color.white);
                    label35.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(label35);
                    label35.setBounds(10, 130, 100, label35.getPreferredSize().height);

                    //---- label36 ----
                    label36.setText("VAT");
                    label36.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    label36.setForeground(Color.white);
                    panel22.add(label36);
                    label36.setBounds(10, 165, 80, label36.getPreferredSize().height);

                    //---- label37 ----
                    label37.setText("NAME");
                    label37.setForeground(Color.white);
                    label37.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(label37);
                    label37.setBounds(10, 55, 75, 27);

                    //---- label38 ----
                    label38.setText("PHONE");
                    label38.setForeground(Color.white);
                    label38.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(label38);
                    label38.setBounds(10, 95, 85, 27);

                    //---- IDTEXT ----
                    IDTEXT.setText("ID");
                    IDTEXT.setForeground(Color.white);
                    IDTEXT.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(IDTEXT);
                    IDTEXT.setBounds(115, 20, 175, 27);

                    //---- NAMETEXT ----
                    NAMETEXT.setText("ID");
                    NAMETEXT.setForeground(Color.white);
                    NAMETEXT.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(NAMETEXT);
                    NAMETEXT.setBounds(115, 55, 170, 27);

                    //---- PHONETEXT ----
                    PHONETEXT.setText("ID");
                    PHONETEXT.setForeground(Color.white);
                    PHONETEXT.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(PHONETEXT);
                    PHONETEXT.setBounds(115, 95, 175, 27);

                    //---- SERVICETEXT ----
                    SERVICETEXT.setText("ID");
                    SERVICETEXT.setForeground(Color.white);
                    SERVICETEXT.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(SERVICETEXT);
                    SERVICETEXT.setBounds(115, 130, 165, 27);

                    //---- VAXTEXT ----
                    VAXTEXT.setText("ID");
                    VAXTEXT.setForeground(Color.white);
                    VAXTEXT.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(VAXTEXT);
                    VAXTEXT.setBounds(115, 165, 175, 27);

                    //---- TOTALTEXT ----
                    TOTALTEXT.setText("ID");
                    TOTALTEXT.setForeground(Color.white);
                    TOTALTEXT.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(TOTALTEXT);
                    TOTALTEXT.setBounds(115, 365, 175, 27);

                    //---- PRICETEXT1 ----
                    PRICETEXT1.setText("ID");
                    PRICETEXT1.setForeground(Color.white);
                    PRICETEXT1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    panel22.add(PRICETEXT1);
                    PRICETEXT1.setBounds(115, 200, 175, 27);

                    //---- PRICETEXT ----
                    PRICETEXT.setText("PRICE");
                    PRICETEXT.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
                    PRICETEXT.setForeground(Color.white);
                    panel22.add(PRICETEXT);
                    PRICETEXT.setBounds(10, 200, 80, 27);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel22.getComponentCount(); i++) {
                            Rectangle bounds = panel22.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel22.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel22.setMinimumSize(preferredSize);
                        panel22.setPreferredSize(preferredSize);
                    }
                }
                transaction.add(panel22);
                panel22.setBounds(875, 100, 290, 420);

                //======== panel23 ========
                {
                    panel23.setBackground(new Color(0, 204, 102));
                    panel23.setLayout(null);

                    //======== panel26 ========
                    {
                        panel26.setBackground(new Color(0, 102, 51));
                        panel26.setLayout(null);
                        panel26.add(label66);
                        label66.setBounds(new Rectangle(new Point(20, 20), label66.getPreferredSize()));

                        //---- label64 ----
                        label64.setText("BALANCE");
                        label64.setForeground(Color.white);
                        label64.setFont(label64.getFont().deriveFont(label64.getFont().getSize() + 13f));
                        panel26.add(label64);
                        label64.setBounds(5, 5, 165, 50);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel26.getComponentCount(); i++) {
                                Rectangle bounds = panel26.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel26.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel26.setMinimumSize(preferredSize);
                            panel26.setPreferredSize(preferredSize);
                        }
                    }
                    panel23.add(panel26);
                    panel26.setBounds(0, 0, 390, 65);

                    //---- balancetext ----
                    balancetext.setText("7,898,50 ");
                    balancetext.setForeground(Color.white);
                    balancetext.setFont(new Font("Bahnschrift", Font.PLAIN, 48));
                    panel23.add(balancetext);
                    balancetext.setBounds(60, 90, 300, 55);

                    //---- button16 ----
                    button16.setText("WITHDRAW");
                    button16.setForeground(Color.white);
                    button16.setFont(button16.getFont().deriveFont(button16.getFont().getStyle() | Font.BOLD, button16.getFont().getSize() + 6f));
                    button16.setBackground(Color.white);
                    button16.setContentAreaFilled(false);
                    panel23.add(button16);
                    button16.setBounds(20, 175, 145, 55);

                    //---- button17 ----
                    button17.setText("DEPOSIT ");
                    button17.setForeground(Color.white);
                    button17.setFont(button17.getFont().deriveFont(button17.getFont().getStyle() | Font.BOLD, button17.getFont().getSize() + 6f));
                    button17.setBackground(Color.white);
                    button17.setContentAreaFilled(false);
                    panel23.add(button17);
                    button17.setBounds(205, 175, 150, 55);

                    //---- balancetext2 ----
                    balancetext2.setText("$");
                    balancetext2.setForeground(Color.white);
                    balancetext2.setFont(new Font("Bahnschrift", Font.PLAIN, 48));
                    panel23.add(balancetext2);
                    balancetext2.setBounds(25, 90, 60, 55);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel23.getComponentCount(); i++) {
                            Rectangle bounds = panel23.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel23.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel23.setMinimumSize(preferredSize);
                        panel23.setPreferredSize(preferredSize);
                    }
                }
                transaction.add(panel23);
                panel23.setBounds(35, 30, 390, 250);

                //======== panel24 ========
                {
                    panel24.setBackground(new Color(153, 0, 153));
                    panel24.setLayout(null);

                    //======== panel27 ========
                    {
                        panel27.setBackground(new Color(102, 0, 102));
                        panel27.setLayout(null);

                        //---- label31 ----
                        label31.setText("PAYMENTS");
                        label31.setForeground(Color.white);
                        label31.setFont(label31.getFont().deriveFont(label31.getFont().getSize() + 13f));
                        panel27.add(label31);
                        label31.setBounds(5, 5, 155, 50);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel27.getComponentCount(); i++) {
                                Rectangle bounds = panel27.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel27.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel27.setMinimumSize(preferredSize);
                            panel27.setPreferredSize(preferredSize);
                        }
                    }
                    panel24.add(panel27);
                    panel27.setBounds(0, 0, 390, 65);

                    //---- vaxtext ----
                    vaxtext.setText("80,5 ");
                    vaxtext.setForeground(Color.white);
                    vaxtext.setFont(new Font("Bahnschrift", Font.PLAIN, 48));
                    panel24.add(vaxtext);
                    vaxtext.setBounds(55, 85, 310, 55);

                    //---- balancetext3 ----
                    balancetext3.setText("$");
                    balancetext3.setForeground(Color.white);
                    balancetext3.setFont(new Font("Bahnschrift", Font.PLAIN, 48));
                    panel24.add(balancetext3);
                    balancetext3.setBounds(20, 85, 60, 55);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel24.getComponentCount(); i++) {
                            Rectangle bounds = panel24.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel24.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel24.setMinimumSize(preferredSize);
                        panel24.setPreferredSize(preferredSize);
                    }
                }
                transaction.add(panel24);
                panel24.setBounds(460, 30, 390, 250);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < transaction.getComponentCount(); i++) {
                        Rectangle bounds = transaction.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = transaction.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    transaction.setMinimumSize(preferredSize);
                    transaction.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("text", transaction);

            //======== panel21 ========
            {
                panel21.setBackground(Color.white);
                panel21.setLayout(null);

                //---- label59 ----
                label59.setIcon(new ImageIcon(getClass().getResource("/MN2/img/2037099.png")));
                panel21.add(label59);
                label59.setBounds(new Rectangle(new Point(140, 10), label59.getPreferredSize()));

                //---- label60 ----
                label60.setText("Phone number : 09999999999");
                label60.setForeground(Color.black);
                label60.setFont(new Font("Sylfaen", Font.PLAIN, 22));
                panel21.add(label60);
                label60.setBounds(735, 80, 290, 40);

                //---- label61 ----
                label61.setText("Email :");
                label61.setForeground(Color.black);
                label61.setFont(new Font("Sylfaen", Font.PLAIN, 22));
                panel21.add(label61);
                label61.setBounds(735, 120, 145, 45);

                //---- label62 ----
                label62.setText("Social :");
                label62.setForeground(Color.black);
                label62.setFont(new Font("Sylfaen", Font.PLAIN, 22));
                panel21.add(label62);
                label62.setBounds(735, 160, 150, 40);

                //---- label68 ----
                label68.setText("Vku@gmail.vn");
                label68.setForeground(Color.black);
                label68.setFont(new Font("Sylfaen", Font.PLAIN, 22));
                panel21.add(label68);
                label68.setBounds(880, 115, 145, 45);

                //---- label69 ----
                label69.setForeground(Color.black);
                label69.setFont(new Font("Sylfaen", Font.PLAIN, 22));
                label69.setText("FB.COM");
                panel21.add(label69);
                label69.setBounds(880, 160, 145, 45);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel21.getComponentCount(); i++) {
                        Rectangle bounds = panel21.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel21.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel21.setMinimumSize(preferredSize);
                    panel21.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("text", panel21);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(170, 15, 1185, 657);

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
    // Generated using JFormDesigner Evaluation license - hoang
    private JPanel panel1;
    private JPanel panel15;
    private JLabel label15;
    private JPanel panel25;
    private JLabel usernameshow;
    private JLabel showacc;
    private JButton button21;
    private JButton homebt;
    private JButton Servicesbt;
    private JButton homebt2;
    private JButton logout;
    private JButton homebt4;
    private JButton homebt5;
    private JButton homebt6;
    private JLabel label1;
    private JLabel label73;
    private JLabel label2;
    private JTabbedPane tabbedPane1;
    private JPanel home;
    private JPanel panel2;
    private JLabel label50;
    private JLabel label51;
    private JButton button14;
    private JLabel label52;
    private JPanel panel3;
    private JLabel label20;
    private JLabel label26;
    private JLabel label49;
    private JButton button13;
    private JPanel panel16;
    private JLabel label18;
    private JLabel label27;
    private JButton button4;
    private JLabel label58;
    private JPanel panel17;
    private JLabel label22;
    private JLabel label23;
    private JLabel label30;
    private JButton button2;
    private JPanel panel18;
    private JLabel label19;
    private JLabel label25;
    private JButton button15;
    private JLabel label63;
    private JPanel panel19;
    private JLabel label21;
    private JLabel label24;
    private JLabel label28;
    private JLabel label29;
    private JButton button1;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel8;
    private JLabel label6;
    private JLabel label7;
    private JPanel panel14;
    private JLabel label3;
    private JPanel panel9;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JButton button5;
    private JPanel panel6;
    private JPanel panel10;
    private JLabel label13;
    private JLabel label14;
    private JLabel label4;
    private JPanel panel11;
    private JLabel label39;
    private JLabel label40;
    private JLabel label41;
    private JLabel label42;
    private JLabel label43;
    private JButton button6;
    private JPanel panel7;
    private JPanel panel12;
    private JLabel label16;
    private JLabel label17;
    private JLabel label5;
    private JPanel panel13;
    private JLabel label44;
    private JLabel label45;
    private JLabel label46;
    private JLabel label47;
    private JLabel label48;
    private JButton button7;
    private JPanel pro;
    private JPanel charlinepn;
    private JScrollPane scrollPane1;
    private JTable servicestb;
    private JTextField serviceText;
    private JButton button11;
    private JButton button12;
    private JTextField s1;
    private JTextField s2;
    private JTextField s3;
    private JTextField s4;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JPanel payment;
    private JPanel chartbar;
    private JPanel linebar;
    private JPanel panel28;
    private JLabel label72;
    private JLabel label33;
    private JLabel label67;
    private JLabel label70;
    private JLabel label71;
    private JLabel label74;
    private JLabel label75;
    private JPanel usermanager;
    private JScrollPane scrollPane2;
    private JTable usermanagertb;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JTextField usercode;
    private JTextField address;
    private JTextField name;
    private JTextField phone;
    private JLabel label53;
    private JLabel label54;
    private JLabel label55;
    private JLabel label56;
    private JComboBox comboBox1;
    private JLabel label57;
    private JButton CLEARBT;
    private JPanel transaction;
    private JButton button3;
    private JScrollPane scrollPane3;
    private JTable trantb;
    private JPanel panel22;
    private JLabel label32;
    private JLabel label34;
    private JLabel label35;
    private JLabel label36;
    private JLabel label37;
    private JLabel label38;
    private JLabel IDTEXT;
    private JLabel NAMETEXT;
    private JLabel PHONETEXT;
    private JLabel SERVICETEXT;
    private JLabel VAXTEXT;
    private JLabel TOTALTEXT;
    private JLabel PRICETEXT1;
    private JLabel PRICETEXT;
    private JPanel panel23;
    private JPanel panel26;
    private JLabel label66;
    private JLabel label64;
    private JLabel balancetext;
    private JButton button16;
    private JButton button17;
    private JLabel balancetext2;
    private JPanel panel24;
    private JPanel panel27;
    private JLabel label31;
    private JLabel vaxtext;
    private JLabel balancetext3;
    private JPanel panel21;
    private JLabel label59;
    private JLabel label60;
    private JLabel label61;
    private JLabel label62;
    private JLabel label68;
    private JLabel label69;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
