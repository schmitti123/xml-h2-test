package de.schmitti123;

import de.schmitti123.xml.JAXBUtil;
import de.schmitti123.xml.XMLObject;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mschmitt on 06.01.16.
 */
public class WriteXmlTest {

    @Test
    public void writeXml() throws Exception{
        XMLObject o = new XMLObject();


        Calendar cal = Calendar.getInstance();
        cal.set(2016, Calendar.JANUARY, 6, 19, 00);

        List<XMLObject.Times> times = new ArrayList<XMLObject.Times>();

        for (int i = 0; i<10; i++) {
            XMLObject.Times time = new XMLObject.Times();
            time.setDesc(generateTimes(cal.getTime(), i*5).toString());
            time.setTime(generateTimes(cal.getTime(), i*5));
            times.add(time);

        }
        o.setTimes(times);

        File file = new File("TestObject.xml");

        JAXBUtil.objectToFile(file, o);

    }

    private Date generateTimes(Date startDate, int additionalMinutes) {


        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.add(Calendar.MINUTE, additionalMinutes);

        return cal.getTime();

    }

}
