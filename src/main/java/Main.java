import com.bisoft.postgre.model.CSVItem;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import javax.persistence.metamodel.EntityType;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class Main {
	private static final SessionFactory ourSessionFactory;
	private static Driver driver = null;
	
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			ourSessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static org.hibernate.Session getSession() throws HibernateException {
		return ourSessionFactory.openSession();
	}
	
	public static void main(final String[] args) throws Exception {
		Set<EntityType<?>> entities = ourSessionFactory.getMetamodel().getEntities();
		saveModel(entities);
		
		driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "admin" ) );
		emptyNeo();
		loadTableData(entities.stream().map(EntityType::getBindableJavaType).filter(v -> !v.getSimpleName().contains("Relation")).collect(Collectors.toList()));
		loadRelationData(entities.stream().map(EntityType::getBindableJavaType).filter(v -> v.getSimpleName().contains("Relation")).collect(Collectors.toList()));
		driver.close();
		
	}
	
	private static void saveModel(Set<EntityType<?>> entities) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		final org.hibernate.Session session = getSession();
		try {
			for(EntityType entity: entities) {
				System.out.println(entity.getName());
				List result = session.createQuery(String.format("from %s", entity.getName())).list();
				SaveTable(entity.getBindableJavaType(), result);
			}
			
		} finally {
			session.close();
		}
	}
	
	private static void loadRelationData(List<Class<?>> entities) {
		try ( org.neo4j.driver.Session session = driver.session() )
		{
			for(Class<?> entity: entities) {
				String greeting = session.writeTransaction( new TransactionWork<String>()
				{
					@Override
					public String execute( Transaction tx )
					{
						String s = "";
						String fromE = "";
						String toE = "";
						String fn = null;
						String[] fields = new String[]{};
						try {
							String val = "";
							fromE = (String)entity.getField("fromEntity").get(val);
							toE = (String)entity.getField("toEntity").get(val);
							fn = (String)entity.getField("file").get(val);
							// fn = String.format("relation_%s_%s",fromE, toE);//(String)(entity.getMethod("readFileName").invoke(null, null));
							//fields = ((String) (entity.getMethod("readTitle").invoke(null, null))).split(",");
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						s = String.format("LOAD CSV WITH HEADERS FROM 'file:///pitc/%s.csv' AS row", fn);
						s += String.format(" MATCH (e:%1$s {uid: toInteger(row.from_id)}), (o:%2$s {uid: toInteger(row.to_id)}) CREATE (e)-[r:CONTAINED_INTO]->(o)"
							, fromE
							, toE);

						Result result = tx.run(s);
						
						return result.toString();
					}
				} );
				System.out.println( entity.getName() );
			}
		}
	}
	
	private static void emptyNeo() {
		try ( org.neo4j.driver.Session session = driver.session() )
		{
			String greeting = session.writeTransaction( new TransactionWork<String>()
			{
				@Override
				public String execute( Transaction tx )
				{
					Result result = tx.run("MATCH()-[n]-() delete n;");
					return result.toString();
				}
			} );
			System.out.println( greeting );
			greeting = session.writeTransaction( new TransactionWork<String>()
			{
				@Override
				public String execute( Transaction tx )
				{
					Result result = tx.run("MATCH(n) DELETE n;");
					return result.toString();
				}
			} );
		}
	}
	
	public static void loadTableData(List<Class<?>> entities)
	{
		try ( org.neo4j.driver.Session session = driver.session() )
		{
			for(Class<?> entity: entities) {
				String greeting = session.writeTransaction( new TransactionWork<String>()
				{
					@Override
					public String execute( Transaction tx )
					{
						String s = "";
						String val = "";
						String fn = null;
						String[] fields = new String[]{};
						try {
								// fn = (String)(entity.getMethod("readFileName").invoke(null, null));
								fn = (String)entity.getField("file").get(val);
							  fields = ((String) (entity.getMethod("readTitle").invoke(null, null))).split(",");
						} catch (IllegalAccessException e) {
								e.printStackTrace();
						} catch (InvocationTargetException e) {
								e.printStackTrace();
						} catch (NoSuchMethodException e) {
								e.printStackTrace();
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						s = String.format("LOAD CSV WITH HEADERS FROM 'file:///pitc/%s.csv' AS row WITH row WHERE row.uid IS NOT NULL", fn);
						String pars = Arrays.stream(fields).map(v -> String.format("%1$s: row.%1$s", v)).collect(Collectors.joining(","));
						pars = pars.replace("row.uid", "toInteger(row.uid)");
						s += String.format(" MERGE (o:%s {%s}); ", fn, pars);
						Result result = tx.run(s);
						return result.toString();
					}
				} );
				System.out.println( greeting );
			}
		}
	}
	
	private static void SaveTable(Class<?> itemClass, List<CSVItem> data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
		// String fn = (String)itemClass.getMethod("readFileName").invoke(null, null);
		String tl = (String)itemClass.getMethod("readTitle").invoke(null, null);
		String val = "";
		String fn = (String)itemClass.getField("file").get(val);
		
		
		
		fn = "C:\\Users\\Chebakov.AA\\neo4j\\import\\pitc\\" + fn + ".csv";
		try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fn), "UTF8")) { //"cp1251"
			out.write(tl + "\r\n");
			data.stream()
				.map(v -> v.readDataRow())
				.forEach(v -> {
					try {
						out.write(v + "\r\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}