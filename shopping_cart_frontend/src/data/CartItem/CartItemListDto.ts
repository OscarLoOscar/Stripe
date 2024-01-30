export interface CartItemListDto {
  cid: number;
  pid: number;
  name: string;
  price: number;
  image_url: string;
  cart_quantity: number;
  stock: number;
}

export interface ExtendedCartItemListDto extends CartItemListDto {
  total_quantity: number;
}