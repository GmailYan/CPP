package ic.doc.cpp.student.client.login;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SignInPageView extends ViewImpl implements
		SignInPagePresenter.MyView {

	 private static String html = "<div>\n"
			    + "<table align=\"center\">\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n"    
			    + "    <td colspan=\"2\" style=\"font-weight:bold;\">Sign In</td>\n"
			    + "  </tr>\n"
			    + "  <tr>\n"
			    + "    <td>User name</td>\n"
			    + "    <td id=\"userNameFieldContainer\"></td>\n"    
			    + "  </tr>\n" 
			    + "  <tr>\n"
			    + "    <td>Password</td>\n"
			    + "    <td id=\"passwordFieldContainer\"></td>\n"    
			    + "  </tr>\n" 
			    + "  <tr>\n"
			    + "    <td></td>\n"
			    + "    <td id=\"signInButtonContainer\"></td>\n"  
			    + "  </tr>\n"     
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n"
			    + "    <td colspan=\"2\">Forget your password?</td>\n"
			    + "  </tr>\n"
			    + "  <tr>\n"
			    + "    <td colspan=\"2\">Contact your CPP Administrator.</td>\n"
			    + "  </tr>\n"    
			    + "</table>\n"
			    + "</div>\n";

	 HTMLPanel panel = new HTMLPanel(html);

	 private final TextBox userNameField;
	 private final PasswordTextBox passwordField;
	 private final Button signInButton;

	 @Inject
	 public SignInPageView() {
		 userNameField = new TextBox();
		 passwordField = new PasswordTextBox();
		 signInButton = new Button("Sign in");

		 userNameField.setText("dzz");
		 passwordField.setText("N0More$ecret");

		 panel.add(userNameField, "userNameFieldContainer");
		 panel.add(passwordField, "passwordFieldContainer");
		 panel.add(signInButton, "signInButtonContainer");
	 }

	 @Override
	 public Widget asWidget() {
		 return panel;
	 }

	 @Override
	 public String getUserName() {
		 return userNameField.getText();
	 }
  
	 @Override
	 public String getPassword() {
		 return passwordField.getText();
	 }  

	 @Override
	 public Button getSignInButton() {
		 return signInButton;
	 }

	 @Override
	 public void resetAndFocus() {
		 userNameField.setFocus(true);
		 userNameField.selectAll();
	 }
}
