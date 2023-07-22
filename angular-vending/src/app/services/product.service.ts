import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


import { Product } from '../models/product';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ProductService {

    private productApiUrl = "http://localhost:8080/products"; /*TODO: https for security*/
    private transactionApiUrl = "http://localhost:8080/transactions";

    constructor(private http: HttpClient) { }

    getProductList(): Observable<Product[]> {
        return this.http.get<Product[]>(this.productApiUrl);
    }

    sendTransaction(theTransaction: any): Observable<any> {
        return this.http.post<any>(`${this.transactionApiUrl}`, theTransaction);
    }
}
