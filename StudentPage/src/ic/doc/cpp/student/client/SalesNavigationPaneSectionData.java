package ic.doc.cpp.student.client;

public class SalesNavigationPaneSectionData {
	
  private static NavigationPaneRecord[] records;

  public static NavigationPaneRecord[] getRecords() {
	if (records == null) {
	  records = getNewRecords();
	}
	return records;
  }

  public static NavigationPaneRecord[] getNewRecords() {
	return new NavigationPaneRecord[]{
	  new NavigationPaneRecord("generalInformation", "GeneralInformation", new AccountsView.Factory(), null),
	  new NavigationPaneRecord("interestedArea", "Interested Area", new AccountsView.Factory(), null),
	  new NavigationPaneRecord("registerCV", "Register CV", new AccountsView.Factory(), null),
	  new NavigationPaneRecord("calendar", "Calendar", new CalendarView.Factory(), null),
	};
  }
}