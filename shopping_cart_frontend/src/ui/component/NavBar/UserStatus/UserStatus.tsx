import React, { useState, MouseEvent, useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Badge, Box, Button, Divider, Popover, Skeleton, Typography, styled } from '@mui/material';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import NotificationsIcon from '@mui/icons-material/Notifications';
import PersonIcon from '@mui/icons-material/Person';
import LogoutIcon from '@mui/icons-material/Logout';
import DeleteOutlineOutlinedIcon from '@mui/icons-material/DeleteOutlineOutlined';
import { LoginUserContext } from '../../../../App';
import { handleSignOut } from '../../../../authService/FirebaseAuthService';
import * as CartApi from '../../../../api/CartItemApi';
import * as TransApi from '../../../../api/TransactionApi';
import { CartItemListDto } from '../../../../data/CartItem/CartItemListDto';

const StyledBadge = styled(Badge)(({ theme }) => ({
  '& .MuiBadge-badge': {
    right: -3,
    top: 13,
    border: `2px solid ${theme.palette.background.paper}`,
    padding: '0 4px',
  },
}));

const UserStatus: React.FC = () => {
  const loginUser = useContext(LoginUserContext);
  const navigate = useNavigate();

  // For Popover
  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null);
  const handleOpenPopoverClick = (event: MouseEvent<HTMLButtonElement> | null = null) => {
    setAnchorEl(event ? event.currentTarget : null);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const open = Boolean(anchorEl);
  const id = open ? 'simple-popover' : undefined;

  const handleUserLogout = async () => {
    await handleSignOut();
    navigate('/logout');
  };
  const navigateLoginPage = () => {
    navigate('/login');
  };

  const navigateThankyouPage = () => {
    navigate('/thankyoupage');
  };

  const navigateErrorPage = () => {
    navigate('/error');
  };
  const [transId, setTransId] = useState<string | undefined>(undefined);
  const [loadingBackdrop, setLoadingBackdrop] = useState<boolean>(false);

  const handleCheckout = async () => {
    try {
      setLoadingBackdrop(true);
      const result = await TransApi.createTransaction();
      setTransId(result.tid.toString());
      navigate(`/checkout/${transId}`);
    } catch (error) {
      console.error('Error during checkout:', error);
    } finally {
      setLoadingBackdrop(false);
    }
  };

  const [cartItems, setCartItems] = useState<CartItemListDto[]>([]);
  const [cartItemLength, setCartItemLength] = useState<number>(0);

  const getCartItemListLength = async () => {
    const result = await CartApi.getCartItemListApi();
    setCartItems(result);
    setCartItemLength(result.length);
  };

  useEffect(() => {
    // Update cartItems and cartItemLength
    if (loginUser) {
      getCartItemListLength();
    }
  }, [loginUser]);

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const result = await CartApi.getCartItemListApi();
        setCartItems(result);
        setCartItemLength(result.length);
      } catch (error) {
        console.error(error);
      }
    };

    fetchCartItems(); // Initial fetch

    // Subsequent fetches whenever the length of cart items changes
    const intervalId = setInterval(fetchCartItems, 5000);

    // Cleanup the interval on component unmount
    return () => clearInterval(intervalId);
  }, [] ); // Empty dependency array, runs only once on component mount


  const handleDeleteCartItem = async (cid: string) => {
    const result = await CartApi.deleteCartItemApi(cid.toString());
    console.log(result);
  };

  const popoverContent = (
    <Box sx={{ width: 'auto', height: 'auto' }}>
      <Typography sx={{ p: 2, fontWeight: '700' }}>Cart</Typography>
      <Divider />
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          height: '70%',
          width: '100%',
          justifyContent: 'center',
          alignItems: 'center',
        }}
      >
        {cartItems.length > 0 ? (
          cartItems.map((item) => (
            <Box key={item.pid} sx={{ display: 'flex', flexDirection: 'row', alignItems: 'center', width: '100%' }}>
              <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                <Typography sx={{ py: 0.5, px: 1, color: '#009688', fontSize: '13px' }}>{item.name}</Typography>
                <Box sx={{ display: 'flex', flexDirection: 'row', alignItems: 'center' }}>
                  <Typography sx={{ py: 0.5, px: 1, color: '#1a237e', fontSize: '13px' }}>
                    ${item.price}.00 x {item.cart_quantity}
                  </Typography>
                  <Typography sx={{ color: '#3d5afe', fontSize: '13px', fontWeight: '700' }}>
                    ${item.price * item.cart_quantity}.00
                  </Typography>
                </Box>
              </Box>

              {/* DELETE ICON */}
              <Box
                sx={{
                  color: '#1de9b6',
                  cursor: 'pointer',
                  opacity: '0.7',
                }}
                onClick={() => handleDeleteCartItem(item.pid.toString())}
              >
                <DeleteOutlineOutlinedIcon />
              </Box>
            </Box>
          ))
        ) : (
          <Typography sx={{ p: 2, fontWeight: '700', color: '#4caf50' }}>Your cart is empty.</Typography>
        )}
        <Box width="45%" sx={{ display: 'flex', alignItems: 'center' }}>
          <Button variant="contained" fullWidth onClick={handleCheckout}>
            Checkout
          </Button>
        </Box>
      </Box>
    </Box>
  );

  return (
    <>
      <BottomNavigation
        sx={{
          width: 'auto',
          display: 'block',
          margin: 'auto',
          backgroundColor: 'transparent',
          cursor: 'pointer',
          '& .Mui-selected': {
            '& .MuiBottomNavigationAction-label': {
              fontSize: (theme) => theme.typography.caption,
              fontWeight: 'bold',
              color: 'white',
            },
          },
        }}
        showLabels
      >
        <BottomNavigationAction
          label={loginUser ? `Logout ${loginUser.email.substring(0, 7)}` : <Skeleton variant="circular" width={40} height={40} />}
          value={loginUser ? 'Welcome' : 'Login'}
          icon={loginUser ? <LogoutIcon sx={{ color: 'white' }} /> : <PersonIcon sx={{ color: 'white' }} />}
          onClick={loginUser ? handleUserLogout : navigateLoginPage}
          sx={{ width: 100, '&:hover': { backgroundColor: '#90caf9' } }}
        />
        <BottomNavigationAction
          label="Notifications"
          icon={<NotificationsIcon sx={{ color: 'white' }} />}
          onClick={navigateThankyouPage}
          sx={{ width: 100, '&:hover': { backgroundColor: '#90caf9' } }}
        />
        <BottomNavigationAction
          label="Favorites"
          icon={<FavoriteIcon sx={{ color: 'white' }} />}
          onClick={navigateErrorPage}
          sx={{ width: 100, '&:hover': { backgroundColor: '#90caf9' } }}
        />
        <BottomNavigationAction
          aria-describedby={id}
          label="Shopping Cart"
          icon={
            <StyledBadge badgeContent={cartItemLength} color="secondary">
              <ShoppingCartIcon sx={{ color: 'white' }} />
            </StyledBadge>
          }
          onClick={(event) => {
            handleOpenPopoverClick(event);
          }}
          sx={{ width: 100, '&:hover': { backgroundColor: '#90caf9' } }}
        />
      </BottomNavigation>

      {/* CART */}
      {/* Popover */}
      <Popover
        id={id}
        open={open}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
        transformOrigin={{ vertical: 'top', horizontal: 'center' }}
        anchorEl={anchorEl}
        onClose={handleClose}
      >
        {popoverContent}
      </Popover>
    </>
  );
};

export default UserStatus;
