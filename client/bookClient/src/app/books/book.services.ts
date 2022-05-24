import {
  HttpClient
} from "@angular/common/http";
import {
  Injectable
} from "@angular/core";
import { Book } from "../book.model";
import { AllBookResponse, Comman, SingleBookResponse } from "../response.model";
@Injectable({
  providedIn: 'root'
})

export class BookServices {

baseurl="http://localhost:8080/books"   
  constructor(private http: HttpClient) {
  }
  getAllBooks() {
    return this.http.get <AllBookResponse>(this.baseurl);
  }
  deleteBook(id:string|null){
    return  this.http.delete<Comman>(this.baseurl+id);
  }
  addNewBook(newBook:Book){
    return this.http.post<SingleBookResponse>(this.baseurl,newBook).subscribe(res=>{console.log("Book Added Successfully")});;
  }
  updateBook( book:Book){
    return this.http.put<SingleBookResponse>(this.baseurl+book.id,book);
  }
  
}
