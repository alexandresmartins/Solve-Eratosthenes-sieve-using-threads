package q3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadSockets extends Thread {
    private Socket socket;
    public ThreadSockets(Socket s) {
        this.socket = s;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName());//Imprimir o nome da Thread
        try {
            //1 - Definir stream de entrada de dados no servidor
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            int mensagem = entrada.readInt();//Recebendo o limite do crivo do Cliente

          
            // Execução do crivo
            boolean prime[] = new boolean[mensagem+1];
            for(int i=0;i<=mensagem;i++)
                prime[i] = true;
             
            for(int p = 2; p*p <=mensagem; p++)
            {
               
                if(prime[p] == true)
                {
                    
                    for(int i = p*p; i <= mensagem; i += p)
                        prime[i] = false;
                }
            }
             
            // Mostra os primos
            for(int i = 2; i <= mensagem; i++)
            {
                if(prime[i] == true)
                    System.out.print(i + " ");
            }
        
        //4 - Definir stream de saída de dados do servidor
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeUTF("Contagem finalizada"); //Enviar mensagem em maiúsculo para cliente

            //3 - Fechar streams de entrada e saída de dados
            entrada.close();
            saida.close();
            
            //4 - Fechar socket de comunicação
            socket.close();
        } catch (IOException ioe) {
            System.out.println("Erro: " + ioe.toString());
        }
    }
}
