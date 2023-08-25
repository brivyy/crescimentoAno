import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


public class Main {
    private JPanel panel1;
    private JComboBox paisDD;
    private JButton gerarGraficoButton;
    private JTextField anoFD;
    private JPanel graficPanel;

    public Main() {
        gerarGraficoButton.addActionListener(e -> {
            graficPanel.setPreferredSize(new Dimension(800,600));
            graficPanel.removeAll();

              if (anoFD.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"Inssira um ano valido!","Ano errado!",JOptionPane.ERROR_MESSAGE);

            }else if(Integer.parseInt(anoFD.getText()) < 2021){
                JOptionPane.showMessageDialog(null,"O ano inserido é inferior ao ano atual.","Ano errado!",JOptionPane.ERROR_MESSAGE);
            } else if (Integer.parseInt(anoFD.getText()) > 2350) {
                 JOptionPane.showMessageDialog(null,"O ano inserido é grande demais.","Ano errado!",JOptionPane.ERROR_MESSAGE);

             }  else if (paisDD.getSelectedItem().toString().equals("Todos")){
                criarPieGrafico(anoFD.getText());
            }
            else {
                criargrafico(paisDD.getSelectedItem().toString(), anoFD.getText());
            }
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(graficPanel);
            topFrame.pack();
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Crescimento anual");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void criarPieGrafico(String anoFinal) {
        DefaultPieDataset pizza = new DefaultPieDataset();
        Map<String, Integer> populacao = new HashMap<>();
        Map<String, Double> percent = new HashMap<>();
        populacao.put("Argentina", 45810000);
        populacao.put("Bolívia", 12080000);
        populacao.put("Brasil", 214300000);
        populacao.put("Chile", 19490000);
        populacao.put("Colômbia", 51520000);
        populacao.put("Equador", 17800000);
        populacao.put("Guiana", 804567);
        populacao.put("Guiana Francesa", 294071);
        populacao.put("Paraguai", 6704000);
        populacao.put("Peru", 33720000);
        populacao.put("Suriname", 612985);
        populacao.put("Uruguai", 3426000);
        populacao.put("Venezuela", 28200000);

        percent.put("Argentina", 0.012);
        percent.put("Bolívia", 0.009);
        percent.put("Brasil", 0.005);
        percent.put("Chile", 0.01);
        percent.put("Colômbia", 0.011);
        percent.put("Equador", 0.012);
        percent.put("Guiana", 0.009);
        percent.put("Guiana Francesa", 0.035);
        percent.put("Paraguai", 0.013);
        percent.put("Peru", 0.012);
        percent.put("Suriname", 0.01);
        percent.put("Uruguai", -0.001);
        percent.put("Venezuela", -0.01);

        for(int j = 1;j < paisDD.getItemCount();j++){
            String pais = paisDD.getItemAt(j).toString();
            ArrayList<Integer> popPerYear = new ArrayList<>();
            popPerYear.add(populacao.get(pais));

            for (int ano = 2022, i = 0;ano <= Integer.parseInt(anoFinal);ano++, i++){
                popPerYear.add((int)(popPerYear.get(i) + (popPerYear.get(i) * percent.get(pais) )));
            }

            pizza.setValue(pais, popPerYear.get(popPerYear.size() - 1));
         }

        JFreeChart grafico = ChartFactory.createPieChart("Estimativa da população em " + anoFinal, pizza, true, true, false);
        ChartPanel painel = new ChartPanel(grafico);
        graficPanel.setLayout(new BorderLayout());
        painel.setDomainZoomable(true);
        graficPanel.add(painel, BorderLayout.CENTER);
        graficPanel.setPreferredSize(new Dimension(800,600));
        graficPanel.repaint();
        graficPanel.setVisible(true);

    }

    private void criargrafico(String pais, String anoFinal){
        Map<String, Integer> populacao = new HashMap<>();
        Map<String, Double> percent = new HashMap<>();
        populacao.put("Argentina", 45810000);
        populacao.put("Bolívia", 12080000);
        populacao.put("Brasil", 214300000);
        populacao.put("Chile", 19490000);
        populacao.put("Colômbia", 51520000);
        populacao.put("Equador", 17800000);
        populacao.put("Guiana", 804567);
        populacao.put("Guiana Francesa", 294071);
        populacao.put("Paraguai", 6704000);
        populacao.put("Peru", 33720000);
        populacao.put("Suriname", 612985);
        populacao.put("Uruguai", 3426000);
        populacao.put("Venezuela", 28200000);

        percent.put("Argentina", 0.012);
        percent.put("Bolívia", 0.009);
        percent.put("Brasil", 0.005);
        percent.put("Chile", 0.01);
        percent.put("Colômbia", 0.011);
        percent.put("Equador", 0.012);
        percent.put("Guiana", 0.009);
        percent.put("Guiana Francesa", 0.035);
        percent.put("Paraguai", 0.013);
        percent.put("Peru", 0.012);
        percent.put("Suriname", 0.01);
        percent.put("Uruguai", -0.001);
        percent.put("Venezuela", -0.01);

        ArrayList<Integer> popPerYear = new ArrayList<>();
        popPerYear.add(populacao.get(pais));

        for (int ano = 2022, i = 0;ano <= Integer.parseInt(anoFinal);ano++, i++){
            popPerYear.add((int)(popPerYear.get(i) + (popPerYear.get(i) * percent.get(pais) )));
        }

        DefaultCategoryDataset data = new DefaultCategoryDataset();

        for (int i = 0, ano = 2021; i < popPerYear.size();i++, ano++){
            data.setValue(popPerYear.get(i),Integer.toString(ano),"");
        }

        JFreeChart grafico = ChartFactory.createBarChart("Estimativa da população ("+ pais +")", "Anos", "População", data, PlotOrientation.VERTICAL,true,true,false);
        ChartPanel painel = new ChartPanel(grafico);
        graficPanel.setLayout(new BorderLayout());
        painel.setDomainZoomable(true);
        graficPanel.add(painel, BorderLayout.CENTER);
        graficPanel.setPreferredSize(new Dimension(800,600));
        graficPanel.repaint();
        graficPanel.setVisible(true);


    }
}
