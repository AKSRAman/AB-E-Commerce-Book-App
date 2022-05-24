 export interface AllBookResponse {
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
  }

  export interface SingleBookResponse {
    status: boolean,
    message: string,
    bookList: {
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
    },
  }
  export interface Comman 
  {
      status:boolean,message:string
}