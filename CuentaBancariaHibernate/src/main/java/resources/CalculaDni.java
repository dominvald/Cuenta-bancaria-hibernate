package resources;

import java.util.*;
//import java.lang.*;
public class CalculaDni {
  public static boolean comprobarDni(String dni){
      boolean comprobarLetra;
      boolean comprobarNumero;
      int letra = 0;
      int contaNumeros = 0;
      String cadNumeros = "";
      int numeros;
      int resto;
      boolean valid = false;
      char[] arr = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
      dni = dni.toUpperCase();
              
      comprobarLetra = Character.isLetter(dni.charAt(8));
      if(comprobarLetra == false) {
          System.out.println("El último carácter debe ser una letra");
      } else {
          letra = dni.charAt(8);
          if(letra >= 'A' && letra <='Z') {
              //lletra = lletra -32;
              cadNumeros = dni.substring(0,8);
          }
          for(int i = 0;i <= 7;i++){
              comprobarNumero = Character.isDigit(dni.charAt(i));
              if(comprobarNumero == true) {
                  contaNumeros++;
              } else {
                  System.out.println("El carácter "+(i+1)+" no es un número");
              }
          }
          if(contaNumeros == 8) {
              numeros = Integer.valueOf(cadNumeros);
              resto = numeros%23;
              if(arr[resto] == letra) {
                  valid = true;
              } else {
                  System.out.println("La letra correspondiente al número "+numeros+" es "+arr[resto]);
              }
          }
      }
          return valid;
  }
  public static void main(String[] args) {
	  String dni="7777777B";

          if(dni.length() == 9) {
              if(comprobarDni(dni) == true) {
                  System.out.println("El DNI: "+dni+" es válido");
              } else {
                  System.out.println("El DNI: "+dni+" no es válido");
              }
          } else if(dni.length() == 8){
              System.out.println("El DNI ha de tener 9 cifras");

  }
  }
}