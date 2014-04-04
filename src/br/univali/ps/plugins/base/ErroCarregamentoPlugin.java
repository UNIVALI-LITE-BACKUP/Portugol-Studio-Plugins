package br.univali.ps.plugins.base;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Luiz Fernando Noschang
 */
public final class ErroCarregamentoPlugin extends Exception
{
    public ErroCarregamentoPlugin(String mensagem, File arquivoJar, Class classePlugin)
    {
        super(String.format("Erro ao carregar o plugin '%s' do arquivo '%s': %s", classePlugin.getName(), obterCaminhoArquivo(arquivoJar), mensagem));
    }
    
    private static String obterCaminhoArquivo(File arquivo)
    {
        try
        {
            return arquivo.getCanonicalPath();                    
        }
        catch (IOException excecao)
        {
            return arquivo.getAbsolutePath();
        }
    }
}
