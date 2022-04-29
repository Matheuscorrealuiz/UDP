import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//Classe do Servidor

public class Servidor {
    public static void main(String args[]) throws Exception {

        //porta onde realiza a conexão
        int porta = 6666;

        DatagramSocket socketServidor = new DatagramSocket(porta);

        byte[] recebeDado = new byte[1024];
        byte[] enviaDado;

        while (true) {
            DatagramPacket recebePacote = new DatagramPacket(recebeDado,
                    recebeDado.length);
            System.out.println("Esperando por uma mensagem na porta " + porta);
            socketServidor.receive(recebePacote);

            //variavel que recebe a mensagem do cliente
            String mensagem = new String(recebePacote.getData());
            System.out.println(mensagem);

            InetAddress EnderecoIP = recebePacote.getAddress();

            int port = recebePacote.getPort();

            String capitalizedSentence = mensagem.toUpperCase();

            enviaDado = capitalizedSentence.getBytes();

            DatagramPacket enviaPacote = new DatagramPacket(enviaDado,
                    enviaDado.length, EnderecoIP, port);

            System.out.print("Enviando " + capitalizedSentence + "...");

            socketServidor.send(enviaPacote);
            System.out.println("Recebido\n");

            if (!capitalizedSentence.equals("SAIR")) {
                System.out.println("Encerrando servidor...");
                break;
            }
        }
    }
}
