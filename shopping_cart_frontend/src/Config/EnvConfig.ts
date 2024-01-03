import DevConfig from "./DevConfig";
import ProdConfig from "./ProdConfig";

export default function getEnvConfig() {
    //React Function
    if(!process.env.NODE_ENV || process.env.NODE_ENV === "development") {
        return DevConfig;
    }
    else {
        return ProdConfig;
    }
}