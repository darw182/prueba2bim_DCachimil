import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormularioPacientes {
    private JTextField cedText;
    private JTextField histText;
    private JTextField nombreText;
    private JTextField apellText;
    private JTextField telText;
    private JTextField edadText;
    private JTextField descrEnfText;
    private JButton registrarBtn;
    private JButton busquedaBtn;
    private JPanel registroPanel;
    private Connection connection;
    private PreparedStatement preparedStatement;


    public FormularioPacientes() {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "123456";

        try {
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("Exito");
            String queryInsert = "insert into paciente values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryInsert);



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedText.getText();
                int historial= Integer.parseInt(histText.getText());
                String nombre = nombreText.getText();
                String apellido=apellText.getText();
                String telefono=telText.getText();
                int edad = Integer.parseInt(edadText.getText());
                String descr = descrEnfText.getText();

                try {
                    Registro registro = new Registro();
                    registro.setCedula(cedula);
                    registro.setHistorial(historial);
                    registro.setNombre(nombre);
                    registro.setApellido(apellido);
                    registro.setTelefono(telefono);
                    registro.setEdad(edad);
                    registro.setDescripcion(descr);

                    preparedStatement.setString(1,registro.getCedula());
                    preparedStatement.setInt(2,registro.getHistorial());
                    preparedStatement.setString(3,registro.getNombre());
                    preparedStatement.setString(4,registro.getApellido());
                    preparedStatement.setString(5,registro.getTelefono());
                    preparedStatement.setInt(6,registro.getEdad());
                    preparedStatement.setString(7,registro.getDescripcion());

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Datos ingresados con exito","Ingreso",JOptionPane.INFORMATION_MESSAGE);

                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
            }
        });
    }

    public JPanel getRegistroPanel() {
        return registroPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormularioPacientes");
        frame.setContentPane(new FormularioPacientes().registroPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
