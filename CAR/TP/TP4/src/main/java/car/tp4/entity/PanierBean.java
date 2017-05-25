package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class PanierBean {

  @PersistenceContext(unitName = "panier-pu")
  private EntityManager entityManager;
  
  
  protected EntityManager getEntityManager() {
      return entityManager;
  }


  /**
   * Sauve la commande dans la base de donnee
   *
   * @param p commande a sauver dans la base de donnee
   */
  public void save(Panier p) {
      this.getEntityManager().persist(p);
  }

  public List<Panier> getAllPanier(){
	  Query query = entityManager.createQuery("SELECT p FROM Panier as p");
	  return query.getResultList();
  }

}