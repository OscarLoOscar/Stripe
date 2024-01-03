import { useNavigate } from "react-router-dom";

export default function LogoImage() {

  const navigate = useNavigate();
  const navigateMainPage = () => {
    navigate('/');
  }

  return (
    <>
      <img
        alt="Logo"
        src="https://venturenixlab.co/wp-content/uploads/2022/05/cropped-cropped-Vlab-horizontal-logo.png"
        title="company_logo"
        //置中3寶
        style={{
          width: "30%",
          display: 'block',
          margin: "auto",
          cursor: 'pointer',
        }}
        onClick={navigateMainPage}
      />
    </>
  );
}