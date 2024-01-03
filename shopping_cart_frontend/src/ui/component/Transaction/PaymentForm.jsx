// import React, { useState } from "react";
// import TextField from "@mui/material/TextField";
// import Button from "@mui/material/Button";
// import Cards, { Focused } from "react-credit-cards-2";
// import { FormControl } from "@mui/material";


// type CardInfo = {
//   number: string,
//   name: string,
//   expiry: string,
//   cvc: string,
//   focused: string,
//   issuer: string
// }
// // export default function PaymentForm() {
// export default function PaymentForm(info: CardInfo) {
//   const [cardInfo, setCardInfo] = useState<CardInfo>({
//     number: "",
//     name: "",
//     expiry: "",
//     cvc: "",
//     focused: "",
//     issuer: ""
//   });

//   const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//     const { name, value } = e.target;
//     setCardInfo((prevCardInfo) => ({
//       ...prevCardInfo,
//       [name]: value,
//     }));
//   };

//   const handleInputFocus = (e: React.FocusEvent<HTMLInputElement>) => {
//     setCardInfo((prevCardInfo) => ({
//       ...prevCardInfo,
//       focused: e.target.name,
//     }));
//   };

//   const handleSubmit = (e: React.FocusEvent<HTMLInputElement>) => {
//     e.preventDefault();
//   //  const { issuer } = info.issuer;
//     const formData = [...e.target]
//       .filter(d => d.name)
//       .reduce((acc, d) => {
//         acc[d.name] = d.value;
//         return acc;
//       }, {});

//     this.setState({ formData });
//     this.form.reset();
//   };


//   return (
//     <>
//       <div key="Payment">
//         <div className="App-payment">
//           <Cards
//             number={cardInfo.number}
//             name={cardInfo.name}
//             expiry={cardInfo.expiry}
//             cvc={cardInfo.cvc}
//             focused={cardInfo.focused as Focused}
//           // callback={this.handleCallback}
//           />
//           <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined" >
//             <form onSubmit={() => { }}>
//               <div className="form-group">
//                 <TextField
//                   type="tel"
//                   name="number"
//                   label="Card Number"
//                   variant="outlined"
//                   placeholder="Card Number"
//                   inputProps={{ pattern: "[0-9]{16,22}" }}
//                   required
//                   onChange={handleInputChange}
//                   onFocus={handleInputFocus}
//                   fullWidth
//                   margin="normal"
//                 />
//               </div>
//               <div className="form-group">
//                 <TextField
//                   type="text"
//                   name="name"
//                   label="Name"
//                   variant="outlined"
//                   placeholder="Name"
//                   required
//                   onChange={handleInputChange}
//                   onFocus={handleInputFocus}
//                   fullWidth
//                   margin="normal"
//                 />
//               </div>
//               <div style={{ display: "flex" }}>

//                 <div className="form-group">
//                   <TextField
//                     type="tel"
//                     name="expiry"
//                     label="Valid Date"
//                     variant="outlined"
//                     placeholder="MM/YY"
//                     inputProps={{ pattern: "\\d\\d/\\d\\d" }}
//                     required
//                     onChange={handleInputChange}
//                     onFocus={handleInputFocus}
//                     fullWidth
//                     margin="normal"
//                   />
//                 </div>
//                 <div className="form-group">
//                   <TextField
//                     type="tel"
//                     name="cvc"
//                     label="CVC"
//                     variant="outlined"
//                     placeholder="CVC"
//                     inputProps={{ pattern: "\\d{3,4}}" }}
//                     required
//                     onChange={handleInputChange}
//                     onFocus={handleInputFocus}
//                     fullWidth
//                     margin="normal"
//                   />
//                 </div>
//               </div>
//               <input type="hidden" name="issuer" />
//               <div style={{ marginTop: "20px" }}>
//                 <Button
//                   type="submit"
//                   variant="contained"
//                   color="primary"
//                   fullWidth
//                 >
//                   PAY
//                 </Button>
//               </div>
//             </form>
//           </FormControl>
//         </div>
//       </div>
//     </>
//   );
// }

import React from "react";
import Card from "react-credit-cards-2";
import SendIcon from '@mui/icons-material/Send';

import {
  formatCreditCardNumber,
  formatCVC,
  formatExpirationDate,
  formatFormData
} from "./utils";

import 'react-credit-cards-2/dist/es/styles-compiled.css';
import { Box, Button, TextField } from "@mui/material";
export default class PaymentForm extends React.Component {
  state = {
    number: "",
    name: "",
    expiry: "",
    cvc: "",
    issuer: "",
    focused: "",
    formData: null
  };

  handleCallback = ({ issuer }, isValid) => {
    if (isValid) {
      this.setState({ issuer });
    }
  };

  handleInputFocus = ({ target }) => {
    this.setState({
      focused: target.name
    });
  };

  handleInputChange = ({ target }) => {
    if (target.name === "number") {
      target.value = formatCreditCardNumber(target.value);
    } else if (target.name === "expiry") {
      target.value = formatExpirationDate(target.value);
    } else if (target.name === "cvc") {
      target.value = formatCVC(target.value);
    }

    this.setState({ [target.name]: target.value });
  };

  handleSubmit = e => {
    e.preventDefault();
    const { issuer } = this.state;
    const formData = [...e.target.elements]
      .filter(d => d.name)
      .reduce((acc, d) => {
        acc[d.name] = d.value;
        return acc;
      }, {});

    this.setState({ formData });
    this.form.reset();
  };

  render() {
    const { name, number, expiry, cvc, focused, issuer, formData } = this.state;

    return (
      <div key="Payment">
        <div className="App-payment">
          <Card
            number={number}
            name={name}
            expiry={expiry}
            cvc={cvc}
            focused={focused}
            callback={this.handleCallback}
          />
          <Box
            component="form"
            sx={{
              '& .MuiTextField-root': { m: 1, width: '25ch' },
            }}
            noValidate
            autoComplete="off"
          >
            <form ref={c => (this.form = c)} onSubmit={this.handleSubmit}>
              <div className="form-group">
                <TextField
                  type="tel"
                  name="number"
                  label="Card Number"
                  variant="outlined"
                  placeholder="Card Number"
                  inputProps={{ pattern: "[0-9]{16,22}" }}
                  required
                  onChange={this.handleInputChange}
                  onFocus={this.handleInputFocus}
                  fullWidth
                  margin="normal"
                />
                {/* </div>
              <div className="form-group"> */}
                <TextField
                  type="text"
                  name="name"
                  label="Name"
                  variant="outlined"
                  placeholder="Name"
                  required
                  onChange={this.handleInputChange}
                  onFocus={this.handleInputFocus}
                  fullWidth
                  margin="normal"
                />
              </div>
              <div className="row">
                <TextField
                  type="tel"
                  name="expiry"
                  label="Valid Date"
                  variant="outlined"
                  placeholder="MM/YY"
                  inputProps={{ pattern: "\\d\\d/\\d\\d" }}
                  required
                  onChange={this.handleInputChange}
                  onFocus={this.handleInputFocus}
                  fullWidth
                  margin="normal"
                />
                {/* </div>
              <div className="col-6"> */}
                <TextField
                  type="tel"
                  name="cvc"
                  label="CVC"
                  variant="outlined"
                  placeholder="CVC"
                  inputProps={{ pattern: "\\d{3,4}}" }}
                  required
                  onChange={this.handleInputChange}
                  onFocus={this.handleInputFocus}
                  fullWidth
                  margin="normal"
                />
              </div>
              <input type="hidden" name="issuer" value={issuer} />
              <div className="form-actions">
                {/* <button className="btn btn-primary btn-block">PAY</button> */}
                <Button
                  variant="contained"
                  endIcon={<SendIcon />}>
                  PAY
                </Button>
              </div>
            </form>
          </Box>
          {
            formData && (
              <div className="App-highlight">
                {formatFormData(formData).map((d, i) => (
                  <div key={i}>{d}</div>
                ))}
              </div>
            )
          }

        </div >
      </div >
    );
  }
}
