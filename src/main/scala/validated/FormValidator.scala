package validation2

import cats.data._
import cats.implicits._

sealed trait FormValidator {
  def validateUserName(userName: String): Either[List[DomainValidation], String] =
    Either
      .cond(
        userName.matches("^[a-zA-Z0-9]+$"),
        userName,
        List(UsernameHasSpecialCharacters),
      )

  def validatePassword(password: String): Either[List[DomainValidation], String] =
    Either
      .cond(
        password.matches("(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"),
        password,
        List(PasswordDoesNotMeetCriteria),
      )

  def validateAge(age: Int): Either[List[DomainValidation], Int] =
    Either
      .cond(
        age >= 18 && age <= 75,
        age,
        List(AgeIsInvalid),
      )

  def validateForm(
    username: String,
    password: String,
    age: Int): Validated[List[DomainValidation], RegistrationData] = {

    (validateUserName(username).toValidated, validatePassword(password).toValidated, validateAge(age).toValidated).mapN(RegistrationData)
  }
}

object FormValidator extends FormValidator
// do some refactor