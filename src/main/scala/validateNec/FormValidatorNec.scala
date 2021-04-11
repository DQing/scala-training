package validation3

import cats.data.{NonEmptyChain, Validated, ValidatedNec}
import cats.syntax.all._
import cats.implicits.catsSyntaxValidatedIdBinCompat0

sealed trait FormValidatorNec {

  private def validateUserName(userName: String): ValidatedNec[DomainValidation, String] =
    if (userName.matches("^[a-zA-Z0-9]+$")) userName.validNec else UsernameHasSpecialCharacters.invalidNec

  private def validatePassword(password: String): ValidatedNec[DomainValidation, String] =
    if (password.matches("(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")) password.validNec
    else PasswordDoesNotMeetCriteria.invalidNec

  private def validateAge(age: Int): Validated[NonEmptyChain[DomainValidation], Int] =
    if (age >= 18 && age <= 75) age.validNec else AgeIsInvalid.invalidNec

  def validateForm(
    username: String,
    password: String,
    age: Int): ValidatedNec[DomainValidation, RegistrationData] =
    (validateUserName(username), validatePassword(password), validateAge(age)).mapN(RegistrationData)

}

object FormValidatorNec extends FormValidatorNec