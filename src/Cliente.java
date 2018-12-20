
import java.rmi.*;
import javax.swing.*;
import java.util.Scanner;
import java.lang.Thread.*;
import java.util.ArrayList;
import java.rmi.RemoteException;

public class Cliente {

 
    
    public static void main(String args[]) {
        try {
            final ServidorChat chat = (ServidorChat) Naming.lookup("rmi://192.168.50.181:1098/ServidorChat");

            String nome;
            String msg = "";
            Scanner scanner = new Scanner(System.in);
            System.out.println("#########################################################");
            System.out.println("#          Chat Feito em Java Utilizando RMI            #");
            System.out.println("#                                                       #");
            System.out.println("#    Integrantes : Bruno Arley, Ismael Ramos            #");
            System.out.println("#                  Leonardo Nascimento e luiz Eduardo   #");
            System.out.println("#                                                       #");
            System.out.println("#              Para Iniciar a Conversa                  #");
            System.out.println("#            digite seu nome e tecle [Enter]            #");
            System.out.println("#            E em seguida digite a mensagem             #");
            System.out.println("#                   e tecle [Enter]                     #");
            System.out.println("#########################################################");
            System.out.println("");
            

            
            
            System.out.println("#-------------Insira seu nome e tecle enter--------------#\n");
            System.out.print("       Meu Nome: ");
             nome = scanner.nextLine();
            System.out.println("\n#--------------------------------------------------------#");
            

            Thread thread = new Thread(new Runnable() {
                int cont = chat.lerMensagem().size();

                @Override
                public void run() {
                    try {
                        while (true) {
                            if (chat.lerMensagem().size() > cont) {
                                
                                    System.out.println(chat.lerMensagem().get(chat.lerMensagem().size()-1));
                                
                                
                                cont++;
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            
            
            while (msg != "exit") {

                
                if(msg.equals("")){
                    System.out.print("Digite a primeira mensagem e tecle enter : ");
                    
                }else{
                    System.out.print("");
                }
                
                
                msg = scanner.nextLine();
                
                
                
                chat.enviarMensagem(nome + ": " + msg);
// System.out.println(chat.lerMensagem().get(cont));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
