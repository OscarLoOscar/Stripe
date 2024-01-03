import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Loading from '../Utility/Loading';
import { StatusTransDto } from '../../../data/Trans/StatusTransDto';
import { Box, Step, StepLabel, Stepper } from '@mui/material';
import ClearIcon from '@mui/icons-material/Clear';

type ReviewProps = {
  transProductData: StatusTransDto[] | undefined;
};

const steps = ['ORDERED', 'PREPARE', 'PROCESSING', 'SUCCESS', 'FINISH'];

export default function OrderListReview({ transProductData }: ReviewProps) {
  console.log('transProductData:', transProductData);

  const renderTransProductList = () => {
    if (!transProductData) {
      return <Loading />;
    }

    return (
      <React.Fragment>
        <List disablePadding>
          {transProductData.map((transaction) => (
            <ListItem key={transaction.tid} sx={{ py: 1, px: 0, display: 'flex', alignItems: 'baseline' }}>
              <Box>
                <ListItemText primary={`Order ID: ${transaction.tid}`} secondary={transaction.datetime.toString()} />
                <Typography variant="body2">Total: ${transaction.total}</Typography>
              </Box>
              {transaction.status === 'NOT_SUCCESS' ? (
                <Box
                  sx={{
                    display: 'flex',
                    alignItems: 'baseline',
                    justifyContent: 'center',
                    flexGrow: 1,
                  }}
                >
                  <Typography variant="body2" color="red">
                    <ClearIcon />
                    {transaction.status}
                  </Typography>
                </Box>
              ) : (
                <Box
                  sx={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    flexGrow: 1,
                  }}
                >
                  <Stepper alternativeLabel>
                    {steps.map((label, index) => (
                      <Step key={label} completed={index < steps.indexOf(transaction.status) + 1}>
                        <StepLabel>{label}</StepLabel>
                      </Step>
                    ))}
                  </Stepper>
                </Box>
              )}
            </ListItem>
          ))}
        </List>
      </React.Fragment>
    );
  };

  return <>{renderTransProductList()}</>;
}
