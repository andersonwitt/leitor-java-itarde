import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Layout extends JFrame {
  private JLabel lblChooser;
  private JFileChooser txfChooser;
  private JButton btnImportar;
  private JTextField txtCaminho;
  private FileNameExtensionFilter fneFilter;
  private JTextArea txaInfo1;
  private JTextArea txaInfo2;
  private JTextArea txaInfo3;
  private JTextArea txaInfo4;
  private JButton btnLimpar;
  private JButton btnInserir;

  public Layout() {
    setTitle("Leitor Java Itarde");
    setResizable(false);
    setSize(900, 680);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    ComponentesLayout();
    setVisible(true);
  }

  public void ComponentesLayout() {
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
    btnImportar.setBackground(Color.BLUE);
    btnImportar.setBorder(new LineBorder(Color.BLACK));
    getContentPane().add(btnImportar);

    // Dados
    Object[][] data = {
        { "214124124", "Anderson", "Pessoa" },
        { "2131231", "Douglas", "Pessoa" },
    };
    Object[] columns = { "Codigo", "Name", "Description" };

    // Tabela
    DefaultTableModel model = new DefaultTableModel(data, columns);
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(20, 100, 845, 100);
    getContentPane().add(scrollPane);

    btnLimpar = new JButton(new AbstractAction("Limpar campos") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnLimpar.setBounds(20, 560, 200, 40);
    btnLimpar.setBackground(Color.RED);
    btnLimpar.setBorder(new LineBorder(Color.BLACK));
    getContentPane().add(btnLimpar);

    btnInserir = new JButton(new AbstractAction("Inserir no banco") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnInserir.setBounds(663, 560, 200, 40);
    btnInserir.setBackground(Color.GREEN);
    btnInserir.setBorder(new LineBorder(Color.BLACK));
    getContentPane().add(btnInserir);
  }

}
