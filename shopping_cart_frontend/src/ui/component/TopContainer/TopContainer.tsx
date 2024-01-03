import Grid from '@mui/material/Grid';
import Tooltip from '@mui/material/Tooltip';
import { useState, Fragment } from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import { useNavigate } from 'react-router-dom';
type Anchor = 'right';
export default function TopContainer() {
  const [state, setState] = useState({
    right: false,
  });
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/orderList");
  };

  const toggleDrawer =
    (anchor: Anchor, open: boolean) =>
      (event: React.KeyboardEvent | React.MouseEvent) => {
        if (
          event.type === 'keydown' &&
          ((event as React.KeyboardEvent).key === 'Tab' ||
            (event as React.KeyboardEvent).key === 'Shift')
        ) {
          return;
        }

        setState({ ...state, [anchor]: open });
      };

  const list = (anchor: Anchor) => (
    <Box
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
        {['用戶資料', '訂單查詢', '通知', '訊息'].map((text, index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton>
              <ListItemIcon onClick={index === 1 ? handleClick : undefined}>
                {index % 3 === 0 ? <AccountBoxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
      <Divider />
      <List>
        {['All mail', 'Trash', 'Spam'].map((text, index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton>
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </Box>
  );

  return (
    <>
      <Grid container spacing={2} columns={10}>
        <Grid item xs={5} sx={{ textAlign: { xs: 'left', md: 'left' } }}>
          <Tooltip title="廣告位置1" placement="top-start" >
            <Button color="success">廣告位置1</Button>
          </Tooltip>
          <Tooltip title="廣告位置2" placement="top">
            <Button color="success">廣告位置2</Button>
          </Tooltip>
          <Tooltip title="廣告位置3" placement="top">
            <Button color="success">廣告位置3</Button>
          </Tooltip>
          <Tooltip title="廣告位置4" placement="top">
            <Button color="success">廣告位置4</Button>
          </Tooltip>
          <Tooltip title="廣告位置5" placement="top-end">
            <Button color="success">廣告位置5</Button>
          </Tooltip>
        </Grid>
        <Grid item xs={5} sx={{
          textAlign: { xs: 'right', md: 'right' },//
          display: 'fiex',
          justifyContent: 'flex-end',//
          flexWrap: 'nowrap'
        }}>
          <Tooltip title="用戶查詢" placement="right-start">
            <div>
              {(['right'] as const).map((anchor) => (
                <Fragment key={anchor}>
                  <Button onClick={toggleDrawer(anchor, true)}>用戶查詢</Button>
                  <Drawer
                    anchor={anchor}
                    open={state[anchor]}
                    onClose={toggleDrawer(anchor, false)}
                  >
                    {list(anchor)}
                  </Drawer>
                </Fragment>
              ))}
            </div>
          </Tooltip>
          <Tooltip title="常見問題2" placement="right">
            <Button>常見問題2</Button>
          </Tooltip>
          <Tooltip title="常見問題3" placement="right">
            <Button>常見問題3</Button>
          </Tooltip>
          <Tooltip title="常見問題4" placement="right-end">
            <Button>常見問題4</Button>
          </Tooltip>
        </Grid>

      </Grid>
    </>
  );
}