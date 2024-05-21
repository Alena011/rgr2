package mybeans.data_sheet_graph;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class GraphInfo extends SimpleBeanInfo {
    private PropertyDescriptor[] propertyDescriptors;

    public GraphInfo() {
        try {
            propertyDescriptors = new PropertyDescriptor[]
                    {
                            new PropertyDescriptor("color", Graph.class),
                            new PropertyDescriptor("filled", Graph.class),
                            new PropertyDescriptor("deltaX", Graph.class),
                            new PropertyDescriptor("deltaY", Graph.class)
                    };
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return propertyDescriptors;
    }

}