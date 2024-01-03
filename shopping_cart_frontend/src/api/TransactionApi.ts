import axios from "axios";
import getEnvConfig from "../Config/EnvConfig";
import { PrepTransDto } from "../data/Trans/PrepTransDto";
import { PayTransDto } from "../data/Trans/PayTransDto";
import { GetTransDto } from "../data/Trans/GetTransDto";
import { FinishTransDto } from "../data/Trans/FinishTransDto";
import * as FirebaseAuthService from "../authService/FirebaseAuthService"
import { StatusTransDto } from "../data/Trans/StatusTransDto";

const baseUrl = getEnvConfig().baseUrl;

export const createTransaction = async () => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  console.log(accessToken)
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    const apiUrl = `${baseUrl}/transaction/prepare`;
    const response = await axios.post<PrepTransDto>(apiUrl, null, config);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const updateTransactionToProcessing = async (tid: string) => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    const apiUrl = `${baseUrl}/transaction/${tid}/pay`;
    const response = await axios.patch<PayTransDto>(apiUrl, null, config);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const getTransactionDetailByTid = async (tid: string) => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  console.log(accessToken)
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    const apiUrl = `${baseUrl}/transaction/${tid}`;
    console.log(apiUrl)
    const response = await axios.get<GetTransDto>(apiUrl, config);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};


export const finishTransaction = async (tid: string) => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    const apiUrl = `${baseUrl}/transaction/${tid}/finish`;
    const response = await axios.patch<FinishTransDto>(apiUrl, null, config);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const getAllTransaction = async () => {
  const accessToken = await FirebaseAuthService.getAccessToken();
  console.log(accessToken);
  try {
    const config = {
      headers: { Authorization: `Bearer ${accessToken}` }
    };
    const apiUrl = `${baseUrl}/transaction/allTransaction`;
    const response = await axios.get<StatusTransDto[]>(apiUrl, config);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}