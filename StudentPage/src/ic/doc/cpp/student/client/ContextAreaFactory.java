package ic.doc.cpp.student.client;

import com.smartgwt.client.widgets.Canvas;

public interface ContextAreaFactory {
	
  Canvas create();

  String getID();

  String getDescription();
}