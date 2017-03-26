/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.commonutils;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ykonoclast
 */
public class IntegPropertiesHandlerTest
{

    @Test
    public void testgetString()
    {
	Assert.assertEquals("erreur d'accès à un fichier de propriétés", PropertiesHandler.getInstance("meugni").getString("pout pouti"));
	Assert.assertEquals("indice", PropertiesHandler.getInstance("commonutils").getString("indice"));
    }

    @Test
    public void testgetErrorMessage()
    {
	Assert.assertEquals("erreur d'accès à un fichier de propriétés", PropertiesHandler.getInstance("meugni").getErrorMessage("pout pouti"));
	Assert.assertEquals("paramétre aberrant:", PropertiesHandler.getInstance("commonutils").getErrorMessage("param_aberr"));
    }
}
