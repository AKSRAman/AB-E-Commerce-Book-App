import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthServices } from './auth.services';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  isLogin = true;

  constructor(private auth: AuthServices) { }

  ngOnInit(): void {

  }

  onSwitchMode() {
    this.isLogin = !this.isLogin;
  }

  onLogin(form: NgForm) {
    console.log(form.value,"amanform login")
    this.auth.userLogin(form.value)
    form.reset();
  }

  onRegistraion(form: NgForm) {
    console.log(form.value,"amanform register")
    this.auth.userRegister(form.value)
    form.reset();
  }


}
