package servidorsnakke;

import Model.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Model.Conta;
/**
 *
 * @author aparicio da silva
 */
public class ServidorSnakke {

    public static void main(String[] args) throws IOException, InterruptedException {
        Principal principal = new Principal();
        principal.start();
       // teste();

    }
    public static void teste(){
        
        Usuario user = new Usuario("nome1", true, "10", "user6");
        DaoUsuario daou = new DaoUsuario();
        DaoConta daoc = new DaoConta();
        user = daou.salvar(user);
        System.out.println(user.getNome());
        Conta conta =new Conta();
        conta.setEmail("email");
        conta.setSenha("senha");
        conta.setTelefone("fone");
        conta.setProprietario(daou.consultarPorUsuario("user6"));
        conta.addContato(daou.consultarPorUsuario("user"));
        conta.addContato(daou.consultarPorUsuario("user2"));
        conta.addContato(daou.consultarPorUsuario("user4"));
        conta = daoc.salvar(conta);
        System.out.println(conta.getEmail());
        System.out.println(conta.getProprietario().getNome());
        System.out.println(conta.getProprietario().getUsuario());
        System.out.println(conta.getSenha());
        
//        for (Usuario object : dao.consultarUsuarios()) {
//        System.out.println(object.getUsuario());            
//        System.out.println(object.getNome());            
//        System.out.println(object.getIp());            
//        }
        
    }

}
