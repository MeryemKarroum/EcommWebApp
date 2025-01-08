import { Component, OnInit } from '@angular/core';
import { CartService} from '../service/cart.service';
import { Observable } from 'rxjs';
import {AsyncPipe, CurrencyPipe, NgForOf, NgIf} from '@angular/common';
import {CartItem} from '../models/cartItem.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  standalone: true,
  imports: [
    AsyncPipe,
    CurrencyPipe,
    NgIf,
    NgForOf
  ],
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems$!: Observable<CartItem[]>;
  isCartOpen = false;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartItems$ = this.cartService.cart$;
  }

  toggleCart(): void {
    this.isCartOpen = !this.isCartOpen;
  }

  removeFromCart(itemId: number): void {
    this.cartService.removeFromCart(itemId);
  }

  updateQuantity(itemId: number, quantity: number): void {
    this.cartService.updateQuantity(itemId, quantity);
  }

  getCartTotal(): number {
    return this.cartService.getCartTotal();
  }

  checkout(): void {
    // Implement checkout logic here
    console.log('Checkout');
    this.cartService.clearCart();
    this.isCartOpen = false;
  }
}

