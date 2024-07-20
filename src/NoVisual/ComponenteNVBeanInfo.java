/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NoVisual;


import java.beans.*;


public class ComponenteNVBeanInfo extends SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(ComponenteNV.class);
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor elements = new PropertyDescriptor("elements", ComponenteNV.class);
            return new PropertyDescriptor[]{elements};
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}