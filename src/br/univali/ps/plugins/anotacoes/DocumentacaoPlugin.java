package br.univali.ps.plugins.anotacoes;

import br.univali.ps.plugins.base.Plugin;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Armazena a documentação do {@link Plugin}
 *
 * @author Luiz Fernando Noschang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DocumentacaoPlugin
{
    /**
     *
     * @return o nome do plugin. Este nome será utilizado para identificar
     *         o plugin na interface do usuário
     */
    String nome();

    /**
     *
     * @return a descrição do plugin, indicando o seu propósito.
     *         Esta descrição poderá ser exibida na interface do usuário
     */
    String descricao();

    /**
     *
     * @return a versão atual do plugin. Deve ser uma String no formato
     *         #.#.#. Exemplo: 1.3.7
     */
    String versao();

    /**
     *
     * @return o nome do arquivo de ícone que representa o plugin
     *         na interface do usuário. Exemplo: meu_plugin.png.
     * <br/>
     * O arquivo de ícone deverá estar na raíz do arquivo JAR e ser
     * uma imagem com dimensões 16x16 pixels. São aceitos todos os formatos
     * suportados pela API ImageIO do Java, tendo preferência os formatos
     * PNG e JPEG
     */
    String icone();

    /**
     *
     * @return o(s) autor(es)/desenvolvedor(es) do plugin
     */
    Autor[] autores();
}
