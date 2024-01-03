import { Link, Typography, AppBar } from "@mui/material";
import { red } from '@mui/material/colors';


export default function BottomWrapper() {
  const color = red[50];

  return (

    <AppBar
      position="relative"
      sx={{
        width: "100%",
        margin: "auto",
        backgroundColor: "#0091ea"
      }}
    >
      <Typography color="text.primary" align="center">
        熱門搜尋:
        {/* {'  (贊助) '} */}
        {'  '}
        <Link underline="hover" color={color} href="/product">
          Bordeaux
        </Link>
        {' | '}
        <Link underline="hover" color={color} href="/shoppingcart">
          Burgundy
        </Link>
        {' | '}
        <Link underline="hover" color={color} href="/#">
          Italy
        </Link>
        {' | '}
        <Link underline="hover" color={color} href="/#">
          Germany
        </Link>
        {' | '}
        <Link underline="hover" color={color} href="/#">
          Spain
        </Link>
        {' | '}
        <Link underline="hover" color={color} href="/#">
          Napa Vally
        </Link>
        {' | '}
        <Link
          underline="hover"
          color={color}
          href="/material-ui/getting-started/installation/"
        >
          Core
        </Link>
      </Typography>
    </AppBar>
  );
}
