import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Book } from '../book.model';

@Injectable({
  providedIn: 'root',
})
export class AuthServices {
  baseurl = 'http://localhost:8080/user';

  constructor(private http: HttpClient, private router: Router) { }

  userRegister(input: NgForm) {
    this.http.post('http://localhost:8080/user/', input).subscribe({
      next: (response) => this.afterPostUser(response),
      error: (error) => { console.log(error); Swal.fire("Oops...", "Something went wrong!", "error"); },
    });
  }

  afterPostUser(input: any) {
    console.log(input);
    if (input.status) {
      Swal.fire(`Thank You ${input.user.fullName}`, `You have registered succesfully!`, `success`)
    } else {
      Swal.fire("Oops...", "Something went wrong!", "error");
    }
  }

  userLogin(form: NgForm) {
    this.http.post('http://localhost:8080/user/login', form).subscribe({
      next: (response) => this.afterLoginUser(response),
      error: (error) => console.log(error),
    });
  }

  userLoginWithOtp(input: NgForm) {
    console.log(input,1)
    this.http.post('http://localhost:8080/user/loginWithOtp',input).subscribe({
      next: (response) => this.sentOtpUser(response),
      error: (error) => console.log(error),
    });
  }

  sentOtpUser(input: any) {
    if (input.status) {
      Swal.fire(`Login OTP sent to your email`, `Please check your email box`, `success`)
    } else {
      Swal.fire("Oops...", "We are very sorry , due to some error you could not be logged in", "error");
    }
  }

  verifyUserWithOtp(input: any) {
    this.http.post(`http://localhost:8080/user/verifyOtp/${input.email}/${input.otp}`, input).subscribe({
      next: (response) => this.afterLoginUser(response),
      error: (error) => console.log(error),
    });
  }

  afterLoginUser(input: any) {
    console.log(input, "loginyeyey")
    if (input.status) {
      localStorage.setItem('jwtToken', JSON.stringify(input.token));
      Swal.fire(`Thank You`, `You have logged in succesfully!`, `success`).then(() =>
        window.location.reload()
       // this.router.navigateByUrl('/books')
      )
    } else {
      Swal.fire("Oops...", "We are very sorry , due to some error you could not be logged in", "error");
    }
  }
}
