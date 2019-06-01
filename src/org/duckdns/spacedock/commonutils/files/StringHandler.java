package org.duckdns.spacedock.commonutils.files;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe chargeant les Strings d'un projet pour une Locale donnée
 *
 * Regles de nommage : les Strings doivent figurer dans deux fichiers :
 * exceptions.properties et generalstrings.properties et être situés dans un
 * répertoire de la forme <package>/resources/strings
 *
 * @author ykonoclast
 */
class StringHandler
{

    /**
     * Textes des exceptions
     */
    private final ResourceBundle m_exceptionsProperties;

    /**
     * Textes des chaînes générales (pas d'erreurs)
     */
    private final ResourceBundle m_stringsProperties;

    /**
     * constructeur : charge les deux fichiers de propriétés traités par cette
     * classe pour la locale donnée
     *
     * @param p_package
     * @param p_locale
     */
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
