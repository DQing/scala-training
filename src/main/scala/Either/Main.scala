package validation1

object Main extends App {
  private val value: Either[DomainValidation, RegistrationData] = FormValidator.validateForm(
    username = "fakeUs3rname",
    password = "password",
    age = 15,
  )
  println(value)
}
