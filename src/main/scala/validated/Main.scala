package validated

import cats.data.{NonEmptyList, Validated}

object Main extends App {
  private val value: Validated[NonEmptyList[DomainValidation], RegistrationData] = FormValidator.validateForm(
    username = "fakeUs3rname",
    password = "password",
    age = 15,
  )
  println(value)
}
