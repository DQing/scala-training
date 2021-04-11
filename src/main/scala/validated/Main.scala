package validation2

import cats.data.Validated

object Main extends App {
  private val value: Validated[List[DomainValidation], RegistrationData] = FormValidator.validateForm(
    username = "fakeUs3rname",
    password = "password",
    age = 15,
  )
  println(value)
}
