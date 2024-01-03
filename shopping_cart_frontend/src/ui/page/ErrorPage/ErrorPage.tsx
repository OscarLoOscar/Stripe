import BottomWrapper from "../../component/BottomWrapper/BottomWrapper";
import Footer from "../../component/Footer/Footer";
import ItemTab from "../../component/ItemTab/ItemTab";
import LogoImage from "../../component/LogoImage/LogoImage";
import NavBar from "../../component/NavBar/NavBar";
import NotFound from "../../component/NotFound/NotFound";
import TopContainer from "../../component/TopContainer/TopContainer";

export default function ErrorPage() {
    return (
        <>
            <title>Error Page</title>
            <TopContainer />
            <LogoImage />
            <ItemTab />

            <NavBar />

            <BottomWrapper />

            <NotFound title="OOps! "
                subtitle="GOTO HOMEPAGE  404 - PAGE NOT FOUND" />

            <Footer />
        </>
    )
}