package com.github.lbroudoux.acceleo.uml.java.services;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Common services utility class.
 * @author laurent
 */
public final class CommonServices{

   /** The constructor. */
   private CommonServices() {
      // prevent instantiation.
   }

   /**
    * Surrounds the value with double quote if is not done (abcd to "abcd")<br/>
    * Replace double quote with \" (ab"cd to "ab\"cd")<br/>
    * Remove simple quote starting and ending in value ('abcd' to "abcd")<br/>
    * Usefull for default string value.
    * @param value the value
    * @return the string
    */
   public static String addQuotes(String value) {
      if (value.endsWith("'") && value.startsWith("'")) {
         value = value.substring(1, value.length() - 1);
      }
      if (value.endsWith("\"") && value.startsWith("\"")) {
         value = value.substring(1, value.length() - 1);
      }
      return "\"" + value.trim().replaceAll("\"", "\\\\\\\"") + "\"";
   }

   /**
    * Gets the date in a long format : January 12, 1952
    * @return String representing the long format date
    */
   public static String getLongDate() {
      Date date = new Date(); // to get the date
      Locale locale = Locale.getDefault();// to get the language of the system
      DateFormat dateFormatShort = DateFormat.getDateInstance(DateFormat.LONG,
            locale);
      return dateFormatShort.format(date);
   }

   /**
    * Gets the date in a short format : 06/08/07
    * @return String representing the short format date
    */
   public static String getShortDate() {
      Date date = new Date(); // to get the date
      Locale locale = Locale.getDefault();// to get the language of the system
      DateFormat dateFormatShort = DateFormat.getDateInstance(DateFormat.SHORT,
            locale);
      return dateFormatShort.format(date);
   }

   /**
    * Returns the current time.
    * @return The current time.
    */
   public static String getTime() {
      Date date = new Date();
      return DateFormat.getTimeInstance(DateFormat.LONG).format(date);
   }
}
