import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Book } from "../book.model";
import { AllBookResponse, Comman, SingleBookResponse } from "../response.model";
@Injectable({
    providedIn: 'root'
})

export class HomeServices {

    loginStatus: boolean = false;

    constructor(private http: HttpClient) { }

    changeLoginStatus() {
        this.loginStatus = true
    }

    fetchUser(): Observable<any> {
        let token: any = localStorage.getItem("jwtToken");
        let message = token = JSON.parse(token);
        if (!token) {
            return message;
        }
        const httpOptions = {
            headers: new HttpHeaders({
                'x-api-key': "I am coming from frontend",
                'Authorization': `Bearer ${token}`
            })
        };
        return this.http.get("http://localhost:8080/user/fetchUser", httpOptions);
    }
}
