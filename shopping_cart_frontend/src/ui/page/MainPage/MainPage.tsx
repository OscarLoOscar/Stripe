import { Container } from "@mui/material";
import BottomWrapper from "../../component/BottomWrapper/BottomWrapper";
import ItemTab from "../../component/ItemTab/ItemTab";
import NavBar from "../../component/NavBar/NavBar";
import TopContainer from "../../component/TopContainer/TopContainer";
import MainWrapper from "../../component/adv/MainWrapper";
import Footer from "../../component/Footer/Footer";
import LogoImage from "../../component/LogoImage/LogoImage";
import ComplexButton from "../../component/ComplexButton/ComplexButton";

const photos = [
  "https://images.hktvmall.com/image_slider/bannerzh_231130040831.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_231201044918.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_231129045222.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_230907025912.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_220712063428.jpg",

];

// define一個ShoppingSite组件
export default function MainPage() {
  return (
    <>
      <title>Main Page</title>
      <TopContainer />
      <LogoImage />
      <ItemTab />
      <NavBar />
      <BottomWrapper />
      <Container >
        <MainWrapper imgs={photos} />
      </Container>
      <Container
        sx={{ display: 'flex', justifyContent: 'center', marginBottom: '5%' }}
      >
        {/* <Parallax /> */}
        <ComplexButton />
      </Container>
      <Footer />
    </>
  );
}
