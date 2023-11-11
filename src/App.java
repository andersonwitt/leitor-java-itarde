import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class App extends JFrame {
    private JLabel lblUsuario;
    private JTextField txfUsuario;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JPasswordField psfSenha;
    private JButton btnEntrar;

    public App() {
        setTitle("Login do Cliente");
        setResizable(false);
        setSize(360, 360);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ComponentesLogin();
        setVisible(true);
    }

    public void ComponentesLogin() {
        lblLogin = new JLabel("LOGIN");
        lblLogin.setBounds(160, 20, 50, 25);
        getContentPane().add(lblLogin);

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(20, 60, 50, 25);
        getContentPane().add(lblUsuario);

        txfUsuario = new JTextField();
        txfUsuario.setBounds(80, 60, 200, 25);
        getContentPane().add(txfUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(28, 90, 50, 25);
        getContentPane().add(lblSenha);

        psfSenha = new JPasswordField();
        psfSenha.setBounds(80, 90, 200, 25);
        getContentPane().add(psfSenha);

        // btnEntrar = new JButton(new AbstractAction("Entrar") {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // if (txfUsuario.getText().equals("admin") &&
        // psfSenha.getPassword().equals("admin")) {
        // new Inicio();
        // dispose();
        // }
        // }
        // });
        //btnEntrar.setBounds(80, 120, 200, 25);
        //getContentPane().add(btnEntrar);
    }

 
}
