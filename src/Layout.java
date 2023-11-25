import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.ConnectionFactory;
import database.dao.CursosDAO;
import database.dao.DisciplinasDAO;
import database.dao.FasesDAO;
import database.dao.ProfessoresDAO;
import database.model.Curso;
import database.model.Disciplina;
import database.model.Fase;
import database.model.Professor;

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
  private JTableHeader headerCurso;
  private JTableHeader headerFase;
  private JTableHeader headerDisciplina;
  private JTableHeader headerProfessor;

  private LeitorResultado result = null;

  public Layout() {
    setTitle("Leitor Java Itarde");
    setResizable(false);
    setSize(900, 780);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    ComponentesLayout();
    setVisible(true);
  }

  public void colorHeaderTable(JTableHeader header) {
    header.setDefaultRenderer(new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
          int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(Color.decode("#4464AD"));
        setForeground(Color.WHITE);
        return this;
      }
    });
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

    // Dados
    Object[][] data = {};
    Object[] professoresColumns = { "Nome", "Título" };

    Object[] disciplinaColumns = { "Nome", "Dia da Semana", "Quantidade de Professores" };

    Object[] fasesColumns = { "Nome", "Quantidade de Disciplinas", "Quantidade de Professores" };

    Object[] cursosColumns = { "Nome", "Data de processamento", "Período Inicial", "Período Final",
        "Sequência", "Versão do Layout" };

    lblCurso = new JLabel("Cursos:");
    lblCurso.setBounds(20, 100, 300, 25);
    getContentPane().add(lblCurso);

    mdlCurso = new DefaultTableModel(data, cursosColumns);
    tblCurso = new JTable(mdlCurso);
    headerCurso = tblCurso.getTableHeader();
    colorHeaderTable(headerCurso);
    scrollPaneCurso = new JScrollPane(tblCurso);
    scrollPaneCurso.setBounds(20, 125, 845, 100);
    getContentPane().add(scrollPaneCurso);

    lblFase = new JLabel("Fases:");
    lblFase.setBounds(20, 230, 300, 25);
    getContentPane().add(lblFase);

    mdlFase = new DefaultTableModel(data, fasesColumns);
    tblFase = new JTable(mdlFase);
    headerFase = tblFase.getTableHeader();
    colorHeaderTable(headerFase);
    scrollPaneFase = new JScrollPane(tblFase);
    scrollPaneFase.setBounds(20, 255, 845, 100);
    getContentPane().add(scrollPaneFase);

    lblDisciplina = new JLabel("Disciplinas:");
    lblDisciplina.setBounds(20, 360, 300, 25);
    getContentPane().add(lblDisciplina);

    mdlDisciplina = new DefaultTableModel(data, disciplinaColumns);
    tblDisciplina = new JTable(mdlDisciplina);
    headerDisciplina = tblDisciplina.getTableHeader();
    colorHeaderTable(headerDisciplina);
    scrollPaneDisciplina = new JScrollPane(tblDisciplina);
    scrollPaneDisciplina.setBounds(20, 385, 845, 100);
    getContentPane().add(scrollPaneDisciplina);

    lblProfessor = new JLabel("Professores:");
    lblProfessor.setBounds(20, 490, 300, 25);
    getContentPane().add(lblProfessor);

    mdlProfessor = new DefaultTableModel(new Object[][] {}, professoresColumns);
    tblProfessor = new JTable(mdlProfessor);
    headerProfessor = tblProfessor.getTableHeader();
    colorHeaderTable(headerProfessor);
    scrollPaneProfessor = new JScrollPane(tblProfessor);
    scrollPaneProfessor.setBounds(20, 515, 845, 100);
    getContentPane().add(scrollPaneProfessor);

    btnLimpar = new JButton(new AbstractAction("Limpar campos") {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnLimpar.setBounds(20, 630, 200, 40);
    btnLimpar.setForeground(Color.WHITE);
    btnLimpar.setBackground(Color.decode("#CA3C25"));
    btnLimpar.setBorder(new LineBorder(Color.BLACK));
    getContentPane().add(btnLimpar);

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
            if (arquivoSelecionado != null) {
              result = new Leitor(arquivoSelecionado).GetTextFromFile();

              result.Cursos.forEach(item -> {
                mdlCurso.addRow(
                    new Object[] { item.getNome(), item.getDataProcessamento(), item.getPeriodoInicial(),
                        item.getPeriodoFinal(), item.getSequencia(), item.getVersaoLayout() });
              });

              result.Professores.forEach(item -> {
                mdlProfessor.addRow(new Object[] { item.getNome(), item.getTitulo() });
              });

              result.Fases.forEach(item -> {
                mdlFase
                    .addRow(new Object[] { item.getNome(), item.getQuantidadeDisciplinas(),
                        item.getQuantidadeProfessores() });
              });

              result.Disciplinas.forEach(item -> {
                mdlDisciplina
                    .addRow(new Object[] { item.getNome(), item.getDiaSemana(),
                        item.getQuantidadeProfessores() });
              });
            }
            break;
          }
        }
      }
    });
    btnImportar.setBounds(665, 60, 200, 25);
    btnImportar.setForeground(Color.WHITE);
    btnImportar.setBackground(Color.decode("#4464AD"));
    btnImportar.setBorder(new LineBorder(Color.BLACK));
    getContentPane().add(btnImportar);

    btnInserir = new JButton(new AbstractAction("Inserir no banco") {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          // Connection conexao = ConnectionFactory.getConnection("127.0.0.1", 3306,
          // "sistema", "root", "@Root");
          Connection conexao = ConnectionFactory.getConnection("26.100.201.167", 3306, "SistemaEscolar", "root", "unesc");
          if (conexao != null) {
            System.out.println("CONECTOU !!!!");

            CursosDAO cursosDao = new CursosDAO(conexao);
            FasesDAO fasesDao = new FasesDAO(conexao);
            DisciplinasDAO disciplinasDao = new DisciplinasDAO(conexao);
            ProfessoresDAO professoresDao = new ProfessoresDAO(conexao);

            for (Curso c : result.Cursos) {
              var cursoId = cursosDao.insert(c);
              for (Fase f : result.Fases) {
                f.setCursoId(cursoId);
                var faseId = fasesDao.insert(f);
              }
              for (Disciplina d : result.Disciplinas) {
                d.setCursoId(cursoId);
                var disciplinaId = disciplinasDao.insert(d);
              }
              for (Professor p : result.Professores) {
                p.setCursoId(cursoId);
                var professorId = professoresDao.insert(p);
              }
            }

          } else {
            System.out.println("NÃO CONECTOU !!!!");
          }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    });
    btnInserir.setBounds(663, 630, 200, 40);
    btnInserir.setForeground(Color.WHITE);
    btnInserir.setBackground(Color.decode("#7FB069"));
    btnInserir.setBorder(new LineBorder(Color.BLACK));
    getContentPane().add(btnInserir);
  }

}
