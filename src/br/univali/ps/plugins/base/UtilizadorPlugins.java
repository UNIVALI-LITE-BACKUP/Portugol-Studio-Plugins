package br.univali.ps.plugins.base;

import javax.swing.Action;

/**
 *
 * @author Luiz Fernando Noschang
 */
public interface UtilizadorPlugins
{
    public void instalarPlugin(Plugin plugin);
    
    public void instalarAcaoPlugin(Plugin plugin, Action acao);
    
    public String obterCodigoFonteUsuario();
    
    public void desinstalarAcaoPlugin(Plugin plugin, Action acao);
    
    public void desinstalarPlugin(Plugin plugin);
}