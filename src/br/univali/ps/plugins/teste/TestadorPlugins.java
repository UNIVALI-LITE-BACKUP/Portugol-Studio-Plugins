package br.univali.ps.plugins.teste;

import br.univali.ps.plugins.base.GerenciadorPlugins;
import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe utilitária que imita uma interface de usuário similar à do Portugol Studio.
 * Utilizada para testar na prático os plugins desenvolvidos sem a necessidade de
 * executar o Portugol Studio
 *
 * @author Luiz Fernando Noschang
 */
final class TestadorPlugins extends JFrame implements UtilizadorPlugins
{
    private final Map<Action, JButton> botoes = new HashMap<>();

    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException excecao)
        {

        }

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                TestadorPlugins testadorPlugins = new TestadorPlugins();
                testadorPlugins.setVisible(true);
            }
        });
    }

    public TestadorPlugins()
    {
        initComponents();

        jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setResizable(false);
        setTitle("Testador de Plugins do Portugol Studio");
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentShown(ComponentEvent e)
            {
                GerenciadorPlugins.getInstance().carregarPlugins();

                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        GerenciadorPlugins.getInstance().instalarPlugins(TestadorPlugins.this);
                    }
                });
            }
        });
    }

    @Override
    public void instalarPlugin(final Plugin plugin)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                painelPlugins.add(plugin.getVisao());
                painelPlugins.validate();
                painelPlugins.repaint();
            }
        });
    }

    @Override
    public void desinstalarPlugin(final Plugin plugin)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                painelPlugins.remove(plugin.getVisao());
                painelPlugins.validate();
                painelPlugins.repaint();
            }
        });
    }
    
    @Override
    public void instalarAcaoPlugin(Plugin plugin, final Action acao)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JButton botaoAcao = new JButton(acao);

                botaoAcao.setBorderPainted(false);
                botaoAcao.setFocusable(false);
                botaoAcao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                botaoAcao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                botaoAcao.setOpaque(false);
                botaoAcao.setText(null);
                botaoAcao.setPreferredSize(new java.awt.Dimension(40, 40));
                botaoAcao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

                jToolBar1.add(botaoAcao);
                jToolBar1.repaint();

                botoes.put(acao, botaoAcao);
            }
        });

    }

    @Override
    public void desinstalarAcaoPlugin(Plugin plugin, final Action acao)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JButton botao = botoes.get(acao);
                jToolBar1.remove(botao);
                jToolBar1.repaint();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel3 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        painelPlugins = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jSeparator3 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setPreferredSize(new java.awt.Dimension(497, 42));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/ps/plugins/unknown.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setOpaque(false);
        jButton1.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/ps/plugins/unknown.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setOpaque(false);
        jButton2.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/ps/plugins/unknown.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setOpaque(false);
        jButton3.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/ps/plugins/unknown.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setOpaque(false);
        jButton4.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jPanel3.add(jToolBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(230);
        jSplitPane1.setDividerSize(8);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel1.setMinimumSize(new java.awt.Dimension(150, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setDividerLocation(250);
        jSplitPane2.setDividerSize(8);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setMinimumSize(new java.awt.Dimension(250, 20));
        jSplitPane2.setOneTouchExpandable(true);
        jSplitPane2.setPreferredSize(new java.awt.Dimension(250, 287));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridLayout(0, 1));

        jButton6.setText("Instalar Plugins");
        jButton6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton6);

        jButton5.setText("Desinstalar Plugins");
        jButton5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5);

        jPanel4.add(jPanel8, java.awt.BorderLayout.NORTH);
        jPanel4.add(jSeparator2, java.awt.BorderLayout.PAGE_END);

        jSplitPane2.setTopComponent(jPanel4);

        painelPlugins.setLayout(new java.awt.GridLayout(1, 1));
        jSplitPane2.setRightComponent(painelPlugins);

        jPanel1.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 10));
        jPanel1.add(jSeparator1, java.awt.BorderLayout.EAST);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jSplitPane3.setDividerLocation(500);
        jSplitPane3.setDividerSize(8);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setOneTouchExpandable(true);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        jEditorPane1.setBorder(null);
        jEditorPane1.setText("programa \n{\n\tfuncao inicio() \n\t{\n\t\tinteiro numero, resultado, contador\n\n\t\tescreva(\"Informe o número para ver sua tabuada: \")\n\t\tleia(numero)\n\t\t\n\t\tpara (contador = 1; contador <= 10; contador ++) \n                                          {\n\t\t    resultado = numero * contador \n\t\t    escreva (numero, \" X \", contador, \" = \", resultado , \"\\n\")\n\t\t}\n\t}\n}\n");
        jScrollPane1.setViewportView(jEditorPane1);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel5.add(jSeparator3, java.awt.BorderLayout.PAGE_END);

        jSplitPane3.setTopComponent(jPanel5);

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(38, 200));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(100, 200));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Console", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Mensagens", jPanel7);

        jSplitPane3.setRightComponent(jTabbedPane1);

        jPanel2.add(jSplitPane3, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
        GerenciadorPlugins.getInstance().desinstalarPlugins(this);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton6ActionPerformed
    {//GEN-HEADEREND:event_jButton6ActionPerformed
        GerenciadorPlugins.getInstance().instalarPlugins(this);
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel painelPlugins;
    // End of variables declaration//GEN-END:variables
}
