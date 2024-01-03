import { Typography, Link } from '@mui/material';
import FacebookIcon from '@mui/icons-material/Facebook';
import TwitterIcon from '@mui/icons-material/Twitter';
import InstagramIcon from '@mui/icons-material/Instagram';
import LinkedInIcon from '@mui/icons-material/LinkedIn';

function Footer() {
  return (
    <footer
      style={{
        position: "fixed",
        bottom: 0,
        width: "100%",
        backgroundColor: "lightgreen",
        paddingBottom: "0.3rem", // Adjust as needed
        zIndex: 1000, // Adjust the z-index as needed
      }}
    >
      <Typography variant="body2"
        color="text.secondary"
        align="center">
        <Link color="inherit"
          href="https://venturenixlab.co/"
          sx={{ fontSize: 15 }}
        >
          Venturenixlab
        </Link>
        {' | '}
        <Link color="inherit" href="https://www.facebook.com/VenturenixLAB">
          <FacebookIcon />
        </Link>
        {' | '}
        <Link color="inherit" href="https://twitter.com/">
          <TwitterIcon />
        </Link>
        {' | '}
        <Link color="inherit" href="https://www.instagram.com/">
          <InstagramIcon />
        </Link>
        {' | '}
        <Link color="inherit" href="https://www.linkedin.com/">
          <LinkedInIcon />
        </Link>
      </Typography>
      <Typography variant="body2"
        color="text.secondary"
        align="center">
        {'Copyright © '}
        <Link color="inherit"
          href="#">
          Oscar@VenturenixLab
        </Link>{' '}
        {new Date().getFullYear()}
        {'. All rights reserved.'}
      </Typography>
    </footer >
  );
}

export default Footer;