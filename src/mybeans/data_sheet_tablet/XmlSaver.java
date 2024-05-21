package mybeans.data_sheet_tablet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlSaver {

    public static void marshalDataToXML(String fileName, Datasheet dataSheet) throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(Datasheet.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dataSheet, new File(fileName));

    }

    public static Datasheet unmarshalOutXMLs(String filePath) throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(Datasheet.class).createUnmarshaller();
        Object element = unmarshaller.unmarshal(new File(filePath));
        if (element instanceof Datasheet) {
            return (Datasheet) element;
        } else throw new JAXBException("is not DataSheet, check your filePath!!");
    }
}