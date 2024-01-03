import { useEffect, useState } from "react";
import ArrowForwardIosSharpIcon from '@mui/icons-material/ArrowForwardIosSharp';
import ArrowBackIosSharpIcon from '@mui/icons-material/ArrowBackIosSharp';
import "./MainWrapper.css";
import { Grid } from "@mui/material";


interface Props {
  imgs: string[];
}
function MainWrapper({ imgs }: Props) {
  const [currentIndex, setCurrentIndex] = useState<number>(0);

  const handlePrevious = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex - 1 < 0 ? imgs.length - 1 : prevIndex - 1
    );
  };

  const handleNext = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex + 1 === imgs.length ? 0 : prevIndex + 1
    );
  };
  //setTime , auto change photo
  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((prevPhoto) => (prevPhoto + 1) % imgs.length);
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  return (
    <Grid container justifyContent="center">
      <div className="carousel">
        <div className="carousel-images">
          {imgs.map((img, index) => (
            <img
              key={index}
              src={img}
              className={currentIndex === index ? "active" : "inactive"}
            />
          ))}
        </div>
        <div className="carousel-controls">
          <button className="left-btn" onClick={handlePrevious}>
            <ArrowBackIosSharpIcon />
          </button>
          <button className="right-btn" onClick={handleNext}>
            <ArrowForwardIosSharpIcon />
          </button>
        </div>
        <div className="carousel-indicator">
          {imgs.map((_, index) => (
            <div
              key={index}
              className={`dot ${currentIndex === index ? "active" : ""}`}
            ></div>
          ))}
        </div>
      </div>
    </Grid>
  );
}
export default MainWrapper;