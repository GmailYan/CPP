package ic.doc.cpp.student.client;

public class ResourceCentreNavigationPaneSectionData {

	  private static NavigationPaneRecord[] records;

	  public static NavigationPaneRecord[] getRecords() {
		if (records == null) {
		  records = getNewRecords();
		}
		return records;
	  }
	 
	  public static NavigationPaneRecord[] getNewRecords() {
		return new NavigationPaneRecord[]{
		  new NavigationPaneRecord("highlights", "Highlights", new AccountsView.Factory(), null),
		  new NavigationPaneRecord("sales", "Sales", new AccountsView.Factory(), null),
		  new NavigationPaneRecord("settings", "Settings", new AccountsView.Factory(), null)
		};
	  }
	}
