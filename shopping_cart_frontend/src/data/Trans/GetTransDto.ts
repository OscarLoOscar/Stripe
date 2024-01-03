export interface GetTransDto {
    tid: number;
    datetime: string;
    status: string;
    total: number;
    buyer_uid: number;
    items: Item[];
}

export interface Item {
    tpid: number;
    tid: number;
    quantity: number;
    subtotal: number;
    product: Product;
}

export interface Product {
    cid: number;
    pid: number;
    name: string;
    description: string;
    image_url: string;
    price: number;
    cart_quantity: number;
    stock: number;
}
