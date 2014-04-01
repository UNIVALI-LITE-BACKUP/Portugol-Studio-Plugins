package br.univali.ps.plugins.teste;

import br.univali.ps.plugins.anotacoes.DocumentacaoPlugin;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe utilitária para validar os plugins desenvolvidos. Esta classe
 * verifica se os plugins seguem as regras de implementação definidas pelo
 * mecanismo de plugins, como por exemplo, se a anotação {@link DocumentacaoPlugin}
 * está presente e se a classe do plugin é <b>final</b>
 *
 * @author Luiz Fernando Noschang
 */
final class ValidadorPlugins extends JFrame
{
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
                ValidadorPlugins validadorPlugins = new ValidadorPlugins();
                validadorPlugins.setVisible(true);
            }
        });
    }

    public ValidadorPlugins()
    {
        initComponents();

        setResizable(false);
        setTitle("Validador de Plugins do Portugol Studio");
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
