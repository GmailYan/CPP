package ic.doc.cpp.student.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.ListGridEditEvent;  
import com.smartgwt.client.types.MultipleAppearance;  
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.grid.*; 
import com.smartgwt.client.widgets.layout.VLayout;

public class GeneralInformationView extends VLayout {
	private static final String DESCRIPTION = "GeneralInformationView";
	
	public GeneralInformationView() {
		final ListGrid countryGrid = new ListGrid();  
        countryGrid.setWidth("100%");  
        countryGrid.setHeight("50%");  
        countryGrid.setShowAllRecords(true);  
  
        ListGridField nameField = new ListGridField("name", "Name", 120);  
        nameField.setCanEdit(false);  
  
        ListGridField valueField = new ListGridField("value", "Value Field", 170);  
  
        countryGrid.setFields(nameField, valueField);  
  
        countryGrid.setData(getData());  
        countryGrid.setEditorCustomizer(new ListGridEditorCustomizer() {  
            public FormItem getEditor(ListGridEditorContext context) {  
                ListGridField field = context.getEditField();  
                if (field.getName().equals("value")) {  
                    NameValueRecord record = (NameValueRecord) context.getEditedRecord();  
                    int id = record.getID();  
                    switch (id) {  
                        case 1:  
                            TextItem textItem = new TextItem();  
                            textItem.setShowHint(true);  
                            textItem.setShowHintInField(true);  
                            textItem.setHint("Some Hint");  
                            return textItem;  
                        case 2:  
                            return new PasswordItem();  
                        case 3:  
                            return new DateItem();  
                        case 4:  
                            CheckboxItem cbi = new CheckboxItem();  
                            cbi.setShowLabel(false);  
                            return cbi;  
                        case 5:  
                            IntegerItem integerItem = new IntegerItem();  
                            integerItem.setMask("###");  
                            return integerItem;  
                        case 6:  
                            SelectItem selectItemMultipleGrid = new SelectItem();  
                            selectItemMultipleGrid.setShowTitle(false);  
                            selectItemMultipleGrid.setMultiple(true);  
                            selectItemMultipleGrid.setMultipleAppearance(MultipleAppearance.PICKLIST);  
                            selectItemMultipleGrid.setValueMap("Cat", "Dog", "Giraffe", "Goat", "Marmoset", "Mouse");  
                            return selectItemMultipleGrid;  
                        case 7:  
                            SliderItem sliderItem = new SliderItem();  
                            sliderItem.setMaxValue(10);  
                            sliderItem.setWidth(160);  
                            return sliderItem;  
                        default:  
                            return context.getDefaultProperties();  
                    }  
                }  
                return context.getDefaultProperties();  
            }  
        });  
  
        countryGrid.setCanEdit(true);  
        countryGrid.setEditEvent(ListGridEditEvent.CLICK);  
        countryGrid.setEditByCell(true);  
  
        this.addMember(countryGrid);
        Button updateButton = new Button();
        updateButton.setTitle("Update");
        updateButton.setVisible(true);
        this.addMember(updateButton);
	}
	
    private ListGridRecord[] getData() {  
        return new ListGridRecord[]{  
                new NameValueRecord(1, "Name", "Zhouzhou Du"),  
                new NameValueRecord(2, "Gender", "Male"),  
                new NameValueRecord(3, "Date of Birth", new Date()),  
                new NameValueRecord(4, "Married", Boolean.FALSE)  
        };  
    }  
  
    public static class NameValueRecord extends ListGridRecord {  
  
        public NameValueRecord(int id, String name, Object value) {  
            setID(id);  
            setName(name);  
            setValue(value);  
        }  
  
        public void setID(int id) {  
            setAttribute("ID", id);  
        }  
  
        public int getID() {  
            return getAttributeAsInt("ID");  
        }  
  
        public void setValue(Object value) {  
            setAttribute("value", value);  
        }  
  
        public Object getValue() {  
            return getAttributeAsObject("value");  
        }  
  
        public void setName(String name) {  
            setAttribute("name", name);  
        }  
  
        public String getName() {  
            return getAttribute("name");  
        }  
  
    }  
	
	  public static class Factory implements ContextAreaFactory {
			
			private String id;
			public static Canvas view;
			
			public Canvas create() {
			  if(view == null)
				 view = new GeneralInformationView();
			  id = view.getID();
				      
			  GWT.log("GeneralInformationView.Factory.create()->view.getID() - " + id, null);
		      return view;
		    }

			public String getID() {
			  return id;
			}

		    public String getDescription() {
		      return DESCRIPTION;
		    }
		  }
}
