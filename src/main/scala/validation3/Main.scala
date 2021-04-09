package validation3

object Main extends App {
  private val result1 = FormValidatorNec.validateForm(
    username = "Joe",
    password = "Passw0r$1234",
    age = 21,
  )
  println(result1)

  private val result2 = FormValidatorNec.validateForm(
    username = "Joe%%%",
    password = "password",
    age = 21,
  )
  println(result2)

}
