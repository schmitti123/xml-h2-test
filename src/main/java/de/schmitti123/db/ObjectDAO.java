package de.schmitti123.db;

import de.schmitti123.xml.XMLObject;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by mschmitt on 06.01.16.
 */
public class ObjectDAO {

    private static final Logger LOGGER = Logger.getLogger(ObjectDAO.class);

    EntityManager entityManager;


    public ObjectDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void persistXmlObject(XMLObject xmlObject) {
        entityManager.getTransaction().begin();


        for (XMLObject.Times time : xmlObject.getTimes()) {
            DBObject dbObject = new DBObject(time.getDesc(), time.getTime());
            entityManager.persist(dbObject);


        }


        entityManager.getTransaction().commit();
    }

    public List<DBObject> readAll() {

        TypedQuery<DBObject> dbObjectTypedQuery = entityManager.createQuery("select p from DBObject p", DBObject.class);

        return dbObjectTypedQuery.getResultList();

    }

    public DBObject findAcuteTime() {
        LOGGER.info("try to find acute plan");
        TypedQuery<Date> maxTimeQuery = entityManager.createNamedQuery(DBObject.NAMED_QUERY_MAX_TIME, Date.class);
        Date maxTime = maxTimeQuery.getSingleResult();

        LOGGER.info("max time : " + maxTime);
        TypedQuery<DBObject> dbObjectTypedQuery = entityManager.createNamedQuery(DBObject.NAMED_QUERY_ACUTE_TIME, DBObject.class);
        dbObjectTypedQuery.setParameter("lala", maxTime);

        return dbObjectTypedQuery.getSingleResult();


    }

}
