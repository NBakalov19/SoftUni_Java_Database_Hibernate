package interfaces;

import java.io.Serializable;

public interface Label extends Serializable {

  long getId();

  void setId(int id);

  String getTitle();

  void setTitle(String title);

  String getSubtitle();

  void setSubtitle(String subtitle);
}
