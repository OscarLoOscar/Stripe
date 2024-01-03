import { Alert, Collapse, Grid, TextField } from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button, { ButtonProps } from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import { styled } from "@mui/material/styles";
import { yellow } from "@mui/material/colors";
import CloseIcon from '@mui/icons-material/Close';
import { ProductDetailsDto } from "../../../data/Product/ProductDetailsDto";
import * as CartApi from "../../../api/CartItemApi"
import { useNavigate } from "react-router-dom";
import { useState, ChangeEvent } from "react";

type Props = {
    productData: ProductDetailsDto
}

const ColorButton = styled(Button)<ButtonProps>(({ theme }) => ({
    color: theme.palette.getContrastText(yellow[500]),
    backgroundColor: yellow[500],
    '&:hover': {
        backgroundColor: yellow[700],
    },
}));

export default function ProductDetailsCard({ productData }: Props) {
    const [itemQty, setItemQty] = useState<number>(1);
    const [addCartItemStatus, setAddCartItemStatus] = useState<string | undefined>(undefined);
    const [messageBoxOpen, setMessageBoxOpen] = useState<boolean>(true);
    const navigate = useNavigate();

    const handleAddCartItem = async () => {
        const result = await CartApi.addCartItemApi(productData.pid.toString(), "1")
        console.log(result);
        setAddCartItemStatus(productData.name);

    }

    const handleTextFieldChange = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setItemQty(Number(event.target.value));
    }

    const addCartMessage = () => {
        if (addCartItemStatus === "SUCCESS") {
            return (
                <Collapse in={messageBoxOpen}>
                    <Alert action={
                        <IconButton
                            aria-label="close"
                            color="inherit"
                            size="small"
                            onClick={() => {
                                setMessageBoxOpen(false);
                            }}>
                            <CloseIcon fontSize="inherit" />
                        </IconButton>}
                    >Item added to cart successfully</Alert>
                </Collapse>

            )
        } else if (addCartItemStatus === "FAIL") {
            return (
                <Collapse in={messageBoxOpen}>
                    <Alert severity="error"
                        action={
                            <IconButton
                                aria-label="close"
                                color="inherit"
                                size="small"
                                onClick={() => {
                                    setMessageBoxOpen(false);
                                }}>
                                <CloseIcon fontSize="inherit" />
                            </IconButton>}
                    >Item failed to add</Alert>
                </Collapse>
            )
        } else {
            return <></>
        }
    }

    function add2CartButton(stock: number) {
        if (stock > 0) {
            return <ColorButton
                variant="contained"
                onClick={handleAddCartItem}
            >Add to Cart</ColorButton>
        } else {
            return <ColorButton variant="contained" disabled>Not Available</ColorButton>
        }
    }

    return <>
        <Grid item xs={12} sm={6}>
            <Typography component="div">
                <img
                    src={productData.image_url}
                    alt={productData.name}
                    loading="lazy"
                    height='220px'
                />
            </Typography>
        </Grid>
        <Grid item xs={12} sm={6}>
            <Box>
                <Typography sx={{
                    fontSize: 18,
                    overflow: 'hidden',
                    textOverflow: 'ellipsis',
                    whiteSpace: 'nowrap',
                    width: '80%'
                }} color="black" gutterBottom>
                    {productData.name}
                </Typography>
            </Box>
            <Box>
                <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                    {productData.description}
                </Typography>
            </Box>
            <Box>
                <Typography variant="body2">
                    ${productData.price}
                </Typography>
            </Box>
            <Box>
                <TextField
                    id="itemQuantity"
                    type="number"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    size={"small"}
                    inputProps={{ min: 0, max: productData.unitStock }}
                    onChange={handleTextFieldChange}
                    defaultValue={itemQty}
                />
                <Box height="30px" />
            </Box>
            <Box>
                {add2CartButton(productData.unitStock)}
            </Box>
        </Grid>
        <Grid item xs={12}>
            {addCartMessage()}
        </Grid>
    </>
}