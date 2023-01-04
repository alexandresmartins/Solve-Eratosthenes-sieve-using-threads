package q3;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Cliente {

    public static void main(String[] args) throws IOException {
        
    	Random rand = new Random(); //instância da classe ramdom
        int upperbound = 25;
          //Gera um número aleatório
        int random = rand.nextInt(upperbound);
    	
       //1 - Abrir conexão
        Socket socket = new Socket("127.0.0.1", 54321);
        
        //2 - Definir stream de saída de dados do cliente
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeInt(random);  //Enviar limite do crivo para o servidor

        //3 - Definir stream de entrada de dados no cliente
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        String novaMensagem = entrada.readUTF();//Receber mensagem de encerramento do servidor
        System.out.println(novaMensagem); //Mostrar mensagem  no cliente

        //4 - Fechar streams de entrada e saída de dados
        entrada.close();
        saida.close();

        //5 - Fechar o socket
        socket.close();
    }
}
