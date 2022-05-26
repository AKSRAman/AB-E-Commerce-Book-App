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
    return this.http.delete<Comman>(this.baseurl + id);
  }
  addNewBook(newBook: Book) {
    return this.http.post<SingleBookResponse>(this.baseurl, newBook).subscribe(res => { console.log("Book Added Successfully") });;
  }
  updateBook(book: Book) {
    return this.http.put<SingleBookResponse>(this.baseurl + book.id, book);
  }

  getUser(): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Access-Control-Allow-Origin': 'http://localhost:4200',
        // 'Access-Control-Allow-Methods': 'GET',
        'x-api-key': "I am coming from frontend",
        // 'Accept': '*/*',
        'authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFua3VtYXJAZ21haWwuY29tIiwibmFtZSI6IkFtYW4gS3VtYXIiLCJleHAiOjE2NTM1NTEzMjksInVzZXJJZCI6IjYyOGQyNDE3MDMxNGIyNzZhMTI5YzFmMyIsImlhdCI6MTY1MzUzMzMyOX0.Lx25mi2Z6lDjftxXN3trBdGV7ZP9POiDj6MFCoc_jRBsi7c9ER-gNYrScw8J8lKuLPs36EQFCPbpibVTMIdTFw'
      })
    };

    // HttpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", "Your Oauth token");

    return this.http.get("http://localhost:8080/user/getUser", httpOptions);
  }

  getCookies() {
    return this.http.get("http://localhost:8080/user/getCookies");
  }
}
