import Box from '@mui/material/Box';
import { Container, List, ListItem } from '@mui/material';
import { useCallback, useEffect, useState } from 'react';
import * as TransApi from '../../../api/TransactionApi';
import { useNavigate } from 'react-router-dom';
import OrderListReview from './OrderListReview.js';
import { StatusTransDto } from '../../../data/Trans/StatusTransDto.js';


export default function OrderList() {
  const navigate = useNavigate();
  const [transProductData, setTransData] = useState<StatusTransDto[] | undefined>(undefined);

  const fetchTransData = useCallback(
    async () => {
      try {
        const data = await TransApi.getAllTransaction();
        console.log(data)
        setTransData(data);
      } catch (e) {
        navigate('/error');
      }
    },
    [navigate]
  );

  useEffect(() => {
    fetchTransData();
  }, [fetchTransData]);

  return (
    <Container>
      <List disablePadding>
        {/* Other Details */}
        <ListItem sx={{ py: 1, px: 0, display: 'flex', alignItems: 'center' }}>
        </ListItem>
      </List>
      <Box sx={{ width: '100%' }}>
        <OrderListReview transProductData={transProductData} />
      </Box>
    </Container>
  );
}