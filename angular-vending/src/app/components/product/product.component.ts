import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

    products: Product[] = [];
    counts: number[] = [];

    errorMessage: string = ""; //value assigned when error occurs. 

    constructor(private productService: ProductService) { }

    ngOnInit(): void {
        this.getProductList();
    }

    getProductList(): void {
        this.productService.getProductList().subscribe(
            products => this.products = products 
        );
    }

    buy(): void {
        const tranProducts = this.products.map((product, i) =>
            ({productId: product.id, count: this.counts[i]})
        );

        this.productService.sendTransaction(tranProducts).subscribe({
            next: (transaction) => {
                //update products and counts after successful purchase
                this.productService.getProductList().subscribe((products) => {
                    this.products = this.products;
                    this.counts = this.products.map(product => product.count);
                });
            },
            
            //catch excception in case user tries to either buy negative items or more items than are available.
            error: (err) => {
                this.errorMessage = err.error;
            }
        });
    }

}
