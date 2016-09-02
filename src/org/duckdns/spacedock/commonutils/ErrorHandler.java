/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.commonutils;

/**
 * Classe de sucre syntactique pour centraliser le message d'erreur sur
 * paramétre envoyé par toutes les méthodes de toutes les classes y étant
 * confrontées
 *
 * @author iconoctopus
 */
public class ErrorHandler
{

    /**
     * envoyer une exception IllegalArgumentException avec un message d'erreur
     * standardisé et un complément de texte
     *
     * @param p_complementTexte
     */
    public static void paramAberrant(String p_complementTexte)
    {
	String message = "";

	message = message.concat(PropertiesHandler.getInstance("commonutils").getErrorMessage("param_aberr"));
	message = message.concat(p_complementTexte);

	throw new IllegalArgumentException(message);
    }

    /**
     * envoyer une exception IllegalStateException avec un message d'erreur
     * standardisé et un complément de texte
     *
     * @param p_complementTexte
     */
    public static void mauvaiseMethode(String p_complementTexte)
    {
	String message = "";

	message = message.concat(PropertiesHandler.getInstance("commonutils").getErrorMessage("mauv_meth"));
	message = message.concat(p_complementTexte);

	throw new IllegalStateException(message);
    }

}
