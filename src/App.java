import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends JFrame {
  private JLabel lblChooser;
  private JFileChooser txfChooser;
  private JButton btnImportar;
  private JTextField txtCaminho;
  private FileNameExtensionFilter fneFilter;

  public App() {
    setTitle("Leitor Java Itarde");
    setResizable(false);
    setSize(900, 500);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    ComponentesLogin();
    setVisible(true);
  }

  public void ComponentesLogin() {
    lblChooser = new JLabel("Importe seu arquivo para inserir no banco:");
    lblChooser.setBounds(20, 20, 300, 25);
    getContentPane().add(lblChooser);

    txfChooser = new JFileChooser();
    txfChooser.setBounds(20, 60, 300, 400);

    fneFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    txfChooser.setFileFilter(fneFilter);

    txtCaminho = new JTextField();
    txtCaminho.setBounds(20, 60, 640, 25);
    txtCaminho.setEditable(false);
    getContentPane().add(txtCaminho);

    btnImportar = new JButton(new AbstractAction("Importar o arquivo") {
      @Override
      public void actionPerformed(ActionEvent e) {
        int retorno = -1;

        while (retorno == -1 || retorno == JFileChooser.APPROVE_OPTION) {
          retorno = txfChooser.showOpenDialog(null);
          if (retorno != JFileChooser.APPROVE_OPTION) {
            break;
          }
          File arquivoSelecionado = txfChooser.getSelectedFile();
          if (!arquivoSelecionado.getName().endsWith(".txt")) {
            JOptionPane.showMessageDialog(null, "O arquivo deve ser do tipo .txt");
          } else {
            txtCaminho.setText(txfChooser.getSelectedFile().getAbsolutePath());
            break;
          }
        }

        // if (retorno == JFileChooser.APPROVE_OPTION) {
        // while (!txfChooser.getSelectedFile().getName().endsWith(".txt") && retorno ==
        // JFileChooser.APPROVE_OPTION) {

        // File arquivoSelecionado = txfChooser.getSelectedFile();
        // if (!arquivoSelecionado.getName().endsWith(".txt")) {

        // JOptionPane.showMessageDialog(null, "O arquivo deve ser do tipo .txt");
        // retorno = txfChooser.showOpenDialog(null);
        // }
        // }
        // txtCaminho.setText(txfChooser.getSelectedFile().getAbsolutePath());
        // }

      }
    });
    btnImportar.setBounds(665, 60, 200, 25);
    getContentPane().add(btnImportar);
  }

}
