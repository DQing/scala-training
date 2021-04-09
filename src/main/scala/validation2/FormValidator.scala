package validation2

import cats.data._
import cats.implicits._

sealed trait FormValidator {
  def validateUserName(userName: String): Validated[DomainValidation, String] =
    Either
      .cond(
        userName.matches("^[a-zA-Z0-9]+$"),
        userName,
        UsernameHasSpecialCharacters,
      )
      .toValidated

  def validatePassword(password: String): Validated[DomainValidation, String] =
    Either
      .cond(
        password.matches("(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"),
        password,
        PasswordDoesNotMeetCriteria,
      )
      .toValidated

  def validateAge(age: Int): Validated[DomainValidation, Int] =
    Either
      .cond(
        age >= 18 && age <= 75,
        age,
        AgeIsInvalid,
      )
      .toValidated

  def validateForm(
    username: String,
    password: String,
    age: Int): Validated[DomainValidation, RegistrationData] = {

    for {
      validatedUserName <- validateUserName(username)
      validatedPassword <- validatePassword(password)
      validatedAge      <- validateAge(age)
    } yield RegistrationData(validatedUserName, validatedPassword, validatedAge)
  }

  // error: value flatMap is not a member of cats.data.Validated[repl.MdocSession.App.DomainValidation,String]
  //     validatedUserName <- validateUserName(username)
  //

}

object FormValidator extends FormValidator
