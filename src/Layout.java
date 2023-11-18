import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Layout extends JFrame {
  private JLabel lblChooser;
  private JFileChooser txfChooser;
  private JButton btnImportar;
  private JTextField txtCaminho;
  private FileNameExtensionFilter fneFilter;
  private JTextArea txaInfo1;
  private JTextArea txaInfo2;
  private JTextArea txaInfo3;
  private JButton btnInserir;

  public Layout() {
    setTitle("Leitor Java Itarde");
    setResizable(false);
    setSize(900, 800);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    ComponentesLoyout();
    setVisible(true);
  }

  public void ComponentesLoyout() {
    lblChooser = new JLabel("Importe seu arquivo para armazenar no banco:");
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
      }
    });
    btnImportar.setBounds(665, 60, 200, 25);
    getContentPane().add(btnImportar);

    txaInfo1 = new JTextArea();
    txaInfo1.setBounds(20, 100, 845, 200);
    txaInfo1.setEditable(false);
    getContentPane().add(txaInfo1);

    txaInfo2 = new JTextArea();
    txaInfo2.setBounds(20, 315, 845, 200);
    txaInfo2.setEditable(false);
    getContentPane().add(txaInfo2);

    txaInfo3 = new JTextArea();
    txaInfo3.setBounds(20, 530, 845, 200);
    txaInfo3.setEditable(false);
    getContentPane().add(txaInfo3);

    btnInserir = new JButton(new AbstractAction("Inserir no banco") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnInserir.setBounds(240, 740, 200, 25);
    getContentPane().add(btnInserir);
  }

}
