import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Grid } from "@mui/material";
import Box from "@mui/material/Box";
import { ProductDetailsDto } from "../../../data/Product/ProductDetailsDto";
import Loading from "../Utility/Loading";
import ProductDetailsCard from "./ProductDetailsCard";
import * as ProductApi from "../../../api/ProductApi"

type Params = {
    productId: string
}

export default function ProductDetails() {
    const [productDetails, setProductDetails] = useState<ProductDetailsDto | undefined>(undefined);
    const navigate = useNavigate()
    const { productId } = useParams<Params>()
    const fetchProductData = async () => {
        try {
            setProductDetails(await ProductApi.getProductByIdApi(productId))
        } catch (e) {
            navigate("/error")
        }
    }

    const renderProductDetail = () => {
        if (productDetails) {
            return <ProductDetailsCard key={productId} data={productDetails} />
        } else {
            return <Loading />
        }
    }

    useEffect(() => {
        void fetchProductData()
    }, []);

    return <>
        <Box height="70px"></Box>
        <Grid container width={900} margin="auto" key="itemDetailGrid">
            {renderProductDetail()}
        </Grid>
    </>
}