import {
  HttpClient
} from "@angular/common/http";
import {
  Injectable
} from "@angular/core";
import { Book } from "../book.model";
@Injectable({
  providedIn: 'root'
})

export class BookServices {
  
baseurl="http://localhost:8080/books"
   
  constructor(private http: HttpClient) {

  }
  getAllBooks() {
    return this.http.get < {
      status: boolean,
      message: string,
      bookList: [{
        addedOn: "",
        authors: "",
        category: "",
        description: "",
        id: "",
        imageUrl: "",
        pages: 0,
        price: 0,
        publishDate: "",
        rating: 0,
        title: "",
        updatedOn: "",
      },]
    } > (this.baseurl+"/getBooks");
  }
  deleteBook(id:string|null){
    return  this.http.delete<{status:boolean,message:string}>(this.baseurl+"/deleteBook/"+id);
  }
  addNewBook(newBook:Book){
    return this.http.post<{status:boolean,message:string,book:Book}>(this.baseurl+"/addNewBooks",newBook);
  }
  
}
