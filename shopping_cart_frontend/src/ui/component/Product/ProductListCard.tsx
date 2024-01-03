import { Box, Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Grid, Typography, Collapse, Alert } from "@mui/material";
import { useState, useEffect } from "react";
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import ShareIcon from '@mui/icons-material/Share';
import { useNavigate } from "react-router-dom";
import { ProductListDto } from "../../../data/Product/ProductListDto";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";
import DescriptionIcon from '@mui/icons-material/Description';
import * as CartApi from "../../../api/CartItemApi"

type Props = {
  productData: ProductListDto;
}
export default function ProductListCard({ productData }: Props) {
  const [addCartItemStatus, setAddCartItemStatus] = useState<string | undefined>(undefined);
  const [messageBoxOpen, setMessageBoxOpen] = useState<boolean>(true);
  const navigate = useNavigate();

  const handleAddCartItem = async () => {
    // //避免跳版
    // event.preventDefault();
    const result = await CartApi.addCartItemApi(productData.pid.toString(), "1")
    console.log(result);
    setAddCartItemStatus(productData.name);

  }

  const handleItemDetail = () => {
    navigate(`/product/` + productData.pid.toString());
  }
  const addCartMessage = () => {
    if (addCartItemStatus === "SUCCESS") {
      return (
        <Box sx={{
          position: 'absolute',
          top: 0,
          left: 0,
          width: '100%',
          zIndex: 'drawer'
        }}
        >
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
        </Box>
      )
    } else if (addCartItemStatus === "FAIL") {
      return (
        <Box sx={{
          position: 'absolute',
          top: 0,
          left: 0,
          width: '100%',
          zIndex: 'drawer'
        }}
        >
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
        </Box>
      )
    } else {
      return <></>
    }
  }

  const [isAdded, setIsAdded] = useState(false);

  useEffect(() => {

  }, []);

  return (
    <>
      <Card sx={{ maxWidth: 350 }}>
        <CardActionArea sx={{ flexGrow: 1, borderRadius: 0 }} >
          <CardMedia
            component="img"
            image={productData.image_url}
            alt=""
            width='50%'  // Set the width to 100% to ensure it takes the full width of its container
            height='250px'  // Set a fixed height for the image (adjust as needed)
            style={{ objectFit: 'cover' }}  // Preserve aspect ratio by covering the container
          />
          <CardContent>
            <Typography sx={{
              fontSize: 18,
              overflow: 'hidden',
              textOverflow: 'ellipsis',
              whiteSpace: 'nowrap',
              width: '80%'
            }} color="black" gutterBottom>
              {productData.name}
            </Typography>
            <Typography gutterBottom variant="h6" component="div">
              {`HK$${productData.price.toLocaleString(undefined, { minimumFractionDigits: 2 })}`}
            </Typography>
            <div style={{ fontSize: "12px", fontWeight: "bolder", color: "black", textAlign: "right" }}>
              {productData.has_stock ? "In-Stock" : "Out of Stock"}
            </div>
          </CardContent>
        </CardActionArea>
        <CardActions>
          <Grid item xs={6} textAlign="left">
            <Button size="small" color="primary" endIcon={<ShareIcon />}>
              Share
            </Button>
          </Grid>
          <Grid item xs={6} textAlign="left">
            <Button size="small"
              color="primary"
              onClick={handleItemDetail}
              endIcon={<DescriptionIcon />}>
              Detail
            </Button>
          </Grid>
          <Grid item xs={8} textAlign="right">
            <Button size="small"
              color="primary"
              // href="#"
              onClick={(event) => {
                event.preventDefault();
                handleAddCartItem();
              }}
              endIcon={<AddShoppingCartIcon />}
            >
              {!isAdded ? "ADD TO CART" : "✔ ADDED"}
            </Button>
          </Grid>
        </CardActions>
      </Card>

    </>
  );
}
