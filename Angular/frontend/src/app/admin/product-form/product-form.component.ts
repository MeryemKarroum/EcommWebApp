import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../service/product.service'; // Import the ProductService
import { Observable } from 'rxjs';
import { Product } from '../models/product.module';
import {NgForOf} from '@angular/common'; // Assuming Product model exists

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgForOf],
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  isEditMode = false;
  selectedImage: File | null = null;
  categories: any[] = [];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService, // Inject the ProductService
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      description: [''],
      categoryId: ['', Validators.required], // Added categoryId
      image: [null], // Added image control
    });
  }

  ngOnInit(): void {
    // Fetch categories from ProductService
    this.productService.getCategories().subscribe((categories) => {
      this.categories = categories;
    });

    // Check if we are in edit mode and load the product
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.isEditMode = true;
      this.loadProduct(productId);
    }
  }

  loadProduct(productId: string): void {
    // Fetch product details for editing
    this.productService.getProductById(productId).subscribe((product) => {
      this.productForm.patchValue({
        name: product.name,
        price: product.price,
        description: product.description,
        categoryId: product.categoryId,
      });
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement | null;
    if (input?.files?.[0]) {
      this.selectedImage = input.files[0];
    } else {
      this.selectedImage = null;
    }
  }

  // Fetch categories from the service (using ProductService)
  getCategories(): Observable<any[]> {
    return this.productService.getCategories(); // Corrected to use ProductService
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const formData = new FormData();
      formData.append('name', this.productForm.value.name);
      formData.append('price', this.productForm.value.price);
      formData.append('description', this.productForm.value.description);
      formData.append('categoryId', this.productForm.value.categoryId);
      if (this.selectedImage) {
        formData.append('image', this.selectedImage);
      }

      if (this.isEditMode) {
        const productId = this.route.snapshot.paramMap.get('id')!;
        this.productService.updateProduct(productId, formData).subscribe(() => {
          this.router.navigate(['/admin/products']);
        });
      } else {
        this.productService.addProduct(formData).subscribe(() => {
          this.router.navigate(['/admin/products']);
        });
      }
    }
  }
}
