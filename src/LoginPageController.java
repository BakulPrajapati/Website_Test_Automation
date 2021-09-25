
public class LoginPageController {

	static void loginPageController() {

		LoginPage.correctId_correctPass();
		LoginPage.correctId_wrongPass();
		LoginPage.correctPass_wrongId();
		LoginPage.passwordEmpty();
		LoginPage.userIdEmpty();
		LoginPage.ManagerHomePage();
		LoginPage.driverClose();
	}

}
