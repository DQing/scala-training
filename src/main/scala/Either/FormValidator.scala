package validation1

sealed trait FormValidator {
  def validateUserName(userName: String): Either[DomainValidation, String] =
    Either.cond(
      userName.matches("^[a-zA-Z0-9]+$"),
      userName,
      UsernameHasSpecialCharacters,
    )

  def validatePassword(password: String): Either[DomainValidation, String] =
    Either.cond(
      password.matches("(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"),
      password,
      PasswordDoesNotMeetCriteria,
    )

  def validateAge(age: Int): Either[DomainValidation, Int] =
    Either.cond(
      age >= 18 && age <= 75,
      age,
      AgeIsInvalid,
    )

  def validateForm(
    username: String,
    password: String,
    age: Int): Either[DomainValidation, RegistrationData] =
    validateUserName(username)
      .flatMap(validatedUserName =>
        validatePassword(password)
          .flatMap(validatedPassword =>
            validateAge(age)
              .map(validatedAge => RegistrationData(validatedUserName, validatedPassword, validatedAge))
          )
      )

}

object FormValidator extends FormValidator
