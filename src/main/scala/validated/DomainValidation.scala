package validated

sealed trait DomainValidation {
  def errorMessage: String
}

case object UsernameHasSpecialCharacters extends DomainValidation {
  def errorMessage: String = "Username cannot contain special characters."
}

case object PasswordDoesNotMeetCriteria extends DomainValidation {
  def errorMessage: String = "Password must be at least 10 characters long, including an uppercase and a lowercase letter, one number and one special character."
}

case object AgeIsInvalid extends DomainValidation {
  def errorMessage: String = "You must be aged 18 and not older than 75 to use our services."
}
