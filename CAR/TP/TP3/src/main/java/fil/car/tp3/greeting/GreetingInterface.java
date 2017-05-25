package fil.car.tp3.greeting;

import java.io.Serializable;

/**
 * Interface des greeting qui retourne des message 
 * @author antoine
 *
 */
public interface GreetingInterface extends Serializable {

	/**
	 * Retourne le message 
	 * @return le message
	 */
	public String getWho();
}
