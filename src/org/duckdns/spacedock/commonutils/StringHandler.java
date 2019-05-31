package org.duckdns.spacedock.commonutils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe chargeant les fichiers de propriétés (notamment strings) et rendant
 * accessibles les informations de configuration
 *
 * Regles de nommage : les propriétés doivent figurer dans deux fichiers :
 * exceptions.properties et generalstrings.properties et être situés dans un
 * répertoire de la forme _répertoire-de-sources_/_nom_du_projet_/strings
 *
 * @author ykonoclast
 */
class StringHandler
{

    /**
     * Textes des exceptions, un bundle de ressources par locale
     */
    //private Properties m_exceptionsProperties;
    private final ResourceBundle m_exceptionsProperties;

    /**
     * Textes des chaînes générales (pas d'erreurs), un bundle de ressources par
     * locale
     */
    //private Properties m_stringsProperties;
    private final ResourceBundle m_stringsProperties;

    StringHandler(String p_package, Locale p_locale)
    {

	m_stringsProperties = ResourceBundle.getBundle(p_package.concat(".strings.generalstrings"), p_locale);
	m_exceptionsProperties = ResourceBundle.getBundle(p_package.concat(".strings.exceptions"), p_locale);
    }

    /**
     *
     * @param p_property
     * @return le message d'erreur idoine
     */
    String getErrorMessage(String p_property)
    {
	return m_exceptionsProperties.getString(p_property);
    }

    /**
     *
     * @param p_property
     * @return la chaîne d'usage général (pas de traitement d'erreurs ici)
     * indiquée en paramétre
     */
    String getString(String p_property)
    {

	return m_stringsProperties.getString(p_property);
    }
}
