package br.univali.ps.plugins.base;

import br.univali.portugol.util.jar.CarregadorJar;
import br.univali.portugol.util.jar.Classes;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.univali.ps.plugins.anotacoes.DocumentacaoPlugin;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;

/**
 *
 * @author Luiz Fernando
 */
public final class GerenciadorPlugins
{
    private static final Logger LOGGER = Logger.getLogger(GerenciadorPlugins.class.getName());
    private static GerenciadorPlugins instance;

    private final List<ObservadorCarregamentoPlugins> observadores = new ArrayList<>();

    private final List<Class<? extends Plugin>> pluginsCarregados = new ArrayList<>();

    private final Map<UtilizadorPlugins, List<Plugin>> mapaUtilizadores = new HashMap<>();
    private final Map<Plugin, List<Action>> mapaAcoes = new HashMap<>();

    private final Map<Class<? extends Plugin>, DocumentacaoPlugin> documentacaoPlugins = new HashMap<>();
    private final CarregadorJar carregadorJar = new CarregadorJar();

    private boolean carregado = false;

    public static GerenciadorPlugins getInstance()
    {
        if (instance == null)
        {
            instance = new GerenciadorPlugins();
        }

        return instance;
    }

    public void adicionarObservador(ObservadorCarregamentoPlugins observador)
    {
        if (!observadores.contains(observador))
        {
            observadores.add(observador);
        }
    }

    public void removerObservador(ObservadorCarregamentoPlugins observador)
    {
        if (observadores.contains(observador))
        {
            observadores.remove(observador);
        }
    }

    public void incluirDiretorioPlugins(File diretorio)
    {
        carregadorJar.incluirCaminho(diretorio);
    }

    public synchronized void carregarPlugins()
    {
        if (!carregado)
        {
            carregadorJar.carregar();

            Classes classes = carregadorJar.listarClasses().queEstendemOuImplementam(Plugin.class);

            for (Class classePlugin : classes)
            {
                carregarPlugin(classePlugin);
            }

            carregado = true;
        }
    }

    private void carregarPlugin(Class<? extends Plugin> classePlugin)
    {
        try
        {
            if (!pluginsCarregados.contains(classePlugin))
            {
                pluginsCarregados.add(classePlugin);
                documentacaoPlugins.put(classePlugin, classePlugin.getAnnotation(DocumentacaoPlugin.class));
            }
            else
            {
                throw new RuntimeException("O plugin " + classePlugin.getName() + " já está registrado!");
            }
        }
        catch (Exception excecao)
        {
            excecao.printStackTrace(System.err);
        }
    }

    public void instalarPlugins(UtilizadorPlugins utilizador)
    {
        for (Class<? extends Plugin> classePlugin : pluginsCarregados)
        {
            try
            {
                if (!mapaUtilizadores.containsKey(utilizador))
                {
                    mapaUtilizadores.put(utilizador, new ArrayList<Plugin>());
                }

                if (!pluginJaInstalado(utilizador, classePlugin))
                {
                    Plugin plugin = classePlugin.newInstance();
                    plugin.inicializar(utilizador);

                    utilizador.instalarPlugin(plugin);

                    mapaUtilizadores.get(utilizador).add(plugin);
                }
            }
            catch (Exception excecao)
            {
                LOGGER.log(Level.SEVERE, String.format("Erro ao instalar o plugin '%s' no utilizador '%s'", classePlugin.getName(), utilizador.getClass().getName()), excecao);
            }
        }
    }

    private boolean pluginJaInstalado(UtilizadorPlugins utilizador, Class<? extends Plugin> classePlugin)
    {
        boolean pluginJaInstalado = false;

        List<Plugin> plugins = mapaUtilizadores.get(utilizador);

        for (Plugin plugin : plugins)
        {
            if (classePlugin.isInstance(plugin))
            {
                pluginJaInstalado = true;
                break;
            }
        }

        return pluginJaInstalado;
    }

    public void desinstalarPlugins(UtilizadorPlugins utilizador)
    {
        if (mapaUtilizadores.containsKey(utilizador))
        {
            List<Plugin> plugins = mapaUtilizadores.get(utilizador);
            Iterator<Plugin> iterador = plugins.iterator();

            while (iterador.hasNext())
            {
                Plugin plugin = iterador.next();

                utilizador.desinstalarPlugin(plugin);
                desinstalarAcoesPlugin(utilizador, plugin);

                iterador.remove();
            }
        }
    }

    private void desinstalarAcoesPlugin(UtilizadorPlugins utilizador, Plugin plugin)
    {
        if (mapaAcoes.containsKey(plugin))
        {
            List<Action> acoes = new ArrayList<>(mapaAcoes.get(plugin));

            for (Action acao : acoes)
            {
                desinstalarAcaoPlugin(plugin, acao);
            }

            mapaAcoes.remove(plugin);
        }
    }

    public void instalarAcaoPlugin(Plugin plugin, Action acao)
    {
        for (UtilizadorPlugins utilizador : mapaUtilizadores.keySet())
        {
            List<Plugin> plugins = mapaUtilizadores.get(utilizador);

            if (plugins.contains(plugin))
            {
                try
                {
                    if (!mapaAcoes.containsKey(plugin))
                    {
                        mapaAcoes.put(plugin, new ArrayList<Action>());
                    }

                    List<Action> acoes = mapaAcoes.get(plugin);

                    if (!acoes.contains(acao))
                    {
                        utilizador.instalarAcaoPlugin(plugin, acao);
                        acoes.add(acao);
                    }
                }
                catch (Exception excecao)
                {
                    LOGGER.log(Level.SEVERE, String.format("Erro ao registrar ação do o plugin '%s' no container '%s'", plugin.getClass().getName(), utilizador.getClass().getName()), excecao);
                }
            }
        }
    }

    public void desinstalarAcaoPlugin(Plugin plugin, Action acao)
    {
        for (UtilizadorPlugins utilizador : mapaUtilizadores.keySet())
        {
            List<Plugin> plugins = mapaUtilizadores.get(utilizador);

            if (plugins.contains(plugin))
            {
                if (mapaAcoes.containsKey(plugin))
                {
                    List<Action> acoes = mapaAcoes.get(plugin);

                    if (acoes.contains(acao))
                    {
                        utilizador.desinstalarAcaoPlugin(plugin, acao);
                        acoes.remove(acao);
                    }
                }
            }
        }
    }

    public List<DocumentacaoPlugin> obterDocumentacaoPlugins()
    {
        return new ArrayList<>(documentacaoPlugins.values());
    }

    /*
     private Class[] extrairTiposParametros(Object[] parametros)
     {
     Class[] tipos = new Class[parametros.length];
        
     for (int i = 0; i < parametros.length; i++)
     {
     tipos[i] = parametros[i].getClass();
     }
        
     return tipos;
     }*/
}
