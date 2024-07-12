import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    private JTextField userText;
    private JTextField passText;
    private JButton ingresarBtn;
    private JButton resetBtn;
    private JPanel mainPanel;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;


    public login(JFrame mainFrame){
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "123456";

        try {
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("Exito");
            //String queryInsert = "insert into info_usuario values(?,?,?,?,?,?)";
            //preparedStatement = connection.prepareStatement(queryInsert);
            String query = "select * from usuario";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String pass = passText.getText();

                try{
                    Usuario usuario = new Usuario(user,pass);

                    while (resultSet.next()){
                        String nombre = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        if (usuario.getUser().equals(nombre) && usuario.getPass().equals(password)){
                            //JOptionPane.showMessageDialog(null,"Usuario encontrado");

                           JFrame registroForm = new JFrame();
                            registroForm.setContentPane(new FormularioPacientes().getRegistroPanel());
                            registroForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            registroForm.pack();
                            registroForm.setVisible(true);
                            mainFrame.dispose();

                        }

                    }
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }


            }
        });
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
