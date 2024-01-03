import BottomWrapper from "../../component/BottomWrapper/BottomWrapper";
import ItemTab from "../../component/ItemTab/ItemTab";
import NavBar from "../../component/NavBar/NavBar";
import TopContainer from "../../component/TopContainer/TopContainer";
import Footer from "../../component/Footer/Footer";
import { useEffect, useState } from "react";
import { GetTransDto } from "../../../data/Trans/GetTransDto";
import * as TransApi from "../../../api/TransactionApi"
import { useNavigate, useParams } from "react-router-dom";
import Loading from "../../component/Utility/Loading";
import LogoImage from "../../component/LogoImage/LogoImage";
import TransItemCard from "../../component/Transaction/TransItemCard";
import Checkout from "../../component/CheckOut/Checkout";

export default function CartPage() {
  const [transData, setTransData] = useState<GetTransDto | undefined>(undefined);
  const [payStatus, setPayStatus] = useState<string | undefined>(undefined)
  const navigate = useNavigate();
  const { transactionId } = useParams(); // useParams to get parameters from the URL

  const fetchTransData = async () => {
    try {
      if (transactionId) {
        console.log(transactionId)
        setTransData(await TransApi.getTransactionDetailByTid(transactionId))
      }
    } catch (e) {
      navigate("/error")
    }
  }


  const renderTransItemList = () => {
    if (transData && transData.items.length > 0) {
      return transData.items.map((value) => {
        return <TransItemCard key={value.tpid} data={value} />
      })
    } else {
      return <Loading />
    }
  }

  const handlePaymentSuccess = async () => {
    if (transactionId) {
      const payResult = await TransApi.finishTransaction(transactionId)
      setPayStatus(payResult.status)
      // setLoadingBackdrop(false)
      navigate('/thankyou')
    }
  }
  useEffect(() => {
    if (payStatus === 'SUCCESS') {
      void handlePaymentSuccess()
    }
    setTransData(undefined)
    void fetchTransData();
  }, [transactionId, payStatus]);

  return (
    <>
      <title>Check Out</title>
      <TopContainer />
      <LogoImage />
      <ItemTab />
      <NavBar />
      <BottomWrapper />
      <Checkout transactionId={transactionId} />
      <Footer />
    </>
  );
}