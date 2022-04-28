import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//Classe do Cliente
public class Cliente {

    public static void main(String args[]) throws Exception {

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                System.in));

        DatagramSocket clienteSocket = new DatagramSocket();

        //nome do servidor
        String servidor = "localhost";
        //porta onde realiza a conexão
        int porta = 6666;

        InetAddress IPAddress = InetAddress.getByName(servidor);

        byte[] enviaDado;
        byte[] recebeDado = new byte[1024];

        System.out.println("Digite o texto para ser enviado: ");
        String texto = "";

        while (!texto.equals("Sair")) {
            texto = inFromUser.readLine();
            enviaDado = texto.getBytes();

            DatagramPacket enviaPacote = new DatagramPacket(enviaDado,
                    enviaDado.length, IPAddress, porta);

            System.out.println("Enviando pacote para " + servidor + ": " + porta);
            clienteSocket.send(enviaPacote);

            DatagramPacket recebePacote = new DatagramPacket(recebeDado,
                    recebeDado.length);

            clienteSocket.receive(recebePacote);
            System.out.println("Mensagem recebida...");

            String recebeTexto = new String(recebePacote.getData());

            System.out.println("Texto recebido do servidor:" + recebeTexto);
        }

        clienteSocket.close();
        System.out.println("Encerrando socket...");
    }
}
