import TopContainer from '../../component/TopContainer/TopContainer';
import BottomWrapper from '../../component/BottomWrapper/BottomWrapper';
import ItemTab from '../../component/ItemTab/ItemTab';
import MainWrapper from '../../component/adv/MainWrapper';
import NavBar from '../../component/NavBar/NavBar';
import { Container } from '@mui/material';
import Footer from '../../component/Footer/Footer';
import ProductDetails from '../../component/Product/ProductDetails';
import LogoImage from '../../component/LogoImage/LogoImage';
const photos = [
  "https://images.hktvmall.com/image_slider/bannerzh_231130040831.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_231201044918.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_231129045222.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_230907025912.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_220712063428.jpg",

];

// define一個ShoppingSite组件
export default function ProductDetailsPage() {
  return (
    <>
      <title>Project Page</title>
      <TopContainer />
      <LogoImage />

      <ItemTab />

      <NavBar />

      <BottomWrapper />
      <Container sx={{ marginBottom: '5%' }}>
        <MainWrapper imgs={photos} />
        <ProductDetails />
      </Container>
      <Footer />
    </>
  );
}
