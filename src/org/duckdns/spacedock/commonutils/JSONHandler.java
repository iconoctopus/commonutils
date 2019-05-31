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
 * @author ykonoclast
 */
class JSONHandler
{

    private final String mf_packageName;

    JsonObject loadJsonFile(String p_fileName)
    {
	String filePath = mf_packageName.concat(p_fileName);
	InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);//on utilise le classloader du thread et pas de la classe pour plus de sûreté
	JsonReader reader = Json.createReader(in);
	return (reader.readObject());
    }

    JSONHandler(String p_package)
    {
	mf_packageName = p_package.concat(".JSON.");
    }

}
