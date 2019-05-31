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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author ykonoclast
 */
class PropertiesHandler
{

    private final String mf_packageName;

    private final Map<String, Properties> mf_appProperties = new HashMap<>();

    PropertiesHandler(String p_package)
    {
	mf_packageName = p_package.concat(".app.");
    }

    String getAppProperty(String p_baseFileName, String p_property)
    {
	//InputStream in = PropertiesHandler.class.getClassLoader().getResourceAsStream("strings/exceptions.properties");
	/*on utilise le classloader pour récupérer le fichier de propriétés ailleurs que dans le même package : il utilise le classpath.
	 *On utilise le classloader du thread afin d'être davantage sur qu'il explorera tout le classpath, contrairement au classloader
	 *de la classe (utilisé dans le bout de code commenté ci-dessus). J'ignore si cela marche bien avec les threads android.
	 */

	String result;

	try
	{

	    if (!mf_appProperties.containsKey(p_baseFileName))
	    {

		InputStream in;
		in = Thread.currentThread().getContextClassLoader().getResourceAsStream(mf_packageName.concat(p_baseFileName));

		Properties properties = new Properties();
		properties.load(in);
		in.close();

		mf_appProperties.putIfAbsent(p_baseFileName, properties);
	    }

	    result = mf_appProperties.get(p_baseFileName).getProperty(p_property);
	}
	catch (Exception e)//toute exception (IO, missing ressource etc. entraîne simple renvoi de la chaîne nulle, on ne fait pas pêter les applis pour ça
	{
	    result = "";
	}
	return result;
    }

}
