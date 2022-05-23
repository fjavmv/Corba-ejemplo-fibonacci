package OperacionesCorba;

import OperacionApp.Operacion;
import OperacionApp.OperacionHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import java.util.Scanner;

public class ClienteCorba {
    static  Operacion operacionImp;

  public static void main(String args[])
    {
      try{
        // Se crea e inicializa el ORB
        ORB orb = ORB.init(args, null);
        // obtener el contexto de la raíz (un nombre)
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        // Utilice NamingContextExt en lugar de NamingContext.
        // Esto es parte del servicio de nombres interoperable.
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // resolver la referencia de objeto en Naming
        String name = "Fibonacci";
        operacionImp = OperacionHelper.narrow(ncRef.resolve_str(name));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nombre del número a calcular");
        String nom = scanner.nextLine();
        System.out.println("Ingresa el número a calcular");
        int num = scanner.nextInt();
        System.out.println("Obtuvo un identificador en el objeto del servidor: " + operacionImp);
        System.out.println(operacionImp.ejecutarOperacion(nom,num));
        operacionImp.apagarConexion();

        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }
}
