import { RouterProvider, createBrowserRouter } from "react-router-dom";
import ShoppingSite from './ui/page/ProductPage/ShoppingSite';
import LoginPage from './ui/page/LoginPage/LoginPage';
import { createContext, useEffect, useState } from "react";
import ThankyouPage from './ui/page/ThankyouPage/ThankyouPage';
import ErrorPage from './ui/page/ErrorPage/ErrorPage';
import { UserData } from './data/User/UserData';
import ProductDetailsPage from './ui/page/ProductPage/ProductDetailsPage';
import MainPage from './ui/page/MainPage/MainPage';
import LoginSuccessPage from "./ui/page/LoginSuccessPage/LoginSuccessPage";
import * as FirebaseAuthService from "./authService/FirebaseAuthService"
import LogOutPage from "./ui/page/LogOutPage/LogOutPage";
import ShoppingCartPage from "./ui/page/ShoppingCartPage/ShoppingCartPage";
import CheckOutPage from "./ui/page/CheckOutPage/CheckOutPage";
import OrderListPage from "./ui/page/OrderListPage/OrderListPage";

//useContext - is a way to manage state globally

// 括號入面放inittial state
export const LoginUserContext = createContext<UserData | undefined | null>(undefined);
function App() {
  // 3 situtation : 
  // 1.有UserData=> logined ,
  // 2.null => 無login ,
  // 3.undefined => 未check＝> firebase async => useEffect
  const [loginUser, setLoginUser] = useState<UserData | null | undefined>(undefined);

  useEffect(() => {
    FirebaseAuthService.handleOnAuthStateChanged(setLoginUser);
  }, [])

  const router = createBrowserRouter([
    {
      path: "/",
      element: <MainPage />
    },
    {
      path: "/product",
      element: <ShoppingSite />
    },
    {
      path: "/product/:productId",
      element: <ProductDetailsPage />
    },
    {
      path: "/login",
      element: <LoginPage />
    },
    {
      path: "/thankyoupage",
      element: <ThankyouPage />
    },
    {
      path: "/checkout/:transactionId",
      element: <CheckOutPage />
    },
    {
      path: "/shoppingcart",
      element: <ShoppingCartPage />
    },
    {
      path: "/error",
      element: <ErrorPage />
    },
    {
      path: "/success",
      element: <LoginSuccessPage />
    },
    {
      path: "/logout",
      element: <LogOutPage />
    }
    , {
      path: "/orderList",
      element: <OrderListPage />
    }
  ])

  return (
    <>
      <LoginUserContext.Provider value={loginUser}>
        <RouterProvider router={router} />
      </LoginUserContext.Provider>
    </>
  )
}

export default App
