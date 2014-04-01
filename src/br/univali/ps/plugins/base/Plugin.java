package br.univali.ps.plugins.base;

import br.univali.ps.plugins.anotacoes.DocumentacaoPlugin;

/**
 * Classe base para a criação de plugins do Portugol Studio.
 *
 * @author Luiz Fernando Noschang
 */
public abstract class Plugin
{
    public DocumentacaoPlugin getDocumentacao()
    {
        return getClass().getAnnotation(DocumentacaoPlugin.class);
    }

    /**
     * Obtém a visão principal do plugin.
     *
     * @return a visão do plugin
     */
    public abstract VisaoPlugin getVisao();

    /**
     * Método utilizado para inicializar o plugin. Este método será chamado automaticamente
     * pelo {@link GerenciadorPlugins} cada vez que o plugin for consumido por um {@link UtilizadorPlugins}
     *
     * @param utilizador o objeto que está utilizando este plugin
     */
    protected void inicializar(UtilizadorPlugins utilizador)
    {

    }
}
