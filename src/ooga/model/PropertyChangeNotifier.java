package ooga.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropertyChangeNotifier {
  protected PropertyChangeSupport propertyChangeSupport;

  public PropertyChangeNotifier(Object sourceBean)
  {
    propertyChangeSupport = new PropertyChangeSupport(sourceBean);
  }

  public void addListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  public void removeListener(PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
  }
}
