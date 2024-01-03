import BottomWrapper from "../../component/BottomWrapper/BottomWrapper";
import Footer from "../../component/Footer/Footer";
import ItemTab from "../../component/ItemTab/ItemTab";
import NavBar from "../../component/NavBar/NavBar";
import TopContainer from "../../component/TopContainer/TopContainer";
import LogoImage from "../../component/LogoImage/LogoImage";
import ShoppingCartList from "../../component/ShoppingCart/ShoppingCartList";

export default function ShoppingCartPage() {
    return <>
        <TopContainer />
        <LogoImage />
        <ItemTab />
        <NavBar />
        <BottomWrapper />
        <ShoppingCartList/>
        <Footer />
    </>
}