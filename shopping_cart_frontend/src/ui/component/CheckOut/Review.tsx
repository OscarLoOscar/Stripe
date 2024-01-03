import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Grid from '@mui/material/Grid';
import { GetTransDto } from '../../../data/Trans/GetTransDto';
import Loading from '../Utility/Loading';
import { ImageListItem } from '@mui/material';

// const products = [
//   { name: 'Shipping', desc: '', price: 'Free' },
// ];

const addresses = ['1203', 'The Burrow', '212 Choi Hung Road', 'Diamond Hill', 'HK'];
const payments = [
  { name: 'Card type', detail: 'Visa' },
  { name: 'Card holder', detail: 'Mr John Smith' },
  { name: 'Card number', detail: 'xxxx-xxxx-xxxx-1234' },
  { name: 'Expiry date', detail: '04/2024' },
];
type ReviewProps = {
  transProductData: GetTransDto | undefined;
};
export default function Review({ transProductData }: ReviewProps) {
  console.log('transProductData:', transProductData);
  const renderTransProductList = () => {
    if (!transProductData) {
      return <Loading />; // You can replace this with a loading component
    }

    return (
      <React.Fragment>
        <Typography variant="h6" gutterBottom>
          Order summary
        </Typography>
        <List disablePadding>
          {transProductData?.items?.map((item) => (
            <React.Fragment key={item.tpid}>
              {/* Image */}
              <ImageListItem sx={{ width: '30%', marginRight: '10px' }}>
                <img
                  srcSet={`${item.product.image_url}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
                  src={`${item.product.image_url}?w=164&h=164&fit=crop&auto=format`}
                  alt={item.product.name}
                  loading="lazy"
                />
              </ImageListItem>

              {/* Other Details */}
              <ListItem sx={{ py: 1, px: 0, display: 'flex', alignItems: 'center' }}>
                <ListItemText primary={item.product.name} secondary={item.product.description} />
                <Typography variant="body2">{item.subtotal}</Typography>
              </ListItem>
            </React.Fragment>
          ))}
          <ListItem sx={{ py: 1, px: 0 }}>
            <ListItemText primary="Total" />
            <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
              {transProductData.total}
            </Typography>
          </ListItem>
        </List>
        <Grid container spacing={2}>
          <Grid item xs={12} sm={6}>
            <Typography variant="h6" gutterBottom sx={{ mt: 2 }}>
              Shipping
            </Typography>
            <Typography gutterBottom>{transProductData.buyer_uid}</Typography>
            <Typography gutterBottom>{addresses.join(', ')}</Typography>
          </Grid>
          <Grid item container direction="column" xs={12} sm={6}>
            <Typography variant="h6" gutterBottom sx={{ mt: 2 }}>
              Payment details
            </Typography>
            <Grid container>
              {payments.map((payment) => (
                <React.Fragment key={payment.name}>
                  <Grid item xs={6}>
                    <Typography gutterBottom>{payment.name}</Typography>
                  </Grid>
                  <Grid item xs={6}>
                    <Typography gutterBottom>{payment.detail}</Typography>
                  </Grid>
                </React.Fragment>
              ))}
            </Grid>
          </Grid>
        </Grid>
      </React.Fragment>
    );
  };
  return (
    <>
      {renderTransProductList()}
    </>
  )
}