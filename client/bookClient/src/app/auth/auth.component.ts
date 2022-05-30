import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthServices } from './auth.services';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
})
export class AuthComponent implements OnInit {
  isLogin = true;

  constructor(private auth: AuthServices) { }

  ngOnInit(): void { }

  loginOtp = false;
  buttonValue = "Login with Otp";

  onSwitchMode() {
    this.isLogin = !this.isLogin;
  }

  loginWithOtp() {
    this.loginOtp ? (this.loginOtp = false, this.buttonValue = "Login with Otp") : (this.loginOtp = true, this.buttonValue = "Login with Password")
  }

  onLogin(form: NgForm) {
    this.auth.userLogin(form.value);
    form.reset();
  }

  onRegistraion(form: NgForm) {
    this.auth.userRegister(form.value);
    form.reset();
  }

  requestOtp(form: NgForm) {
    this.auth.userLoginWithOtp(form.value);
  }

  verifyLogin(form: NgForm) {
    this.auth.verifyUserWithOtp(form.value)
  }

}
