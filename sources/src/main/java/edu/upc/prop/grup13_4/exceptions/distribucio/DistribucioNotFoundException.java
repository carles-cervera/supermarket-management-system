/**
 * @file DistribucioNotFoundException.java
 * @brief Excepció personalitzada per indicar que no s'ha trobat una distribució.
 */

package edu.upc.prop.grup13_4.exceptions.distribucio;

/**
 * @class DistribucioNotFoundException
 * @brief Excepció llançada quan no es troba una distribució.
 *
 * Aquesta excepció es fa servir quan una distribució no es pot localitzar,
 * ja sigui perquè no existeix o perquè no es troba dins de les dades disponibles.
 */
public class DistribucioNotFoundException extends RuntimeException {

  /**
   * @brief Constructor de la classe DistribucioNotFoundException.
   * @param message Missatge d'error que descriu el motiu de l'excepció.
   */
  public DistribucioNotFoundException(String message) {
    super(message);
  }
}
