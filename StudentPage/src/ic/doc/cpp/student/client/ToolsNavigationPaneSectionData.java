package ic.doc.cpp.student.client;

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
		  new ListgridSectionStackSectionRecord("calendar", "Calendar", new CalendarView.Factory(), null),
		  new ListgridSectionStackSectionRecord("automatch", "Company Auto Match", new CalendarView.Factory(), null),
		};
	  }
	}
