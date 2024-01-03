import Box from "@mui/material/Box";
import { TextField } from "@mui/material";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from '@mui/icons-material/Delete';
import Typography from "@mui/material/Typography";
import * as CartApi from "../../../api/CartItemApi.js";
import { useNavigate } from "react-router-dom";
import { CartItemListDto } from "../../../data/CartItem/CartItemListDto.js";
import { useEffect, useState } from "react";
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
type Props = {
    data: CartItemListDto
    update: () => void;
}

export default function ShoppingCartListCard({ data, update }: Props) {
    const [quantity, setQuantity] = useState<number>(1);
    const [itemSubtotal, setItemSubtotal] = useState<number>(data.price * quantity);
    const HKDollar = new Intl.NumberFormat('zh-HK', {
        style: 'currency',
        currency: 'HKD',
    });

    const navigate = useNavigate();

    const getCartItemList = async () => {
        try {
            //      update(undefined)
            const result = await CartApi.getCartItemListApi();
            console.log(result);
            //        update(result);
        } catch (error) {
            console.error(error);
        }
    }

    const handleQtyChange = async (newQuantity: number) => {
        try {
            // 检查新数量是否在合理范围内
            if (newQuantity >= 1 && newQuantity <= data.stock) {
                // 调用 API 更新购物车商品数量
                const updatedCartItem: CartItemListDto | undefined = await CartApi.updateCartItemApi(
                    data.pid.toString(),
                    newQuantity.toString()
                );

                if (updatedCartItem) {
                    // 如果 API 调用成功，更新本地状态并触发父级组件更新
                    setItemSubtotal(updatedCartItem.price * newQuantity);
                    update();
                }
            } else {
                // 如果新数量不在合理范围内，进行相应处理（比如导航到错误页面）
                setItemSubtotal(data.cart_quantity * data.price);
                navigate("/error");
            }
        } catch (e) {
            // 处理 API 调用中的错误，比如导航到错误页面
            navigate("/error");
        }
    };

    useEffect(() => {
        // 在 quantity 更新後執行 update 函數
        // update();
    }, [quantity]);

    const handlePlusButton = () => {
        const newQuantity = Math.max(quantity + 1, 1);
        setQuantity(newQuantity);
        handleQtyChange(newQuantity);
    };

    const handleMinusButton = () => {
        // if (quantity > 1)
        //     setQuantity((quantity) => quantity - 1);
        const newQuantity = Math.min(quantity - 1, 1);
        setQuantity(newQuantity);
        handleQtyChange(newQuantity);
    };

    const handleDeleteCartItem = async () => {
        try {
            if (!data) {
                // 如果 data 為 undefined，可能需要進行處理或拋出錯誤
                throw new Error("Data is undefined");
            }
            const result = await CartApi.deleteCartItemApi(data?.cid.toString())
            console.log(data.cid);
            console.log(result);
            update();
        } catch (e) {
            navigate("/error")
        }
    }

    return <>

        <Box display="flex" flexDirection="row" key={data.cid}>
            <Box width="20%">
                <img src={data.image_url}
                    alt={data.name}
                    loading="lazy"
                    height='80px' />
            </Box>
            <Box width="20%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <Typography
                    sx={{ margin: "auto auto auto 0" }}>
                    {data.name}
                </Typography>
            </Box>
            <Box width="15%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <Typography
                    sx={{ margin: "auto" }}>
                    {HKDollar.format(data.price)}
                </Typography>
            </Box>
            <Box width="15%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                {/* Controll _itemQuantity */}
                <IconButton aria-label="minusOne" onClick={handleMinusButton}>
                    <RemoveIcon />
                </IconButton>
                <TextField
                    required
                    id={data.pid.toString() + "_itemQuantity"}
                    fullWidth
                    size={"small"}
                    // inputProps={{ min: 1, max: data.stock }}
                    //     onBlur={(e) => handleQtyChange(Number(e.target.value))}
                    value={quantity}
                />
                <IconButton aria-label="plueOne" onClick={handlePlusButton}>
                    <AddIcon />
                </IconButton>
            </Box>

            <Box width="25%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <Typography
                    sx={{ margin: "auto" }}>
                    {HKDollar.format(itemSubtotal)}
                </Typography>

            </Box>
            <Box width="5%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <IconButton
                    size="large"
                    color="inherit"
                    onClick={handleDeleteCartItem}
                >
                    <DeleteIcon />
                </IconButton>
            </Box>
        </Box>
        <Typography variant="body2">
            Selected Product: {data.name} | Price: {HKDollar.format(data.price)} | Quantity: {quantity}
        </Typography>
    </>
}