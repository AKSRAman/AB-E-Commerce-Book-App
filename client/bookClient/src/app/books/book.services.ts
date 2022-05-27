import {
  HttpClient, HttpHeaders
} from "@angular/common/http";
// import { Http, Headers, Response, RequestOptions } from '@angular/http';
import {
  Injectable
} from "@angular/core";
import { Observable } from "rxjs";
import { Book } from "../book.model";
import { AllBookResponse, Comman, SingleBookResponse } from "../response.model";
@Injectable({
  providedIn: 'root'
})

export class BookServices {

  baseurl = "http://localhost:8080/books"

  constructor(private http: HttpClient) {
  }
  getAllBooks() {
    return this.http.get<AllBookResponse>(this.baseurl);
  }

  deleteBook(id: string | null) {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': "I am coming from frontend",
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.delete<Comman>(this.baseurl + "/" + id, httpOptions);
  }

  addNewBook(newBook: Book) {
    return this.http.post<SingleBookResponse>(this.baseurl, newBook).subscribe(res => { console.log("Book Added Successfully") });;
  }


  updateBook(book: Book) {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': "I am coming from frontend",
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.put<SingleBookResponse>(this.baseurl + "/" + book.id, book, httpOptions);
  }


  getSingleBook(id: any) {
    console.log(id);
    return this.http.get<SingleBookResponse>(this.baseurl + "/" + id);
  }


  getUser(): Observable<any> {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': "I am coming from frontend",
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.get("http://localhost:8080/user/getUser", httpOptions);
  }
  getCookies() {
    return this.http.get("http://localhost:8080/user/getCookies");
  }

}