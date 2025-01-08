import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {CartItem} from '../models/cartItem.model';
import {CartService} from '../service/cart.service';
import {AsyncPipe, CurrencyPipe, NgForOf, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    CurrencyPipe,
    RouterLink
  ],
  templateUrl: './cart-page.component.html',
  styleUrl: './cart-page.component.css'
})

export class CartPageComponent implements OnInit {
  cartItems$!: Observable<CartItem[]>;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartItems$ = this.cartService.cart$;
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
  }
}
