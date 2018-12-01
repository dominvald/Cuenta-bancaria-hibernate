package resources;



import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Alberto Domínguez Morán
 */
public class HibernateUtil {
	/**
	 * La necesitamos para crear la SessionFactory, que hará la conexión con la base de datos,
	 * la declaramos como estática porque una vez creada e iniciada la utilizaremos para crear
	 * nuestras entitymanager, y la mantedremos activa todo el tiempo, pasándola de un lado a otro
	 * pasando la SessionFactory con el método getSessionFactory():
	 */
	private static SessionFactory sessionFactory=null;
	/**
	 * Log para toda la aplicación
	 */
	private final static Logger LOG = Logger.getLogger("file_data");

	static {
		try {
			//Configuramos hibernate
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("META-INF/hibernate.cfg.xml").build();
			//Configuramos los metadatos de hibernate
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			//Creamos la SessionFactory
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		} catch (Throwable th) {
			LOG.fatal("La creación de SessionFactory inicial ha fallado." + th);
		}
	}
/**
 * 
 * @return Devuelve la SessionFactory
 */
	public static SessionFactory getSessionFactory() {

		return sessionFactory;

	}
}
