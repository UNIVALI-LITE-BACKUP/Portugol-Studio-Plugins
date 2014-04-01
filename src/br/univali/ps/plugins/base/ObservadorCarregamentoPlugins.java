package br.univali.ps.plugins.base;

import br.univali.ps.plugins.anotacoes.DocumentacaoPlugin;
import java.io.File;

/**
 * Monitora o processo de carregamento dos plugins na classe {@link GerenciadorPlugins}.
 * Os observadores serão notifcados a cada etapa do carregamento.
 * 
 * @author Luiz Fernando Noschang
 */
public interface ObservadorCarregamentoPlugins
{
    public void carregamentoIniciado(int quantidadeDiretorios);
    
    public void varreduraDiretorioIniciada(File diretorio);
    
    public void varreduraDiretorioFinalizada(File diretorio, int quantidadeJarsEncontrados);
    
    public void carregamentoJarIniciado(File jar);
    
    public void carregamentoJarFinalizado(File jar);
    
    public void varreduraJarIniciada(File jar);
    
    public void varreduraJarFinalizada(File jar, int quantidadePluginsEncontrados);
    
    public void carregamentoPluginIniciado(String nomeClasse);
    
    public void carregamentoPluginFinalizado(Class<? extends Plugin> classePlugin, DocumentacaoPlugin documentacaoPlugin);
    
    public void falhaCarregamentoPlugin(String nomeClasse);
    
    public void carregamentoFinalizado();
}
