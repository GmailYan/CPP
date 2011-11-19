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
				new ListgridSectionStackSectionRecord("opportunity", NameTokens.eventfeed, "News Feed", null),
				new ListgridSectionStackSectionRecord("profile", NameTokens.profile, "User Profile", null),
				new ListgridSectionStackSectionRecord("calendar", NameTokens.calendar, "Calendar", null),
				new ListgridSectionStackSectionRecord("automatch", NameTokens.automatch, "Company Auto Match", null)
		};
	  }
	}
