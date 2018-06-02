package models.service

import javax.inject.Singleton

@Singleton
class LoginService {

  def verifyLogin(username: String, password: String): Boolean = {
    username.equals("admin") && password.equals("password")
  }

}
