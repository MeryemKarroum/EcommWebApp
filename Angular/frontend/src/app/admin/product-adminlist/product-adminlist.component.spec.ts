import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductAdminlistComponent } from './product-adminlist.component';

describe('ProductAdminlistComponent', () => {
  let component: ProductAdminlistComponent;
  let fixture: ComponentFixture<ProductAdminlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductAdminlistComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductAdminlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
