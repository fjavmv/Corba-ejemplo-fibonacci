package OperacionesCorba;

import OperacionApp.*;
import OperacionApp.OperacionHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import java.util.Properties;

public class ServerCorba{
    public static void main(String args[]) {
        try{
          // crear e inicializar el ORB
          ORB orb = ORB.init(args, null);
    
          // obtenemos una referencia al rootpoa y activamos el POAManager
          POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
          rootpoa.the_POAManager().activate();
    
          // se crea el servant y lo registra con el ORB
          OperacionImp operacionImp = new OperacionImp();
          operacionImp.setORB(orb);

          // obtenemos uan referencia del objeto de el servant
          org.omg.CORBA.Object ref = rootpoa.servant_to_reference(operacionImp);
          Operacion href = OperacionHelper.narrow(ref);
              
          // obtenermos la raiz del nombre del contexto
          org.omg.CORBA.Object objRef =
              orb.resolve_initial_references("NameService");
         //Use NamingContextExt que es parte de Interoperable
          //Especificación del servicio de nombres (INS).
          NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
    
          // vincular la referencia de objeto en la denominación
          String name = "Fibonacci";
          NameComponent path[] = ncRef.to_name( name );
          ncRef.rebind(path, href);
    
          System.out.println("Servidor listo y en espera ...");
    
          // esperando la invocación del cliente
          orb.run();
        } 
            
          catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
          }
              
          System.out.println("Servidor finalizado ...");
            
      }
}