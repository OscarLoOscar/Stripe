import TopContainer from '../../component/TopContainer/TopContainer';
import BottomWrapper from '../../component/BottomWrapper/BottomWrapper';
import ItemTab from '../../component/ItemTab/ItemTab';
import MainWrapper from '../../component/adv/MainWrapper';
import NavBar from '../../component/NavBar/NavBar';
import { Container } from '@mui/material';
import Footer from '../../component/Footer/Footer';
import ProductList from '../../component/Product/ProductList';
import LogoImage from '../../component/LogoImage/LogoImage';
const photos = [
  "https://images.hktvmall.com/image_slider/bannerzh_231130040831.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_231201044918.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_231129045222.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_230907025912.jpg",
  "https://images.hktvmall.com/image_slider/bannerzh_220712063428.jpg",
];

// define一個ShoppingSite组件
export default function ShoppingSite() {
  return (
    <>
      <title>Shopping Page</title>
      <TopContainer />
      <LogoImage />
      <ItemTab />
      <NavBar />
      <BottomWrapper />
      <MainWrapper imgs={photos} />
      <Container fixed sx={{ marginBottom: '5%' }}>
        <ProductList />
      </Container>
      {/*[if IE]>


<![endif]*/}
      < input
        className="landingCurrentPage"
        type="hidden"
        defaultValue="page-GadgetsandelectronicsLandingPage"
      />
      <Footer />
    </>
  );
}
