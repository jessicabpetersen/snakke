package servidorsnakke;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Model.Conta;

/**
 *
 * @author aparicio da silva
 */
public class DaoConta {

//    private Conta2 conta;
    public Conta salvar(Conta conta) {
        EntityManager entityManager = getEntityManager();

        try {
            entityManager.getTransaction().begin();
            System.out.println("Salvando a conta.");
            if (consultarPorConta(conta.getProprietario().getUsuario()) == null) {
                //Salva os dados
                entityManager.persist(conta);
            } else {
                //Atualiza os dados
                conta = entityManager.merge(conta);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {            
            System.err.println(e);
//            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return conta;
    }

    public void excluir(String id) {
        EntityManager entityManager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a pessoa na base de dados através do seu ID.
            Conta conta = entityManager.find(Conta.class, id);
            System.out.println("Excluindo os dados de: " + conta);
            // Remove a pessoa da base de dados.
            entityManager.remove(conta);
            // Finaliza a transação.
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public Conta consultarPorConta(String id) {
        EntityManager entityManager = getEntityManager();
        Conta conta = null;
        try {
            conta = entityManager.find(Conta.class, id);
        } finally {
            entityManager.close();
        }
        return conta;
    }

    public List<Conta> consultarContas() {
        EntityManager entityManager = getEntityManager();
        List<Conta> contas = null;
        try {
            contas = entityManager.createQuery("FROM public.conta").getResultList();
        } finally {
            entityManager.close();
        }
        return contas;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("servidorSnakkePU");
        return factory.createEntityManager();
    }
}
