import { useState, MouseEvent, useContext, useEffect } from 'react';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import NotificationsIcon from '@mui/icons-material/Notifications';
import PersonIcon from '@mui/icons-material/Person';
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom';
import { handleSignOut } from '../../../../authService/FirebaseAuthService';
import { CartItemListDto } from '../../../../data/CartItem/CartItemListDto';
import * as CartApi from '../../../../api/CartItemApi';
import { Badge, BadgeProps, Box, Button, Divider, Popover, Skeleton, Typography, styled } from '@mui/material';
import DeleteOutlineOutlinedIcon from '@mui/icons-material/DeleteOutlineOutlined';
import { LoginUserContext } from '../../../../App';
import * as TransApi from "../../../../api/TransactionApi";

const StyledBadge = styled(Badge)<BadgeProps>(({ theme }) => ({
  '& .MuiBadge-badge': {
    right: -3,
    top: 13,
    border: `2px solid ${theme.palette.background.paper}`,
    padding: '0 4px',
  },
}));

export default function UserStatus() {
  // const [value, setValue] = useState('Login');
  // const handleChange = (_event: SyntheticEvent, newValue: string) => {
  //   setValue(newValue);
  // };

  //user login
  const loginUser = useContext(LoginUserContext);
  const renderLoginContainer = () => {
    if (loginUser) {
      return (
        <>
          <div style={{ color: "white" }}>
            Logout {loginUser.email.substring(0, 7)}
          </div>
        </>
      )
    } else if (loginUser === null) {
      return (
        <>
          <Skeleton variant="circular" width={40} height={40} >
            Login
          </Skeleton>
        </>
      )
    } else {
      return (
        <>
          <Skeleton variant="circular" width={40} height={40} />
        </>
      )
    }
  }
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

  const navigate = useNavigate();

  const navigateLoginPage = () => {
    navigate('/login');
  };

  const navigateThankyouPage = () => {
    navigate('/thankyoupage');
  };

  const navigateErrorPage = () => {
    navigate('/error');
  };

  // const navigateShoppingCartPage = () => {
  //   navigate('/shoppingcart');
  // };

  const handleUserLogout = async () => {
    await handleSignOut();
    navigate('/logout');
  }
  const [transId, setTransId] = useState<string | undefined>(undefined);
  const [loadingBackdrop, setLoadingBackdrop] = useState<boolean>(false);
  const handleCheckout = async () => {
    setLoadingBackdrop(true)
    const result = await TransApi.createTransaction()
    setTransId(result.tid.toString())
    setLoadingBackdrop(false)
    navigate("/checkout/" + transId)
  }

  const [cartItems, setCartItems] = useState<CartItemListDto[]>([]);
  const [cartItemLength, setCartItemLength] = useState<number>(0);

  const getCartItemListLength = async () => {
    const result = await CartApi.getCartItemListApi();
    setCartItems(result);
    setCartItemLength(result.length);
  }

  // const handleUpdateCartItem = async (pid: string) => {
  //   const result = await CartApi.updateCartItemApi(pid.toString(), "1")
  //   console.log(result);
  // }

  const handleDeleteCartItem = async (cid: string) => {
    const result = await CartApi.deleteCartItemApi(cid.toString())
    console.log(result);
  }

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
            <Box
              key={item.pid}
              sx={{
                display: 'flex',
                flexDirection: 'row',
                alignItems: 'center',
                width: '100%',
              }}
            >
              <Box
                sx={{
                  display: 'flex',
                  flexDirection: 'column',
                }}
              >
                <Typography sx={{ py: 0.5, px: 1, color: '#009688', fontSize: '13px' }}>
                  {item.name}
                </Typography>
                <Box
                  sx={{
                    display: 'flex',
                    flexDirection: 'row',
                    alignItems: 'center',
                  }}
                >
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
        <Box width="25%" sx={{
          display: "flex",
          alignItems: "center"
        }}>
          <Button variant="contained" fullWidth
            onClick={handleCheckout}
          >Checkout</Button>
        </Box>
      </Box>
    </Box>
  );
  useEffect(() => {
    // Update cartItems and cartItemLength
    if (loginUser) {
      getCartItemListLength();
    }
  }, [loginUser]);

  // useEffect(() => {
  //   setCartItemLength(cartItems.length);
  // }, [cartItems])
  return (
    <>
      {/** handleChange之後keep白色 */}
      <BottomNavigation
        sx={{
          width: "auto",
          display: 'block',
          margin: "auto",
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
      //  onChange={handleChange}
      >
        <BottomNavigationAction
          label={renderLoginContainer()}
          value={loginUser ? 'Welcome' : 'Login'}
          icon={loginUser ? <LogoutIcon sx={{ color: 'white' }} /> : <PersonIcon sx={{ color: 'white' }} />}
          onClick={loginUser ? handleUserLogout : navigateLoginPage}
          sx={{
            width: 100,
            '&:hover': {
              backgroundColor: '#90caf9',
            },
          }}
        />
        {/**handleChange 之前icon白色 */}
        <BottomNavigationAction
          label="Notifications"
          icon={<NotificationsIcon sx={{ color: 'white' }} />}
          onClick={navigateThankyouPage}
          sx={{
            width: 100,
            '&:hover': {
              backgroundColor: '#90caf9',
            },
          }}
        />
        <BottomNavigationAction
          label="Favorites"
          icon={<FavoriteIcon sx={{ color: 'white' }} />}
          onClick={navigateErrorPage}
          sx={{
            width: 100,
            '&:hover': {
              backgroundColor: '#90caf9',
            },
          }}
        />
        {/* Drawer -> 黑屏選項 效果 */}
        {/* Popover -> 彈出shopping cart 效果 */}
        <BottomNavigationAction
          aria-describedby={id}
          label="Shopping Cart"
          icon={
            <StyledBadge badgeContent={cartItemLength}
              color="secondary">
              <ShoppingCartIcon sx={{ color: 'white' }} />
            </StyledBadge>}
          onClick={(event) => {
            handleOpenPopoverClick(event);
          }}
          sx={{
            width: 100,
            '&:hover': {
              backgroundColor: '#90caf9',
            },
          }}
        />
        {/* </IconButton> */}
      </BottomNavigation >


      {/* CART */}
      {/* Popover */}
      <Popover
        id={id}
        open={open}
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'left',
        }}
        transformOrigin={{
          vertical: 'top',
          horizontal: 'center',
        }}
        anchorEl={anchorEl}
        onClose={handleClose}
      >
        {popoverContent}
      </Popover>
    </>
  );
}

