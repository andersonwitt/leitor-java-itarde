import database.ConnectionFactory;
import database.dao.CursosDAO;
import database.dao.DisciplinasDAO;
import database.dao.FasesDAO;
import database.dao.ProfessoresDAO;
import database.model.Curso;
import database.model.Disciplina;
import database.model.Fase;
import database.model.Professor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import utils.NumberUtils;

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
  private LeitorResultado dadosMapeados = null;
  private List<String> mensagensSucessoResultados = new ArrayList<>();
  private List<String> mensagensWarningResultados = new ArrayList<>();

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
    header.setDefaultRenderer(
      new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(
          JTable table,
          Object value,
          boolean isSelected,
          boolean hasFocus,
          int row,
          int column
        ) {
          super.getTableCellRendererComponent(
            table,
            value,
            isSelected,
            hasFocus,
            row,
            column
          );
          setBackground(Color.decode("#4464AD"));
          setForeground(Color.WHITE);
          return this;
        }
      }
    );
  }

  public void ComponentesLayout() {
    Object[][] dadosTabela = {};
    Object[] professoresColumns = { "Nome", "Título" };

    Object[] disciplinaColumns = {
      "Nome",
      "Dia da Semana",
      "Quantidade de Professores",
    };

    Object[] fasesColumns = {
      "Nome",
      "Quantidade de Disciplinas",
      "Quantidade de Professores",
    };

    Object[] cursosColumns = {
      "Nome",
      "Data de processamento",
      "Período Inicial",
      "Período Final",
      "Sequência",
      "Versão do Layout",
    };

    btnImportar =
      new JButton(
        new AbstractAction("Importar o arquivo") {
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
                String errorMessage = "O arquivo deve ser do tipo .txt";
                JOptionPane.showMessageDialog(
                  null,
                  errorMessage,
                  "Erro de Seleção",
                  JOptionPane.ERROR_MESSAGE
                );
              } else {
                txtCaminho.setText(
                  txfChooser.getSelectedFile().getAbsolutePath()
                );
                if (arquivoSelecionado != null) {
                  dadosMapeados =
                    new Leitor(arquivoSelecionado).GetTextFromFile();

                  dadosMapeados.Cursos.forEach(item -> {
                    mdlCurso.addRow(
                      new Object[] {
                        item.getNome(),
                        item.getDataProcessamento(),
                        item.getPeriodoInicial(),
                        item.getPeriodoFinal(),
                        item.getSequencia(),
                        item.getVersaoLayout(),
                      }
                    );
                  });

                  dadosMapeados.Professores.forEach(item -> {
                    mdlProfessor.addRow(
                      new Object[] { item.getNome(), item.getTitulo() }
                    );
                  });

                  dadosMapeados.Fases.forEach(item -> {
                    mdlFase.addRow(
                      new Object[] {
                        item.getNome(),
                        item.getQuantidadeDisciplinas(),
                        item.getQuantidadeProfessores(),
                      }
                    );
                  });

                  dadosMapeados.Disciplinas.forEach(item -> {
                    mdlDisciplina.addRow(
                      new Object[] {
                        item.getNome(),
                        item.getDiaSemana(),
                        item.getQuantidadeProfessores(),
                      }
                    );
                  });

                  btnInserir.setBackground(Color.decode("#7FB069"));
                  btnLimpar.setBackground(Color.decode("#CA3C25"));
                  btnLimpar.setEnabled(true);
                  btnInserir.setEnabled(true);
                }
                break;
              }
            }
          }
        }
      );
    btnImportar.setBounds(665, 60, 200, 25);
    btnImportar.setForeground(Color.WHITE);
    btnImportar.setBackground(Color.decode("#4464AD"));
    btnImportar.setBorder(new LineBorder(Color.BLACK));
    getContentPane().add(btnImportar);

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

    lblCurso = new JLabel("Cursos:");
    lblCurso.setBounds(20, 100, 300, 25);
    getContentPane().add(lblCurso);

    mdlCurso = new DefaultTableModel(dadosTabela, cursosColumns);

    tblCurso = new JTable(mdlCurso);
    tblCurso.setEnabled(false);

    headerCurso = tblCurso.getTableHeader();
    colorHeaderTable(headerCurso);
    scrollPaneCurso = new JScrollPane(tblCurso);
    scrollPaneCurso.setBounds(20, 125, 845, 100);
    getContentPane().add(scrollPaneCurso);

    lblFase = new JLabel("Fases:");
    lblFase.setBounds(20, 230, 300, 25);
    getContentPane().add(lblFase);

    mdlFase = new DefaultTableModel(dadosTabela, fasesColumns);

    tblFase = new JTable(mdlFase);
    tblFase.setEnabled(false);

    headerFase = tblFase.getTableHeader();
    colorHeaderTable(headerFase);
    scrollPaneFase = new JScrollPane(tblFase);
    scrollPaneFase.setBounds(20, 255, 845, 100);
    getContentPane().add(scrollPaneFase);

    lblDisciplina = new JLabel("Disciplinas:");
    lblDisciplina.setBounds(20, 360, 300, 25);
    getContentPane().add(lblDisciplina);

    mdlDisciplina = new DefaultTableModel(dadosTabela, disciplinaColumns);

    tblDisciplina = new JTable(mdlDisciplina);
    tblDisciplina.setEnabled(false);

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
    tblProfessor.setEnabled(false);

    headerProfessor = tblProfessor.getTableHeader();
    colorHeaderTable(headerProfessor);
    scrollPaneProfessor = new JScrollPane(tblProfessor);
    scrollPaneProfessor.setBounds(20, 515, 845, 100);
    getContentPane().add(scrollPaneProfessor);

    btnLimpar =
      new JButton(
        new AbstractAction("Limpar campos") {
          @Override
          public void actionPerformed(ActionEvent e) {
            mdlCurso.setRowCount(0);
            mdlProfessor.setRowCount(0);
            mdlFase.setRowCount(0);
            mdlDisciplina.setRowCount(0);
            txfChooser.setSelectedFile(null);
            txtCaminho.setText("");
            dadosMapeados = null;
            btnLimpar.setBackground(Color.decode("#CCCCCC"));
            btnInserir.setBackground(Color.decode("#CCCCCC"));
            btnInserir.setEnabled(false);
            btnLimpar.setEnabled(false);
          }
        }
      );
    btnLimpar.setBounds(20, 630, 200, 40);
    btnLimpar.setForeground(Color.WHITE);
    btnLimpar.setBackground(Color.decode("#CCCCCC"));
    btnLimpar.setBorder(new LineBorder(Color.BLACK));
    btnLimpar.setEnabled(false);
    getContentPane().add(btnLimpar);

    btnInserir =
      new JButton(
        new AbstractAction("Inserir no banco") {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Connection conexao = ConnectionFactory.getConnection(
                "26.100.201.167",
                3306,
                "SistemaEscolar",
                "root",
                "unesc"
              );
              if (conexao != null) {
                CursosDAO cursosDao = new CursosDAO(conexao);
                FasesDAO fasesDao = new FasesDAO(conexao);
                DisciplinasDAO disciplinasDao = new DisciplinasDAO(conexao);
                ProfessoresDAO professoresDao = new ProfessoresDAO(conexao);

                for (Curso c : dadosMapeados.Cursos) {
                  var cursoId = cursosDao.insert(c);

                  if (cursoId[0] == 0 && cursoId[1] >= 0) {
                    mensagensSucessoResultados.add(
                      c.getNome() + ", inserido com sucesso!"
                    );
                  } else {
                    mensagensWarningResultados.add(
                      c.getNome() + ", já existe no banco"
                    );
                  }

                  for (Fase f : dadosMapeados.Fases) {
                    f.setCursoId(cursoId[1]);
                    var retornoFase = fasesDao.insert(f);
                    if (!NumberUtils.IsNumber(retornoFase.toString())) {
                      mensagensWarningResultados.add(
                        f.getNome() + ", já existe no banco"
                      );
                    } else {
                      mensagensSucessoResultados.add(
                        f.getNome() + ", inserido com sucesso!"
                      );
                    }
                  }
                  for (Disciplina d : dadosMapeados.Disciplinas) {
                    d.setCursoId(cursoId[1]);
                    var retornoDisciplina = disciplinasDao.insert(d);
                    if (
                      NumberUtils.tryParseInt(retornoDisciplina.toString()) >= 0
                    ) {
                      mensagensWarningResultados.add(
                        d.getNome() + ", já existe no banco"
                      );
                    } else {
                      mensagensSucessoResultados.add(
                        d.getNome() + ", inserido com sucesso!"
                      );
                    }
                  }
                  for (Professor p : dadosMapeados.Professores) {
                    p.setCursoId(cursoId[1]);
                    var retornoProfessor = professoresDao.insert(p);
                    if (!NumberUtils.IsNumber(retornoProfessor.toString())) {
                      mensagensWarningResultados.add(
                        p.getNome() + ", já existe no banco"
                      );
                    } else {
                      mensagensSucessoResultados.add(
                        p.getNome() + ", inserido com sucesso!"
                      );
                    }
                  }
                }

                StringBuilder mensagem = new StringBuilder();
                StringBuilder mensagemSucesso = new StringBuilder();
                boolean existe = false;
                boolean existeSucesso = false;
                for (String mensagemResultado : mensagensWarningResultados) {
                  if (mensagemResultado.contains("existe")) {
                    existe = true;
                  }
                }

                for (String mensagemResultado : mensagensSucessoResultados) {
                  if (mensagemResultado.contains("inserido")) {
                    existeSucesso = true;
                  }
                }

                if ((existe && !existeSucesso) || (existe && existeSucesso)) {
                  if (existe) {
                    for (String mensagemResultado : mensagensWarningResultados) {
                      mensagem.append(mensagemResultado).append("\r\n");
                    }

                    JOptionPane.showMessageDialog(
                      null,
                      mensagem.toString(),
                      "Dados Repetidos",
                      JOptionPane.WARNING_MESSAGE
                    );
                  }
                  if (existeSucesso) {
                    for (String mensagemResultado : mensagensSucessoResultados) {
                      mensagemSucesso.append(mensagemResultado).append("\r\n");
                    }
                    JOptionPane.showMessageDialog(
                      null,
                      mensagemSucesso.toString(),
                      "Dados Salvos",
                      JOptionPane.INFORMATION_MESSAGE
                    );
                  }

                  mensagensSucessoResultados.clear();
                  mensagensWarningResultados.clear();
                } else {
                  mdlCurso.setRowCount(0);
                  mdlProfessor.setRowCount(0);
                  mdlFase.setRowCount(0);
                  mdlDisciplina.setRowCount(0);
                  txfChooser.setSelectedFile(null);
                  txtCaminho.setText("");
                  dadosMapeados = null;
                  mensagensSucessoResultados.clear();
                  mensagensWarningResultados.clear();
                  btnLimpar.setBackground(Color.decode("#CCCCCC"));
                  btnInserir.setBackground(Color.decode("#CCCCCC"));
                  btnInserir.setEnabled(false);
                  btnLimpar.setEnabled(false);
                  String successMessage = "Dados inseridos com sucesso!";
                  JOptionPane.showMessageDialog(
                    null,
                    successMessage,
                    "Dados Salvos",
                    JOptionPane.INFORMATION_MESSAGE
                  );
                }
              } else {
                String errorMessage =
                  "Não foi possível estabelecer conexão com o banco, tente novamente!";
                JOptionPane.showMessageDialog(
                  null,
                  errorMessage,
                  "Erro de inserção",
                  JOptionPane.ERROR_MESSAGE
                );
              }
            } catch (SQLException ex) {
              ex.printStackTrace();
              String errorMessage =
                "Erro ao conectar ao banco de dados. Verifique a conexão e tente novamente.";
              JOptionPane.showMessageDialog(
                null,
                errorMessage,
                "Erro de Conexão",
                JOptionPane.ERROR_MESSAGE
              );
            }
          }
        }
      );
    btnInserir.setBounds(663, 630, 200, 40);
    btnInserir.setForeground(Color.WHITE);
    btnInserir.setBackground(Color.decode("#CCCCCC"));
    btnInserir.setBorder(new LineBorder(Color.BLACK));
    btnInserir.setEnabled(false);

    getContentPane().add(btnInserir);
  }
}
