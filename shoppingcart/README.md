# Shopping_Cart
## Shopping_Cart_Controller

### 1. CartItemController

- @PutMapping("/{pid}/{quantity}")addCartItem(JwtAuthenticationToken jwt, inputPid, inputQuantity)
- getUserCartItems(JwtAuthenticationToken jwt)
- updateCartQuantity(inputPid, inputQuantity, JwtAuthenticationToken jwt)
- removeCartItem(inputPid, JwtAuthenticationToken jwt)

### 2. ProductController

- addProduct(ProductRequest product)
- getAllProduct()
- getProductById(productId)
- deleteProductById(productId)
- editProduct(ProductRequest product)
- editProductPrice(productId)

### 3. TransactionController

- createTransaction(JwtAuthenticationToken jwt)
- getTransactionDetailByTransactionId(transactionId, JwtAuthenticationToken jwt)
- updateTransactionToProcessing(tid, JwtAuthenticationToken jwt)
- finishTransaction(tid, JwtAuthenticationToken jwt)

### 4. UserController

- getMyUserDetails(JwtAuthenticationToken jwtToken)


## Shopping_Cart_Service

### 1. CartItemService

- findAllByUserUid(uid)
- getUserCartItemsByProductId(pid)
- addCartItem(userId, pid, quantity)
- updateCartQuantity(userId, pid, quantity)
- getCartItemDetails(userId, productId)
- deleteCartItemByCartItemId(userid, cartItemId)
- deleteAllCartItem()

### 2. ProductService

- addProduct(product)
- getAllProduct()
- getProductById(productId)
- getProductEntityById(productId)
- editProduct(product)
- editProductPrice(productId, price)
- updateProductstock(productId, unitStock)
- deleteProductById(productId)
- isEnoughStock(pid, quantity)

### 3. TransactionProductService

- save(UserOrder)
- findAllTransactionProductByTransactionId(tid)
- findByProductId(pid)
- findByTranProductId(tpid)
- deleteByProductId(pid)

### 4. TransactionService

- createTransaction(userId)
- getTransactionDetailByTransactionId(tid, uid)
- getTransactionByTransactionId(transactionId)
- updateTransactionToProcessing(tid, uid)
- findAllByBuyerUid(uid)
- findByTidAndUid(tid, uid)
- finishTransaction(tid, uid)

### 5. UserService

- addUser(user)
- getAllUsers()
- getEntityByFireBaseUserData(fireBaseUserData)
- getUserById(userId)
- findUserByEmail(email)
- updateUser(user)
- deleteUserById(userId)
