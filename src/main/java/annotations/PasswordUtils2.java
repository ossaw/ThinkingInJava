package annotations;

public class PasswordUtils2 {

	@UseCase2(id = 45, desc = "Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return password.matches("\\w*\\d\\w*");
	}

	@UseCase2(id = 46)
	public String encryptPassword(String password) {
		StringBuilder sb = new StringBuilder(password);
		return sb.reverse().toString();
	}

	@UseCase2(id = 47, desc = "new password can not contain old password")
	public boolean checkPassword(String oldPassword, String newPassword) {
		return newPassword.contains(oldPassword);
	};

}
