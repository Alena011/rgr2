package mybeans.data_sheet_tablet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DTPListType", propOrder = {
        "data"
})
public class Datasheet {
    private List<Data> data;

    public Datasheet() {
        data = new ArrayList<>();
    }

    public void addDataItem(Data item) {
        data.add(item);
    }

    public Data getDataItem(int pos) {
        return data.get(pos);
    }

    public void removeDataItem(int pos) {
        data.remove(pos);
    }

    public int size() {
        return data.size();
    }
}