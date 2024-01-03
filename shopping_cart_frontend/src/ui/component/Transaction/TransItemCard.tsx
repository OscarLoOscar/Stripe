import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { Item } from "../../../data/Trans/GetTransDto";

type Props = {
    data: Item
}

export default function TransItemCard(props:Props) {
    const HKDollar = new Intl.NumberFormat('zh-HK', {
        style: 'currency',
        currency: 'HKD',
    });

    return <>
        <Box display="flex" flexDirection="row" key={props.data.tpid}>
            <Box width="20%">
                <img src={props.data.product.image_url}
                     alt={props.data.product.name}
                     loading="lazy"
                     height='80px'/>
            </Box>
            <Box width="20%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <Typography
                    sx={{margin: "auto auto auto 0"}}>
                    {props.data.product.name}
                </Typography>
            </Box>
            <Box width="15%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <Typography
                    sx={{margin: "auto"}}>
                    {HKDollar.format(props.data.product.price)}
                </Typography>
            </Box>
            <Box width="15%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <Typography
                    sx={{margin: "auto"}}>
                    {props.data.quantity}
                </Typography>
            </Box>
            <Box width="25%" sx={{
                display: "flex",
                alignItems: "center"
            }}>
                <Typography
                    sx={{margin: "auto"}}>
                    {HKDollar.format(props.data.subtotal)}
                </Typography>
            </Box>
        </Box>
    </>
}