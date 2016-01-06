package de.schmitti123.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by mschmitt on 06.01.16.
 */
public class JAXBUtil {

    public static <U> U fileToObject(File file, Class<?>... clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller um = context.createUnmarshaller();
        U object = (U) um.unmarshal(file);
        return object;
    }

    public static <U> U streamToObject(InputStream stream, Class<?>... clazz) throws JAXBException {
        if (stream == null) {
            throw new IllegalArgumentException("Inputstream is null");
        }

        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller um = context.createUnmarshaller();
        U object = (U) um.unmarshal(stream);
        return object;
    }


    public static <U> U stringToObject(String string, Class<?>... clazz) throws JAXBException {


        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller um = context.createUnmarshaller();
        StringReader reader = new StringReader(string);
        U object = (U) um.unmarshal(reader);
        return object;
    }

    public static String objectToXml(Object o) throws JAXBException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(o, os);
        return os.toString();
    }

    public static void objectToFile(File f, Object o) throws JAXBException, IOException {
        FileOutputStream os = new FileOutputStream(f);

        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(o, os);

    }




}
