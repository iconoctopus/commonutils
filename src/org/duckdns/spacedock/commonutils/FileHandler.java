/*
 * Copyright (C) 2019 ykonoclast
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.duckdns.spacedock.commonutils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.json.JsonObject;

/**
 *
 * @author ykonoclast
 */
public class FileHandler
{

    /**
     * instances statiques uniques par package
     */
    private final static Map<String, FileHandler> mf_instances = new HashMap<>();

    private final String mf_packageName;

    private final Map<Locale, StringHandler> m_stringsHandlers = new HashMap<>();

    private JSONHandler m_JSONHandler;

    private PropertiesHandler m_PropertiesHandler;

    public static FileHandler getInstance(String p_package)
    {
	if (!mf_instances.containsKey(p_package))
	{
	    mf_instances.put(p_package, new FileHandler(p_package));
	}

	return mf_instances.get(p_package);
    }

    /**
     * véritable contructeur, appelé par getInstance() si l'instance n'existe
     * pas. Construit les objets properties à partir des fichiers idoines afin
     * qu'il ne soit pas nécessaire de le faire de façon synchrone durant le
     * reste de l'exécution
     *
     */
    private FileHandler(String p_package)
    {
	mf_packageName = p_package.concat(".resources");
    }

    public String getString(String p_property, Locale p_locale)
    {
	secureStringHandler(p_locale);

	return m_stringsHandlers.get(p_locale).getString(p_property);
    }

    /**
     * envoyer une exception IllegalArgumentException avec un message d'erreur
     * standardisé et un complément de texte
     *
     * @param p_propExcep
     */
    public void paramAberrant(String p_propExcep, Locale p_locale)
    {
	paramAberrant(p_propExcep, "", p_locale);
    }

    public void paramAberrant(String p_propExcep, String p_complementTexte, Locale p_locale)
    {
	String msg = baseExcep("param_aberr", p_propExcep, p_locale).concat(p_complementTexte);
	throw new IllegalArgumentException(msg);
    }

    public void mauvaiseMethode(String p_propExcep, Locale p_locale)
    {
	mauvaiseMethode(p_propExcep, "", p_locale);
    }

    public void mauvaiseMethode(String p_propExcep, String p_complementTexte, Locale p_locale)
    {
	String msg = baseExcep("mauv_meth", p_propExcep, p_locale).concat(p_complementTexte);
	throw new IllegalArgumentException(msg);
    }

    private String baseExcep(String p_typeExcep, String p_propExcep, Locale p_locale)
    {
	secureStringHandler(p_locale);

	String message = getInstance("org.duckdns.spacedock.commonutils").getString(p_typeExcep, p_locale);
	message = message.concat(m_stringsHandlers.get(p_locale).getErrorMessage(p_propExcep));
	return (message);
    }

    private void secureStringHandler(Locale p_locale)
    {
	if (!m_stringsHandlers.containsKey(p_locale))
	{
	    StringHandler handler = new StringHandler(mf_packageName, p_locale);
	    m_stringsHandlers.put(p_locale, handler);
	}
    }

    //loadJsonFile(String p_fileName)
    public JsonObject loadJsonFile(String p_baseFileName)
    {
	if (m_JSONHandler == null)
	{
	    m_JSONHandler = new JSONHandler(mf_packageName);
	}

	return m_JSONHandler.loadJsonFile(p_baseFileName);
    }

    public String getAppProperty(String p_baseFileName, String p_property)
    {
	if (m_PropertiesHandler == null)
	{
	    m_PropertiesHandler = new PropertiesHandler(mf_packageName);
	}

	return m_PropertiesHandler.getAppProperty(p_baseFileName, p_property);
    }

}
