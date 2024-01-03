import HomeIcon from '@mui/icons-material/Home';
import {  Button, Grid } from '@mui/material';
import { FC } from 'react';

interface NotFoundProps {
  title: string;
  subtitle: string;
}

const NotFound: FC<NotFoundProps> = ({ title, subtitle }) => {
  return (
    <>
      <Grid
        container
        justifyContent="center"
        display={"grid"}
        sx={{
          position: 'absolute',
          left: '50%',
          top: '50%',
          transform: 'translate(-50%, -50%)'
        }}>
        <h1>{title}</h1>
        <p></p>

        <h2>{subtitle}</h2>
        <Button variant="contained" size="large" endIcon={<HomeIcon />}
          onClick={() => window.location.href = '/'}
        >
          GOTO HOMEPAGE
        </Button>
      </Grid>
    </>
  );
};

export default NotFound;