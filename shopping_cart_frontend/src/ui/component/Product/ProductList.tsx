import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Grid, styled } from "@mui/material";
import Box from '@mui/material/Box';
import ProductListCard from "./ProductListCard";
import { ProductListDto } from '../../../data/Product/ProductListDto.js';
import * as ProductApi from "../../../api/ProductApi"
import Loading from '../Utility/Loading';
import Paper from '@mui/material/Paper';
const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));
export default function ProductList() {
    const [productList, setProductList] = useState<ProductListDto[] | undefined>(undefined);
    const navigate = useNavigate()

    const fetchProductData = async () => {
        try {
            setProductList(await ProductApi.getProductListApi())
        } catch (e) {
            navigate("/error")
        }
    }

    const renderProductList = () => {
        if (productList) {
            return (
                <Box sx={{ flexGrow: 1 }}>
                    <Grid
                        container
                        spacing={{ xs: 2, md: 3 }}
                        columns={{ xs: 4, sm: 8, md: 12 }}
                    >
                        {productList.map((value) => (
                            <Grid item xs={12} sm={6} md={4} lg={3} key={value.pid} >
                                <ProductListCard
                                    key={value.pid}
                                    productData={value}
                                // onAddToCart={fetchProductData}
                                />
                            </Grid >
                        ))}
                    </Grid>
                </Box>
            );
        } else {
            return <Loading />
        }
    }
    useEffect(() => {
        void fetchProductData()
    }, []);

    return <>
        <Box height="30px"></Box>
        {renderProductList()}
    </>
}