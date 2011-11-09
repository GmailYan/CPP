package ic.doc.cpp.student.client.core;

import ic.doc.cpp.student.client.place.NameTokens;

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
				new ListgridSectionStackSectionRecord(NameTokens.profile, "User Profile", null),
				new ListgridSectionStackSectionRecord(NameTokens.calendar, "Calendar", null),
				new ListgridSectionStackSectionRecord(NameTokens.automatch, "Company Auto Match", null)
		};
	  }
	}
