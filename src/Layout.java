import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
  private JLabel lblCurso;
  private DefaultTableModel mdlCurso;
  private JTable tblCurso;
  private JScrollPane scrollPaneCurso;
  private JLabel lblFase;
  private DefaultTableModel mdlFase;
  private JTable tblFase;
  private JScrollPane scrollPaneFase;
  private JLabel lblDisciplina;
  private DefaultTableModel mdlDisciplina;
  private JTable tblDisciplina;
  private JScrollPane scrollPaneDisciplina;
  private JLabel lblProfessor;
  private DefaultTableModel mdlProfessor;
  private JTable tblProfessor;
  private JScrollPane scrollPaneProfessor;
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

    lblCurso = new JLabel("Cursos:");
    lblCurso.setBounds(20, 20, 300, 25);
    getContentPane().add(lblCurso);

    mdlCurso = new DefaultTableModel(data, columns);
    tblCurso = new JTable(mdlCurso);
    scrollPaneCurso = new JScrollPane(tblCurso);
    scrollPaneCurso.setBounds(20, 100, 845, 100);
    getContentPane().add(scrollPaneCurso);

    lblFase = new JLabel("Fases:");
    lblFase.setBounds(20, 20, 300, 25);
    getContentPane().add(lblFase);

    mdlFase = new DefaultTableModel(data, columns);
    tblFase = new JTable(mdlFase);
    scrollPaneFase = new JScrollPane(tblFase);
    scrollPaneFase.setBounds(20, 215, 845, 100);
    getContentPane().add(scrollPaneFase);

    lblDisciplina = new JLabel("Disciplinas:");
    lblDisciplina.setBounds(20, 20, 300, 25);
    getContentPane().add(lblDisciplina);

    mdlDisciplina = new DefaultTableModel(data, columns);
    tblDisciplina = new JTable(mdlDisciplina);
    scrollPaneDisciplina = new JScrollPane(tblDisciplina);
    scrollPaneDisciplina.setBounds(20, 330, 845, 100);
    getContentPane().add(scrollPaneDisciplina);

    lblProfessor = new JLabel("Professores:");
    lblProfessor.setBounds(20, 20, 300, 25);
    getContentPane().add(lblProfessor);

    mdlProfessor = new DefaultTableModel(data, columns);
    tblProfessor = new JTable(mdlProfessor);
    scrollPaneProfessor = new JScrollPane(tblProfessor);
    scrollPaneProfessor.setBounds(20, 445, 845, 100);
    getContentPane().add(scrollPaneProfessor);

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
