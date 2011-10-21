package ic.doc.cpp.student.client;

public class SettingsNavigationPaneSectionData {

	  private static NavigationPaneRecord[] records;

	  public static NavigationPaneRecord[] getRecords() {
		if (records == null) {
		  records = getNewRecords();
		}
		return records;
	  }

	  public static NavigationPaneRecord[] getNewRecords() {
		return new NavigationPaneRecord[]{
		  new NavigationPaneRecord("all", "All", new AccountsView.Factory(), null),
		  new NavigationPaneRecord("it", "IT", new AccountsView.Factory(), null),
		  new NavigationPaneRecord("bank", "Bank", new AccountsView.Factory(), null)
		};
	  }
	}
