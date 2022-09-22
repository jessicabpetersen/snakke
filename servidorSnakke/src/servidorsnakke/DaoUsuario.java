package servidorsnakke;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Model.Usuario;

/**
 *
 * @author aparicio da silva
 */
public class DaoUsuario {

    public Usuario salvar(Usuario usuario) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            System.out.println("Salvando a pessoa.");
            if (entityManager.find(Usuario.class, usuario.getUsuario()) == null) {
                entityManager.persist(usuario);
            } else {
                usuario = entityManager.merge(usuario);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return usuario;
    }

    public void excluir(String id) {
        
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Usuario usuario = entityManager.find(Usuario.class, id);
            System.out.println("Excluindo os dados de: " + usuario.getNome());
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public Usuario consultarPorUsuario(String id) {
        
        EntityManager entityManager = getEntityManager();
        Usuario usuario = null;
        try {
            usuario = entityManager.find(Usuario.class, id);
        } finally {
            entityManager.close();
        }
        return usuario;
    }

//    public List<Usuario> consultarUsuarios() {
//        
//        EntityManager entityManager = getEntityManager();
//        List<Usuario> usuarios = null;
//        try {
//            usuarios = entityManager.createQuery("FROM Usuario").getResultList();
//        } finally {
//            entityManager.close();
//        }
//        return usuarios;
//    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("servidorSnakkePU");
        EntityManager retorno = factory.createEntityManager();
//        factory.close();
        return retorno;
    }
}
