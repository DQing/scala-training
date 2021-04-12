package validated

import cats.data._
import cats.implicits._

sealed trait FormValidator {
  def validateUserName(userName: String): Validated[NonEmptyList[DomainValidation], String] =
    Either
      .cond(
        userName.matches("^[a-zA-Z0-9]+$"),
        userName,
        NonEmptyList.of(UsernameHasSpecialCharacters),
      )
      .toValidated

  def validatePassword(password: String): Validated[NonEmptyList[DomainValidation], String] =
    if (password.matches("(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")) password.validNel
    else PasswordDoesNotMeetCriteria.invalidNel

  def validateAge(age: Int): Validated[NonEmptyList[DomainValidation], Int] =
    if (age >= 18 && age <= 75) age.validNel else AgeIsInvalid.invalidNel

  def validateForm(
    username: String,
    password: String,
    age: Int): Validated[NonEmptyList[DomainValidation], RegistrationData] =
    (validateUserName(username), validatePassword(password), validateAge(age)).mapN(RegistrationData)
}

object FormValidator extends FormValidator
