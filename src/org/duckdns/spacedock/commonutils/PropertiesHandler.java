package org.duckdns.spacedock.commonutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Classe chargeant les fichiers de propriétés (notamment strings) et rendant
 * accessibles les informations de configuration
 *
 * Regles de nommage : les propriétés doivent figurer dans deux fichiers :
 * exceptions.properties et generalstrings.properties et être situés dans un
 * répertoire de la forme _répertoire-de-sources_/_nom_du_projet_/strings
 *
 * @author iconoctopus
 */
public class PropertiesHandler
{

    /**
     * message d'erreur par défaut
     */
    private final static String m_propertiesErrorMessage = "erreur d'accès à un fichier de propriétés";

    /**
     * si les propriétés des exceptions ont pu être récupérées
     */
    private boolean m_exceptionsRecovered;

    /**
     * si les propriétés des chaînes ont pu être récupérées
     */
    private boolean m_stringsRecovered;

    /**
     * instances statiques uniques par répertoire de configuration
     */
    private final static ArrayList<PropertiesHandler> m_instances = new ArrayList<PropertiesHandler>();

    /**
     * noms des répertoires de configuration déjà chargés
     */
    private final static ArrayList<String> m_repertoiresCharges = new ArrayList<String>();

    /**
     * Textes des exceptions
     */
    private Properties m_exceptionsProperties;

    /**
     * Textes des chaînes générales (pas d'erreurs)
     */
    private Properties m_stringsProperties;

    /**
     * pseudo-constructeur statique permettant d'accéder à l'instance (la crée
     * si elle n'existe pas)
     *
     * @param p_repertoire le répertoire de configuration à charger
     * @return l'instance unique
     */
    public static PropertiesHandler getInstance(String p_repertoire)
    {
	if (!m_repertoiresCharges.contains(p_repertoire))
	{
	    m_repertoiresCharges.add(p_repertoire);
	    m_instances.add(new PropertiesHandler(p_repertoire));
	}

	return m_instances.get(m_repertoiresCharges.indexOf(p_repertoire));
    }

    /**
     * véritable contructeur, appelé par getInstance() si l'instance n'existe
     * pas. Construit les objets properties à partir des fichiers idoines afin
     * qu'il ne soit pas nécessaire de le faire de façon synchrone durant le
     * reste de l'exécution
     *
     */
    private PropertiesHandler(String p_repertoire)
    {
	try
	{
	    m_exceptionsProperties = readProperties(p_repertoire.concat("/strings/exceptions.properties"));
	    m_exceptionsRecovered = true;
	}
	catch (IOException | NullPointerException e)
	{
	    m_exceptionsRecovered = false;
	}

	try
	{
	    m_stringsProperties = readProperties(p_repertoire.concat("/strings/generalstrings.properties"));
	    m_stringsRecovered = true;
	}
	catch (IOException | NullPointerException e)
	{
	    m_stringsRecovered = false;
	}
    }

    private Properties readProperties(String p_path) throws IOException
    {
	//InputStream in = PropertiesHandler.class.getClassLoader().getResourceAsStream("strings/exceptions.properties");
	/*on utilise le classloader pour récupérer le fichier de propriétés ailleurs que dans le même package : il utilise le classpath.
	 *On utilise le classloader du thread afin d'être davantage sur qu'il explorera tout le classpath, contrairement au classloader
	 *de la classe (utilisé dans le bout de code commenté ci-dessus). J'ignore si cela marche bien avec les threads android.
	 */
	InputStream in;
	in = Thread.currentThread().getContextClassLoader().getResourceAsStream(p_path);

	//on construit l'objet propriété demandé avant de le renvoyer
	Properties result = new Properties();
	result.load(in);
	in.close();

	return result;
    }

    /**
     *
     * @param p_property
     * @return le message d'erreur idoine
     */
    public String getErrorMessage(String p_property)
    {
	String result;
	if (m_exceptionsRecovered)
	{
	    result = m_exceptionsProperties.getProperty(p_property);
	}
	else
	{
	    result = m_propertiesErrorMessage;
	}
	return result;
    }

    /**
     *
     * @param p_property
     * @return la chaîne d'usage général (pas de traitement d'erreurs ici)
     * indiquée en paramétre
     */
    public String getString(String p_property)
    {
	String result;
	if (m_stringsRecovered)
	{
	    result = m_stringsProperties.getProperty(p_property);
	}
	else
	{
	    result = m_propertiesErrorMessage;
	}
	return result;
    }
}
