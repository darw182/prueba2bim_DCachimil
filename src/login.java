import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class login {
    private JTextField userText;
    private JTextField passText;
    private JButton ingresarBtn;
    private JButton resetBtn;
    private JPanel mainPanel;
    private Connection connection;
    private PreparedStatement preparedStatement;


    public login(){
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "123456";

        try {
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("Exito");
            //String queryInsert = "insert into info_usuario values(?,?,?,?,?,?)";
            //preparedStatement = connection.prepareStatement(queryInsert);



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
