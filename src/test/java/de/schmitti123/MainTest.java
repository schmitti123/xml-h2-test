package de.schmitti123;

import de.schmitti123.db.DBObject;
import de.schmitti123.db.ObjectDAO;
import de.schmitti123.xml.JAXBUtil;
import de.schmitti123.xml.XMLObject;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.util.TimeZone;

/**
 * Created by mschmitt on 06.01.16.
 */
public class MainTest {

    private static final Logger LOGGER = Logger.getLogger(MainTest.class);

    ObjectDAO dao;

    @Before
    public void init () throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xml-h2-test");
        EntityManager em = emf.createEntityManager();



        dao = new ObjectDAO(em);


    }


    @Test
    public void readAllTest() throws Exception {
        for (DBObject o : dao.readAll()) {
            LOGGER.info("DBTIME (UTC): " + o.getTime());

        }

    }

    @Test
    public void readAndPersistXmlTest() throws Exception {
        File f = new File(this.getClass().getResource("/TestObject.xml").toURI());
        LOGGER.info(f.getAbsolutePath());
        XMLObject xmlObject = JAXBUtil.fileToObject(f, XMLObject.class);

        dao.persistXmlObject(xmlObject);

    }

    @Test
    public void findAcuteTimeTest() throws Exception {
        LOGGER.info(dao.findAcuteTime());


    }


}
