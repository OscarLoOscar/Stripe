export interface PrepTransDto {
    tid:       number;
    datetime:  Date;
    status:    string;
    total:     number;
    buyer_uid: number;
    items:     Item[];
}

export interface Item {
    tpid:     number;
    quantity: number;
    subtotal: number;
    product:  Product;
}

export interface Product {
    pid:         number;
    name:        string;
    description: string;
    price:       number;
    image_url:   string;
    stock:       number;
}
