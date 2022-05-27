import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import { Book } from "../book.model";

@Injectable({
  providedIn: 'root'
})

export class AuthServices {

  baseurl = "http://localhost:8080/user"

  constructor(private http: HttpClient, private router: Router) {
  }

  userRegister(input: NgForm) {
    this.http.post("http://localhost:8080/user/", input)
      .subscribe({
        next: (response) => this.afterPostUser(response), error: (error) => console.log(error),
      });
  }

  afterPostUser(input: any) {
    console.log(input)
    if (input.status) {
      alert("You have registered successfully")
    } else {
      alert("We are very sorry , due to some error you could not be registered")
    }
  }

  userLogin(form: NgForm) {
    this.http.post("http://localhost:8080/user/login", form).subscribe({
      next: (response) => this.afterLoginUser(response), error: (error) => console.log(error),
    });
  }

  afterLoginUser(input: any) {
    console.log(input, "loginyeyey")
    if (input.status) {
      localStorage.setItem("jwtToken", JSON.stringify(input.token))
      alert("You have logged in successfully")
    } else {
      alert("We are very sorry , due to some error you could not be logged in")
    }
  }

}