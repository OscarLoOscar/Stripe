import { Button, Card, CardActions, CardContent, Container, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import TopContainer from "../../component/TopContainer/TopContainer";
import ItemTab from "../../component/ItemTab/ItemTab";
import BottomWrapper from "../../component/BottomWrapper/BottomWrapper";
import NavBar from "../../component/NavBar/NavBar";
import Footer from "../../component/Footer/Footer";


export default function LogOutPage() {
    const [count, setCount] = useState<number>(15);
    const navigate = useNavigate()

    useEffect(() => {
        setTimeout(() => {
            setCount((state) => (state) - 1);

            if (count === 0) {
                navigate("/");
            }
        }, 1000)
    }, [count])

    return (
        <>
            <title>Logout Page</title>
            <TopContainer />
            <img
                alt="Logo"
                src="https://venturenixlab.co/wp-content/uploads/2022/05/cropped-cropped-Vlab-horizontal-logo.png"
                title="company_logo"
                width={500}
                style={{ display: 'block', margin: 'auto' }}
            />
            <ItemTab />

            <NavBar />

            <BottomWrapper />

            <Container sx={{ width: 500 }}>

                <Card sx={{ textAlign: "center", justifyContent: "center" }}>
                    <CardContent>
                        <CheckCircleOutlineIcon sx={{ fontSize: 100, color: 'primary.main' }} />
                        <Typography variant="h2" gutterBottom>
                            Thank You!
                        </Typography>
                        <Typography variant="subtitle1" gutterBottom>
                            See You Next Time!
                        </Typography>
                    </CardContent>
                    <CardActions style={{ justifyContent: 'center' }}>
                        <Button variant="contained"
                            color="success" href="/">
                            Back to Home
                        </Button>
                    </CardActions>
                </Card>
            </Container>
            <Footer />
        </>
    );
}