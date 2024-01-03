import { useState, ChangeEvent, FormEvent, useContext, useEffect } from "react";
import * as Components from './Components';
import TopContainer from "../../component/TopContainer/TopContainer";
import ItemTab from "../../component/ItemTab/ItemTab";
import NavBar from "../../component/NavBar/NavBar";
import Footer from "../../component/Footer/Footer";
import LogoImage from "../../component/LogoImage/LogoImage";
import * as FirebaseAuthService from "../../../authService/FirebaseAuthService"
import { useNavigate } from "react-router-dom";
import { LoginUserContext } from "../../../App";

export default function LoginPage() {
  const [signIn, toggle] = useState(true);
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const loginUser = useContext(LoginUserContext);
  const navigate = useNavigate();

  //點估event type ？用IDE估，onChange={(e)} ：React.ChangeEvent<HTMLInputElement>
  const handleEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value)
  }
  const handlePasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value)
  }

  const handleSumit = async (event: FormEvent<HTMLFormElement>) => {
    //避免跳版
    event.preventDefault();
    console.log("email : ", email);
    console.log("password :  ", password);
    const loginResult = await FirebaseAuthService.handleSignInWithEmailAndPassword(email, password);
    if (loginResult) {
      navigate("/success");
    } else {
      alert("Login failed");
    }
  }

  useEffect(() => {
    if (loginUser) {
      navigate("/");
    }
  }, [loginUser, navigate]);
  //state variable 有任何改變，都行useEffect一次=> checking loging status 

  return (
    <>
      <title>Login Page</title>
      <TopContainer />
      <LogoImage />

      <ItemTab />

      <NavBar />

      <Components.Container>
        <Components.SignUpContainer signinIn={signIn}>
          <Components.Form>
            <Components.Title>Create Account</Components.Title>
            <Components.Input type='text' placeholder='Name' />
            <Components.Input
              type='email'
              placeholder='Email'
              onChange={handleEmailChange}
              value={email}
            />
            <Components.Input
              type='password'
              placeholder='Password'
              onChange={handlePasswordChange}
              value={password} // come from State
            />
            <Components.Button>Sign Up</Components.Button>
          </Components.Form>
        </Components.SignUpContainer>

        <Components.SignInContainer signinIn={signIn}>
          <Components.Form
            onSubmit={handleSumit} >
            <Components.Title>Sign in</Components.Title>
            <Components.Input
              type='email'
              placeholder='Email'
              onChange={handleEmailChange}
              value={email} // come from State
            />
            <Components.Input
              type='password'
              placeholder='Password'
              onChange={handlePasswordChange}
              value={password} // come from State
            />
            <Components.Anchor href='#'>Forgot your password?</Components.Anchor>
            <Components.Button>Sigin In</Components.Button>
          </Components.Form>
        </Components.SignInContainer>

        <Components.OverlayContainer signinIn={signIn}>
          <Components.Overlay signinIn={signIn}>
            <Components.LeftOverlayPanel signinIn={signIn}>
              <Components.Title>Welcome Back!</Components.Title>
              <Components.Paragraph>
                To keep connected with us please login with your personal info
              </Components.Paragraph>
              <Components.GhostButton onClick={() => toggle(true)}>
                Sign In
              </Components.GhostButton>
            </Components.LeftOverlayPanel>

            <Components.RightOverlayPanel signinIn={signIn}>
              <Components.Title>Hello, Friend!</Components.Title>
              <Components.Paragraph>
                Enter Your personal details and start the journey with us
              </Components.Paragraph>
              <Components.GhostButton onClick={() => toggle(false)}>
                Sign Up
              </Components.GhostButton>
            </Components.RightOverlayPanel>
          </Components.Overlay>
        </Components.OverlayContainer>
      </Components.Container>
      <Footer />

    </>
  );
}