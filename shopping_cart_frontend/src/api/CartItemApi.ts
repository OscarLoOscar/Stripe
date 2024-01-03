import axios from "axios";
import getEnvConfig from "../Config/EnvConfig";
import { CartItemListDto } from "../data/CartItem/CartItemListDto";
import * as FirebaseAuthService from "../authService/FirebaseAuthService"
const baseUrl = getEnvConfig().baseUrl;

export const addCartItemApi = async (productId: string, productQty: string) => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  console.log("addCartItemApi running")
  if (!accessToken)
    throw new Error();
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    if (productId) {
      const apiUrl = `${baseUrl}/cart/${productId}/${productQty}`
      const response = await axios.put(apiUrl,"", config);
      console.log(response.data);
    }
  }
  catch (error) {
    console.error(error);
    throw error;
  }
}

export const deleteCartItemApi = async (cartItemId: string) => {
  const accessToken = await FirebaseAuthService.getAccessToken()
  try {
    if (accessToken) {
      const response = await axios.delete<CartItemListDto>(`${baseUrl}/cart/${cartItemId}`,
        { headers: { "Authorization": `Bearer ${accessToken}` } });
      console.log(response.data)
      return response.data;
    }
  }
  catch (error) {
    console.error(error);
    throw error;
  }
};

export const getCartItemListApi = async () => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  console.log(accessToken)
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    const apiUrl = `${baseUrl}/cart`;
    const response = await axios.get<CartItemListDto[]>(apiUrl, config);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const updateCartItemApi = async (productId: string, productQty: string) => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  console.log("updateCartItemApi running")
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    if (productId) {
      const apiUrl = `${baseUrl}/cart/${productId}/${productQty}`;
      const response = await axios.patch<CartItemListDto>(apiUrl, "", config);
      return response.data;
    }
  } catch (error) {
    console.error(error);
    throw error;
  }
};
