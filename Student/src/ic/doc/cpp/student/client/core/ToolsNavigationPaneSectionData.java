package ic.doc.cpp.student.client.core;

public class ToolsNavigationPaneSectionData {

	  private static ListgridSectionStackSectionRecord[] records;

	  public static ListgridSectionStackSectionRecord[] getRecords() {
		if (records == null) {
		  records = getNewRecords();
		}
		return records;
	  }

	  public static ListgridSectionStackSectionRecord[] getNewRecords() {
		return new ListgridSectionStackSectionRecord[]{
		  new ListgridSectionStackSectionRecord("calendar", "Calendar", null),
		  new ListgridSectionStackSectionRecord("automatch", "Company Auto Match", null),
		};
	  }
	}
