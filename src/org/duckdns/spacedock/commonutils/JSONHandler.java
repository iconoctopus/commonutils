/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.commonutils;

import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author iconoctopus
 */
public class JSONHandler
{

    /**
     *
     * @param p_projectName
     * @param p_filePath un chemin relatif à partir d'un répertoire de la forme
     * _répertoire-de-sources_/_nom_du_projet_/JSON
     * @return u objet JSON lu dans le fichier
     */
    public static JsonObject loadJsonFile(String p_projectName, String p_filePath)
    {
	String filePath = p_projectName.concat("/JSON/").concat(p_filePath);
	InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);//on utilise le classloader du thread et pas de la classe pour plus de sûreté
	JsonReader reader = Json.createReader(in);
	return (reader.readObject());
    }
}
